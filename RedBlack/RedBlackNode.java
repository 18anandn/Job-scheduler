package RedBlack;

import Util.RBNodeInterface;

import java.util.LinkedList;
import java.util.List;

public class RedBlackNode<T extends Comparable, E> implements RBNodeInterface<E> {
	T key;
	public List<E> values;
	boolean color;
	RedBlackNode<T, E> parent;
	public RedBlackNode<T, E> left;
	public RedBlackNode<T, E> right;
	RedBlackNode(T key, E value) {
		this.key = key;
		values = new LinkedList<E>();
		values.add(value);
		color = false;
		left = null;
		right = null;
	}

    @Override
    public E getValue() {
        return values.get(values.size() - 1);
    }

    @Override
    public List<E> getValues() {
        return values;
    }

    public void put(E value) {
    	values.add(value);
    }
}