/*

125. Valid Palindrome (Easy)

A phrase is a palindrome if, after converting all uppercase letters into lowercase letters and removing all non-alphanumeric characters, it reads the same forward and backward. Alphanumeric characters include letters and numbers.

Given a string s, return true if it is a palindrome, or false otherwise.


Example 1:

Input: s = "A man, a plan, a canal: Panama"
Output: true
Explanation: "amanaplanacanalpanama" is a palindrome.

Example 2:

Input: s = "race a car"
Output: false
Explanation: "raceacar" is not a palindrome.

Example 3:

Input: s = " "
Output: true
Explanation: s is an empty string "" after removing non-alphanumeric characters.
Since an empty string reads the same forward and backward, it is a palindrome.


Constraints:

    1 <= s.length <= 2 * 105
    s consists only of printable ASCII characters.


*/


public class ValidPalindrome {

    // traversing string from each end to the middle point (2 pointers from outside)
    public static boolean isPalindrome1(String s) {
        // remove all spaces and special characters from string and change to lower case
        String str = s.replaceAll("[^a-zA-Z0-9]","").toLowerCase();
        // initialize left/right pointers at start and end of string respectively
        int left = 0, right = str.length()-1;
        // loop through string characters while comparing them, then move the pointers closer to the center
        while(left < right) {
            // character comparison
            if(str.charAt(left) != str.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }


    // traversing string from middle to each end (2 pointers from center)
    public static boolean isPalindrome2(String s) {
        // remove all spaces and special characters from string and change to lower case
        String str = s.replaceAll("[^a-zA-Z0-9]","").toLowerCase();
        int length = str.length();
        // initialize left/right pointers to point at the middle index of the string;
        // indexes start at 0, so we have to floor() the value from dividing length by 2 in order to get the index of the center
        int left = (int) Math.floor(length / 2.0), right = left;
        // if the string is even, move left pointer back by 1 so left/right are pointing at the 2 middle values respectively.
        if(length % 2 == 0) {
            left--;
        }
        // loop through the string while expanding pointers outwards comparing the characters.
        while(left >= 0 && right < length) {
            // character comparison
            if(str.charAt(left) != str.charAt(right)) {
                return false;
            }
            left--;
            right++;
        }
        return true;
    }


    // reversing the string and comparing (compare against reverse)
    public static boolean isPalindrome3(String s) {
        // remove all spaces and special characters from string and change to lower case
        String str = s.replaceAll("[^a-zA-Z0-9]","").toLowerCase();
        // deploy string builder
        StringBuilder reverse = new StringBuilder();
        // generate a reverse string using a reverse for loop.
        for(int i = str.length()-1; i >= 0; i--) {
            reverse.append(str.charAt(i));
        }
        // a boolean comparison of the two strings
        return reverse.toString().equals(str);
    }


    // driver
    public static void main(String[] args) {
        // input
        String sample_input_s1 = "A man, a plan, a canal: Panama";
        String sample_input_s2 = "race a car";
        String sample_input_s3 = " ";
        String sample_input_s4 = "a";
        // output
        System.out.println(isPalindrome2(sample_input_s1)); // true
        System.out.println(isPalindrome2(sample_input_s2)); // false
        System.out.println(isPalindrome2(sample_input_s3)); // true
        System.out.println(isPalindrome2(sample_input_s4)); // true
    }

}
