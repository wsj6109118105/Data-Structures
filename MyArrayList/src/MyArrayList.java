import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * user:lufei
 * DATE:2021/10/25
 **/
public class MyArrayList<AnyType> implements Iterable<AnyType> {

    //默认长度
    private static final int DEFAULT_CAPACITY = 10;

    //长度
    private int theSize;

    private AnyType[] theItems;

    public MyArrayList() {
        doclear();
    }

    public void clear() {
        doclear();
    }

    private void doclear() {
        theSize = 0;
        ensureCapacity(DEFAULT_CAPACITY);
    }

    public int size() {
        return theSize;
    }

    public boolean isEmpty() {
        return size()==0;
    }

    public void trimToSize() {
        ensureCapacity(size());
    }

    private void ensureCapacity(int newCapacity) {
        if(newCapacity<theSize)
            return;
        if(newCapacity==DEFAULT_CAPACITY) {
            theItems = (AnyType[]) new Object[newCapacity];
            return;
        }
        AnyType[] old = theItems;
        theItems = (AnyType[]) new Object[newCapacity];
        for(int i=0;i<newCapacity;i++) {
            theItems[i] = old[i];
        }
    }

    public boolean add(AnyType x){
        add(size(),x);
        return true;
    }

    public void add(int idx, AnyType x) {
        if(theItems.length==size()){
            ensureCapacity(2 * size() + 1);
        }
        for(int i = theSize;i>idx;i--) {
            theItems[i] = theItems[i-1];
        }
        theItems[idx] = x;

        theSize++;
    }

    public AnyType get(int idx) {
        if(idx<0||idx>=size())
            throw new ArrayIndexOutOfBoundsException();
        return theItems[idx];
    }

    public AnyType remove(int idx) {
        if(idx<0||idx>=size())
            throw new ArrayIndexOutOfBoundsException();
        AnyType item = theItems[idx];
        for(int i=idx;i<size()-1;i++) {
            theItems[i] = theItems[i+1];
        }
        theSize--;
        return item;
    }

    @Override
    public Iterator<AnyType> iterator() {
        return new ArraylistIterator();
    }

    private class ArraylistIterator implements Iterator<AnyType> {

        private int cur = 0;

        @Override
        public boolean hasNext() {
            return cur<size();
        }

        @Override
        public AnyType next() {
            if(!hasNext())
                throw new NoSuchElementException();
            return theItems[cur++];
        }

        @Override
        public void remove() {
            MyArrayList.this.remove(--cur);
        }
    }
}
