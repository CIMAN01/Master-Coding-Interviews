/*

142. Linked List Cycle II (Medium)


Given the head of a linked list, return the node where the cycle begins. If there is no cycle, return null.

There is a cycle in a linked list if there is some node in the list that can be reached again by continuously following the next pointer.
Internally, pos is used to denote the index of the node that tail's next pointer is connected to (0-indexed).
It is -1 if there is no cycle. Note that pos is not passed as a parameter.

Do not modify the linked list.


Example 1:

Input: head = [3,2,0,-4], pos = 1
Output: tail connects to node index 1
Explanation: There is a cycle in the linked list, where tail connects to the second node.

Example 2:

Input: head = [1,2], pos = 0
Output: tail connects to node index 0
Explanation: There is a cycle in the linked list, where tail connects to the first node.

Example 3:

Input: head = [1], pos = -1
Output: no cycle
Explanation: There is no cycle in the linked list.


Constraints:

    The number of the nodes in the list is in the range [0, 104].
    -105 <= Node.val <= 105
    pos is -1 or a valid index in the linked-list.


Follow up: Can you solve it using O(1) (i.e. constant) memory?


*/


import java.util.HashSet;


public class LinkedListCycle2 {

    // inner class
    public static class Node {
        // inner class fields
        private int value;
        private Node next;
        // inner class constructor
        public Node(int value) {
            this.value = value;
            next = null;
        }
        // getter
        public int getValue() {
            return value;
        }
        // setter
        public void setValue(int value) {
            this.value = value;
        }
        // getter
        public Node getNext() {
            return next;
        }
        // setter
        public void setNext(Node next) {
            this.next = next;
        }
    }

    // outer class field (composition)
    private Node head; // head of list



    // Brute-force solution for detecting a cycle within a linked list -> time: O(n) | space: O(n)
    public static int detectCycle(Node head) {
        // use a HashSet to store each node during a list traversal
        HashSet<Node> seenNodes = new HashSet<>();
        // create a pointer node that will traverse the list
        Node current = head;
        // traverse the list as long as we do not come across a repeated node
        while(!seenNodes.contains(current)) { // HashSet initially empty
            // check if list is contains a cycle (if any node is equal to null the list does not contain a cycle)
            if(current.next == null) {
                return -1; // return null
            }
            // add current node to the HashSet
            seenNodes.add(current);
            current = current.next; // advance to the next node
        }
        // current now points to the repeated node
        return current.getValue()-1; // return the value of the repeated node and subtract one to account for a 0-based index
    }


    // Optimal solution for detecting a cycle within a linked list using Floyd’s Tortoise And Hare Algorithm -> time: O(n) | space: O(1)
    public static int detectCycleOp(Node head) {
        // edge case where list is empty
        if(head == null) {
            return -1; // return null
        }
        // use two pointers (tortoise/slow and hare/fast) to traverse the list
        Node hare = head; // fast pointer
        Node tortoise = head; // slow pointer
        // use do-while loop instead of while loop
        do {
            // move the tortoise and hare one step forward
            tortoise = tortoise.next;
            hare = hare.next;
            // if hare pointer is equal to null or pointer.next is equal to null (meaning there is a tail value)
            if (hare == null || hare.next == null) {
                return -1; // break out of entire method and return a -1 (null) as there is no cycle
            }
            else { // otherwise, advance the hare one more step
                hare = hare.next; // the hare will now have advanced two steps ahead of the tortoise
            }
        // continue advancing hare and tortoise until they overlap
        } while (tortoise != hare); // once the loop finishes, both the hare and tortoise are at the meeting point list node
        // create two new pointers that will traverse together to find the list node that represent the start of the cycle
        Node p1 = head, p2 = tortoise; // can also be p2 = hare as they point to the same value (both at meeting point node)
        // advance p1 and p2 together until they meet/overlap
        while(p1 != p2) {
            p1 = p1.next;
            p2 = p2.next;
        }
        // once p1 and p2 have met/overlapped we have the node that represent the node of the cycle, so return either p1 or p2
        return p1.getValue()-1; // return the value of the p1 node minus 1 to account for a zero-based index
    }


    //  Floyd’s Tortoise And Hare (Cycle-finding) Algorithm -> time: O(n) | space: O(1)
    public static boolean hasLoop(Node head) {
        // use two pointers (slow and fast) to traverse the list
        Node slow = head;
        Node fast = head;
        // if the Hare at any point lands on list node where the next value is null or the Hare itself points to null, then there is no cycle
        while(fast != null && fast.next != null) {
            // move the slow pointer one step forward and the fast pointer two steps forward
            slow = slow.next;
            fast = fast.next.next;
            // if there’s a loop, at some point, the fast pointer will meet the slow pointer and overtake it
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }


    // driver
    public static void main(String[] args) {
        ////////////////////// input //////////////////////
        // Example 1
        LinkedListCycle2 linkedList = new LinkedListCycle2();

        Node example1_n1 = new Node(3);
        Node example1_n2 = new Node(2);
        Node example1_n3 = new Node(0);
        Node example1_n4 = new Node(-4);

        linkedList.head = example1_n1;

        example1_n1.setNext(example1_n2);
        example1_n2.setNext(example1_n3);
        example1_n3.setNext(example1_n4);
        example1_n4.setNext(example1_n2);

        // Example 2
        LinkedListCycle2 linkedList2 = new LinkedListCycle2();

        Node example2_n1 = new Node(1);
        Node example2_n2 = new Node(2);

        example2_n1.setNext(example2_n2);
        example2_n2.setNext(example2_n1);

        linkedList2.head = example2_n1;

        // Example 3
        LinkedListCycle2 linkedList3 = new LinkedListCycle2();

        Node example3_n1 = new Node(1);

        example3_n1.setNext(null);

        linkedList3.head = example3_n1;

        ////////////////////// output  //////////////////////
        // Example 1
        System.out.println("input: head = [3,2,0,-4]");
//        System.out.println("output: " + "pos = " + detectCycle(linkedList.head)); // pos = 1
        System.out.println("output: " + "pos = " + detectCycleOp(linkedList.head)); // pos = 1
        System.out.println();
        // Example 2
        System.out.println("input: head = [1,2]");
//        System.out.println("output: " + "pos = " + detectCycle(linkedList2.head)); // pos = 0
        System.out.println("output: " + "pos = " + detectCycleOp(linkedList2.head)); // pos = 0
        System.out.println();
        // Example 3
        System.out.println("input: head = [1]");
//        System.out.println("output: " + "pos = " + detectCycle(linkedList3.head)); // pos = -1
        System.out.println("output: " + "pos = " + detectCycleOp(linkedList3.head)); // pos = -1
        System.out.println();

        // boolean check for chosen linkedList
        if(hasLoop(linkedList.head)) {
            System.out.println("Loop is present in list");
        }
        else {
            System.out.println("No Loop present in list");
        }
    }

}
