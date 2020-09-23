package ProjectManagement;

import PriorityQueue.PriorityQueueDriverCode;
import RedBlack.RBTree;
import RedBlack.RedBlackNode;
import Trie.Trie;
import PriorityQueue.MaxHeap;
import PriorityQueue.Pair;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class Scheduler_Driver extends Thread implements SchedulerInterface {
	int global_time = 0;
	MaxHeap<Job> jobs = new MaxHeap<Job>();
	HashMap<String, Job> all_jobs = new HashMap<String, Job>();
	HashMap<String, User> users = new HashMap<String, User>();
	Trie<Project> projects = new Trie<Project>();
	RBTree<String, Pair<Integer, Job>> not_executed_jobs = new RBTree<String, Pair<Integer, Job>>();
	ArrayList<Job> completed_jobs = new ArrayList<Job>();
	ArrayList<Job> not_completed_jobs = new ArrayList<Job>();

    public static void main(String[] args) throws IOException {
        Scheduler_Driver scheduler_driver = new Scheduler_Driver();

        File file;
        if (args.length == 0) {
            URL url = PriorityQueueDriverCode.class.getResource("INP");
            file = new File(url.getPath());
        } else {
            file = new File(args[0]);
        }
        scheduler_driver.execute(file);
    }

    public void execute(File file) throws IOException {

        URL url = Scheduler_Driver.class.getResource("INP");
        file = new File(url.getPath());

        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            System.err.println("Input file Not found. "+file.getAbsolutePath());
        }
        String st;
        while ((st = br.readLine()) != null) {
            String[] cmd = st.split(" ");
            if (cmd.length == 0) {
                System.err.println("Error parsing: " + st);
                return;
            }

            switch (cmd[0]) {
                case "PROJECT":
                    handle_project(cmd);
                    break;
                case "JOB":
                    handle_job(cmd);
                    break;
                case "USER":
                    handle_user(cmd[1]);
                    break;
                case "QUERY":
                    handle_query(cmd[1]);
                    break;
                case "":
                    handle_empty_line();
                    break;
                case "ADD":
                    handle_add(cmd);
                    break;
                default:
                    System.err.println("Unknown command: " + cmd[0]);
            }
        }

        run_to_completion();

        print_stats();
    }

    @Override
    public void run() {
        schedule();
    }

    @Override
    public void run_to_completion() {
    	Pair<Integer, Job> j = jobs.max();
    	int i = 0;
    	while(j != null) {
    		Job job = j.obj;
    		if(job.project.Budget >= job.run_time) {
    			if(i == 0) {
    				System.out.println("Running code");
    				System.out.println("Remaining jobs: " + jobs.size());
    			}
    			global_time += job.run_time;
        		job.project.reduce(job.run_time);
        		job.end_time = global_time;
        		job.status = "COMPLETED";
        		completed_jobs.add(job);
        		all_jobs.replace(job.name, job);
        		System.out.println("Executing: " + job.name +" from: " + job.project.name);
        		System.out.println("Project: " + job.project.name + " budget remaining: " + job.project.Budget);
       			System.out.println("System execution completed");
       			i = 0;
    		}
    		else {
    			if(i == 0) {
    				System.out.println("Running code");
    				System.out.println("Remaining jobs: " + jobs.size());
    			}
    			System.out.println("Executing: " + job.name +" from: " + job.project.name);
        		System.out.println("Un-sufficient budget.");
        		not_executed_jobs.insert(j.obj.project.name, j);
    			not_completed_jobs.add(job);
    			all_jobs.replace(job.name, job);
    			i++;
    		}
    		jobs.remove();
    		j = jobs.max();
    	}
    }

    @Override
    public void handle_project(String[] cmd) {
    	System.out.println("Creating project");
    	Project p = new Project(cmd[1], Integer.valueOf(cmd[2]), Integer.valueOf(cmd[3]));
    	projects.insert(p.name, p);
    }

    @Override
    public void handle_job(String[] cmd) {
    	System.out.println("Creating job");
    	if(projects.search(cmd[2]) == null) {
    		System.out.println("No such project exists. " + cmd[2]);
    		return;
    	}
    	Project p = projects.search(cmd[2]).value;
    	User u = users.get(cmd[3]);
    	if(u == null) {
    		System.out.println("No such user exists: " + cmd[3]);
    		return;
    	}
    	Job j = new Job(cmd[1], p, u, Integer.valueOf(cmd[4]));
    	jobs.insert(j);
    	all_jobs.put(j.name, j);
    }

    @Override
    public void handle_user(String name) {
    	System.out.println("Creating user");
    	User u = new User(name);
    	users.put(name, u);
    }

    @Override
    public void handle_query(String key) {
    	System.out.println("Querying");
    	Job j = all_jobs.get(key);
    	if(j == null) {
    		System.out.println(key + ": NO SUCH JOB");
    	}
    	else {
    		if(j.status.equals("COMPLETED")) {
    			System.out.println(key + ": COMPLETED");
    		}
    		else {
    			System.out.println(key + ": NOT FINISHED");
    		}
    	}
    }

    @Override
    public void handle_empty_line() {
    	schedule();
    }

    @Override
    public void handle_add(String[] cmd) {
    	System.out.println("ADDING Budget");
    	Project p = projects.search(cmd[1]).value;
    	if(p == null) {
    		return;
    	}
    	p.add(Integer.valueOf(cmd[2]));
    	RedBlackNode<String, Pair<Integer, Job>> node = not_executed_jobs.search(cmd[1]);
    	if(node.values != null) {
    		int i;
    		for(i = 0; i < node.values.size(); ++i) {
    			jobs.insert(node.values.get(i));
    		}
    		node.values.clear();
    	}
    }

    @Override
    public void print_stats() {
    	System.out.println("--------------STATS---------------");
    	int i;
    	System.out.println("Total jobs done: " + completed_jobs.size());
    	for(i = 0; i < completed_jobs.size(); ++i) {
    		System.out.println(completed_jobs.get(i).toString());
    	}
    	System.out.println("------------------------");
    	System.out.println("Unfinished jobs: ");
    	for(i = 0; i < not_completed_jobs.size(); ++i) {
    		System.out.println(not_completed_jobs.get(i));
    	}
    	System.out.println("Total unfinished jobs: " + not_completed_jobs.size());
    	System.out.println("--------------STATS DONE---------------");
    }

    @Override
    public void schedule() {
    	System.out.println("Running code");
    	System.out.println("Remaining jobs: " + jobs.size());
    	Pair<Integer, Job> j = jobs.max();
    	while(j != null && j.obj.project.Budget < j.obj.run_time) {
    		System.out.println("Executing: " + j.obj.name +" from: " + j.obj.project.name);
    		System.out.println("Un-sufficient budget.");
    		not_executed_jobs.insert(j.obj.project.name, j);
    		jobs.remove();
    		j = jobs.max();
    	}
    	if(j != null) {
    		Job job = j.obj;
    		jobs.remove();
    		System.out.println("Executing: " + job.name +" from: " + job.project.name);
    		global_time += job.run_time;
    		job.project.reduce(job.run_time);
    		job.end_time = global_time;
   			job.status = "COMPLETED";
    		completed_jobs.add(job);
   			all_jobs.replace(job.name, job);
   			System.out.println("Project: " + job.project.name + " budget remaining: " + job.project.Budget);
   			System.out.println("Execution cycle completed");
    	}
    }
}