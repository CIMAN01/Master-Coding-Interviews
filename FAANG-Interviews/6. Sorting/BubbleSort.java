import java.util.Arrays;

public class BubbleSort {

    // time complexity: O(n^2) | space complexity : O(1)
    public static void sort(int[] array) {
        // traverse the array
        for (int i = 0; i < array.length; i++) {
            // traverse the array ahead of i
            for (int j = i + 1; j < array.length; j++) {
                // bubble up
                if(array[i] > array[j]) {
                    // swap numbers
                    int temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
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
