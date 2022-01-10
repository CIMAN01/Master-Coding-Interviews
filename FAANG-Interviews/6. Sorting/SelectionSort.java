import java.util.Arrays;

public class SelectionSort {

    // time complexity: O(n^2) | space complexity : O(1)
    public static void sort(int[] array) {
        // traverse the array
        for (int i = 0; i < array.length; i++) {
            // set current index as minimum
            int min = i; // index of min value
            // find min with a loop and comparison
            for (int j = i + 1; j < array.length; j++) {
                // update minimum if current is lower than what we had previously
                if(array[j] < array[min]) {
                    min = j; // index of min value
                }
            }
            // swap numbers
            int temp = array[i];
            array[i] = array[min];
            array[min] = temp;
        }
    }

    // driver
    public static void main(String[] args) {
        int[] arr = {99, 44, 6, 2, 1, 5, 63, 87, 283, 4, 0};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

}
