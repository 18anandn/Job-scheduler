package ProjectManagement;

public class Project {
	String name;
	int Budget;
	int Priority;
	Project(String n, int p, int b) {
		name = n;
		Budget = b;
		Priority = p;
	}
	public void add(int x) {
		this.Budget = this.Budget + x;
	}

	public void reduce(int x) {
		this.Budget = this.Budget - x;
	}
}