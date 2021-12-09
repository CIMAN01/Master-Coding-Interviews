import java.util.Arrays;

// Implement two stacks in one array. Support these operations:
//        push1() // to push in the first stack
//        push2() // to push in the second stack
//        pop1()
//        pop2()
//        isEmpty1()
//        isEmpty2()
//        isFull1()
//        isFull2()
//        Make sure your implementation is space efficient. (hint: do not allocate the same amount of space by dividing the array in half.)


public class TwoStacks {
    // fields
    private int top1;
    private int top2;
    private int[] items;

    // constructor
    public TwoStacks(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("capacity must be 1 or greater.");
        }
        items = new int[capacity];
        top1 = -1;
        top2 = capacity;
    }

    /* methods start here */

    public void push1(int item) {
        if (isFull1()) {
            throw new IllegalStateException();
        }
        items[++top1] = item;
    }

    public int pop1() {
        if (isEmpty1()) {
            throw new IllegalStateException();
        }
        return items[top1--];
    }

    public boolean isEmpty1() {
        return top1 == -1;
    }

    public boolean isFull1() {
        return top1 + 1 == top2;
    }

    public void push2(int item) {
        if (isFull2()) {
            throw new IllegalStateException();
        }
        items[--top2] = item;
    }

    public int pop2() {
        if (isEmpty2()) {
            throw new IllegalStateException();
        }
        return items[top2++];
    }

    public boolean isEmpty2() {
        return top2 == items.length;
    }

    public boolean isFull2() {
        return top2 - 1 == top1;
    }

    @Override
    public String toString() {
        return Arrays.toString(items);
    }


    // driver
    public static void main(String[] args) {

        TwoStacks stack = new TwoStacks(5);

        stack.push1(10);
        stack.push1(20);
        stack.push1(30);
        stack.push2(50);
        stack.push2(40);

        System.out.println(stack);
        System.out.println();
        System.out.println(stack.pop2());
        System.out.println(stack.pop1());

    }

}
