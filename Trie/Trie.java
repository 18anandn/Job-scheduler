package Trie;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Trie<T> implements TrieInterface<T> {
	TrieNode<T> root;

	@Override
	public boolean insert(String word, T value) {
		char[] chars = word.toCharArray();
		if(root == null) {
			root = new TrieNode<T>();
		}
		TrieNode<T> curr = root;
		int i;
		for(i = 0; i < chars.length; ++i) {
			Characters c = new Characters(chars[i]);
			if(!curr.contains(c)) {
				curr.put(c);
			}
			curr = curr.get(c);
		}
		if(curr.getValue() == null) {
			curr.value = value;
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public TrieNode<T> search(String word) {
		if(root == null) {
			return null;
		}
		char[] chars = word.toCharArray();
		TrieNode<T> curr = root;
		int i;
		for(i = 0; i < chars.length; ++i) {
			Characters c = new Characters(chars[i]);
			if(curr.contains(c)) {
				curr = curr.get(c);
			}
			else {
				return null;
			}
		}
		if(curr.getValue() != null) {
			return curr;
		}
		else {
			return null;
		}
	}

	@Override
	public TrieNode<T> startsWith(String prefix) {
		if(root == null) {
			return null;
		}
		char[] chars = prefix.toCharArray();
		TrieNode<T> curr = root;
		int i;
		for(i = 0; i < chars.length; ++i) {
			Characters c = new Characters(chars[i]);
			if(curr.contains(c)) {
				curr = curr.get(c);
			}
			else {
				return null;
			}
		}
		return curr;
	}

	@Override
	public void printTrie(TrieNode trieNode) {
		if(trieNode == null) {
			return;
		}
		if(trieNode.getValue() != null) {
			System.out.println(trieNode.getValue().toString());
		}
		if(trieNode.isEmpty()) {
			return;
		}
		TrieNode<T> t = (TrieNode<T>) trieNode;
		for(Characters c : t.map.keySet()) {
			printTrie(trieNode.get(c));
		}
	}

	@Override
	public boolean delete(String word) {
		if(root == null) {
			return false;
		}
		char[] chars = word.toCharArray();
		Characters ch = new Characters(chars[0]);
		TrieNode<T> parent = root;
		TrieNode<T> curr = root;
		int i;
		for(i = 0; i < chars.length; ++i) {
			Characters c = new Characters(chars[i]);
			if(curr.contains(c)) {
				if(curr.size() > 1 || curr.getValue() != null) {
					ch = new Characters(chars[i]);
					parent = curr;
				}
				curr = curr.get(c);
			}
			else {
				return false;
			}
		}
		if(curr.getValue() == null) {
			return false;
		}
		if(curr.size() > 0) {
			curr.value = null;
			return true;
		}
		parent.remove(ch);
		if(root.size() == 0) {
			root = null;
		}
		return true;
	}

	@Override
	public void print() {
		if(root.isEmpty()) {
			return;
		}
		System.out.println("Printing Trie");
		Queue<TrieNode<T>> q = new LinkedList<TrieNode<T>>();
		int level = 1;
		q.add(root);
		int nodes = 1;
		while(nodes != 0){
			ArrayList<Characters> a = new ArrayList<Characters>();
			while(nodes > 0){
				TrieNode<T> node = q.poll();
				for(Characters c : node.map.keySet()) {
					insert(a, c);
				}
				if(nodes == 1) {
					System.out.print("Level " + level + ": ");
					int i;
					for(i = 0; i < a.size(); ++i) {
						if(i < a.size() - 1) {
							System.out.print(a.get(i).character() + ",");
						}
						else {
							System.out.print(a.get(i).character());
						}
					}
					System.out.println();
					level++;
				}
				for(Characters c : node.map.keySet()) {
					q.add(node.get(c));
				}
				nodes--;
			}
			nodes = q.size();
		}
		System.out.println("-------------");
	}

	@Override
	public void printLevel(int level) {
		if(root.isEmpty()){
			return;
		}
		Queue<TrieNode<T>> q = new LinkedList<TrieNode<T>>();
		int x = 1;
		q.add(root);
		int nodes = 1;
		while(nodes != 0){
			ArrayList<Characters> a = new ArrayList<Characters>();
			while(nodes > 0) {
				TrieNode<T> node = q.poll();
				if(x == level) {
					for(Characters c : node.map.keySet()) {
						insert(a, c);
					}
					if(nodes == 1) {
						System.out.print("Level " + level + ": ");
						int i;
						for(i = 0; i < a.size(); ++i) {
							if(i < a.size() - 1) {
								System.out.print(a.get(i).character() + ",");
							}
							else {
								System.out.print(a.get(i).character());
							}
						}
						System.out.println();
						System.out.println("-------------");
						return;
					}
				}
				if(nodes == 1) {
					x++;
				}
				for(Characters c : node.map.keySet()) {
					q.add(node.get(c));
				}
				nodes--;
			}
			nodes = q.size();
		}
	}

    private void insert(ArrayList<Characters> a, Characters c) {
    	if(c.character() == ' ') {
    		return;
    	}
    	a.add(c);
    	int i = a.size() - 1;
    	while(i > 0 && a.get(i).compareTo(a.get(i - 1)) < 0) {
    		Characters temp = a.get(i);
    		a.set(i, a.get(i - 1));
    		a.set(i - 1, temp);
    		i--;
    	}
    }
}