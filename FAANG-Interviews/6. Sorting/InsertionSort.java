import java.util.Arrays;

public class InsertionSort {

    // time complexity: O(n^2) | space complexity : O(1)
    public static void sort(int[] array) {
        // iterate over unsorted partition (which starts at i = 1 because first index is assumed to be in its correct position or sorted)
        for(int i = 1; i < array.length; i++) {
            // store the current item so that it's not lost when shifting
            int item = array[i];
            // set j to the first index of the unsorted partition
            int j = i;
            // iterate over the sorted partition (from right to left) and compare each of its elements to the current item to find the correct insertion position
            // (only sort a number smaller than numbers on the left of it -> this is the part of insertion sort that makes it fast if the array is almost sorted)
            while(j > 0 && array[j-1] > item) { // while we haven't hit the front of the sorted partition and while the current item is smaller
                array[j] = array[j-1]; // shift items to the right to make room for the current item
                j--;
            }
            // the right spot to insert the item has been found
            array[j] = item;
        }
    }


    // time complexity: O(n^2) | space complexity : O(1)
    public static void sort2(int[] arr) {
        // iterate over unsorted partition
        for (int i = 0; i < arr.length; ++i) {
            // store the current element
            int elem = arr[i];
            // iterate over the sorted partition
            for (int j = 0; j < i; ++j) {
                // compare values
                if(arr[j] > elem) {
                    System.arraycopy(arr, j, arr, j+1, i-j); // shift items
                    arr[j] = elem; // insert item in the right spot
                    break;
                }
            }
        }
    }


    // driver
    public static void main(String[] args) {
        int[] arr = {99, 44, 6, 2, 1, 5, 63, 87, 283, 4, 0};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

}
