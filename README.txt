COL106:
Assignment 4:

1. Trie:
1.A) Characters:
	This class stores a character and plays a special role in assigning indexes in the hashmap use in TrieNode class. The hashcode is overriden so
that the particular characters are stored at positions in hashmap such that they are sorted by the character stored in it. The default hashcode was
generated by right clicking on character argument and clicking on 'Source' and then clicking on 'Generate hashcode() and equals' and the hashcode was
then modified.
1.B) TrieNode:
	This class has hashmap and value as variables. The hashmap takes 'Characters' as key which maps to TrieNode. The size of hashmap is set to 95
as maximum possible Characters are 95. It implements the NodeInterface. The methods have been created as per the requirements.
1.C) Trie:
	It takes the root as variable which is initially null. The insert, search and delete operations are O(n) where n is the lenghth of the string to
be searched. printTrie method is recursive and prints the objects to string format in lexographical order. print and printLevel use breadth first
traversal using queue. To get the characters in lexographical order, a private method of insert is created so as to insert in an arraylist using bubble
sort method. As hashmap is used, there is constant time in returning a value of a particular key.

2. Red-Black tree:
2.A) RedBlackNode:
	It has value which is comparable and list as variables and stores references left, right and parent. It stores a boolean which can be considered as a color. The list contains all the values of the same key. It implements RBNodeInterface.
2.B) RBTree:
	The RBTree has root as variable and a fixed ref which is used in search. Three private methods are created. Two are used for rotations and one for swapping the colors of the nodes. The insert and search operations are worst case O(logn) time.

3. PriorityQueue:
3.A) Pair:
	Pair is used to put a key to the object. Here, key does not mean that a mapping is used.
3.B) MaxHeap:
	This class has a ArrayList of pair type and has an initial capacity of 10000. This ArrayList stores pairs. Each pair has a value and an integer associated with it. The integer comes from a private count and it helps in specifying which value was inserted in which relative order in the heap. Extra methods are created apart from the ones in the interface which are used in the Scheduler part.

4. ProjectManagement:
4.A) Job:
	Job has name, project, user, run_time, end_time and status as parameters. It has a compareTo method which compares two jobs on the basis of their individual priorities of project.
4.B) Scheduler_Driver:
	The Scheduler_Driver has all the the necessary data structures to execute jobs performing pojects. They are as follows:
	1. jobs: This is a MaxHeap of the jobs which are stored based on the priorities of their projects. The job with highest priority to be executed is 	         taken from here.
	2. all_jobs: This is a HashMap of all the jobs. Information about the required job can be retrieved within constant time. The key is the job name. 		 Jobs can be updated and stored here. Queries regarding a job can be answered from here.
	3. users: All the users are stored in a HashMap. This is just to see whether a user with a particular name exists in performing the jobs.
	4. projects: All the projects are stored in a Trie with its name as key. If a particular budget is added to a project then all the particular 		  		 projects in the jobs get updated as the jobs contain the address of their own project.
	5. not_executed_jobs: All the jobs extracted(returned and removed) from the jobs MaxHeap which are unable to be executed due unsufficient budget 			  are inserted in this RBTree. The key is the project name and value stored is Pair. Pair is stored because it contains the count which 
		  tells when, relatively,  it was inserted in the jobs MaxHeap. The jobs having same project name are stored in the same RedBlackNode. These 		  jobs will be 
	6. completed_jobs: All the jobs which were extracted from jobs MaxHeap and executed successfully are stored in this ArrayList.
	7. not_completed_jobs: All the jobs which were unexecuted in run_to_completion() due to unsufficient budget of their respective projects are stored
		in this ArrayList.

	Methods:
	1. handle_project: Creates the projects with the given information and inserts it in a projects Trie.
	2. handle_job: Creates a job with given information. Inserts it in all_jobs HashMap and jobs MaxHeap.
	3. handle_user: Creates a user with given information and stores it in users HashMap.
	4. handle_query: Searches in the all_jobs HashMap and returns its status.
	5. handle_empty_line: Extracts a single job of highest priority from jobs MaxHeap and executes it if its project's budget is greater than or equal
		to the runtime of the job. It keeps on extracting till an executable job is found. All the extracted, non-executable jobs are stored in
		not_executed_jobs RBTree which has project's name as key.
	6. schedule: (Same as handle_empty_line)
	7. handle_add: Adds budget to the project in projects Trie. Respective changes occur in all jobs. Now all the jobs in not_executed_jobs RBTree with
		this project's name as key is inserted back in the MaxHeap.
	8. run_to_completion: All the unexecuted jobs in the jobs MaxHeap are executed till it is empty. Remaining data structures are updated.
	9. print_stats: Prints all the executed jobs in the order they were executed and the non-executed jobs in run_to_completion in the order they were
		encountered in the MaxHeap. These non-executed are always in project priority order and in FIFO if their priorities are same.