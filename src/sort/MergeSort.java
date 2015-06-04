package sort;

/**
 * 
 * Merge sort implementation
 * 
 * @author shaun.viguerie
 *
 */
public class MergeSort implements ISorter {

	/* the initial array */
	private int[] numbers;
	/* the copied collection */
	private int[] helper;
	/* number of elements to sort! */
	private int number;
	/* for detailed console printing */
	private boolean verbose;

	@Override
	public void sort(int[] inputArr) {
		this.numbers = inputArr;
		this.number = inputArr.length;
		this.helper = new int[number];
		verbose = false;
		mergeSort(0, number - 1);
	}

	@Override
	public void sortAndPrint(int[] inputArr) {
		this.numbers = inputArr;
		this.number = inputArr.length;
		this.helper = new int[number];
		printValues(numbers, 0, number - 1);
		verbose = true;
		mergeSort(0, number - 1);
	}

	private void mergeSort(int low, int high) {
		// check if low is smaller then high, if not then the array is sorted
		if (low < high) {

			// get the middle of the current scope
			int middle = low +  (high - low) / 2;

			// sort left side of the array
			mergeSort(low, middle);

			// sort the right side of the array
			mergeSort(middle + 1, high);

			// merge the results together!
			merge(low, middle, high);

		}
	}

	private void merge(int low, int middle, int high) {
		for (int i = low; i <= high; i++) {
			helper[i] = numbers[i];
		}

		int i = low;
		int j = middle + 1;
		int k = low;

		while (i <= middle && j <= high) {

			if (helper[i] <= helper[j]) {
				numbers[k] = helper[i];
				i++;
			} else {
				numbers[k] = helper[j];
				j++;
			}

			k++;
		}

		while (i <= middle) {
			numbers[k] = helper[i];
			k++;
			i++;
		} 

		if (verbose) {
			printValues(numbers, low, high);
		}
	}

	private static void printValues(int[] arr, int low, int high) {

		for (int i = low; i <= high; i++) {
			System.out.print(arr[i] + " ");
		}

		System.out.println();
	}

	public static void main(String a[]) {

		ISorter sorter = new MergeSort();
		int[] input = { 24, 2, 45, 20, 56, 75, 2, 56, 99, 53, 12 };
	//	int[] input = { 7, 11, 2, 6, 5, 1, 14, 8, 10, 24, 2, 45, 20, 56, 75, 2, 56, 99, 53, 12 };

		sorter.sortAndPrint(input);
	}

}
