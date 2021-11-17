/*

LeetCode 42. Trapping Rain Water (Hard)

Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it can trap after raining.

Example 1:

Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
Output: 6
Explanation: The above elevation map (black section) is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped.

Example 2:

Input: height = [4,2,0,3,2,5]
Output: 9


Constraints:

    n == height.length
    1 <= n <= 2 * 104
    0 <= height[i] <= 105

*/

public class TrappingRainWater {

    // brute force solution -> time: O(n^2) | space: O(1)
    public static int getTrappedRainwater(int[] heights) {
        int totalWater = 0;

        for(int p = 0; p < heights.length; p++) {
            int leftP = p, rightP = p;
            int maxLeft = 0, maxRight = 0;

            while(leftP >= 0) {
                maxLeft = Math.max(maxLeft, heights[leftP]);
                leftP--;
            }

            while(rightP < heights.length) {
                maxRight = Math.max(maxRight, heights[rightP]);
                rightP++;
            }

            int currentWater = Math.min(maxLeft, maxRight) - heights[p];

            if(currentWater >= 0) {
                totalWater += currentWater;
            }
        }

        return totalWater;
    }

    // optimal force solution -> time: O(n) | space: O(1)
    public static int getTrappedRainwaterOp(int[] heights) {
        int left = 0, right = heights.length-1;
        int maxLeft = 0, maxRight = 0;
        int totalWater = 0;
        // iterate until pointers meet (traverse the entire array)
        while(left < right) {
            // this if statement decides which pointer to operate on (left/right)
            if(heights[left] <= heights[right]) {
                // this if statements decides whether to update max or to add water
                if(heights[left] >= maxLeft) {
                    maxLeft = heights[left];
                }
                else {
                    totalWater += maxLeft - heights[left];
                }
                left++; // move inward
            }
            else {
                // the logic that decides whether to update max or to add water
                if(heights[right] >= maxRight) {
                    maxRight = heights[right];
                }
                else {
                    totalWater += maxRight - heights[right];
                }
                right--; // move inward
            }
        }
        return totalWater;
    }

    // driver
    public static void main(String[] args) {
        // example inputs
        int[] input_height_1 = {0,1,0,2,1,0,1,3,2,1,2,1};
        int[] input_height_2 = {4,2,0,3,2,5};
        int[] edge_case_1 = {4,2};
        int[] edge_case_2 = {4};
        int[] edge_case_3 = {};
        // Brute force solution
        System.out.println("Brute force solution:");
        System.out.println(getTrappedRainwater(input_height_1)); // 6
        System.out.println(getTrappedRainwater(input_height_2)); // 9
//        System.out.println(getTrappedRainwater(edge_case_1)); // 0
//        System.out.println(getTrappedRainwater(edge_case_2)); // 0
//        System.out.println(getTrappedRainwater(edge_case_3)); // 0
        System.out.println();
        // Optimal solution
        System.out.println("Optimal solution:");
        System.out.println(getTrappedRainwaterOp(input_height_1)); // 6
        System.out.println(getTrappedRainwaterOp(input_height_2)); // 9
//        System.out.println(getTrappedRainwaterOp(edge_case_1)); // 0
//        System.out.println(getTrappedRainwaterOp(edge_case_2)); // 0
//        System.out.println(getTrappedRainwaterOp(edge_case_3)); // 0
    }

}
