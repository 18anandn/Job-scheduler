package ProjectManagement;

public class User implements Comparable<User> {
	String name;
	User(String n) {
		name = n;
	}

    @Override
    public int compareTo(User user) {
        return 0;
    }
}