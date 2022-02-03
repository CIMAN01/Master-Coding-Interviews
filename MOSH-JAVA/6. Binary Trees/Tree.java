import java.util.ArrayList;
import java.util.List;

public class Tree {

    // inner
    private class Node {
        // fields
        private int value;
        private Node leftChild;
        private Node rightChild;

        // constructor
        public Node(int value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "Node=" + value;
        }
    }

    private Node root; // tree root

    // insert method
    public void insert(int value) {
        // create a new node
        Tree.Node node = new Node(value); // refactor
        // if tree is empty, set root to a new node
        if(root == null) {
            root = node;
            return;
        }
        Tree.Node current = root;
        // find the parent for the new node being inserted
        while(true) {
            if(value < current.value) {
                // if current left child is null, the parent has been found
                if(current.leftChild == null) {
                    current.leftChild = node;
                    break;
                }
                current = current.leftChild; // else go one level down
            }
            else {
                // if current right child is null, the parent has been found
                if(current.rightChild == null) {
                    current.rightChild = node;
                    break;
                }
                current = current.rightChild; // else go one level down
            }
        }
    }

    // find method
    public boolean find(int value) {
        // create a new current node and assign it the root value
        Tree.Node current = root;
        // keep searching as long as tree is not empty
        while(current != null) {
            // compare value passed in with the current value in the tree
            if(value < current.value) { // if value is greater than the current value,
                current = current.leftChild; // go to the left subtree
            }
            else if (value > current.value) { // if value is less than the current value,
                current = current.rightChild; // go to the right subtree
            }
            else { // otherwise, target node has been found
                return true;
            }
        }
        return false;
    }

    /* traversals */

    public void traversePreOrder() {
        traversePreOrder(root);
    }

    // Pre-order
    private void traversePreOrder(Node root) {
        // handle empty tree
        if(root == null) {
            return;
        }
        System.out.println(root.value);
        traversePreOrder(root.leftChild);
        traversePreOrder(root.rightChild);
    }


    public void traverseInOrder() {
        traverseInOrder(root);
    }

    // In-order
    private void traverseInOrder(Node root) {
        // handle empty tree
        if(root == null) {
            return;
        }
        traverseInOrder(root.leftChild);
        System.out.println(root.value);
        traverseInOrder(root.rightChild);
    }


    public void traversePostOrder() {
        traversePostOrder(root);
    }

    // Post-order
    private void traversePostOrder(Node root) {
        // handle empty tree
        if(root == null) {
            return;
        }
        traversePostOrder(root.leftChild);
        traversePostOrder(root.rightChild);
        System.out.println(root.value);
    }


    public int height() {
        return height(root);
    }

    // calculate the height of the tree
    private int height(Node root) {
        // base condition
        if(root == null) {
            return -1;
        }
        // check for a leaf node
        if(isLeaf(root)) {
            return 0;
        }
        // root plus the maximum height of left and right subtrees
        return 1 + Math.max(height(root.leftChild), height(root.rightChild));
    }

    private boolean isLeaf(Node node) {
        return node.leftChild == null && node.rightChild == null;
    }


    // min method used for a binary search tree -> time: O(log n)
    public int min() {
        // guard against null pointer exception when tree is empty
        if(root == null) {
            throw new IllegalStateException();
        }
        Tree.Node current = root;
        Tree.Node last = current;
        // current will hit null at some point which will break the loop
        while(current != null) {
            last = current;
            current = current.leftChild;
        }
        return last.value; // will reference the left-most child (min value)
    }

    // min method when dealing with a binary tree -> time: O(n)
    private int min(Node root) {
        // base condition: when we reach a leaf node we should return its value
        if(isLeaf(root)) {
            return root.value;
        }
        int left = min(root.leftChild);
        int right = min(root.rightChild);
        // return the minimum of three values (compare left child, right child, and root node)
        return Math.min(Math.min(left, right), root.value);
    }


    // compare two nodes for equality -> common interview question
    public boolean equals(Tree other) {
        // handle an empty tree
        if(other == null) {
            return false;
        }
        // a call to recursive private equals method
        return equals(root, other.root);
    }

    // compare two nodes for equality (recursive) -> common interview question
    private boolean equals(Node first, Node second) {
        // handle the case where both trees are null
        if(first == null && second == null) {
            return true;
        }
        // handle the case where neither trees are null -> pre-order traversal
        if(first != null && second != null) {
            return first.value == second.value
                    && equals(first.leftChild, second.leftChild) // recursive call
                    && equals(first.rightChild, second.rightChild); // recursive call
        }
        // handle the case where one tree is null while the other is not
        return false;
    }


