/*

---------------------------------------------------------------------------------------------------------------------------------------------------

    Build a hash table from scratch. Use linear probing strategy for

        handling collisions. Implement the following operations:
        - put(int, String)
        - get(int)
        - remove(int)
        - size()

        Solution: HashMap

---------------------------------------------------------------------------------------------------------------------------------------------------

*/


import java.util.Arrays;


public class HashMap {

    // inner class
    private class Entry {
        // fields
        private int key;
        private String value;
        // constructor
        public Entry(int key, String value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return value;
        }
    }

    private Entry[] entries = new Entry[5];
    private int count;

    // put method
    public void put(int key, String value) {
        // get and store entry
        var entry = getEntry(key);
        // update entry value if not null
        if(entry != null) {
            entry.value = value;
            return;
        }
        if (isFull()) {
            throw new IllegalStateException();
        }
        entries[getIndex(key)] = new Entry(key, value);
        count++;
    }

    // get method
    public String get(int key) {
        var entry = getEntry(key);
        return (entry != null) ? entry.value : null;
    }

    // remove method
    public void remove(int key) {
        var index = getIndex(key);
        // return if index is negative or entry is null
        if (index == -1 || entries[index] == null) {
            return;
        }
        entries[index] = null;
        count--;
    }

    public int size() {
        return count;
    }


    private Entry getEntry(int key) {
        var index = getIndex(key);
        return (index >= 0) ? entries[index] : null;
    }


    private int getIndex(int key) {
        int steps = 0;
        // Linear probing algorithm: we keep looking until we find an empty slot or a slot with the same key.
        // We use this loop conditional to prevent an infinite loop that will happen if the array is full (and we keep probing with no success).
        // So, the number of steps (or probing attempts) should be less than the size of our table.
        while(steps < entries.length) {
            int index = index(key, steps++);
            var entry = entries[index];
            // return index if entry is null or when we have a matching key
            if(entry == null || entry.key == key)
                return index;
        }
        // This will happen if we looked at every slot in the array and couldn't find a place for this key. That basically means the table is full.
        return -1;
    }


    private boolean isFull() {
        return count == entries.length;
    }


    // linear probing
    private int index(int key, int i) {
        return (hash(key) + i) % entries.length;
    }


    private int hash(int key) {
        return key % entries.length;
    }


    @Override
    public String toString() {
        return Arrays.toString(entries);
    }


    // driver
    public static void main(String[] args) {
        // create a new hash map
        HashMap map = new HashMap();
        // add some values to map
        map.put(6, "A");
        map.put(8, "B");
        map.put(11, "C");
        map.put(6, "A+");
        map.remove(8);
//        table.remove(60); // exception thrown
        System.out.println(map.get(6)); // A+
        System.out.println(map.get(8)); // null
        System.out.println(map.get(10)); // null;
        System.out.println(map.size());
    }

}
