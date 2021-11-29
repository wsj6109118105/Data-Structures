import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * user:lufei
 * DATE:2021/11/29
 **/
public class MyBinaryHeap <AnyType extends Comparable<? super AnyType>>{

    private int currentSize;
    private AnyType[] array;
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * 无参构造
     */
    public MyBinaryHeap(){
        this(DEFAULT_CAPACITY);
    }

    /**
     * 指定初始容量
     * @param capacity 初始容量
     */
    public MyBinaryHeap(int capacity) {
        currentSize = 0;
        array = (AnyType[]) new Comparable[capacity+1];
    }

    /**
     * 使用给定的 n 个元素初始化一个堆
     * @param items 给定的初始元素
     */
    public MyBinaryHeap(AnyType[] items) {
        currentSize = items.length;
        array = (AnyType[]) new Comparable[(currentSize+2)*11/10];
        int i = 1;
        for (AnyType item : items) {
            array[i++] = item;
        }
        buildHeap();
    }

    /**
     * 向堆中插入元素
     * @param i 插入的元素
     */
    public void insert(AnyType i) {
        if (currentSize==array.length-1) {
            enlargeArray(currentSize*2+1);
        }
        int hole = ++currentSize;
        for (array[0]=i;array[hole/2].compareTo(i)>0;hole/=2) {
            array[hole] = array[hole/2];
        }
        array[hole] = i;
    }

    /**
     *
     * @return 返回最小元素
     */
    public AnyType findMin() {
        if (isEmpty())
            throw new NoSuchElementException();
        return array[1];
    }

    /**
     * 删除最小元素
     * @return 返回删除的元素
     */
    public AnyType deleteMin() {
        if (isEmpty())
            throw new NoSuchElementException();
        AnyType min = array[1];
        array[1] = array[currentSize--];
        percolateDown(1);
        return min;
    }

    /**
     * 判断堆是否为空
     * @return 返回是否为空
     */
    public boolean isEmpty() {
        return currentSize==0;
    }

    /**
     * 将堆置空
     */
    public void makeEmpty() {
        currentSize = 0;
    }

    /**
     * 下滤操作
     * @param hole 需要下滤的节点
     */
    private void percolateDown(int hole) {
        AnyType tem = array[hole];
        int child;
        for (;hole*2<=currentSize;hole=child) {
            child = hole*2;
            if (child!=currentSize&&(array[child].compareTo(array[child+1]))>0)
                child++;
            if (array[child].compareTo(tem)<0) {
                array[hole] = array[child];
            }else {
                break;
            }
        }
        array[hole] = tem;
    }

    /**
     * 构造堆操作
     */
    private void buildHeap() {
        for (int i = currentSize/2;i>0;i--) {
            percolateDown(i);
        }
    }

    /**
     * 扩容操作
     * @param newCapacity 新的容量大小
     */
    private void enlargeArray(int newCapacity) {
        AnyType[] old = array;
        array = (AnyType[]) new Comparable[newCapacity];
        for (int i = 0;i< old.length;i++) {
            array[i] = old[i];
        }
//        array = Arrays.copyOf(array, newCapacity);
    }
}
