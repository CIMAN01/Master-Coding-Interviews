/*

LeetCode 11. Container With Most Water (Medium)

Given n non-negative integers a1, a2, ..., an , where each represents a point at coordinate (i, ai). n vertical lines are drawn such that the two endpoints
of the line i is at (i, ai) and (i, 0). Find two lines, which, together with the x-axis forms a container, such that the container contains the most water.

Notice that you may not slant the container.

Example 1:

Input: height = [1,8,6,2,5,4,8,3,7]
Output: 49
Explanation: The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7]. In this case, the max area of water (blue section) the container can contain is 49.

Example 2:

Input: height = [1,1]
Output: 1

Example 3:

Input: height = [4,3,2,1,4]
Output: 16

Example 4:

Input: height = [1,2,1]
Output: 2


Constraints:

    n == height.length
    2 <= n <= 105
    0 <= height[i] <= 104

*/


public class MaxWaterContainer {

    // brute force solution -> time: O(n^2) | space: O(1)
    public static int findMaxContainer(int[] heights) {
        int maxArea = 0;
        // pointer a for outer loop
        for (int a = 0; a < heights.length; a++) {
            // pointer b for inner loop
            for (int b = a + 1; b < heights.length; b++) {
                // get height and hidth
                int height = Math.min(heights[a], heights[b]);
                int width = b - a;
                // get total area (H x W)
                int area = height * width;
                // get max area found so far
                maxArea = Math.max(maxArea, area);
            }
        }
        return maxArea;
    }

    // optimal solution -> time: O(n) | space: O(1)
    public static int findMaxContainerOp(int[] heights) {
        int maxArea = 0;
        int a = 0;
        int b = heights.length-1;
        // two pointer technique
        while(a < b) {
            // get height and width
            int height = Math.min(heights[a], heights[b]);
            int width = b - a;
            // get total area (H x W)
            int area = height * width;
            // get max area found so far
            maxArea = Math.max(maxArea, area);
            // the logic for how to move the two pointers
            if(heights[a] <= heights[b]) {
                a++;
            }
            else {
                b--;
            }
        }
        return maxArea;
    }

    // driver
    public static void main(String[] args) {
        // example inputs
        int[] input_height_1 = {1,8,6,2,5,4,8,3,7};
        int[] input_height_2 = {1,1};
        int[] input_height_3 = {4,3,2,1,4};
        int[] input_height_4 = {1,2,1};
        // edge cases
        int[] edge_case_1 = {};
        int[] edge_case_2 = {9};
        // using brute force method
        System.out.println("Brute force solution:");
        System.out.println(findMaxContainer(input_height_1)); // 49
        System.out.println(findMaxContainer(input_height_2)); // 1
        System.out.println(findMaxContainer(input_height_3)); // 16
        System.out.println(findMaxContainer(input_height_4)); // 2
//        System.out.println(findMaxContainer(edge_case_1)); // 0
//        System.out.println(findMaxContainer(edge_case_2)); // 0
        System.out.println();
        // using optimal method
        System.out.println("Optimal solution:");
        System.out.println(findMaxContainerOp(input_height_1)); // 49
        System.out.println(findMaxContainerOp(input_height_2)); // 1
        System.out.println(findMaxContainerOp(input_height_3)); // 16
        System.out.println(findMaxContainerOp(input_height_4)); // 2
//        System.out.println(findMaxContainerOp(edge_case_1)); // 0
//        System.out.println(findMaxContainerOp(edge_case_2)); // 0
    }

}
