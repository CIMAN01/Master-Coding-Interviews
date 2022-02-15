/*

104. Maximum Depth of Binary Tree (Easy)


Given the root of a binary tree, return its maximum depth.

A binary tree's maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.

Example 1:

 	     3
       /   \
	  9	    20
           /  \
		  15   7

Input: root = [3,9,20,null,null,15,7]
Output: 3

Example 2:

Input: root = [1,null,2]
Output: 2


Constraints:

    The number of nodes in the tree is in the range [0, 104].
    -100 <= Node.val <= 100

*/


public class MaximumDepthOfBinaryTree {


    // a method that finds the maximum depth of a binary tree -> time: O(n) | space: O(n)
    public static int maxDepth(TreeNode node, int currentDepth) { // use DFS
        // base case
        if (node == null) {
            return currentDepth;
        }
        currentDepth++; // increment count (how many nodes traversed to reach current node)
        // determine the max depth count for each left and right child for each node in the tree
        return Math.max(maxDepth(node.left, currentDepth), maxDepth(node.right, currentDepth));
    }


    // a method that finds the maximum depth of a binary tree -> time: O(n) | space: O(n)
    public static int maxDepth(TreeNode root) { // use DFS
        // algorithm: Tree Height = 1 (root) + Max(left sub-tree, right sub-tree)

        // base case for recursive calls
        if(root == null){
            return 0;
        }
        // repeat algorithm recursively for each node in the tree
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right)); // 1 is the root node
    }

    
    // driver
    public static void main(String[] args) {
        // create the tree -> [3,9,20,null,null,15,7]
        // create a new root node
        TreeNode root = new TreeNode(3);
        // populate tree by adding children nodes to the root node
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        System.out.println();
        // find the maximum depth of the tree and print the results
        System.out.println("maxDepth: " + maxDepth(root, 0)); // 3
        // create the tree -> [1,null,2]
        // create a new root node
        TreeNode root2 = new TreeNode(1);
        // populate tree by adding children nodes to the root node
        root2.left = new TreeNode(2);
        // find the maximum depth of the tree and print the results
        System.out.println("maxDepth: " + maxDepth(root2)); // 2
    }

}
