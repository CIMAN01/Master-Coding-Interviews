/*

199. Binary Tree Right Side View (Medium)


Given the root of a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to bottom.


Example 1:

                  1
		/   \
	       2     3
		    /  \
		   5    4

Input: root = [1,2,3,null,5,null,4]
Output: [1,3,4]

Example 2:

Input: root = [1,null,3]
Output: [1,3]

Example 3:

Input: root = []
Output: []



Constraints:

    The number of nodes in the tree is in the range [0, 100].
    -100 <= Node.val <= 100


*/


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


public class BinaryTreeRightSideView {

    // a method that uses breadth first search to return the values of the nodes of binary tree as seen from the right side view -> time: O(n) | space: O(n) for a full/complete tree
    public static List<Integer> rightSideView(TreeNode root) { // BFS (level order) -> space: O(width)
        /////////////////////////////////////////////////////////////////////////////////////
        //  step 1. identify the end of level                                             //
        //  step 2. add last node to result                                              //
        //////////////////////////////////////////////////////////////////////////////////
        // create a list of a list of integers
        List<Integer> result = new ArrayList<>();
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
            TreeNode currentNode = null; // current node to be processed
            // identify the end of level (step 1)
            while(count < size) {
                // get the current tree node to be processed
                currentNode = queue.remove();
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
            result.add(currentNode.val); // add only the last node to result at each level (step 2)
        }
        return result;
    }


    // a method that uses depth first search to return the values of the nodes of binary tree as seen from the right side view -> time: O(n) | space: O(n) for a fully skewed tree
    public static List<Integer> rightSideViewRecursive(TreeNode root) { // DFS -> space: O(height)
        //////////////////////////////////////////////////////////////////////////////////////
        //  step 1. prioritize the right side                                              //
        //  step 2. keep track of level of nodes                                          //
        ///////////////////////////////////////////////////////////////////////////////////
        // create a list of a list of integers
        List<Integer> result = new ArrayList<>();
        // handle an empty tree
        if(root == null) {
            return result;
        }
        // call recursive method to perform a modified pre-order (NRL) traversal
        dfs(root, 0, result);

        return result;
    }

    // implements the logic for traversing a tree and filtering out the values that are blocked from view while adding those that aren't
    public static void dfs(TreeNode node, int currentLevel, List<Integer> result) {
        // base condition
        if(node == null) {
            return;
        }
        // only add node values that been visited first for a particular level
        if(currentLevel >= result.size()) {
            result.add(node.val);
        }
        // pre-order (NRL) where right node is visited first (step 1)
        if(node.right != null) {
            dfs(node.right, currentLevel+1, result); // increase and pass the level down (step 2)
        }
        // then left node is visited
        if(node.left != null) {
            dfs(node.left, currentLevel+1, result); // increase and pass the level down (step 2)
        }
    }


    // driver
    public static void main(String[] args) {
        // create a binary tree ->  [1,2,3,null,5,null,4]
        TreeNode root = new TreeNode(1); // create a new root node with a value of 3
        // populate tree by adding children nodes to the root node
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(4);
        // return the level order traversal of its nodes as viewed at right side only
        System.out.println();
        System.out.println("BFS: " + rightSideView(root)); // [1,3,4]
        System.out.println("DFS: " + rightSideViewRecursive(root)); // [1,3,4]
        // create a new binary tree -> [1,null,3]
        TreeNode root2 = new TreeNode(1); // create a new root node with a value of one
        // populate tree by adding children nodes to the root node
        root2.right = new TreeNode(3);
        // return the level order traversal of its nodes as viewed at right side only
        System.out.println();
        System.out.println("BFS: " + rightSideView(root2)); // [1,3]
        System.out.println("DFS: " + rightSideViewRecursive(root2)); // [1,3]
        // create a new empty binary tree -> []
        TreeNode root3 = new TreeNode(); // create a null root
        // return the level order traversal of its nodes as viewed at right side only
        System.out.println();
        System.out.println("BFS: " + rightSideView(root3)); // [0]
        System.out.println("DFS: " + rightSideViewRecursive(root3)); /// [0]
    }

}
