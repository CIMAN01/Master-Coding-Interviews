// Design a stack that supports push, pop and retrieving the minimum value in constant time.
//        For example, we populate our stack with [5, 2, 10, 1] (from left to right).
//        stack.min() // 1
//        stack.pop()
//        stack.min() // 2


import java.util.Arrays;

// Solution: We need two stacks to implement a min stack. One stack holds the values, the other stack (called minStack) holds the minimums.
public class MinStack {
    // fields
    private Stack stack = new Stack();
    private Stack minStack = new Stack();

    public void push(int item) {
        stack.push(item);

        if(minStack.isEmpty()) {
            minStack.push(item);
        }
        else if(item < minStack.peek()) {
            minStack.push(item);
        }
    }

    public int pop() {

        if (stack.isEmpty()) {
            throw new IllegalStateException();
        }

        int top = stack.pop();

        if(minStack.peek() == top) {
            minStack.pop();
        }

        return top;
    }

    public int min() {
        return minStack.peek();
    }


    // driver
    public static void main(String[] args) {

        MinStack stack = new MinStack();

        stack.push(5);
        stack.push(2);
        stack.push(10);
        stack.push(1);

        System.out.println(stack.min()); // 1
        stack.pop();
        System.out.println(stack.min()); // 2

    }
}
