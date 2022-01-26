import java.util.Arrays;


// Build and Implement a Stack Using an Array
public class Stack {

    // private fields
    private int[] items = new int[5];
    private int count;

    // a method that adds an item to the stack
    public void push(int item) {
        // handle error (if stack is full)
        if(count == items.length) {
            throw new StackOverflowError();
        }
        items[count++] = item;
    }

    // a method that pops an item from the stack
    public int pop() {
        // handle error (if stack is empty)
        if(count == 0) {
            throw new IllegalStateException();
        }
        return items[--count];
    }

    // a method that peeks at the top of the stack
    public int peek() {
        // handle error (if stack is empty)
        if(count == 0) {
            throw new IllegalStateException();
        }
        return items[count-1];
    }

    // a method that checks if the stack is empty or not
    public boolean isEmpty() {
        return count == 0;
    }

    @Override
    public String toString() {
        int[] content = Arrays.copyOfRange(items, 0, count);
        return Arrays.toString(content);
    }


    // driver
    public static void main(String[] args) {
        Stack stack = new Stack();
        stack.push(10);
        stack.push(20);
        stack.push(30);
//        stack.pop();
        // make a call toString method on the object
//        System.out.println(stack);
        System.out.println(stack.peek());
    }

}
