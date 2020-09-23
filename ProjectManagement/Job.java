package ProjectManagement;

public class Job implements Comparable<Job> {
	String name;
	Project project;
	User user;
	int run_time;
	int end_time;
	String status;
	Job(String n, Project p, User u, int r) {
		name = n;
		project = p;
		user = u;
		run_time = r;
		status = "REQUESTED";
	}

    @Override
    public int compareTo(Job job) {
        if(this.project.Priority > job.project.Priority) {
        	return 1;
        }
        else if(this.project.Priority < job.project.Priority) {
        	return -1;
        }
        else {
        	return 0;
        }
    }

    public String toString() {
    	if(this.end_time != 0) {
    		return "Job{user='" + user.name + "', project='" + project.name + "', jobstatus=" + this.status + ", execution_time=" + this.run_time + ", end_time=" + this.end_time + ", name='" + this.name + "'}";
    	}
    	else {
    		return "Job{user='" + user.name + "', project='" + project.name + "', jobstatus=" + this.status + ", execution_time=" + this.run_time + ", end_time=null" + ", name='" + this.name + "'}";
    	}
    }
}