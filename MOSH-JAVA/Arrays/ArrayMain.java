import java.util.Arrays;

public class ArrayMain {

    public static void main(String[] args) {
        Array numbers = new Array(3);
        numbers.insert(10);
        numbers.insert(20);
        numbers.insert(30);
        numbers.insert(40);
        //numbers.removeAt(2);
        //System.out.println(numbers.indexOf(10));
        //System.out.println(numbers.max());
        //int[] otherNumbers = {15, 20, 25, 30, 35};
        //numbers.intersect(otherNumbers);
        //System.out.println(Arrays.toString(numbers.reverse()));
        numbers.print();
        System.out.println();
        numbers.insertAt(50, 3);
        numbers.print();
    }

}




