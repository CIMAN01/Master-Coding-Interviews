import java.util.Arrays;
import java.util.List;
import java.util.Stack;


public class Stacks {

    // private fields
    private static final List<Character> leftBrackets = Arrays.asList('(', '{', '[', '<');
    private static final List<Character> rightBrackets = Arrays.asList(')', '}', ']', '>');


    // helper method for isBalanced method
    private static boolean isLeftBracket(char ch) {
        return leftBrackets.contains(ch);
    }

    // a helper method for isBalanced method
    private static boolean isRightBracket(char ch) {
        return rightBrackets.contains(ch);
    }

    // a helper method for isBalanced method
    private static boolean bracketsMatch(char left, char right) {
        // check if brackets match by comparing the indices of each array list
        return leftBrackets.indexOf(left) == rightBrackets.indexOf(right);
    }

    // a method that check for balanced brackets (i.e. proper syntax)
    public static boolean isBalanced(String str) {

        Stack<Character> stack = new Stack<>();

        for (char ch : str.toCharArray()) {
            if(isLeftBracket(ch)) {
                stack.push(ch);
            }
            if(isRightBracket(ch)) {
                // edge case
                if(stack.isEmpty()) {
                    return false;
                }
                char top = stack.pop();
                // top = left, ch = right
                if(!bracketsMatch(top, ch)) {
                    return false;
                }
            }
        }
        // return true is stack is empty
        return stack.empty();
    }


    // a method that reverse a string
    public static String reverseAString(String str) {
        // edge case
        if (str == null) {
            throw new IllegalArgumentException();
        }

        Stack<Character> stack = new Stack<>();

        for (char ch : str.toCharArray()) {
            stack.push(ch);
        }

        StringBuilder reverse = new StringBuilder();

        while (!stack.isEmpty()) {
            char character = stack.pop();
            reverse.append(character);
        }

        return reverse.toString();
    }


    // driver
    public static void main(String[] args) {
        String str = "abcd";
        String reverse = reverseAString(str);
        System.out.println(reverse);
        String expression = "((1+2))";
        // edge case
        // (
        // (()
        // ( ]
        // ) (
        boolean result = isBalanced(expression);
        System.out.println(result);
    }

}
