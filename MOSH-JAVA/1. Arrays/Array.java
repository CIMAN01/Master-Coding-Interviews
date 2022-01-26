
public class Array {

    // fields
    private int[] items;
    private int count;

    // constructor
    public Array(int length) {
        items = new int[length];
    }

    // O(n)
    public void insert(int item) {
        if(items.length == count) {
            int[] newItems = new int[count * 2];
            for (int i = 0; i < count; i++) {
                newItems[i] = items[i];
            }
            items = newItems;
        }
        items[count++] = item;
    }

    // O(n)
    public void removeAt(int index) {
        if(index < 0 || index >= count) {
            throw new IllegalArgumentException();
        }
        for (int i = index; i < count; i++) {
            items[i] = items[i+1];
        }
        count--;
    }

    // O(n)
    public int indexOf(int item) {
        for (int i = 0; i < count; i++) {
            if(items[i] == item) {
                return i;
            }
        }
        return -1;
    }

    public void print() {
        for (int i = 0; i < count; i++) {
            System.out.println(items[i]);
        }
    }

    /****************************************    Arrays Exercises:         *********************************************************/


    // 1. Extend the Array class and add a new method to return the largest number. What is the runtime complexity of this method?
    //    Solution: Array.max()

    // O(n)
    public int max() {
        int max = items[0];
        for (int i = 0; i < count; i++) {
            if(items[i] > max) {
                max = items[i];
            }
        }
        return max;
    }


    // 2. Extend the Array class and add a method to return the common items in this array and another array.
    //    Solution: Array.intersect()

    // O(n)
    public void intersect(int[] otherItems) {
        int found = 0;
        for (int i = 0; i < otherItems.length; i++) {
            if(indexOf(otherItems[i]) >= 0) {
                System.out.println(otherItems[i]);
                found++;
            }
        }
        if(found == 0) {
            System.out.println("No common items were found.");
        }
    }

    // 3. Extend the Array class and add a method to reverse the array. For example, if the array includes [1, 2, 3, 4], after reversing and printing it, we should see [4, 3, 2, 1].
    //    Solution: Array.reverse()

    // O(n)
    public int[] reverse() {
        int[] reversedItems = new int[count];
        for (int i = 0; i < reversedItems.length; i++) {
            reversedItems[i] = items[--count];
        }
        return reversedItems;
    }


    // 4. Extend the Array class and add a new method to insert an item at a given index: public void insertAt(int item, int index)
    //    Solution: Array.insertAt()

    // O(n)
    public void insertAt(int item, int index) {
        int[] addItems = new int[count * 2];
        for (int i = 0; i < index; i++) {
            addItems[i] = items[i];
        }
        addItems[index] = item;
        for (int i = index; i < count; i++) {
            addItems[i+1] = items[i];
        }
        items = addItems;
        count++;
    }


}
