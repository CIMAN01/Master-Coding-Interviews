/*

1- Given an integer K and a queue of integers, write code to reverse the order of the first K elements of the queue.

Input: Q = [10, 20, 30, 40, 50], K = 3

Output: Q = [30, 20, 10, 40, 50]

*/

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;


public class QueueReverser {

    // a method that reverses the order of the first K elements of the queue
    public static void reverse(Queue<Integer> queue, int k) {
        // handle edge cases where k is negative or bigger than the size of the queue
        if(k < 0 || k > queue.size()) {
            throw new IllegalArgumentException();
        }
        // create a new stack
        Stack<Integer> stack = new Stack<>();
        // dequeue the first K elements from the queue and push them onto the stack
        for(int i = 0; i < k; i++) {
            stack.push(queue.remove());
        }
        // enqueue the content of the stack at the back of the queue
        while(!stack.empty()) {
            queue.add(stack.pop());
        }
        // add the remaining items in the queue (items after the first K elements) to
        // the back of the queue and remove them from the beginning of the queue
        for(int i = 0; i < queue.size()-k; i++) {
            queue.add(queue.remove());
        }
    }

    // driver
    public static void main(String[] args) {
        // create a new queue
        Queue<Integer> queue = new ArrayDeque<>();
        // add some values
        queue.add(10);
        queue.add(20);
        queue.add(30);
        queue.add(40);
        queue.add(50);
        // print the queue
        System.out.println(queue);
        // reverse the queue
        reverse(queue, 3);
        // print the reversed queue
        System.out.println(queue); // [30, 20, 10, 40, 50]
    }

}