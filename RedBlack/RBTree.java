package RedBlack;

import RedBlack.RBTreeInterface;
import RedBlack.RedBlackNode;

public class RBTree<T extends Comparable<T>, E> implements RBTreeInterface<T, E>  {
	public RedBlackNode<T, E> root;
	public RedBlackNode<T, E> ref = new RedBlackNode<T,E>(null,null);

	private RedBlackNode<T, E> rLeft(RedBlackNode<T, E> node) {
		RedBlackNode<T, E> parent = node.parent;
		RedBlackNode<T, E> temp = node.right;
		node.right = temp.left;
		if(temp.left != null) {
			temp.left.parent = node;
		}
		temp.left = node;
		node.parent = temp;
		temp.parent = parent;
		if(parent != null) {
			if(parent.left == node) {
				parent.left = temp;
			}
			else {
				parent.right = temp;
			}
		}
		return temp;
	}

	private RedBlackNode<T, E> rRight(RedBlackNode<T, E> node) {
		RedBlackNode<T, E> parent = node.parent;
		RedBlackNode<T, E> temp = node.left;
		node.left = temp.right;
		if(temp.right != null) {
			temp.right.parent = node;
		}
		temp.right = node;
		node.parent = temp;
		temp.parent = parent;
		if(parent != null) {
			if(parent.left == node) {
				parent.left = temp;
			}
			else {
				parent.right = temp;
			}
		}
		return temp;
	}

	private void swapColor(RedBlackNode<T, E> m, RedBlackNode<T, E> n) {
		boolean temp = m.color;
		m.color = n.color;
		n.color = temp;
	}

    @Override
    public void insert(T key, E value) {
    	if(root == null) {
    		root = new RedBlackNode<T, E>(key, value);
    		root.parent = null;
    		root.color = true;
    		return;
    	}
    	RedBlackNode<T, E> curr = root;
    	while(true) {
    		if(key.compareTo(curr.key) < 0) {
    			if(curr.left == null) {
    				curr.left = new RedBlackNode<T, E>(key, value);
    				curr.left.parent = curr;
    				curr = curr.left;
    				break;
    			}
    			curr = curr.left;
    		}
    		else if(key.compareTo(curr.key) > 0) {
    			if(curr.right == null) {
    				curr.right = new RedBlackNode<T, E>(key, value);
    				curr.right.parent = curr;
    				curr = curr.right;
    				break;
    			}
    			curr = curr.right;
    		}
    		else {
    			curr.put(value);
    			return;
    		}
    	}
    	while(true) {
   			RedBlackNode<T, E> parent = curr.parent;
   			if(parent == null) {
   				curr.color = true;
   				root = curr;
   				break;
   			}
   			if(parent.color == true || curr.color == true) {
   				break;
   			}
   			RedBlackNode<T, E> gparent = parent.parent;
   			if(gparent == null) {
   				break;
   			}
   			RedBlackNode<T, E> uncle = null;
   			if(parent == gparent.left) {
   				uncle = gparent.right;
   			}
   			else {
   				uncle = gparent.left;
   			}
   			if(uncle != null && !uncle.color) {
   				parent.color = true;
   				gparent.color = false;
   				uncle.color = true;
   			}
   			else {
   				if(parent == gparent.left) {
   					if(curr == parent.left) {
   						gparent = rRight(gparent);
   						swapColor(gparent, gparent.right);
   					}
   					else {
   						gparent.left = rLeft(gparent.left);
   						gparent = rRight(gparent);
   						swapColor(gparent, gparent.right);
   					}
   				}
   				else {
   					if(curr == parent.left) {
   						gparent.right = rRight(gparent.right);
   						gparent = rLeft(gparent);
   						swapColor(gparent, gparent.left);
   						
   					}
   					else {
   						gparent = rLeft(gparent);
   						swapColor(gparent, gparent.left);
   					}
   				}
   			}
   			curr = gparent;
   		}
   	}

    @Override
    public RedBlackNode<T, E> search(T key) {
    	ref.values=null;
    	if(root == null) {
    		return ref;
    	}
    	RedBlackNode<T, E> curr = root;
    	while(true) {
    		if(key.compareTo(curr.key) < 0) {
    			if(curr.left == null) {
    				return ref;
    			}
    			curr = curr.left;
    		}
    		else if(key.compareTo(curr.key) > 0) {
    			if(curr.right == null) {
    				return ref;
    			}
    			curr = curr.right;
    		}
    		else {
    			break;
    		}
    	}
    	return curr;
    }
}