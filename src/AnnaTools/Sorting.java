package AnnaTools;

public class Sorting {

	//-----------------------------------------------------------------------

	/* BUBBLE SORT (the list) */
	public static void bubbleSort (int[] list){
		boolean sorted = false; //flag
		for (int top = list.length - 1; top > 0 && !sorted; top--){ //top starts at the end and -- with each pass
			sorted = true; //for now. Will stay true if list is not modified
			for (int i = 0; i < top; i++){ //less that (not equal to) top because we access i + 1
				if (list[i] > list[i+1]){ //COMPARE
					sorted = false;
					int  temp = list[i]; //swap the values
					list[i] = list[i+1];
					list[i+1] = temp;
				}
			}
		}
	}

	//-----------------------------------------------------------------------

	/* SELECTION SORT (the list)
	* Start at the end (top) and go to the front of the list to find the next largest
	* value (largeLoc). Then SWAP this largest value with the current top value.
	*/
	public static void selectSort (int[] list){
		for (int top = list.length - 1; top > 0; top--){
			int largeLoc = 0; // location of largest element
							  // assume list[0] is largest to start
			for (int i = 1; i <= top; i++) { // check list[i] to list[top]
				if (list[i] > list[largeLoc]) {
					largeLoc = i;
				}
			}
			int temp = list[top]; //temporary storage
			list[top] = list[largeLoc]; //swap these values
			list[largeLoc] = temp;
		}
	}

	//-----------------------------------------------------------------------

	/* INSERTION SORT (the list)
	* Start at the front (top) of the list until list.length - 1 inclusive.
	* For each of these values, check all the previous ones to determine its
	* correct position. Store this value, shift all the elements down, then INSERT
	* the value.
	*/
	public static void insertSort(int[] list){
		for (int top = 1; top < list.length; top++){
			int item = list[top]; // temporary storage of item
			int i = top;
			while (i > 0 && item < list[i-1]){
				list[i] = list[i-1]; //shift larger items to the RIGHT by one
				i--; //prepare to check the next item to the left
			}
			list[i] = item; //put sorted item in open location
		}
	}

	//-----------------------------------------------------------------------

	/* QUICK SORT (the list, the bottom and tops of the list)
	* Take a pivot value. Go through the list and everything less than the pivot
	* goes to the left, everything greater goes to the right. Pivot value is stored
	* somewhere and is position 0 of the list by default so that there is one
	* extra space to move things around.
	*
	* Starting from the RIGHT, search for values LESS THAN THE PIVOT POINT. If found,
	* add it to the current free spot of the LEFT.
	* Then, starting from the LEFT, search for values GREATER THAN THE PIVOT POINT. If
	* found, add it to the current free spot on the RIGHT.
	* Finally, insert the pivot into the last empty position. At this point low >= high
	*
	* After everything is done for one pass, recursively split the list in half and do the same.
	*/
	public static void quickSort (int[] list, int low, int high){
		final int MOVING_LEFT = 0; //constants for readability
		final int MOVING_RIGHT = 1;
		if (low < high){
			int left = low; //the leftmost
			int right = high; //the rightmost
			int currentDirection = MOVING_LEFT; //start at right (moving left) because pivot starts at leftmost pos
			int pivot = list[low]; //leftmost pos
			while (left < right){ //until not met in the middle do everything below
				if (currentDirection == MOVING_LEFT){
					while ((list[right] >= pivot) && (left < right))
						right--;
					list[left] = list[right];
					currentDirection = MOVING_RIGHT;
				}
				if (currentDirection == MOVING_RIGHT){
					while ((list[left] <= pivot) && (left < right))
						left++;
					list[right] = list[left];
					currentDirection = MOVING_LEFT;
				}
			}
			list[left] = pivot; // or list[right] = pivot, since left == right
			quickSort(list, low, left-1); //recursive call
			quickSort(list, right+1, high); //recursive call
		}
	}

	//-----------------------------------------------------------------------

	/* https://youtu.be/4VqmGXwpLqc?si=ZTIeiCZGPRGInObC */

	private static int[] numbers; //the original array given
	private static int[] helper; //helper array for copy
	private static int number;
	public static void mergeSort(int[] values) { /* This copies the array & starts the sort */
		numbers = values;
		number = values.length;
		helper = new int[number];
		mergeSort(0, number - 1);
	}
	private static void mergeSort(int low, int high) {
		if (low < high){ // Check if low is smaller then high, if not then the array is sorted
			int middle = low + (high - low) / 2; // Get the index of the element which is in the middle
			mergeSort(low, middle); // Sort the left side of the array
			mergeSort(middle + 1, high); // Sort the right side of the array
			merge(low, middle, high); // Combine them both
		}
	}
	private static void merge(int low, int middle, int high) {
		// Copy both parts into the helper array
		for (int i = low; i <= high; i++) {
			helper[i] = numbers[i];
		}
		int i = low;
		int j = middle + 1;
		int k = low;
		// Copy the smallest values from either the left or the right side back
		// to the original array
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
		// Copy the rest of the left side of the array into the target array
		while (i <= middle) {
			numbers[k] = helper[i];
			k++;
			i++;
		}
	}

	//-----------------------------------------------------------------------

}
