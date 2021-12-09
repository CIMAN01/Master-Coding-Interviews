/*

20. Valid Parentheses (Easy)

Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

An input string is valid if:

    Open brackets must be closed by the same type of brackets.
    Open brackets must be closed in the correct order.


Example 1:

Input: s = "()"
Output: true

Example 2:

Input: s = "()[]{}"
Output: true

Example 3:

Input: s = "(]"
Output: false

Example 4:

Input: s = "([)]"
Output: false

Example 5:

Input: s = "{[]}"
Output: true


Constraints:

    1 <= s.length <= 104
    s consists of parentheses only '()[]{}'.


*/


import java.util.Stack;



public class ValidParentheses {

    // a method that check a string for valid parenthesis -> time: O(n) | space: O(n)
    public static boolean isValid(String s) {
        // if string is empty, string is considered valid
        if(s.length() == 0) {
            return true;
        }
        // if string only contains one character, string is considered invalid
        if(s.length() == 1) {
            return false;
        }
        // create a new stack
        Stack<Character> stack = new Stack<>();
        // iterate over the string
        for(char ch : s.toCharArray()) {
            // if a left bracket, push the current character to the stack
            if(ch == '(' || ch == '[' || ch == '{') {
                stack.push(ch);
            }
            // if a right bracket
            else { // if(ch == ')' || ch == ']' || ch == '}') {
                // make sure stack is not empty before you pop a character (edge case)
                if(stack.empty()) {
                    return false;
                }
                // take a peek the top of the stack for a comparison
                char top = stack.peek();
                // if left and right brackets match, pop the top of the stack
                if(top == '(' && ch == ')' || top == '{' && ch == '}' || top == '[' && ch == ']') {
                    stack.pop();
                }
                // otherwise, return false if there is no match
                else {
                    return false;
                }
            }
        }
        // if the stack is empty it means all characters/brackets in the string must match (valid parenthesis)
        return stack.empty();
    }


    // driver
    public static void main(String[] args) {
        // input brackets
        String s1 = "()"; // true
        String s2 = "()[]{}"; // true
        String s3 = "(]"; // false
        String s4 = "([)]"; // false
        String s5 = "{[]}"; // true
        String s6 = ""; // true
        String s7 = "("; // false
        // output results
        System.out.println(isValid(s1)); // true
        System.out.println(isValid(s2)); // true
        System.out.println(isValid(s3)); // false
        System.out.println(isValid(s4)); // false
        System.out.println(isValid(s5)); // true
        System.out.println(isValid(s6)); // true
        System.out.println(isValid(s7)); // false
    }

}
