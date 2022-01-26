/*

Build a queue using a linked list from scratch.

Implement the following operations and calculate their runtime complexities:
    - enqueue
    - dequeue
    - peek
    - size
    - isEmpty

*/


import java.util.ArrayList;


public class LinkedListQueue {
    // inner class
    private class Node {
        // fields
        private Node next;
        private int value;
        // constructor
        public Node(int value) {
            this.value = value;
        }
    }

    // fields
    private Node head;
    private Node tail;
    private int count;

    // O(1)
    public void enqueue(int item) {
        // create a new node
        Node node = new Node(item);
        // if empty the new node will be both the head and the tail
        if (isEmpty()) {
            head = tail = node;
        }
        else { // else, point the last node to the new node and then make it the new tail
            tail.next = node;
            tail = node;
        }
        count++; // increase the size of the list (queue)
    }

    // O(1)
    public int dequeue() {
        // handle an empty list (queue)
        if (isEmpty()) {
            throw new IllegalStateException();
        }
        int value;
        // if only one item in the list
        if(head == tail) {
            value = head.value; // store the value
            head = tail = null; // remove
        }
        else {
            value = head.value; // store the value
            Node second = head.next; // store the next node
            head.next = null; // unlink the head (remove node from list)
            head = second; // set previous next node to be the new head
        }
        count--; // decrease the size of the list (queue)
        return value;
    }

    // O(1)
    public int peek() {
        // handle an empty list (queue)
        if(isEmpty()) {
            throw new IllegalStateException();
        }
        return head.value;
    }

    // O(1)
    public int size() {
        return count;
    }

    // O(1)
    public boolean isEmpty() {
        return head == null;
    }

    // O(n)
    public String toString() {
        // create a new array list
        ArrayList<Integer> list = new ArrayList<>();
        // create a node that points to the beginning of the linked list
        Node current = head;
        // traverse the linked list and add each value from it to the array list
        while(current != null) {
            list.add(current.value);
            current = current.next;
        }
        return list.toString();
    }

    // driver
    public static void main(String[] args) {
        // create a new queue
        LinkedListQueue queue = new LinkedListQueue();
        // add some values
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);
        queue.enqueue(40);
        queue.enqueue(50);
        // print the queue to the console
        System.out.println("queue: " + queue);
        // remove the first item from the queue
        queue.dequeue();
        // peek at the first item in the queue and print it to the console
        System.out.println("first item in the queue (peek): " + queue.peek());
        // print the size of the queue
        System.out.println("size of queue: "+ queue.size());
    }

}
