import java.util.NoSuchElementException;

public class LinkedList {

    // private inner class
    private class Node {
        // inner class fields
        private int value;
        private Node next;
        // inner class constructor
        public Node(int value) {
            this.value = value;
        }
    }

    // fields
    private Node first; // head
    private Node last; // tail
    private int size;


    private boolean isEmpty() {
        return first == null;
    }


    public void addLast(int item) {
        Node node = new Node(item); // a node must have a value, hence the constructor

        if(isEmpty()) {
            first = last = node;
        }
        else {
            last.next = node;
            last = node;
        }

        size++;
    }


    public void addFirst(int item) {
        Node node = new Node(item);

        if(isEmpty()) {
            first = last = node;
        }
        else {
            node.next = first;
            first = node;
        }

        size++;
    }


    public int indexOf(int item) {
        int index = 0;
        Node current = first;

        while(current != null) {
            if(current.value == item) {
                return index;
            }
            current = current.next;
            index++;
        }
        return -1;
    }


    public boolean contains(int item) {
        return indexOf(item) != -1;
    }


    public void removeFirst() {
        // handle an empty list
        if(isEmpty()) {
            throw new NoSuchElementException();
        }
        // handle when list contains only one item
        if(first == last) {
            first = last = null;
        }
        else {
            Node second = first.next;
            first.next = null;
            first = second;
        }

        size--;
    }


    public void removeLast() {
        // handle an empty list
        if(isEmpty()) {
            throw new NoSuchElementException();
        }
        // handle when list contains only one item
        if(first == last) {
            first = last = null;
        }
        else {
            Node previous = getPrevious(last); // last.previous
            last = previous; // shrink the list
            last.next = null;
        }

        size--;
    }


    private Node getPrevious(Node node) {
        Node current = first;

        while(current != null) {
            // the condition needed to get the previous node (node.previous)
            if(current.next == node) {
                return current;
            }
            current = current.next;
        }
        return null;
    }

    // O(1)
    public int size() {
        return size;
    }

    public int[] toArray() {
        int index = 0;
        int[] array = new int[size];
        Node current = first;

        while(current != null) {
            array[index++] = current.value;
            current = current.next;
        }

        return array;
    }

    // Exercise: Reversing a Linked List
    public void reverse() {
        // handle an empty list
        if(isEmpty()) {
            return;
        }
        // store original references
        Node previous = first; // first node
        Node current = first.next; // second node
        // traverse the list (reverse the direction of the links)
        while(current != null) {
            Node next = current.next;
            // make current node point to previous node
            current.next = previous;
            // move previous and current forward
            previous = current;
            current = next;
        }
        // lastly, update the private first/last fields to reflect the new list
        last = first;
        last.next = null;
        first = previous; // after the traversal ends previous will point to the last item/node in the list
    }


    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////// // Exercises //////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    // Find the Kth node from the end of a linked list in one pass

    // [10 -> 20 -> 30 -> 40 -> 50]
    //		         *	         *
    // K = 1 (50)
    // K = 2 (40)
    // K = 3 (30)	(distance = 2 or k-1)

    public int getKthFromTheEnd(int k) {
        // handle edge case (only if size is known) where k is larger than the size of the list:
        if(k > size) {
            throw new IllegalArgumentException();
        }
        // handle edge case where list is empty
        if(isEmpty()) {
            throw new IllegalStateException();
        }
        // use two pointers (the technique used to solve this problem)
        Node pointer1 = first;
        Node pointer2 = first;
        // move second pointer a distance of k-1 away from first pointer
        for (int i = 0; i < k-1; i++) {
            pointer2 = pointer2.next;
            // handle edge case (if size is unknown) where k is larger than the size of the list:
            if(pointer2 == null) {
                throw new IllegalArgumentException();
            }
        }
        // move both pointers until the second pointer hits the end of the list
        while(pointer2 != last) {
            pointer1 = pointer1.next;
            pointer2 = pointer2.next;
        }

        return pointer1.value;
    }


    //  Find the middle of a linked list in one pass. If the list has an even number of nodes, there would be two middle nodes.
    //  (Note: Assume that you don’t know the size of the list ahead of time.)

    public void printMiddle() {
        // handle empty list
        if (isEmpty()) {
            throw new IllegalStateException();
        }

        Node a = first; // first pointer
        Node b = first; // second pointer

        while (b != last && b.next != last) {
            b = b.next.next;
            a = a.next;
        }
        // if the list has an odd number of nodes (at the end of the last iteration) the second pointer will reference the tail node. Otherwise, it’ll be null.
        if (b == last) {
            System.out.println(a.value); // odd number of nodes
        }
        else { // if (b == null)
            System.out.println(a.value + ", " + a.next.value); // even number of nodes
        }
    }


    //  Check to see if a linked list has a loop.

    //  Hint: use two pointers (slow and fast) to traverse the list. Move the slow pointer one step forward and the fast pointer two steps forward.
    //  If there’s a loop, at some point, the fast pointer will meet the slow pointer and overtake it. Draw this on a paper and see it for yourself.
    //  This algorithm is called Floyd’s Cycle-finding Algorithm.

    public boolean hasLoop() {
        Node slow = first;
        Node fast = first;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                return true;
            }
        }

        return false;
    }

    public static LinkedList createWithLoop() {
        LinkedList list = new LinkedList();

        list.addLast(10);
        list.addLast(20);
        list.addLast(30);

        // get a reference to 30
        Node node = list.last;

        list.addLast(40);
        list.addLast(50);

        // create the loop
        list.last.next = node;

        return list;
    }


}
