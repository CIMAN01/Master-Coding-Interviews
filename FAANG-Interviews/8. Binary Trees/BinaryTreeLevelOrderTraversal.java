/*

102. Binary Tree Level Order Traversal (Medium)


Given the root of a binary tree, return the level order traversal of its nodes' values. (i.e., from left to right, level by level).

Example 1:

                  3
		/   \
	       9    20
		   /  \
		  15   7

Input: root = [3,9,20,null,null,15,7]
Output: [[3],[9,20],[15,7]]


Example 2:

Input: root = [1]
Output: [[1]]


Example 3:

Input: root = []
Output: []


Constraints:

    The number of nodes in the tree is in the range [0, 2000].
    -1000 <= Node.val <= 1000


*/


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


public class BinaryTreeLevelOrderTraversal {


    // a method that performs a level order traversal (bfs) and stores each level's nodes in a sub-array -> time: O(n) | space: O(n)
    public static List<List<Integer>> levelOrder(TreeNode root) { // breadth first search
        // create a list of a list of integers
        List<List<Integer>> result = new ArrayList<>();
        // handle an empty tree
        if(root == null) {
            return result;
        }
        // create a queue that holds a tree node
        Queue<TreeNode> queue = new LinkedList<>();
        // add the root to the queue
        queue.add(root);
        // while there are still items in queue to be processed
        while(!queue.isEmpty()) {
            // create a variable that holds the size of the queue for the current level
            int size = queue.size(); // this will determine how many tree nodes we need to process on the current iteration of the loop
            int count = 0; // count will work in tandem with size to keep track of which nodes belong to which level during each iteration
            // create a list that holds all the nodes' values that need to be processed at the current level
            List<Integer> currentLevel = new ArrayList<>();
            // go through each item in the queue
            while(count < size) {
                // get the current tree node to be processed
                TreeNode currentNode = queue.remove(); // or queue.poll();
                // once tree node is retrieved, add its value to the list at the current level
                currentLevel.add(currentNode.val);
                // check if current node has left child and, if it does, add child node to the queue for processing during the next iteration
                if(currentNode.left != null) {
                    queue.add(currentNode.left);
                }
                // check if current node has right child and, if it does, add child node to the queue for processing during the next iteration
                if(currentNode.right != null) {
                    queue.add(currentNode.right);
                }
                count++; // increment count after each node is processed at each level
            }
            result.add(currentLevel); // add the list at the current level (sub list) to the resulting list
        }
        return result;
    }


    // driver
    public static void main(String[] args) {
        // create a binary tree -> [3,9,20,null,null,15,7]
        TreeNode root = new TreeNode(3); // create a new root node with a value of 3
        // populate tree by adding children nodes to the root node
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        // return the level order traversal of its nodes
        System.out.println(levelOrder(root)); // [[3],[9,20],[15,7]]
        // create a new binary tree -> [1,null,null]
        TreeNode root2 = new TreeNode(1); // create a new root node with a value of one
        // return the level order traversal of its nodes
        System.out.println(levelOrder(root2)); // [[1]]
        // create a new empty binary tree -> [null]
        TreeNode root3 = new TreeNode(); // create a null root
        // return the level order traversal of its nodes
        System.out.println(levelOrder(root3)); // [[0]]
    }

}
