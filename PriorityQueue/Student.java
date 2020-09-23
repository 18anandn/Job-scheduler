package PriorityQueue;

public class Student implements Comparable<Student> {
    private String name;
    private Integer marks;
    int index = 0;

    public Student(String trim, int parseInt) {
    	name = trim;
    	marks = parseInt;
    }

    @Override
    public int compareTo(Student student) {
        if(marks > student.marks) {
        	return 1;
        }
        if(marks < student.marks) {
        	return - 1;
        }
        else {
        	return 0;
        }
    }

    public String getName() {
        return name;
    }
    
    public String toString() {
    	return "Student{name='" + name + "', marks=" + String.valueOf(marks) + "}";
    }
}