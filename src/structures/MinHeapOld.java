package structures;

import java.util.ArrayList;

/**
 * MinHeap.java defines objects of type min-heap. Class is defined generically
 * so that MinHeap objects can work with any Comparable object.
 * 
 * @author Shaun Viguerie
 * @date April 4, 2012
 */
public class MinHeapOld<T extends Comparable<T>> {

	private ArrayList<T> heap;

	/**
	 * Two-parameter constructor for creating MinHeap objects.
	 * 
	 * @param ArrayList
	 *            <T> list list of comparable objects that will be in the heap
	 * @param boolean bottomUp constructs MinHeap with bottom-up approach when
	 *        true, and top-down approach when false
	 */
	public MinHeapOld(ArrayList<T> list, boolean bottomUp) {
		heap = new ArrayList<T>();
		heap.add(null); // so that heap is constructed starting at 1
		if (bottomUp) {
			for (T element : list) {
				heap.add(element);
			}
			/* heapify starting from the last internal node */
			for (int i = heap.size() / 2; i >= 1; i--) {
				heapify(i);
			}
		} else { // top down construction of the heap
			for (T element : list) {
				this.insert(element);
			}
		}
	}

	/**
	 * Standard isEmpty method
	 * 
	 * @return boolean true if empty, false otherwise
	 */
	public boolean isEmpty() {
		if (this.size() <= 1) {
			return true;
		}
		return false;
	}

	/**
	 * Instance method for getting the size of the heap
	 * 
	 * @return int the size of the heap
	 */
	public int size() {
		return heap.size() - 1;
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

	/**
	 * Instance method for deleting the minimum value in the heap. The method
	 * obtains the element from the heap, and then removes the last leaf on the
	 * heap. It places the last leaf at the root of the heap, and then
	 * "percolates down" with a call to heapify().
	 * 
	 * @return T the minimum value on the heap.
	 */
	public T deleteMin() {
		T lastLeaf = heap.remove(heap.size() - 1);
		T min = heap.get(1);
		heap.set(1, lastLeaf);
		heapify(1);
		return min;
	}

	/**
	 * Instane method for inserting an element into the heap. The method inserts
	 * the element at its proper position in the heap.
	 * 
	 * @param T
	 *            element the element to be inserted into the heap
	 */
	public void insert(T element) {
		heap.add(element);
		int index = heap.size() - 1;
		while (index > 1 && element.compareTo(heap.get(index / 2)) < 0) {
			heap.set(index, heap.get(index / 2));
			heap.set(index / 2, element);
			index = index / 2;
		}
	}

	/**
	 * Instance method for "percolating down" the heap to ensure that an element
	 * ends up at its proper position on the heap.
	 * 
	 * @param int i index of the element to begin heapifying at
	 */
	public void heapify(int i) {
		T element = heap.get(i); // store original value;
		int size = heap.size();
		boolean valid = false;
		while (!valid && (2 * i) <= size - 1) {
			int childIdx = 2 * i; // index of the node's child
			// if the index is less than size-1, then the node has 2 children.
			if (childIdx < size - 1) {
				// if the element at childIdx is less than the element at
				// childIdx+1, then the element needs to be compared to the
				// other child, so increment childIdx
				if (heap.get(childIdx).compareTo(heap.get(childIdx + 1)) > 0) {
					childIdx++;
				}
			}
			// if the element is less than the larger of its two children,
			// [or less than its only child], then it is in its correct
			// position.
			if (element.compareTo(heap.get(childIdx)) <= 0) {
				valid = true;
			} else {
				heap.set(i, heap.get(childIdx));
				i = childIdx;
			}

		}
		// place the element into its correct spot on the heap.
		heap.set(i, element);
	}
}
