import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;


public class ReverseAQueue {

    // a method that reverses a queue
    public static void reverse(Queue<Integer> queue) {
        // create a stack
        Stack<Integer> stack = new Stack<>();
        // remove items from the queue and add them to the stack
        while(!queue.isEmpty()) {
            stack.push(queue.remove());
        }
        // remove items from the stack and add them to the queue
        while(!stack.isEmpty()){
            queue.add(stack.pop());
        }
    }

    // driver
    public static void main(String[] args) {
        // Queue is an interface and so must declare a new instance from ArrayDeque class
        Queue<Integer> queue = new ArrayDeque<>();
        // add some values to the queue
        queue.add(10);
        queue.add(20);
        queue.add(30);
        // print the queue
        System.out.println(queue);
        // reverse the queue
        reverse(queue);
        // print the reversed queue
        System.out.println(queue);
    }

}