    // a method that checks if a tree is binary search tree -> common interview question
    public boolean isBinarySearchTree() {
        // a call to recursive private isBinarySearchTree method
        return isBinarySearchTree(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    // a method that checks if a tree is binary search tree -> common interview question
    private boolean isBinarySearchTree(Node root, int min, int max) {
        // handle an empty tree
        if(root == null) {
            return true;
        }
        // check if value is in the correct range
        if(root.value < min || root.value > max) {
            return false; // is out of range
        }
        // look at the left and right subtrees recursively
        return isBinarySearchTree(root.leftChild, min, root.value - 1) // range should be from min to the value of the root minus one
                && isBinarySearchTree(root.rightChild, root.value + 1, max); // range should be from the value of the root plus one to max
    }


    // a method that retrieves Nodes at K Distance from the Root -> popular interview question
    public List<Integer> getNodesAtDistance(int distance) {
        // create a new list
        List<Integer> list = new ArrayList<>();
        // call private recursive method
        getNodesAtDistance(root, distance, list);

        return list;
    }

    // a method that retrieves Nodes at K Distance from the Root -> popular interview question
    private void getNodesAtDistance(Node root, int distance, List<Integer> list) {
        // handle an empty tree
        if(root == null) {
            return;
        }
        // if distance is zero we only care about the root node
        if(distance == 0) {
            list.add(root.value);
            return;
        }
        // recursively call the left and right subtrees
        getNodesAtDistance(root.leftChild, distance - 1, list);
        getNodesAtDistance(root.rightChild, distance - 1, list);
    }

    // level-order traversal method (breath first search)
    public void traverseLevelOrder() {
        // print nodes at each level based on the height of the tree
        for(int i = 0; i <= height(); i++) {
            // get the nodes at given distance from the root at the current level
            for(var value : getNodesAtDistance(i))
                System.out.println(value);
        }
    }


    // this method calls the size implementation method
    public int size() {
        return size(root);
    }

    // implements a method to calculate the size of a binary tree
    private int size(Node root) {
        // handle empty tree
        if(root == null) {
            return 0;
        }
        // check if node is a leaf
        if(isLeaf(root)) {
            return 1;
        }
        // check every node (every root and its children) recursively
        return 1 + size(root.leftChild) + size(root.rightChild);
    }


    // this method calls the countLeaves implementation method
    public int countLeaves() {
        return countLeaves(root);
    }

    // implements a method to count the number of leaves in a binary tree
    private int countLeaves(Node root) {
        // handle empty tree
        if(root == null) {
            return 0;
        }
        // check if node is a leaf
        if(isLeaf(root)) {
            return 1;
        }
        // check every child node recursively (similar to size() method except we do not include root nodes)
        return countLeaves(root.leftChild) + countLeaves(root.rightChild);
    }


    // this method calls the max implementation method
    public int max() {
        // handle empty tree
        if(root == null) {
            throw new IllegalStateException();
        }

        return max(root);
    }

    // implements a method to return the maximum value in a binary search tree using recursion
    private int max(Node root) {
        // if node does not have a right child, then its value will be the max value
        if(root.rightChild == null) {
            return root.value;
        }
        // otherwise, we keep search for the max value by visiting the right child(ren), recursively
        return max(root.rightChild);
    }


    // this method calls the contains implementation method
    public boolean contains(int value) {
        return contains(root, value);
    }

    // implements a method to check for the existence of a value in a binary tree using recursion.
    private boolean contains(Node root, int value) {
        // handle empty tree
        if(root == null) {
            return false;
        }
        // check for a match by comparing values (base condition)
        if(root.value == value) {
            return true;
        }
        // keep search each node (left and right children) recursively
        return contains(root.leftChild, value) || contains(root.rightChild, value);
    }


    // this method calls the areSibling implementation method
    public boolean areSibling(int first, int second) {
        return areSibling(root, first, second);
    }

    // implements a method to check to see if two values are siblings in a binary tree
    private boolean areSibling(Node root, int first, int second) {
        // handle empty tree
        if(root == null) {
            return false;
        }
        boolean areSibling = false;
        // if neither left nor right child is null
        if(root.leftChild != null && root.rightChild != null) {
            // compare values already stored in the tree to the values passed in (as arguments) for a possible match for either child and update boolean value accordingly
            areSibling = (root.leftChild.value == first && root.rightChild.value == second) || (root.rightChild.value == first && root.leftChild.value == second);
        }
        // visit every child node recursively
        return areSibling || areSibling(root.leftChild, first, second) || areSibling(root.rightChild, first, second);
    }


    // this method calls the getAncestors implementation method
    public List<Integer> getAncestors(int value) {
        // create a new list
        List<Integer> list = new ArrayList<>();
        // a call to recursive implementation method
        getAncestors(root, value, list);

        return list;
    }

    // implements a method to return the ancestors of a value in a List<Integer>
    private boolean getAncestors(Node root, int value, List<Integer> list) {
        // we should traverse the tree until we find the target value
        if(root == null) { // handle empty tree
            return false;
        }
        // if we find the target value,
        if(root.value == value) {
            // we return true without adding the current node to the list (otherwise, if we ask for ancestors of 5, 5 will be also added to the list)
            return true;
        }
        // if we find the target value in the left or right subtrees, that means the current node (root) is one of the ancestors, so we add it to the list
        if(getAncestors(root.leftChild, value, list) || getAncestors(root.rightChild, value, list)) {
            list.add(root.value);
            return true;
        }

        return false;
    }


    // this method calls the isBalanced implementation method
    public boolean isBalanced() {
        return isBalanced(root);
    }

    // implements a method to check if binary tree is balanced
    private boolean isBalanced(Node root) {
        // handle empty tree
        if(root == null) {
            return true;
        }
        // subtract the height of right child from the height of left child
        var balanceFactor = height(root.leftChild) - height(root.rightChild);
        // perform a mathematical comparison of each node using recursion to decide if tree is balanced or not
        return Math.abs(balanceFactor) <= 1 && isBalanced(root.leftChild) && isBalanced(root.rightChild);
    }


    // implements a method to check if binary tree is perfect
    public boolean isPerfect() {
        // check if tree size is equal to 2^(height+1)-1
        return size() == (Math.pow(2, height() + 1) - 1);
    }

}
