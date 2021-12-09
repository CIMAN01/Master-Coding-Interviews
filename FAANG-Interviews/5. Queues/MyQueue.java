/*

232. Implement Queue using Stacks (Easy0

Implement a first in first out (FIFO) queue using only two stacks. The implemented queue should support all the functions of a normal queue (push, peek, pop, and empty).

Implement the MyQueue class:

    void push(int x) Pushes element x to the back of the queue.
    int pop() Removes the element from the front of the queue and returns it.
    int peek() Returns the element at the front of the queue.
    boolean empty() Returns true if the queue is empty, false otherwise.

Notes:

    You must use only standard operations of a stack, which means only push to top, peek/pop from top, size, and is empty operations are valid.
    Depending on your language, the stack may not be supported natively. You may simulate a stack using a list or deque (double-ended queue)
    as long as you use only a stack's standard operations.


Example 1:

Input:
["MyQueue", "push", "push", "peek", "pop", "empty"]
[[], [1], [2], [], [], []]

Output:
[null, null, null, 1, 1, false]

Explanation:

MyQueue myQueue = new MyQueue();
myQueue.push(1); // queue is: [1]
myQueue.push(2); // queue is: [1, 2] (leftmost is front of the queue)
myQueue.peek(); // return 1
myQueue.pop(); // return 1, queue is [2]
myQueue.empty(); // return false


Constraints:

    1 <= x <= 9
    At most 100 calls will be made to push, pop, peek, and empty.
    All the calls to pop and peek are valid.

*/


import java.util.Stack;

// class
class MyQueue {
    // fields
    Stack<Integer> in;
    Stack<Integer> out;
    // constructor
    public MyQueue() {
        this.in = new Stack<>(); // stack1
        this.out = new Stack<>(); // stack2
    }

    // enqueue method -> time: O(1) | space: O(n)
    public void push(int val) {
        this.in.push(val);
    }

    // dequeue method -> time: O(n) | space: O(n)
    public int pop() {
        // if stack2 is empty
        if(this.out.size() == 0) {
            // as long as stack1 is not empty
            while(this.in.size() > 0) {
                this.out.push(this.in.pop()); // pop values from stack1 and push them to stack2 (reverse the order)
            }
        } // stack2 now has the same order of retrieval as a queue
        return this.out.pop(); // stack2.pop() repeats until stack2 is empty before pushing values from stack1 again
    }

    // peek method -> time: O(n) | space: O(n)
    public int peek() {
        // if stack2 is empty
        if(this.out.size() == 0) {
            // as long as stack1 is not empty
            while(this.in.size() > 0) {
                this.out.push(this.in.pop()); // pop values from stack1 and push them to stack2 (reverse the order)
            }
        } // stack2 now has the same order of retrieval as a queue
        return this.out.peek(); // stack2.peek() repeats until stack2 is empty before pushing values from stack1 again
    }


    // check if stacks are empty (if queue is empty) -> time: O(1) | space: O(1)
    public boolean empty() {
        return this.in.size() == 0 && this.out.size() == 0;
    }


    // driver
    public static void main(String[] args) {
        // create a new queue object
        MyQueue myQueue = new MyQueue();
        // add values to the queue
        myQueue.push(1); // queue is: [1]
        myQueue.push(2); // queue is: [1, 2] (leftmost is front of the queue)
        // peek at the front of the queue
        System.out.println(myQueue.peek()); // return 1
        // pop the front of the queue
        System.out.println(myQueue.pop()); // return 1, queue is: [2]
        // check if queue is empty
        System.out.println(myQueue.empty()); // return false
    }

}

