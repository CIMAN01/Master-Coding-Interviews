
public class Factorial {

    // iterative approach to finding a factorial of n
    public static int factorialIt(int n) {
        // initialize factorial to one
        int factorial = 1;
        // iterate until i reaches n
        for (int i = n; i > 1; i--) {
            factorial *= i;
        }
        return factorial;
    }

    // recursive approach to finding a factorial of n
    public static int factorialR(int n) {
        // base condition
        if(n == 0) {
            return 1;
        }
        return n * factorialR(n-1);
    }

    // driver
    public static void main(String[] args) {
        // 4! = 4 * 3! = 4 * 3 * 2 * 1 = 24
        // n! = n * (n-1)!
        System.out.println(factorialIt(4)); // 24
        System.out.println(factorialR(4)); // 24
    }

}
