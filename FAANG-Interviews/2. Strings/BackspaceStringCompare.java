/*

844. Backspace String Compare
Easy

Given two strings s and t, return true if they are equal when both are typed into empty text editors. '#' means a backspace character.

Note that after backspacing an empty text, the text will continue empty.


Example 1:

Input: s = "ab#c", t = "ad#c"
Output: true
Explanation: Both s and t become "ac".

Example 2:

Input: s = "ab##", t = "c#d#"
Output: true
Explanation: Both s and t become "".

Example 3:

Input: s = "a##c", t = "#a#c"
Output: true
Explanation: Both s and t become "c".

Example 4:

Input: s = "a#c", t = "b"
Output: false
Explanation: s becomes "c" while t becomes "b".


Constraints:

    1 <= s.length, t.length <= 200
    s and t only contain lowercase letters and '#' characters.



Follow up: Can you solve it in O(n) time and O(1) space?


*/

import java.util.ArrayList;
import java.util.Stack;


public class BackspaceStringCompare {

    // Brute force -> time: O(n) | space: O(n)

    // method that takes care of the backspaces
    public static String buildString(String str) {
        // create an arrayList
        ArrayList<Character> arrList = new ArrayList<>();
        // iterate over the entire string
        for (int i = 0; i < str.length(); i++) {
            // if the current character is not a backspace (#)
            if(str.charAt(i) != '#') {
                // add it to the arrayList
                arrList.add(str.charAt(i));
            }
            else { // otherwise, remove the character before the #
                // only remove previous char if the list isn't empty
                if (arrList.size() != 0) { // !arrList.isEmpty()
                    arrList.remove(arrList.size()-1);
                }
            }
        }
        // create a string builder
        StringBuilder strBuilder = new StringBuilder(arrList.size());
        // add each char from the arrayList to the string
        for(Character ch: arrList) {
            strBuilder.append(ch);
        }
        // return the final converted string of characters
        return strBuilder.toString();
    }


    // method that compares two strings to see if they are equal
    public static boolean backspaceCompare(String s, String t) {
        // call buildString method that takes care of the backspaces
        String strS = buildString(s);
        String strT = buildString(t);
        // compare the sizes of the two strings
        if(strS.length() != strT.length()) {
            return false;
        }
        // perform char comparison
        else {
            // can use either strS or strT since they are equal
            for(int i = 0; i < strS.length(); i++) {
                // check if each char in S string matches the corresponding char in T string
                if(strS.charAt(i) != strT.charAt(i)) {
                    return false;
                }
            }
        }
        // if both string are equal in length and each corresponding char matches one another, they must be equal
        return true;
    }


    // **************** DOES NOT WORK FOR STRINGS THAT START OR END WITH HASHES (solution meant for Javascript) ****************

    // Optimal solution -> time: O(n) | space: O(1)
    public static boolean backspaceCompareOp(String s, String t) {
        // pointers start at the end of each string due to the nature of the deletion by #
        int ptrS = s.length()-1, ptrT = t.length()-1;
        // iterate over each string
        while(ptrS >= 0 || ptrT >= 0) {
            // check if each string contains '#' character at the given pointer, respectively
            if(s.charAt(ptrS) == '#' || t.charAt(ptrT) == '#') { // the logic that deals with any hashes
                // check string s
                if(s.charAt(ptrS) == '#') {
                    int backCount = 2; // keeping track of backspaces or amount # encountered (2 because we count # itself and the char it deletes)
                    // the logic for dealing with the amount of '#' encountered so far
                    while(backCount > 0) {
                        ptrS--;
                        backCount--; // since pointer moved, backCount is also consumed
                        // check if the new value the pointer is directed to is a '#'
                        if(s.charAt(ptrS) == '#') {
                            backCount += 2; // if hashes are grouped together we need to increase backCount by two
                        }
                    }
                }
                // check string t
                if(t.charAt(ptrT) == '#') {
                    int backCount = 2; // keeping track of backspaces or amount # encountered (2 because we count # and the char it deletes)
                    // the logic for dealing with the amount of '#' encountered so far
                    while(backCount > 0) {
                        ptrT--;
                        backCount--; // since pointer moved, backCount is also consumed
                        // check if the new value the pointer is directed to is a '#'
                        if(t.charAt(ptrT) == '#') {
                            backCount += 2; // if hashes are grouped together we need to increase backCount by two
                        }
                    }
                }
            }
            // when strings no longer contain any hashes, we compare regular characters from each string to each other
            else {
                // if the characters do not match at any given time, the strings are not equal
                if(s.charAt(ptrS) != t.charAt(ptrT)) {
                    return false;
                }
                else { // as long as they match, keep moving the pointers to the left
                 ptrS--;
                 ptrT--;
                }
            }
        }
        return true;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////// Alternate solution using Stacks /////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // method that takes care of the backspaces -> time: O(n) | space: O(n)
    public static String buildStringOp(String s) {
        // create a new stack of characters
        Stack<Character> stack = new Stack<>();
        // convert the string to an array of characters
        char[] chars = s.toCharArray();
        // for every current character in the array
        for (char ch : chars) {
            // if character does not contain a backspace
            if (ch != '#') {
                // push the current character onto the stack
                stack.push(ch);
            }
            // if ch == '#'
            else {
                // as long as the stack is not empty
                if (!stack.empty()) {
                    // pop the top of the stack
                    stack.pop();
                }
            }
        }
        // returns the string representation of the char stack argument.
        return String.valueOf(stack);
    }

    // a method that compares if two strings are equal when using backspace in a text editor (shorter version)
    public static boolean backspaceCompareSt(String s, String t) {
        // build a new string s by invoking build() method passing in S as argument
        String strS = buildStringOp(s);
        // build a new string t by invoking build() method passing in T as argument
        String strT = buildStringOp(t);
        // check new strings s and t for equality, return true if they match or false if they don't
        return strS.equals(strT);
    }


    // driver
    public static void main(String[] args) {
        // example inputs
        String s_1 = "ab#c", t_1 = "ad#c";
        String s_2 = "ab##", t_2 = "c#d#";
        String s_3 = "a##c", t_3 = "#a#c";
        String s_4 = "a#c",  t_4 = "b";
        // display resulting outputs
        System.out.println("Brute force solution:");
        System.out.println(backspaceCompare(s_1, t_1)); // true
        System.out.println(backspaceCompare(s_2, t_2)); // true
        System.out.println(backspaceCompare(s_3, t_3)); // true
        System.out.println(backspaceCompare(s_4, t_4)); // false
        System.out.println();
//        System.out.println("Optimal solution:");
//        System.out.println(backspaceCompareOp(s_1, t_1)); // true
//        System.out.println(backspaceCompareOp(s_2, t_2)); // true
//        System.out.println(backspaceCompareOp(s_3, t_3)); // true
//        System.out.println(backspaceCompareOp(s_4, t_4)); // false
        System.out.println("Stack solution:");
        System.out.println(backspaceCompareSt(s_1, t_1)); // true
        System.out.println(backspaceCompareSt(s_2, t_2)); // true
        System.out.println(backspaceCompareSt(s_3, t_3)); // true
        System.out.println(backspaceCompareSt(s_4, t_4)); // false
    }

}
