/*

92. Reverse Linked List II (Medium)

Given the head of a singly linked list and two integers left and right where left <= right,
reverse the nodes of the list from position left to position right,
and return the reversed list.


Example 1:

Input: head = [1,2,3,4,5], left = 2, right = 4
Output: [1,4,3,2,5]

Example 2:

Input: head = [5], left = 1, right = 1
Output: [5]


Constraints:

    The number of nodes in the list is n.
    1 <= n <= 500
    -500 <= Node.val <= 500
    1 <= left <= right <= n


Follow up: Could you do it in one pass?


*/


class ReverseLinkedList2 {

    // inner Node class
    private static class Node {
        // fields
        private int value;
        private Node next;
        // constructor
        public Node(int value) {
            this.value = value;
        }
        // getter
        public int value() {
            return value;
        }
        // getter
        public Node next() {
            return next;
        }
    }

    // outer class field (composition)
    private Node head;

    // outer class constructor
    public ReverseLinkedList2(Node head) {
        this.head = head;
    }


    // add an element to linked list
    public void add(Node node) {
        // create a new node that starts at the head
        Node current = head;
        // traverse the entire list
        while (current != null) {
            if (current.next == null) {
                current.next = node; // add item to list at next available node
                break;
            }
            current = current.next; // move to the next node
        }
    }


    // print a linked list
    public void print(Node head) {
        // create a new node that starts at the head
        Node current = head;
        System.out.print("[");
        // traverse the entire list and print each node/item
        while (current != null) {
            System.out.print(current.value());
            current = current.next(); // move to the next node
            if(current != null) {
                System.out.print(", ");
            }
        }
        System.out.print("]");
    }


    // reverse a linked list
    public Node reverseList(Node head) {
        Node previous = null;
        Node current = head;
        // while we haven't reached the tail
        while(current != null) {
            // create a temp next that points to the next node
            Node next = current.next;
            // rearrange the links in the chain
            current.next = previous;
            previous = current;
            current = next;
        }
        // previous is now the new head
        return previous;
    }


    // reverse an inner portion of a linked list -> Time: O(n) | Space: O(1)
    public Node reverseBetween(Node head, int left, int right) {
        // initialize variables needed to keep track of list at each node iteration
        int currentPosition = 1; // start with the same value as head
        Node currentNode = head; // starting position will be at head
        Node start = head; // represents the last list node (left - 1) before the start of reversing the link list section
        // determine where in the list to start reversing the inner linked list (determine left-1 for start)
        while(currentPosition < left) {
            start = currentNode; // get the value that's equal to the node right before the start of the reversal of the inner linked list (left-1)
            currentNode = currentNode.next; // after loop, this will be positioned one node after start
            currentPosition++; // after loop, this will also be positioned one node after start (or equal to left)
        }
        // initialize other values
        Node tail = currentNode; // store tail value (which needs to be equal to the first value that is at the starting node of the reversal)
        // newList represents the list so far (initially set to null)
        Node newList = null; // newList is always the head of the actual full list that's reversed
        // portion of the method that reverses the inner linked list
        // reverse the inner linked list while currentPosition is within the boundaries of left and right
        while(currentPosition >= left && currentPosition <= right) {
            Node next = currentNode.next; // cache next node
            currentNode.next = newList; // currentNode.next = previous;
            // currentNode represents the head of the list built so far
            newList = currentNode; // previous = currentNode;
            currentNode = next; // currentNode becomes the next node cached above
            currentPosition++;  // currentPosition determines start/finish of reversal
        }
        // start now needs to point to the head of the new inner linked list (start positioned at left-1 needs to point to newList positioned at left)
        start.next = newList; // newList is the head of the linked list that's reversed
        // the tail (positioned at right) will need to point to the currentNode that's currently positioned at right + 1
        tail.next = currentNode;
        // conditional check:
        // edge case where the head value actually ends up being the tail value of the reversed linked list (when left = 1)
        if(left > 1) {
            return head; // head always stays the same in memory and will always be pointing to the first node in the original linked list
        }
        else {
            return newList; // the actual head of the partially reversed linked list
        }
    }


    // driver
    public static void main(String[] args) {
        // create a new head node
        ReverseLinkedList2.Node head = new ReverseLinkedList2.Node(1);
//        ReverseLinkedList2.Node head = new ReverseLinkedList2.Node(5);
        // create a new linked list (with head as the only node)
        ReverseLinkedList2 linkedList = new ReverseLinkedList2(head);
        // add a new node into linked list
        linkedList.add(new ReverseLinkedList2.Node(2));
        linkedList.add(new ReverseLinkedList2.Node(3));
        linkedList.add(new ReverseLinkedList2.Node(4));
        linkedList.add(new ReverseLinkedList2.Node(5));
        System.out.println();
        // print a linked list
        System.out.print("Original LinkedList: ");
        linkedList.print(head);
        System.out.println();
        System.out.println();
        // reverse the linked list and print it to the console
//        System.out.println("LinkedList reversed: ");
//        linkedList.print(linkedList.reverseList(head));
        // reverse the linked list between left and right and print it to the console
        System.out.print("LinkedList reversed (between 2 and 4): ");
        linkedList.print(linkedList.reverseBetween(head, 2, 4)); // [1, 4, 3, 2, 5]
//        System.out.print("LinkedList reversed (between 1 and 1): ");
//        linkedList.print(linkedList.reverseBetween(head, 1, 1)); // [5]
        System.out.println();
    }

}


