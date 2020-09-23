package PriorityQueue;

import java.util.ArrayList;

public class MaxHeap<T extends Comparable<T>> implements PriorityQueueInterface<T> {
	public ArrayList<Pair<Integer, T>> heap = new ArrayList<Pair<Integer, T>>(10000);
	
	private void swap(int i, int j) {
		Pair<Integer, T> temp = heap.get(j);
		heap.set(j, heap.get(i));
		heap.set(i, temp);
	}

	private T value(int i) {
		return heap.get(i).obj();
	}

	private int key(int i) {
		return heap.get(i).key();
 	}

	private int compare(int i, int j) {
		return value(i).compareTo(value(j));
	}

	private int count = 0;
	@Override
    public void insert(T element) {
		Pair<Integer, T> p = new Pair<Integer, T>(count, element);
		count++;
		heap.add(p);
		int i = heap.size() - 1;
        while (i > 0 && compare((i - 1) / 2, i) < 0) {
        	swap(i, (i - 1) / 2);
            i = (i - 1) / 2;
        }
	}

	public void insert(Pair<Integer, T> p) {
		heap.add(p);
		int i = heap.size() - 1;
		while (i > 0 && compare((i - 1) / 2, i) <= 0) {
			if(compare((i - 1) / 2, i) < 0) {
				swap(i, (i - 1) / 2);
				i = (i - 1) / 2;
			}
			else if(compare((i - 1) / 2, i) == 0) {
				if(key((i - 1) / 2) > key(i)) {
					swap(i, (i - 1) / 2);
					i = (i - 1) / 2;
				}
				else {
					break;
				}
			}
			else {
				break;
			}
			
		}
	}

    @Override
    public T extractMax() {
    	if(heap.isEmpty()) {
    		return null;
    	}
    	int x = heap.size() - 1;
    	T max = value(0);
    	swap(0, x);
    	heap.remove(x);
    	int i = 0;
    	while((2*i) + 1 < heap.size()) {
    		int l = (2*i) + 1;
    		int r = (2*i) + 2;
    		if(r < heap.size()) {
    			if(compare(i, l) <= 0 || compare(i, r) <= 0) {
    				if(compare(l, r) > 0) {
    					if(compare(i, l) < 0) {
    						swap(i, l);
    						i = l;
    					}
    					else if(compare(i, l) == 0){
    						if(key(i) > key(l)) {
    							swap(i, l);
    							i = l;
    						}
    						else {
    							break;
    						}
    					}
    					else {
    						break;
    					}
    				}
    				else if(compare(l, r) < 0) {
    					if(compare(i, r) < 0) {
    						swap(i, r);
    						i = r;
    					}
    					else if(compare(i, r) == 0){
    						if(key(i) > key(r)) {
    							swap(i, r);
    							i = r;
    						}
    						else {
    							break;
    						}
    					}
    					else {
    						break;
    					}
    				}
    				else {
    					if(key(l) < key(r)) {
    						if(compare(i, l) < 0) {
    							swap(i, l);
    							i = l;
    						}
    						else if(compare(i, l) == 0) {
    							if(key(i) > key(l)) {
    								swap(i, l);
    								i = l;
    							}
    							else {
    								break;
    							}
    						}
    						else {
    							break;
    						}
    					}
    					else {
    						if(compare(i, r) < 0) {
    							swap(i, r);
    							i = r;
    						}
    						else if(compare(i, r) == 0) {
    							if(key(i) > key(r)) {
    								swap(i, r);
    								i = r;
    							}
    							else {
    								break;
    							}
    						}
    						else {
    							break;
    						}
    					}
    				}
   				}
    			else {
    				break;
    			}
    		}
    		else if(compare(i, l) < 0) {
    			swap(i, l);
	    		i = l;
    		}
    		else if(compare(i, l) == 0) {
    			if(key(i) > key(l)) {
					swap(i, l);
					i = l;
				}
				else {
					break;
				}
    		}
    		else {
    			break;
    		}
    	}
		return max;
    }
    public void remove() {
    	if(heap.isEmpty()) {
    		return;
    	}
    	int x = heap.size() - 1;
    	swap(0, x);
    	heap.remove(x);
    	int i = 0;
    	while((2*i) + 1 < heap.size()) {
    		int l = (2*i) + 1;
    		int r = (2*i) + 2;
    		if(r < heap.size()) {
    			if(compare(i, l) <= 0 || compare(i, r) <= 0) {
    				if(compare(l, r) > 0) {
    					if(compare(i, l) < 0) {
    						swap(i, l);
    						i = l;
    					}
    					else if(compare(i, l) == 0){
    						if(key(i) > key(l)) {
    							swap(i, l);
    							i = l;
    						}
    						else {
    							break;
    						}
    					}
    					else {
    						break;
    					}
    				}
    				else if(compare(l, r) < 0) {
    					if(compare(i, r) < 0) {
    						swap(i, r);
    						i = r;
    					}
    					else if(compare(i, r) == 0){
    						if(key(i) > key(r)) {
    							swap(i, r);
    							i = r;
    						}
    						else {
    							break;
    						}
    					}
    					else {
    						break;
    					}
    				}
    				else {
    					if(key(l) < key(r)) {
    						if(compare(i, l) < 0) {
    							swap(i, l);
    							i = l;
    						}
    						else if(compare(i, l) == 0) {
    							if(key(i) > key(l)) {
    								swap(i, l);
    								i = l;
    							}
    							else {
    								break;
    							}
    						}
    						else {
    							break;
    						}
    					}
    					else {
    						if(compare(i, r) < 0) {
    							swap(i, r);
    							i = r;
    						}
    						else if(compare(i, r) == 0) {
    							if(key(i) > key(r)) {
    								swap(i, r);
    								i = r;
    							}
    							else {
    								break;
    							}
    						}
    						else {
    							break;
    						}
    					}
    				}
    			}
    			else {
    				break;
    			}
    		}
    		else if(compare(i, l) < 0) {
    			swap(i, l);
    			i = l;
    		}
    		else if(compare(i, l) == 0) {
    			if(key(i) > key(l)) {
    				swap(i, l);
    				i = l;
    			}
    			else {
    				break;
    			}
    		}
    		else {
    			break;
    		}
    	}
    }

    public int size() {
    	return heap.size();
    }

    public Pair<Integer, T> max() {
    	if(heap.isEmpty()) {
    		return null;
    	}
    	return heap.get(0);
    }
}