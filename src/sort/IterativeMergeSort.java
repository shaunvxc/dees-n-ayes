package sort;

public class IterativeMergeSort {
	
	
	
	public static void mergeSort(int[] nums) {
		
		int currSize = 1;
		int leftStart;
		int mid;
		for(currSize = 1; currSize <= nums.length -1; currSize = 2 * currSize) {
			for(leftStart = 0; leftStart < nums.length -1; leftStart += (currSize * 2)) {
				mid = leftStart + currSize -1;
				int rightEnd = Math.min(leftStart + (currSize * 2) -1, nums.length -1);
				merge(nums, leftStart,  mid, rightEnd);
			}
		}
		
	}

	private static void merge(int[] nums,int left,  int mid, int right) {

			int i, j, k;
			int n1 = mid - left + 1 ;
			int n2 = right - mid;
			if(mid >= nums.length) { return; }
			if(right > nums.length) { right  = nums.length -1;}
			int[] temp1 = new int[n1];
			int[] temp2 = new int[n2];
			
			for( i=0 ; i < n1	 ; i++	) {
				temp1[i ] = nums[left + i];
			}
			
			for( j=0 ; j < n2	 ; j++	) {
				temp2[j] = nums[ mid + 1 + j];
			}
			
			i = 0;
			j =0;
			
			k = left;
			while(i < n1 && j < n2	) {
				if(temp1[i] <= temp2[j]) {
					nums[k ] = temp1[i];
					i++;
				} else {
					nums[k] = temp2[j];
					j++;
				}
				k++;
			}
			
			  /* Copy the remaining elements of L[], if there are any */
		    while (i < n1)
		    {
		        nums[k] = temp1[i];
		        i++;
		        k++;
		    }
		 
		    /* Copy the remaining elements of R[], if there are any */
		    while (j < n2)
		    {
		        nums[k] = temp2[j];
		        j++;
		        k++;
		    }
	}
	
	private static void printValues(int[] arr, int low, int high) {

		for (int i = low; i <= high; i++) {
			System.out.print(arr[i] + " ");
		}

		System.out.println();
	}
	
	public static void main(String[] args) {
		int[] nums = { 24, 2, 45, 20, 56, 75, 2, 56, 99, 53, 12 };

		mergeSort(nums);
		printValues(nums, 0, nums.length -1 );
	}
	
}
