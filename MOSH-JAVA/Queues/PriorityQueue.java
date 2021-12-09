import java.util.Arrays;


public class PriorityQueue {
    // fields
    private int[] items = new int[5];
    private int count;

    // a helper method that shifts items and returns the proper position (index) for an insertion
    private int shiftItemsToInsert(int item) {
        // shifting items
        int i; // declared outside loop
        for(i = count - 1; i >= 0; i--) {
            if(items[i] > item) { // if the current item is greater than the value to be inserted
                items[i + 1] = items[i]; // shift items to the right
            }
            else { // found the position of insertion (done with the shifting)
                break;
            }
        }
        return i + 1; // i + 1 because 'items[i+1] = item' has been changed to 'items[i] = item'
    }

    // O(n)
    public void add(int item) {
        // handle a full queue
        if(isFull()) {
            throw new IllegalStateException();
        }
        int i = shiftItemsToInsert(item); // the index (position) to add the item
        items[i] = item; // add the item to the array at the given index
        count++;
    }

    // O(1)
    public int remove() {
        // handle an empty queue
        if(isEmpty()) {
            throw new IllegalStateException();
        }
        return items[--count]; // decrement first to account for zero-based indexing of arrays
    }

    public boolean isFull() {
        return count == items.length;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    @Override
    public String toString() {
        return Arrays.toString(items);
    }


    // driver
    public static void main(String[] args) {
        // create a new queue object
        PriorityQueue queue = new PriorityQueue();
        // add some value
        queue.add(5);
        queue.add(3);
        queue.add(6);
        queue.add(1);
        queue.add(4);
        // print the queue
        System.out.println(queue);
        // remove each item from the queue while the queue is not empty
        while(!queue.isEmpty()) {
            System.out.println(queue.remove());
        }
    }

}
