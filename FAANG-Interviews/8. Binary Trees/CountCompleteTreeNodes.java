/*

222. Count Complete Tree Nodes (Medium)


Given the root of a complete binary tree, return the number of the nodes in the tree.

According to Wikipedia, every level, except possibly the last, is completely filled in a complete binary tree, and all nodes in the last level are as far left as possible.

It can have between 1 and 2^h nodes inclusive at the last level h.

Design an algorithm that runs in less than O(n) time complexity.


Example 1:

                 1
               /   \
              2     3
             / \    /
            4   5  6


Input: root = [1,2,3,4,5,6]
Output: 6

Example 2:

Input: root = []
Output: 0

Example 3:

Input: root = [1]
Output: 1


Constraints:

    The number of nodes in the tree is in the range [0, 5 * 104].
    0 <= Node.val <= 5 * 104
    The tree is guaranteed to be complete.


*/

/**

Definitions:

 - for a full binary tree, any tree node must either have two children or no children at all.

 - for a complete binary tree, every level must be full except for the last level where all the nodes must be pushed to left as much as possible. <- LeetCode question

 - for a complete & full binary tree, tree nodes have either two or no children and every level is filled including the last level.

*/

public class CountCompleteTreeNodes {

    // a method that determines the height of the tree -> time: O(h) / O(log n)
    public static int getTreeHeight(TreeNode root) {
        int height = 0;
        // determine the height of the tree
        while(root.left != null) {
            height++;
            root = root.left; // left child becomes the new root
        }
        // if the tree contains only the root, the height will remain at 0
        return height;
    }


    // a method that checks if the node exists -> time: O(h) / O(log n)
    public static boolean nodeExists(int indexToFind, int height, TreeNode node) {
        // initialize left and right pointers
        int left = 0;
        int right = (int) (Math.pow(2, height) - 1); // 2^(h-1) - 1
        // initialize count: keeps track of how many steps down that we've made (starts at 0 and ends when it reaches the height)
        int count = 0;
        // loop will increment count until it's equal to the height
        while(count < height) {
            // use a (slightly modified) binary search to find the middle of the (root) node
            int middleOfNode = left + (right-left) / 2 + 1; // Math.ceil((left + right) / 2) -> round up to be inclusive in binary search
            // perform a check to determine which way to traverse down the tree
            if(indexToFind >= middleOfNode) { // middleOfNode is inclusive
                node = node.right; // traverse to the right (take one step down to the right)
                left = middleOfNode; // reduce search space by shifting left over (right value stays the same)
            }
            else {
                node = node.left; // traverse to the left instead
                right = middleOfNode - 1; // right value will now shrink instead
            }
            count++; // count represents the numbers of steps taken along the way (depth-wise)
        }

        return node != null; // checks if node exists
    }


    // a method that uses binary search to count all the nodes in a complete binary tree -> time: O(h^2) / O(log^2 n) | space: O(1)
    public static int countNodes(TreeNode root) {
        // handle an empty tree
        if(root == null) {
            return 0;
        }
        // get the height of the tree before determining the upper portion of the tree
        int height = getTreeHeight(root);
        // handle a tree with only a root (2^0)
        if(height == 0) {
            return 1;
        }
        // determine the value of the upper portion of the binary tree
        int upperCount = (int) (Math.pow(2, height) - 1);
        // treat lower portion of the tree as an indexed array
        int left = 0, right = upperCount; // index left and right values
        // implement a modified binary search for the lower portion of the binary tree
        while(left < right) { // no need for left and right to overlap since we are only checking for a value that exists
            // calculate the middle of two values
            int indexToFind = left + (right-left) / 2 + 1; // Math.ceil((left + right) / 2)
            // perform a search from the top all the way down to the node to check if it exists or not
            // indexToFind is the value we're looking for and the height is the number of steps we need to take
            if(nodeExists(indexToFind, height, root)) {
                left = indexToFind; // reduce search space by shifting left to be equal to indexToFind (left is inclusive)
            }
            else {
                right = indexToFind - 1; // reduce search space by shifting right one index to the left
            }
        }
        // combine upper and lower portion of the tree to get the total count of nodes in the tree
        return upperCount + left + 1; // can use either left or right plus one (plus one because index starts at 0)
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // alternate way to count the nodes in a complete binary tree (using less code):
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static int getLeftHeight(TreeNode root) {
        if(root == null) {
            return 0;
        }

        return 1 + getLeftHeight(root.left);
    }

    public static int getRightHeight(TreeNode root) {
        if(root == null) {
            return 0;
        }

        return 1 + getRightHeight(root.right);
    }

    public static int countTheNodes(TreeNode root) {
        // handle an empty tree
        if(root == null) {
            return 0;
        }
        // get the height of both the left-most and right-most node
        int leftHeight = getLeftHeight(root);
        int rightHeight = getRightHeight(root);
        // if the height of left-most node and the height of right-most node are the same, then it is a complete tree
        if(leftHeight == rightHeight) {
            return (int) Math.pow(2, rightHeight) - 1; // calculate the number of nodes in a complete tree, 2^h – 1, where h is the height
        }
        // if the two are not the same, then recursively solve the problem:
        // count the nodes for the left subtree and the right subtree, then combine the nodes and add one
        return 1 + countTheNodes(root.left) + countTheNodes(root.right);
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    // driver
    public static void main(String[] args) {
        // create a binary tree -> [1,2,3,4,5,6]
        TreeNode root = new TreeNode(1); // create a new root node with a value of 3
        // populate the tree by adding children nodes to the root node
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        // return how many nodes are in the given tree
        System.out.println();
        System.out.println("Node count: " + countNodes(root)); // 6
        System.out.println("Node count: " + countTheNodes(root)); // 6
        System.out.println();
        // create a new empty binary tree -> []
        TreeNode root2 = null; // create a new null root
        // return how many nodes are in the given tree
        System.out.println("Node count: " + countNodes(root2)); // 0
        System.out.println("Node count: " + countTheNodes(root2)); // 0
        // create a new binary tree -> [1]
        TreeNode root3 = new TreeNode(1); // create a root node with a value of one
        // return how many nodes are in the given tree
        System.out.println();
        System.out.println("Node count: " + countNodes(root3)); // 1
        System.out.println("Node count: " + countTheNodes(root3)); // 1
    }

}
