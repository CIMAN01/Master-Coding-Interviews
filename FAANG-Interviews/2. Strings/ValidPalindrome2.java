/*

680. Valid Palindrome II (Easy)

Given a string s, return true if the s can be palindrome after deleting at most one character from it.

Example 1:

Input: s = "aba"
Output: true

Example 2:

Input: s = "abca"
Output: true
Explanation: You could delete the character 'c'.

Example 3:

Input: s = "abc"
Output: false



Constraints:

    1 <= s.length <= 105
    s consists of lowercase English letters.


*/


public class ValidPalindrome2 {


    // a (helper) method that checks a string to determine if it's a valid palindrome
    public static boolean isValidSubPalindrome(String s, int left, int right) {
        // traverse the string
        while(left < right) {
            // check for conflicting characters
            if(s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        // string is a valid palindrome when all characters match
        return true;
    }


    // Optimal Solution: time: O(n) | space: O(1)

    // a method that determines if a string is almost a palindrome (a palindrome once a character is deleted)
    public static boolean isAlmostPalindrome(String s) {
        // remove all spaces and special characters from string and change to lower case
        String str = s.replaceAll("[^a-zA-Z0-9]","").toLowerCase();
        // initialize left/right pointers at start and end of string respectively
        int left = 0, right = str.length()-1;
        // loop through string characters while comparing them, then move the pointers closer to the center
        while(left < right) {
            // check for conflicting characters
            if(str.charAt(left) != str.charAt(right)) {
                // compare strings after shifting one of the two pointers by one for each string (deleting a character from each one)
                return isValidSubPalindrome(str, left+1, right) || isValidSubPalindrome(str, left, right-1);
            }
            left++;
            right--;
        }
        return true;
    }


    // driver
    public static void main(String[] args) {
        // input
        String sample_input_s1 = "aba";
        String sample_input_s2 = "abca";
        String sample_input_s3 = "abc";
        String sample_input_s4 = "a";
        String sample_input_s5 = " ";
        String sample_input_s6 = "abccdba";
        // output
        System.out.println(isAlmostPalindrome(sample_input_s1)); // true
        System.out.println(isAlmostPalindrome(sample_input_s2)); // true
        System.out.println(isAlmostPalindrome(sample_input_s3)); // false
        System.out.println(isAlmostPalindrome(sample_input_s4)); // true
        System.out.println(isAlmostPalindrome(sample_input_s5)); // true
        System.out.println(isAlmostPalindrome(sample_input_s6)); // true
    }

}
