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

    // Brute force solution -> time: O(n^2) | space: O(1)
    public static int getTrappedRainwater(int[] heights) {
        int totalWater = 0;
        // traverse the entire array
        for(int p = 0; p < heights.length; p++) {
            // left and right pointers
            int left = p, right = p;
            // maximum left and right pointers
            int maxLeft = 0, maxRight = 0;
            // scan left to determine the maximum height to the left of current element (maxLeft)
            while(left >= 0) {
                maxLeft = Math.max(maxLeft, heights[left]);
                left--;
            }
            // scan right to determine the maximum height to the right of current element (maxRight)
            while(right < heights.length) {
                maxRight = Math.max(maxRight, heights[right]);
                right++;
            }
            // formula to calculate the water at any element in the array when a wall is formed
            int currentWater = Math.min(maxLeft, maxRight) - heights[p];
            // only add positive values (logically negative numbers cannot hold water)
            if(currentWater >= 0) {
                totalWater += currentWater;
            }
        }
        return totalWater;
    }

    // Optimal force solution -> time: O(n) | space: O(1)
    public static int getTrappedRainwaterOp(int[] heights) {
        int totalWater = 0;
        // initialize pointers
        int left = 0, right = heights.length-1; // left and right pointers
        int maxLeft = 0, maxRight = 0; // maximum left and right pointers
        // iterate until pointers meet (traverse the entire array)
        while(left < right) {
            // check which value is larger between right side and left side to determine which side (pointer) to operate on
            if(heights[left] <= heights[right]) { // if equal, go with left side
                // the logic that decides whether to update maxLeft or to add water
                if(heights[left] >= maxLeft) {
                    maxLeft = heights[left];
                }
                else {
                    // the new formula to calculate the water at any element in the array when a wall if formed
                    totalWater += maxLeft - heights[left]; // if check at line 67 dictates that other side has a wall that is taller
                }
                left++; // move pointer inward
            }
            else {
                // the logic that decides whether to update maxRights or to add water
                if(heights[right] >= maxRight) {
                    maxRight = heights[right];
                }
                else {
                    // the new formula to calculate the water at any element in the array when a wall if formed
                    totalWater += maxRight - heights[right]; // if check at line 67 dictates that other side has a wall that is taller
                }
                right--; // move pointer inward
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
