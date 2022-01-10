/*

215. Kth Largest Element in an Array (Medium)


Given an integer array nums and an integer k, return the kth largest element in the array.

Note that it is the kth largest element in the sorted order, not the kth distinct element.


Example 1:

Input: nums = [3,2,1,5,6,4], k = 2
Output: 5

Example 2:

Input: nums = [3,2,3,1,2,4,5,5,6], k = 4
Output: 4


Constraints:

    1 <= k <= nums.length <= 104
    -104 <= nums[i] <= 104

*/


public class KthLargestElement {

    // swap method
    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    // partition method
    private static int getPartition(int[] array, int left, int right) {
        // create a pivot and a partition index
        int pivot = array[right]; // choose last array element as the pivot
        int partitionIndex = left;
        // iterate over a segment of the array
        for(int j = left; j < right; j++) { // j < right because there is no need to compare j to the pivot element
            // if an item is smaller than the pivot
            if(array[j] < pivot) {
                // put it in the left partition (increment the boundary first then do a swap)
                swap(array, partitionIndex, j);
                partitionIndex++;
            }
        }
        // swap i (partitionIndex) with pivot (right)
        swap(array, partitionIndex, right); // right represents the pivot
        // pivot is now in its correct position 
        return partitionIndex;
    }

    // Quicksort method -> time complexity: O(n log n) / O(n^2) | space complexity: O(log n)
    private static void quickSort(int[] array, int left, int right) {
        // handle a single item or empty array
        if(left >= right) {
            return;
        }
        // partition a segment of the array (pivot will then move to its correct position)
        int partitionIndex = getPartition(array, left, right);
        // partitionIndex now represents the position or index of the pivot
        quickSort(array, left, partitionIndex - 1); // sort left (recursive call)
        quickSort(array, partitionIndex + 1, right); // sort right (recursive call)
    }


    // Hoare's QuickSelect Algorithm -> time complexity: O(n) / O(n^2) | space complexity: O(1) <-> tail recursion
    public static int quickSelect(int[] array, int left, int right, int indexToFind) {
        // what we do with the partition index is how quickSelect differs from quickSort
        int partitionIndex = getPartition(array, left, right);
        // compare partition index to the index that we want to find
        if(partitionIndex == indexToFind) {
            return array[partitionIndex]; // here we have found the answer
        }
        else if(indexToFind < partitionIndex) {
            return quickSelect(array, left, partitionIndex - 1, indexToFind);
        }
        else {
            return quickSelect(array, partitionIndex + 1, right, indexToFind);
        }
    }


    //  using QuickSort -> time complexity: O(n log n) / O(n^2) | space complexity: O(log n)
    public static int getKthLargest(int[] nums, int k) {
        // the length of the array minus k gives us the index of the largest element in a sorted array
        int indexToFind = nums.length-k;
        // call quickSort method to sort the array in place
        quickSort(nums,0,nums.length-1);
        // once quickSort is done, the array is fully sorted
        return nums[indexToFind];
    }

    //  using Quick-select (optimal solution) -> time complexity: O(n) / O(n^2) | space complexity: O(1)
    public static int getKthLargestOpt(int[] nums, int k) {
        // the length of the array minus k gives us the index of the largest element in a sorted array
        int indexToFind = nums.length-k;
        // call Quick-select method to sort the array in place
        return quickSelect(nums, 0, nums.length - 1, indexToFind);
    }


    // driver
    public static void main(String[] args) {
        // create an array and a K value
        int[] nums1 = {3,2,1,5,6,4};
        int k1 = 2;
        // get the K-th largest array element
        System.out.println(getKthLargest(nums1, k1)); // 5
        // create another array and K value
        int[] nums2 = {3,2,3,1,2,4,5,5,6};
        int k2 = 4;
        // get the K-th largest array element
        System.out.println(getKthLargestOpt(nums2, k2)); // 4
    }

}
