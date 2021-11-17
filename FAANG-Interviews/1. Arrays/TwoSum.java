import java.util.Arrays;
import java.util.HashMap;

public class TwoSum {

    // Brute force solution -> time: O(n^2) | space: O(1)
    public static int[] twoSum(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if(arr[i] + arr[j] == target) {
                   return new int[]{i, j};
                }
            }
        }
        return null;
    }

    // Optimal solution -> time: O(n) | space: O(n)
    public static int[] twoSumOp(int[] arr, int target) {
        HashMap<Integer, Integer> hashmap = new HashMap<>();
        for (int p = 0; p < arr.length; p++) {
            int numberToFind = target - arr[p];
            if(hashmap.containsKey(numberToFind)) {
                return new int[]{hashmap.get(numberToFind), p};
            }
            hashmap.put(arr[p], p);
        }
        return null;
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, 7, 9, 2};
        int target = 11;
        System.out.println(Arrays.toString(twoSum(nums, target)));
        System.out.println(Arrays.toString(twoSumOp(nums, target)));
    }

}
