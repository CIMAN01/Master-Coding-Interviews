import java.util.Arrays;

public class LinkedListMain {

    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        list.addLast(10);
        list.addLast(20);
        list.addLast(30);
        list.addLast(40);
        list.addLast(50);
//        list.addLast(60);
//        System.out.println(list.size());

//        int[] array = list.toArray();
//        System.out.println(Arrays.toString(array));

//        System.out.println(list.indexOf(30));

//        list.removeFirst();
//        list.removeLast();
//        System.out.println(list.size());
//        System.out.println(list.contains(30));

//        list.reverse();
//        int[] array = list.toArray();
//        System.out.println(Arrays.toString(array));

//        System.out.println(list.getKthFromTheEnd(1));
//        System.out.println(list.getKthFromTheEnd(2));
        System.out.println(list.getKthFromTheEnd(3));

        list.printMiddle();

        //
        var list2 = LinkedList.createWithLoop();
        System.out.println(list2.hasLoop());
    }

}
