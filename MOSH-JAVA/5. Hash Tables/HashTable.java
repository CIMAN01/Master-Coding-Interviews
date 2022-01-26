import java.util.LinkedList;

// implementation of a Hash-table
public class HashTable {

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
    }


    // create an array of linked-lists (use chaining)
    private LinkedList<Entry>[] entries = new LinkedList[5];


    // a hash function (assumes key is a positive value)
    private int hash(int key)  {
        return key % entries.length; // reduce key to the size of the array
    }

    // refactor
    private LinkedList<Entry> getBucket(int key) {
        // use hash function to find the index needed to store or retrieve an entry
        return entries[hash(key)]; // return bucket
    }

    // refactor
    private Entry getEntry(int key) {
        // create a bucket using the key
        var bucket = getBucket(key);
        // only loop when bucket is not null to avoid throwing an exception
        if(bucket != null) {
            // iterate over each entry in the bucket
            for(var entry : bucket) {
                // if an entry has the same key, return it
                if(entry.key == key) {
                    return entry;
                }
            }
        }
        return null; // bucket is null or no entry was found with the given key
    }

    // refactor
    private LinkedList<Entry> getOrCreateBucket(int key) {
        // create an index that will decide where to store the entry by using the hash function
        int index = hash(key);
        // if bucket is null, create a new linked-list and store it in the entries array at the proper index
        if(entries[index] == null) {
            entries[index] = new LinkedList<>();
        }
        return entries[index]; // return bucket
    }

    // put method
    public void put(int key, String value) {
        // get the entry for a given key (lookup entry)
        var entry = getEntry(key);
        // if entry already exists, update its value
        if(entry != null) {
            entry.value = value;
            return;
        }
        // else, get bucket (or create a new one) and add the new entry (value-pair) to the bucket
        getOrCreateBucket(key).add(new Entry(key, value));
    }

    // get method
    public String get(int key) {
        // find an entry using the key
        var entry = getEntry(key);
        // if entry is null return null, otherwise, return its value
        return (entry == null) ? null : entry.value;
    }

    // remove method
    public void remove(int key) {
        // find an entry using the key
        var entry = getEntry(key);
        // if entry is null, throw an exception
        if(entry == null) {
            throw new IllegalStateException();
        }
        getBucket(key).remove(entry); // otherwise, remove entry from bucket
    }


    // driver
    public static void main(String[] args) {
        // create a new hash-table
        HashTable table = new HashTable();
        // add some values to table
        table.put(6, "A");
        table.put(8, "B");
        table.put(11, "C");
        table.put(6, "A+");
        table.remove(8);
//        table.remove(60); // exception thrown
        System.out.println(table.get(6)); // A+
        System.out.println(table.get(8)); // null
        System.out.println(table.get(10)); // null
    }

}
