import java.util.Stack;

public class QueueWithTwoStacks {
    // create two stacks
    private Stack<Integer> stack1 = new Stack<>();
    private Stack<Integer> stack2 = new Stack<>();

    // a method that adds an item at the back of the queue -> O(1) operation
    public void enqueue(int item) {
       stack1.push(item);
    }

    // refactored method
    private void moveStack1ToStack2() {
        // only add items to second stack if it's empty
        if(stack2.isEmpty()) {
            while(!stack1.isEmpty()) {
                stack2.push(stack1.pop()); // add items from first to second stack (the order will be reversed)
            }
        }
    }

    // a method that removes an item at the front of the queue -> O(n) operation
    public int dequeue() {
        // handle empty stacks
        if(isEmpty()) {
            throw new IllegalStateException();
        }
        // move items from first stack to second stack
        moveStack1ToStack2();
        // peek at the top of the second stack (remove item from the front of the queue)
        return stack2.pop();
    }

    // a method that removes an item at the front of the queue -> O(n) operation
    public int peek() {
        // handle empty stacks
        if(isEmpty()) {
            throw new IllegalStateException();
        }
        // move items from first stack to second stack
        moveStack1ToStack2();
        // peek at the top of the second stack (front of the queue)
        return stack2.peek();
    }

    // checks if stacks are empty
    public boolean isEmpty() {
        return stack1.isEmpty() && stack2.isEmpty();
    }


    // driver
    public static void main(String[] args) {
        QueueWithTwoStacks queue = new QueueWithTwoStacks();
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);
        int first = queue.dequeue();
        System.out.println(first);
    }

}
