/*

LeetCode 53. Maximum Subarray

Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.

A subarray is a contiguous part of an array.

Example 1:

Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
Output: 6
Explanation: [4,-1,2,1] has the largest sum = 6.

Example 2:

Input: nums = [1]
Output: 1

Example 3:

Input: nums = [5,4,-1,7,8]
Output: 23

Constraints:

  *  1 <= nums.length <= 105
  *  -104 <= nums[i] <= 104

Follow up: If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, which is more subtle.

*/


public class MaxSubArray {

    /*

    Solution:

    The simple idea of Kadane’s algorithm is to look for all positive contiguous segments of the array (localMaxSum is used for this),
    and keep track of maximum sum contiguous segment among all positive segments (globalMaxSum is used for this).

    Each time we get a positive-sum compare it with globalMaxSum and update globalMaxSum if it is greater than globalMaxSum (localMaxSum > globalMaxSum).

    */

    // Kadane’s Algorithm -> time complexity: O(n)
    public static int findMaxSubArraySum(int[] arr) {
        int globalMaxSum = Integer.MIN_VALUE;
        int localMaxSum = 0;

        for(int i = 0; i < arr.length; i++) {
            localMaxSum = localMaxSum + arr[i];
            if(globalMaxSum < localMaxSum) {
                globalMaxSum = localMaxSum;
            }
            if(localMaxSum<0) {
                localMaxSum=0;
            }
        }
        return globalMaxSum;
    }


    public static void main(String[] args) {
        int[] nums1 = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int[] nums2 = {1};
        int[] nums3 = {5, 4, -1, 7, 8};
        System.out.println((findMaxSubArraySum(nums1)));  // 6
        System.out.println((findMaxSubArraySum(nums2)));  // 1
        System.out.println((findMaxSubArraySum(nums3)));  // 23
    }

}
