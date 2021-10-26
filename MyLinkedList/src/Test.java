import java.util.Iterator;

/**
 * user:lufei
 * DATE:2021/10/26
 **/
public class Test {
    public static void main(String[] args) {
        MyLinkedList<Integer> list = new MyLinkedList<>();
        list.add(0);
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(5);
        list.add(5);
        list.remove(6);
        list.remove(6);
        System.out.println(list.get(2));
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
