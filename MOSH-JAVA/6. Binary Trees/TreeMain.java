/*

           7
        /    \
       4      9
      / \    / \
     1   6  8   10


*/

public class TreeMain {

    public static void main(String[] args) {
        // create a new tree
        Tree tree = new Tree();
        // add some values to the tree
        tree.insert(7);
        tree.insert(4);
        tree.insert(9);
        tree.insert(1);
        tree.insert(6);
        tree.insert(8);
        tree.insert(10);
        // find the value 10 in the tree and print the result(s)
        System.out.println(tree.find(10)); // true
        System.out.println(tree.find(11)); // false
        System.out.println();
        // traverse the tree
        tree.traversePreOrder(); //
        System.out.println();
        // print the height of the tree
        System.out.println("height: " + tree.height()); // 2
        //
        System.out.println("min: " + tree.min()); // 1
        System.out.println();
        // create a new tree
        Tree tree2 = new Tree();
        // add same values to the second tree
        tree2.insert(7);
        tree2.insert(4);
        tree2.insert(9);
        tree2.insert(1);
        tree2.insert(6);
        tree2.insert(8);
        tree2.insert(10);
        // compare trees and print the result
        System.out.println(tree.equals(tree2)); // true
        // check if tree is a binary search tree or not
        System.out.println(tree.isBinarySearchTree()); // false
        System.out.println();
        // perform a level order traversal
        tree.traverseLevelOrder();
    }

}