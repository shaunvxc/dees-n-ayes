package sort;

/**
 * 
 * Quick Sort implementation
 * 
 * @author shaun.viguerie
 *
 */
public class QuickSort implements ISorter {

	private int[] array;
	private int length;

	private boolean verbose;

	@Override
	public void sort(int[] inputArr) {
		this.array = inputArr;
		this.length = inputArr.length;
		verbose = false;
		quickSort(0, length - 1);
	}

	@Override
	public void sortAndPrint(int[] inputArr) {

		this.array = inputArr;
		this.length = inputArr.length;
		printValues(array);
		verbose = true;
		quickSort(0, length - 1);
		printValues(array);
	}

	private void quickSort(int lowerIndex, int higherIndex) {
		int i = lowerIndex;
		int j = higherIndex;

		// step 1. Calculate pivot
		int pivot = array[lowerIndex + (higherIndex - lowerIndex) / 2];

		while (i <= j) {

			// 2. find value on the left size which is GREATER THAN the pivot
			while (array[i] < pivot) {
				i++;
			} 

			// 3. find a value on the right side which is LESS THAN the pivot
			while (array[j] > pivot) {
				j--;
			}

			if (i <= j) {
				swapNumbers(i, j);
				// move indices to the next position on both sides
				i++;
				j--;
			}
		}

		if (lowerIndex < j) {
			quickSort(lowerIndex, j);
		}

		if (i < higherIndex) {
			quickSort(i, higherIndex);
		}
	}

	/**
	 * swaps the values at the given indices of the array
	 */
	private void swapNumbers(int i, int j) {
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;

		if (verbose) {
			printNumbersWithIndices(i, j);
		}
	}

	private void printNumbersWithIndices(int i, int j) {

		for (int x = 0; x < array.length; x++) {
			if (x == i || x == j) {
				System.out.print(array[x] + "* ");
			} else {
				System.out.print(array[x] + "  ");
			}
		}

		System.out.println();
	}

	private static void printValues(int[] arr) {
		for (int i : arr) {
			System.out.print(i + "  ");
		}

		System.out.println();
	}

	public static void main(String a[]) {
		QuickSort sorter = new QuickSort();
		//int[] input = { 24, 2, 45, 20, 56, 75, 2, 56, 99, 53, 12 };
		int[] input = { 7, 11, 2, 6, 5, 1, 14, 8, 10 };
		sorter.sortAndPrint(input);
	}

}
