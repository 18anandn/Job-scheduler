package Trie;

import Util.NodeInterface;
import java.util.HashMap;

public class TrieNode<T> implements NodeInterface<T> {
	HashMap<Characters, TrieNode<T>> map = new HashMap<Characters, TrieNode<T>>(95);
	public T value;

    @Override
    public T getValue() {
        return value;
    }
    public TrieNode<T> get(Characters c) {
    	return map.get(c);
    }

    public boolean contains(Characters c) {
    	if(map.containsKey(c)) {
    		return true;
    	}
    	return false;
    }

	public int size() {
		return map.size();
	}

    public void put(Characters c) {
    	TrieNode<T> n = new TrieNode<T>();
    	map.put(c, n);
    }

    public void remove(Characters c) {
    	map.remove(c);
    }

    public boolean isEmpty() {
		if(map.isEmpty()) {
			return true;
		}
	return false;
	}
}