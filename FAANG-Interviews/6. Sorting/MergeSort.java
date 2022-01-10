import java.util.Arrays;

public class MergeSort {

    // time complexity: O(n log n) | space complexity : O(n)
    public static void sort(int[] array) {
        // base case for recursive calls
        if(array.length < 2) {
            return;
        }
        // divide array in half
        int middle = array.length / 2; // find the middle in order to split array in half
        // create a left array
        int[] left = new int[middle];
        // copy items from input array to left array
        System.arraycopy(array, 0, left, 0, middle);
        // create a right array
        int[] right = new int[array.length-middle];
        // copy items from input array to right array
        System.arraycopy(array, middle, right, 0, array.length - middle);
        // sort each half
        sort(left); // recursive call
        sort(right); // recursive call
        // merge the result
        merge(left, right, array);
    }

    // merge method
    private static void merge(int[] left, int[] right, int[] result) {
        // i iterates over left partition, j over right, and k over result array
        int i = 0, j = 0, k = 0;
        // traverse both the left and right partitions
        while(i < left.length && j < right.length) {
            // find the smaller value and store it in the result array
            if(left[i] <= right[j]) {
                // result[k] = left[i]; i++; k++
                result[k++] = left[i++];
            }
            else {
                result[k++] = right[j++];
            }
        }
        // if the left partition ends up being larger than the right partition
        while(i < left.length) {
            result[k++] = left[i++]; // copy remaining items to the result array
        }
        // if right partition ends up being larger than the left partition
        while(j < right.length) {
            result[k++] = right[j++]; // copy remaining items to the result array
        }
    }

    // driver
    public static void main(String[] args) {
        int[] arr = {99, 44, 6, 2, 1, 5, 63, 87, 283, 4, 0};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

}
