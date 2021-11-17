/*

430. Flatten a Multilevel Doubly Linked List (Medium)


You are given a doubly linked list, which contains nodes that have a next pointer, a previous pointer, and an additional child pointer.
This child pointer may or may not point to a separate doubly linked list, also containing these special nodes.
These child lists may have one or more children of their own, and so on, to produce a multilevel data structure as shown in the example below.

Given the head of the first level of the list, flatten the list so that all the nodes appear in a single-level, doubly linked list.
Let curr be a node with a child list.
The nodes in the child list should appear after curr and before curr.next in the flattened list.

Return the head of the flattened list. The nodes in the list must have all of their child pointers set to null.


Example 1:

Input: head = [1,2,3,4,5,6,null,null,null,7,8,9,10,null,null,11,12]
Output: [1,2,3,7,8,11,12,9,10,4,5,6]


Example 2:

Input: head = [1,2,null,3]
Output: [1,3,2]


Example 3:

Input: head = []
Output: []
Explanation: There could be empty list in the input.


Constraints:

    The number of Nodes will not exceed 1000.
    1 <= Node.val <= 105


How the multilevel linked list is represented in test cases:

We use the multilevel linked list from Example 1 above:

 1---2---3---4---5---6--NULL
         |
         7---8---9---10--NULL
             |
             11--12--NULL

The serialization of each level is as follows:

[1,2,3,4,5,6,null]
[7,8,9,10,null]
[11,12,null]

To serialize all levels together, we will add nulls in each level to signify no node connects to the upper node of the previous level. The serialization becomes:

[1,    2,    3, 4, 5, 6, null]
             |
[null, null, 7,    8, 9, 10, null]
                   |
[            null, 11, 12, null]

Merging the serialization of each level and removing trailing nulls we obtain:

[1,2,3,4,5,6,null,null,null,7,8,9,10,null,null,11,12]


*/



public class FlattenMultilevelDoublyLinkedList {

    // inner Node class
    private static class Node {
        // fields
        private int value;
        private FlattenMultilevelDoublyLinkedList.Node previous;
        private FlattenMultilevelDoublyLinkedList.Node next;
        private FlattenMultilevelDoublyLinkedList.Node child;
        // constructor
        public Node(int value) {
            this.value = value;
        }
        // getter
        public int getValue() {
            return value;
        }
        // getter
        public Node getNext() {
            return next;
        }
    }


    // outer class field (composition)
    private FlattenMultilevelDoublyLinkedList.Node head;

    // outer class constructor
    public FlattenMultilevelDoublyLinkedList(FlattenMultilevelDoublyLinkedList.Node head) {
        this.head = head;
    }


    // a utility method to create a linked list with n nodes.
    public static Node createList(int[] arr, int n) {
        // the data of nodes is taken from arr[]
        Node node = null, p = null;
        // all child pointers are set as null
        for (int i = 0; i < n; ++i) {
            if (node == null) {
                node = p = new Node(arr[i]);
            } else {
                p.next = new Node(arr[i]);
                p = p.next;
            }
            p.next = p.child = null;
        }
        return node;
    }


    // create a multi-level linked list
    public Node createList() {
        // create one array for each linked list per level
        int[] arr1 = new int[]{1, 2, 3, 4, 5, 6}; // [1,2,3,4,5,6,null] <- [1,2,3,4,5,6,null,null,null,7,8,9,10,null,null,11,12]
        int[] arr2 = new int[]{7, 8, 9, 10}; //  [7,8,9,10,null] <-
        int[] arr3 = new int[]{11, 12}; //  [11,12,null] <-
        // create 3 linked lists
        Node head1 = createList(arr1, arr1.length);
        Node head2 = createList(arr2, arr2.length);
        Node head3 = createList(arr3, arr3.length);
        // modify child pointers to create the list shown above
        head1.next.next.child = head2;
        head2.next.child = head3;
        // return head pointer of first linked list (note that all nodes are reachable from head1)
        return head1;
    }


    // print a linked list
    public void print(FlattenMultilevelDoublyLinkedList.Node head) {
        // create a new node that starts at the head
        FlattenMultilevelDoublyLinkedList.Node current = head;
        System.out.print("[");
        // traverse the entire list and print each node/item
        while (current != null) {
            System.out.print(current.getValue());
            current = current.getNext(); // move to the next node
            if(current != null) {
                System.out.print(", ");
            }
        }
        System.out.print("]");
    }



    // flatten a multi-level doubly linked list -> Time: O(n) | Space: O(1)
    public Node flatten(Node head) {
        // edge case when list is empty
        if(head == null) {
            return null;
        }
        // create a current node that acts as a pointer for the top-level linked list
        Node current = head; // current node (which also references current.child node) gives access to all 4 important nodes needed for a merge
        // traverse the entire main level linked list
        while(current != null) {
            // check if current node has a child
            if(current.child == null) { // if it does not have a child
                // advance current node forward
                current = current.next;
            }
            else { // if current node has a child, begin merging process
                // create a child node that acts as a pointer for the sub linked list
                Node tail = current.child; // current.child references the head of the sub linked list
                // traverse the child or sub-level linked list until it reaches the last node (tail node)
                while(tail.next != null) {
                    // tail pointer will end up pointing to the actual tail of the child linked list when loop ends
                    tail = tail.next; // advance forward
                }
                // merge the tail values first before merging the head values (the order matters here to avoid losing pointer references):
                // 1. merge the tail values
                tail.next = current.next; // merge the nodes
                // perform a conditional check before settings the previous value:
                // edge case where a node that has a child is at the very end of the top-level linked list (current.next cannot be null)
                if(tail.next != null) { // if(current.next != null)
                    // the node that tail.next points to should point back (tail.next.previous) to the tail
                    tail.next.previous = tail; // merge the nodes
                }
                // 2. merge the head values
                current.next = current.child; // current.next needs to point to the child (which is the head of the child linked list)
                current.next.previous = current; // current.next.previous needs to point back to the current node
                // current node's reference to child no longer needed -> set it to null
                current.child = null;
            }
        }
        return head; // the head of the entire doubly linked list
    }


    // driver
    public static void main(String[] args) {
        // create a new linked list with a head node
        FlattenMultilevelDoublyLinkedList.Node head = new FlattenMultilevelDoublyLinkedList.Node(1);
        FlattenMultilevelDoublyLinkedList linkedList = new FlattenMultilevelDoublyLinkedList(head);
        // create a multi-level doubly linked list and update the head
        head = linkedList.createList();
        // flatten the doubly linked list
        linkedList.flatten(head);
        System.out.println();
        // print the results
        linkedList.print(head); // [1, 2, 3, 7, 8, 11, 12, 9, 10, 4, 5, 6]
        System.out.println();
    }

}
