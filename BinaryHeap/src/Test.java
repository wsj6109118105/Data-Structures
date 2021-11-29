/**
 * user:lufei
 * DATE:2021/11/29
 **/
public class Test {
    public static void main(String[] args) {
        int numItems = 10000;
        MyBinaryHeap<Integer> h = new MyBinaryHeap<>();
        int i = 37;

        for( i = 37; i != 0; i = ( i + 37 ) % numItems )
            h.insert( i );
        for( i = 1; i < numItems; i++ )
            if( h.deleteMin( ) != i )
                System.out.println( "Oops! " + i );
    }
}
