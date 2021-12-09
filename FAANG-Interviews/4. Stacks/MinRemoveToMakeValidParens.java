/*

Minimum Remove to Make Valid Parentheses (Medium)

Given a string s of '(' , ')' and lowercase English characters.

Your task is to remove the minimum number of parentheses ( '(' or ')', in any positions ) so that the resulting parentheses string is valid and return any valid string.

Formally, a parentheses string is valid if and only if:

    It is the empty string, contains only lowercase characters, or
    It can be written as AB (A concatenated with B), where A and B are valid strings, or
    It can be written as (A), where A is a valid string.


Example 1:

Input: s = "lee(t(c)o)de)"
Output: "lee(t(c)o)de"
Explanation: "lee(t(co)de)" , "lee(t(c)ode)" would also be accepted.

Example 2:

Input: s = "a)b(c)d"
Output: "ab(c)d"

Example 3:

Input: s = "))(("
Output: ""
Explanation: An empty string is also valid.

Example 4:

Input: s = "(a(b(c)d)"
Output: "a(b(c)d)"


Constraints:

    1 <= s.length <= 105
    s[i] is either'(' , ')', or lowercase English letter.


*/


import java.util.Stack;


public class MinRemoveToMakeValidParens {

    // a method that removes the least amount of parenthesis from a string -> time: O(n) | space: O(n)
    public static String minRemoveToMakeValid(String s) {
        // convert string to an array of characters
        char[] array = s.toCharArray();
        // create a new stack
        Stack<Integer> stack = new Stack<>();
        // iterate over the array
        for (int i = 0; i < array.length; i++) {
            // if it's left bracket, push its index to the stack
            if(array[i] == '(') {
                stack.push(i);
            }
            // if it's a right bracket and stack is not empty (if a left bracket already exists in the stack)
            else if(array[i] == ')' && stack.size() > 0) {
                stack.pop(); // pop the most recent left bracket
            }
            // if it's a right bracket and no left brackets come before it (stack.size() == 0)
            else if(array[i] == ')') { // else if(array[i] == ')' && stack.size() == 0) {
                array[i] = ' '; // we can immediately remove it from the array
            }
        }
        // deal with any remaining values in the stack
        while(!stack.empty()) {
            // get rid of any invalid brackets that have not been closed
            int currentIndex = stack.pop();
            array[currentIndex] = ' ';
        }
        // convert array back to string
        return String.valueOf(array);
    }


    // driver
    public static void main(String[] args) {
        // input brackets
        String s1 = "lee(t(c)o)de)";
        String s2 = "a)b(c)d";
        String s3 = "))((";
        String s4 = "(a(b(c)d)";
        // output results
        System.out.println();
        System.out.println("lee(t(c)o)de) -> " + minRemoveToMakeValid(s1)); // "lee(t(c)o)de"
        System.out.println("a)b(c)d -> " + minRemoveToMakeValid(s2)); // "ab(c)d"
        System.out.println("))(( -> " + minRemoveToMakeValid(s3)); // ""
        System.out.println("(a(b(c)d) -> " + minRemoveToMakeValid(s4)); // "a(b(c)d)"
    }

}
