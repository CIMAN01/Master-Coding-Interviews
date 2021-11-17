/*

3. Longest Substring Without Repeating Characters (Medium)


Given a string s, find the length of the longest substring without repeating characters.

Example 1:

Input: s = "abcabcbb"
Output: 3
Explanation: The answer is "abc", with the length of 3.

Example 2:

Input: s = "bbbbb"
Output: 1
Explanation: The answer is "b", with the length of 1.

Example 3:

Input: s = "pwwkew"
Output: 3
Explanation: The answer is "wke", with the length of 3.
Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.

Example 4:

Input: s = ""
Output: 0


Constraints:

    0 <= s.length <= 5 * 104
    s consists of English letters, digits, symbols and spaces.

*/


import java.util.HashMap;
import java.util.Map;

public class LongestSubstring {

    // assumptions:
    // substring is contiguous (not a subsequence) and that case sensitivity does not matter (all characters in string are lower case)


    // Brute-force solution -> time: O(n^2) | space: O(n)
    public static int lengthOfLongestSubstring(String s) {
        // edge case
        if(s.length() <= 1) {
            return s.length();
        }
        // length of substring
        int longest = 0;
        // inner
        for(int left = 0; left < s.length(); left++) {
            // use a hashmap that keeps track of what characters have been seen
            HashMap<Character, Boolean> seenChars = new HashMap<>();
            int currentLength = 0;
            // outer
            for(int right = left; right < s.length(); right++) {
                char currentChar = s.charAt(right);
                // if current characters is not found in stored hashmap
                if(!seenChars.containsKey(currentChar)) {
                    currentLength++; // increment the length of substring
                    seenChars.put(currentChar, true); // add the current character to the hashmap
                    longest = Math.max(longest, currentLength); // update substring's longest length
                }
                else { // else, break the loop and move left pointer forwards
                    break;
                }
            }
        }
        return longest;
    }


    // Optimal solution (using sliding window technique) -> time: O(n) | space: O(n)
    public static int lengthOfLongestSubstringOp(String s) {
        // edge case (optimization)
        if(s.length() <= 1) {
            return s.length();
        }
        // create a new hashmap of previously seen characters
        HashMap<Character, Integer> seen = new HashMap<>();
        // store a left pointer and the longest length of substring
        int left = 0, longest = 0;
        // iterate over the original string
        for(int right = 0; right < s.length(); right++) {
            // current character the right pointer is directed at
            char currentChar = s.charAt(right);
            // index of previously seen character
            int indexOfPrevSeenChar = 0;  // *** declaration linked to if statement to avoid NullPointerException ***
            // if seen map contains the current character
            if(seen.containsKey(currentChar)) { // *** if statement needed to avoid NullPointerException ***
                // grab its index from hashmap and update previously seen character index
                indexOfPrevSeenChar = seen.get(currentChar);
            }
            // check if the index of previously seen character is within the sliding window
            if(indexOfPrevSeenChar >= left) {
                left = indexOfPrevSeenChar + 1; // shift left up to the position of that index plus one
            }
            // always update seen hashmap with the current character and its index during each iteration
            seen.put(currentChar, right);
            // 'right - left' to get the length and  '+ 1' to account for the off-set (index starts at 0)
            longest = Math.max(longest, right - left + 1);
        }
        return longest;
    }


    // Optimal solution (slightly shorter) -> time: O(n) | space: O(n)
    public static int lengthOfLongestSubstringOp2(String s) {
        // edge case (optimization)
        if(s.length() <= 1) {
            return s.length();
        }
        // create a new hashmap of previously seen characters
        HashMap<Character, Integer> seenMap = new HashMap<>();
        // store a left pointer and the longest length of substring
        int longest = 0, left = 0;
        // iterate over the original string
        for (int right = 0; right < s.length(); right++) {
            // current character the right pointer is directed at
            char currentChar = s.charAt(right);
            // update left pointer via hashmap by applying the sliding window technique (performs same steps as lines 96-105)
            if(seenMap.containsKey(currentChar)) {
                left = Math.max(seenMap.get(currentChar) + 1, left);
            }
            // always update seen hashmap with the current character and its index during each iteration
            seenMap.put(currentChar, right);
            // 'right - left' to get the length and  '+ 1' to account for the off-set (index starts at 0)
            longest = Math.max(longest, right - left + 1);
        }
        return longest;
    }


    // driver
    public static void main(String[] args) {
        // example inputs
        String input_s_0 = "abcbdaac";
        String input_s_1 = "abcabcbb";
        String input_s_2 = "bbbbb";
        String input_s_3 = "pwwkew";
        String input_s_4 = "";
        // brute-force outputs
        System.out.println("Brute-force solution:");
        System.out.println(lengthOfLongestSubstring(input_s_0)); // 4
        System.out.println(lengthOfLongestSubstring(input_s_1)); // 3
        System.out.println(lengthOfLongestSubstring(input_s_2)); // 1
        System.out.println(lengthOfLongestSubstring(input_s_3)); // 3
        System.out.println(lengthOfLongestSubstring(input_s_4)); // 0
        System.out.println();
        // optimal solution
        System.out.println("Optimal Solution:");
        System.out.println(lengthOfLongestSubstringOp(input_s_0)); // 4
        System.out.println(lengthOfLongestSubstringOp(input_s_1)); // 3
        System.out.println(lengthOfLongestSubstringOp(input_s_2)); // 1
        System.out.println(lengthOfLongestSubstringOp(input_s_3)); // 3
        System.out.println(lengthOfLongestSubstringOp(input_s_4)); // 0
        System.out.println();
    }

}
