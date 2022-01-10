/*

Find First and Last Position of Element in Sorted Array (Medium)


Given an array of integers nums sorted in non-decreasing order, find the starting and ending position of a given target value.

If target is not found in the array, return [-1, -1].

You must write an algorithm with O(log n) runtime complexity.


Example 1:

Input: nums = [5,7,7,8,8,10], target = 8
Output: [3,4]

Example 2:

Input: nums = [5,7,7,8,8,10], target = 6
Output: [-1,-1]

Example 3:

Input: nums = [], target = 0
Output: [-1,-1]


Constraints:

    0 <= nums.length <= 105
    -109 <= nums[i] <= 109
    nums is a non-decreasing array.
    -109 <= target <= 109


*/


import java.util.Arrays;

public class FindFirstLastElemPosition {


    // Binary Search -> time complexity: O(log n) | space complexity: O(1)
    public static int binarySearch(int[] array, int left, int right, int target) {
        // loop through the array to find a match
        while(left <= right) {
            // create a mid point
            int mid = left + (right-left) / 2;
            int foundValue = array[mid];
            // check for a match
            if(foundValue == target) {
                return mid; // found a match
            }
            else if(foundValue < target) { // if no match found, reduce search space by half (binary search)
                left = mid + 1;
            }
            else {
                right = mid - 1;
            }
        }
        return -1; // have not found a match after a complete binary search
    }


    // a method that finds the start and end indices of a target in array -> time complexity: O(log n) | space complexity: O(1)
    public static int[] searchRange(int[] nums, int target) {
        // handle an empty array
        if(nums.length < 1) {
            return new int[]{-1,-1};
        }
        // store the index of the first occurrence of the target value found in the array
        int firstPosition = binarySearch(nums, 0, nums.length-1, target); // binary search
        // if we cannot find one, we return [-1,-1]
        if(firstPosition == -1) {
            return new int[]{-1,-1};
        }
        // initialize the starting, ending, and temp placeholders
        int startPosition = firstPosition;
        int endPosition = firstPosition;
        int temp1 = 0, temp2 = 0;
        // keep searching for the target value until the binary search method returns -1 for the portion of the array to the left of mid
        while(startPosition != -1) {
            temp1 = startPosition; // temp1 will hold the startPosition's last value before it hits -1
            startPosition = binarySearch(nums, 0, startPosition-1, target); // shrink search space
        }
        startPosition = temp1; // override -1 with the value temp holds
        // keep searching for the target value until the binary search method returns -1 for the portion of the array to the right of mid
        while(endPosition != -1) {
            temp2 = endPosition; // temp1 will hold the startPosition's last value before it hits -1
            endPosition = binarySearch(nums, endPosition+1, nums.length-1, target); // shrink search space
        }
        endPosition = temp2; // override -1 with the value temp holds
        // return a new array with that holds the proper starting and ending indices
        return new int[]{startPosition, endPosition};
    }


    // driver
    public static void main(String[] args) {
        // create an array and a target value
        int[] nums1 = {5,7,7,8,8,10};
        int target1 = 8;
        // find the starting and ending indices
        System.out.println(Arrays.toString(searchRange(nums1, target1))); // Output: [3,4]
        // create another array and a target value
        int[] nums2 = {5,7,7,8,8,10};
        int target2 = 6;
        // find the starting and ending indices
        System.out.println(Arrays.toString(searchRange(nums2, target2))); // Output: [-1,-1]
        // create another array and a target value
        int[] nums3 = {};
        int target3 = 0;
        // find the starting and ending indices
        System.out.println(Arrays.toString(searchRange(nums3, target3))); // Output: [-1,-1]
    }

}
