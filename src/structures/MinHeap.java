package structures;

import java.util.ArrayList;
import java.util.List;

/* 
 * Alternatively, if the tree root is at index 1, with valid indices 1 through n, then each element a at index i has

 children at indices 2i and 2i +1
 its parent at index floor(i ∕ 2).
 * 
 */
public class MinHeap<T extends Comparable<T>> {

	private static final int FRONT = 1;
	private List<T> heap;

	public MinHeap() {
		heap = new ArrayList<T>();
		heap.add(null); // first index should be empty
	}

	public MinHeap(T elem) {
		this();
		heap.add(elem);
	}

	/**
	 * Instance method for getting the size of the heap
	 * 
	 * @return int the size of the heap
	 */
	public int size() {
		return heap.size() -1;
	}

	/**
	 * Instance method for finding the minimum value in the heap. In this
	 * implementation, it will always be the root node.
	 * 
	 * @return T the smallest element in the heap (the root)
	 */
	public T findMin() {
		return heap.get(1);
	}

	public void insert(T item) {
		heap.add(item);
		int current = size();
		
		while (current > 1 && heap.get(current).compareTo(heap.get(getParentIdx(current))) < 0) {
			swap(current, getParentIdx(current));
			current = getParentIdx(current);
		}
		
	}

	public T deleteMin() {
		T min = findMin();
		T lastLeaf = heap.remove(size());
		heap.set(FRONT, lastLeaf);
		minHeapify(FRONT);
		return min;
	}

	private int getParentIdx(int pos) {
		return pos / 2;
	}

	private int leftChild(int pos) {
		return (2 * pos);
	}

	private int rightChild(int pos) {
		return (2 * pos) + 1;
	}

	private void swap(int childIdx, int parentIdx) {
		T parent = heap.get(parentIdx);
		heap.set(parentIdx, heap.get(childIdx));
		heap.set(childIdx, parent);
	}

	private boolean isLeaf(int pos) {
		if (pos >= (size() / 2) && pos <= size()) {
			return true;
		}
		return false;
	}

	private void minHeapify(int startPos) {

		T start = heap.get(startPos);
		if (!isLeaf(startPos)) {
			if (start.compareTo(heap.get(leftChild(startPos))) > 0 || start.compareTo(heap.get(rightChild(startPos))) > 0) {

				if (heap.get(leftChild(startPos)).compareTo(heap.get(rightChild(startPos))) < 0) {
					swap(leftChild(startPos), startPos);
					minHeapify(leftChild(startPos));
				} else {
					swap(rightChild(startPos), startPos);
					minHeapify(rightChild(startPos));
				}
			}
		}
	}

	public void print() {
		for (int i = 1; i <= size() / 2; i++) {
			System.out.print(" PARENT : " + heap.get(i) + " LEFT CHILD : " + heap.get(2 * i) + " RIGHT CHILD :" + heap.get(2 * i + 1));
			System.out.println();
		}
	}
	
    public static void main(String...arg)
    {
        System.out.println("The Min Heap is ");
        MinHeap<Integer> minHeap = new MinHeap<Integer>();
        minHeap.insert(5);
        minHeap.insert(3);
        minHeap.insert(17);
        minHeap.insert(10);
        minHeap.insert(84);
        minHeap.insert(19);
        minHeap.insert(6);
        minHeap.insert(22);
        minHeap.insert(9);
      //  minHeap.minHeap();
 
        minHeap.print();
        System.out.println("The Min val is " + minHeap.deleteMin());
    }
	
}
