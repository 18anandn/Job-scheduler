package PriorityQueue;

public class Pair<A, B> {
	public A key;
	public B obj;
	public Pair (A a, B b){
		key = a;
		obj = b;
	}
	public A key() {
		return key;
	}
	public B obj() {
		return obj;
	}
}