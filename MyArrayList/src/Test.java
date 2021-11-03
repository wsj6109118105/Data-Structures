import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;

/**
 * user:lufei
 * DATE:2021/10/25
 **/
public class Test {
    public static void main(String[] args) {
        MyArrayList<Integer> integers = new MyArrayList<>();

        integers.add(1);
        integers.add(0,0);
        integers.get(1);
        integers.remove(1);
        integers.add(1);
        integers.add(2);
        integers.add(3);
        integers.add(4);
        Iterator<Integer> iterator = integers.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        Deque<Integer> deque = new ArrayDeque<>();
        deque.remove();
    }
}
