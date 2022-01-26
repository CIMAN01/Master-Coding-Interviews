import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public class CharFinder {

    // a method that finds the first non-repeated character in a string
    public static char FindFirstNonRepeatedChar(String str) {
        // create a new hash-map
        Map<Character, Integer> map = new HashMap<>();
        // convert string to an array of characters
        char[] chars = str.toCharArray();
        // iterate over the entire array of characters and store each value and how many times that value is repeated in the hash-map
        for(char ch : chars) {
            int count = map.containsKey(ch) ? map.get(ch) : 0;
            map.put(ch, count + 1);
        }
        // iterate of the array of characters again and return the character that has a count of one in the hash-map
        for(char ch : chars) {
            if(map.get(ch) == 1) {
                return ch;
            }
        }
        return Character.MIN_VALUE; // acts sort of like a null when a character with a count of one is not found
    }

    // a method that finds the first repeated character in a string
    public static char findFirstRepeatedChar(String str) {
        // create a new hash-set (a hash-set does not allow duplicate keys)
        Set<Character> set = new HashSet<>();
        // convert string to an array of characters
        char[] chars = str.toCharArray();
        // for each character in the string
        for(char ch : chars) {
            // check if set contains character (if character is repeated)
            if(set.contains(ch)) {
                return ch;
            }
            set.add(ch); // otherwise, add character to the set
        }
        return Character.MIN_VALUE; // acts sort of like a null when a character with a count of one is not found
    }

    // driver
    public static void main(String[] args) {
        String text = "a green apple";
        System.out.println(FindFirstNonRepeatedChar(text)); // 'g'
        System.out.println(findFirstRepeatedChar(text)); // 'e'
    }

}
