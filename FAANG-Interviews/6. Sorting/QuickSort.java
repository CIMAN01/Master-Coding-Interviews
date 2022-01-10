import java.util.Arrays;

// QuickSort is preferred to MergeSort since it requires less space given its in-place algorithm (assuming a good pivot is selected)
public class QuickSort {

    // swap method
    private static void swap(int[] array, int index1, int index2) {
        int temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }

    // partition method
    private static int partition(int[] array, int start, int end) {
        // create a pivot and a boundary
        int pivot = array[end]; // choose last array element as the pivot
        int boundary = start - 1; // left partition is empty, right partition starts from index 0
        // iterate over a segment of the array
        for(int i = start; i <= end; i++) { // smaller or equal to end to include the indexed pivot
            // if an item is smaller than the pivot
            if(array[i] <= pivot) {
                // put it in the left partition (increment the boundary first then do a swap)
                swap(array, i, ++boundary);
            }
        }
        return boundary; // this is index of the pivot after it has moved
    }

    // average time: O(n log n) & worst time: O(n^2) | best space: O(log n) & worst space: O(n)
    private static void sort(int[] array, int start, int end) {
        // handle a single item or empty array (base condition for recursive calls)
        if(start >= end) {
            return;
        }
        // partition a segment of the array (pivot will then move to its correct position)
        int boundary = partition(array, start, end);
        // boundary now represents the position or index of the pivot
        sort(array, start, boundary - 1); // sort left (recursive call)
        sort(array, boundary + 1, end); // sort right (recursive call)
    }

    // sort method with one param that calls sort method with three params
    public static void sort(int[] array) {
        sort(array, 0, array.length - 1);
    }

    // driver
    public static void main(String[] args) {
        int[] arr = {99, 44, 6, 2, 1, 5, 63, 87, 283, 4, 0};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

}
