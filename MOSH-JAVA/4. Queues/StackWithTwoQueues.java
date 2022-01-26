/*

Build a stack using two queues.

Implement the following operations and calculate their runtime complexities:
    - push
    - pop
    - peek
    - size
    - isEmpty

*/



import java.util.ArrayDeque;
import java.util.Queue;

/*

We always use queue1 for pushing new items.

When popping an item, we move all the items except the last one from queue1 to queue2.

Q1 [10, 20, 30, 40] => Q1 [40]
Q2 []               => Q2 [10, 20, 30]

Now the item to be popped is in Q1. We remove and return that as the item on top of the stack.

Q1 []
Q2 [10, 20, 30]

We should swap the queues, so we can repeat this algorithm next time we need to pop the stack.

Q1 [10, 20, 30]
Q2 []  (ready for moving items)


*/

public class StackWithTwoQueues {
    // fields
    private Queue<Integer> queue1 = new ArrayDeque<>();
    private Queue<Integer> queue2 = new ArrayDeque<>();
    private int top;

    // helper method to move items from queue2 back to queue1
    private void swapQueues() {
        Queue<Integer> temp = queue1;
        queue1 = queue2;
        queue2 = temp;
    }

    // O(1)
    public void push(int item) {
        queue1.add(item);
        top = item;
    }

    // O(n)
    public int pop() {
        // handle an empty queue
        if(isEmpty()) {
            throw new IllegalStateException();
        }
        // we move all the items except the last one from queue1 to queue2
        while(queue1.size() > 1) {
            top = queue1.remove();
            queue2.add(top);
        }
        // swap the queues
        swapQueues();
        // queue2 now has the item from queue1 that is the top of the stack which we want to pop
        return queue2.remove();
    }

    // O(1)
    public boolean isEmpty() {
        return queue1.isEmpty();
    }

    // O(1)
    public int size() {
        return queue1.size();
    }

    // O(1)
    public int peek() {
        // handle an empty queue
        if (isEmpty()) {
            throw new IllegalStateException();
        }
        return top;
    }

    @Override
    public String toString() {
        return queue1.toString();
    }

    // driver
    public static void main(String[] args) {
        // create a new stack
        StackWithTwoQueues stack = new StackWithTwoQueues();
        // add some values
        stack.push(10);
        stack.push(20);
        stack.push(30);
        stack.push(40);
        // pop the top of the stack and print it to the console
        System.out.println("top: " + stack.pop());
        // peek at the top of the stack and print it to console
        System.out.println("peek: " + stack.peek());
        // print the size of the stack
        System.out.println("size: " + stack.size());
    }

}
