import java.util.Arrays;


public class ArrayQueue {
    // fields
    private int[] items;
    private int back;
    private int front;
    private int count;
    // constructor
    public ArrayQueue(int capacity) {
        items = new int[capacity];
    }

    // a method that adds an item at the back of the queue (using a circular array)
    public void enqueue(int item) {
        // check if queue is full
        if(count == items.length) {
            throw new IllegalStateException();
        }
        items[back] = item;
        back = (back + 1) % items.length; // make array circular
        count++;
    }

    // a method that removes an item at the front of the queue (using a circular array)
    public int dequeue() {
        int item = items[front];
        items[front] = 0;
        front = (front +1) % items.length; // make array circular
        count--;
        return item;
    }

    public int peek() {
        if(isEmpty()) {
            throw new IllegalStateException();
        }
        return items[front];
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public boolean isFull() {
        return count == items.length;
    }

    @Override
    public String toString() {
        return Arrays.toString(items);
    }

    // driver
    public static void main(String[] args) {
        // Queue is an interface and so must declare a new instance from ArrayDeque class
        ArrayQueue queue = new ArrayQueue(5);
        // add some values to the queue
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);
        System.out.println("peek at the front of the queue: " + queue.peek());
        // remove the first value from the queue and display it
        int front = queue.dequeue();
        System.out.println(front);
        // add more items
        queue.enqueue(40);
        queue.enqueue(50);
        queue.enqueue(60);
//        queue.enqueue(70); // exception
        // print the queue
        System.out.println(queue);
        System.out.println("is the queue full? " + queue.isFull());
    }

}
