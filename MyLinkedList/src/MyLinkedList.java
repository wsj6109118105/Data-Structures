import javax.swing.text.EditorKit;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * user:lufei
 * DATE:2021/10/26
 **/
public class MyLinkedList<AnyType> implements Iterable<AnyType> {

    //节点信息
    private static class Node<AnyType> {
        public AnyType data;
        public Node<AnyType> prev;
        public Node<AnyType> next;

        public Node(AnyType data, Node<AnyType> prev, Node<AnyType> next) {
            this.data = data;
            this.prev = prev;
            this.next = next;
        }
    }

    private int theSize;

    private int modCount = 0;

    //开始头节点
    private Node<AnyType> begin;

    //结尾节点
    private Node<AnyType> end;

    public MyLinkedList() {
        doClear();
    }

    public void clear() {
        doClear();
    }

    //初始化
    private void doClear() {
        begin = new Node<AnyType>(null,null,null);
        end = new Node<AnyType>(null,begin,null);
        begin.next = end;
        theSize = 0;
        modCount++;
    }

    //返回大小
    public int size() {
        return theSize;
    }

    //是否为空
    public boolean isEmpty() {
        return size()==0;
    }

    //添加方法
    public boolean add(AnyType x) {
        add(size(),x);
        return true;
    }

    //添加指定下标位置
    public void add(int idx,AnyType x) {
        addBefore(getNode(idx,0,size()),x);
    }

    public void addBefore(Node<AnyType> p,AnyType x) {
        Node<AnyType> node = new Node<AnyType>(x,null,null);
        node.prev = p.prev;
        p.prev = p.prev.next = node;
        node.next = p;
        theSize++;
        modCount++;
    }

    //通过下标获取值
    public AnyType get(int idx) {
        return getNode(idx).data;
    }

    //通过下标获取节点
    public Node<AnyType> getNode(int idx) {
        return getNode(idx,0,size());
    }

    public Node<AnyType> getNode(int idx,int low,int upper) {
        Node<AnyType> p;
        //下标越界
        if(idx<low||idx>upper) {
            throw  new IndexOutOfBoundsException();
        }
        //前/后两部分部分
        if(idx<size()/2) {
            p = begin.next;
            for(int i = 0;i<idx;i++) {
                p = p.next;
            }
        }else {
            p = end;
            for(int i = size();i>idx;i--) {
                p = p.prev;
            }
        }
        return p;
    }

    //移除指定下标元素
    public Node<AnyType> remove(int idx) {
        return remove(getNode(idx));
    }

    public Node<AnyType> remove(Node<AnyType> node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
        theSize--;
        modCount++;
        return node;
    }


    //返回一个迭代器
    @Override
    public Iterator<AnyType> iterator() {
        return new LinkedListIterator();
    }

    //迭代器：私有内部类实现
    private class LinkedListIterator implements Iterator<AnyType> {

        private Node<AnyType> current = begin.next;
        private Node<AnyType> cur = end.prev;
        private int expectedModCount = modCount;
        private boolean okToRemove = false;

        //是否有下一个
        @Override
        public boolean hasNext() {
            return current!=end;
        }

        //输出迭代器走过的值
        @Override
        public AnyType next() {
            if(modCount!=expectedModCount)
                throw new ConcurrentModificationException();
            if(!hasNext())
                throw new NoSuchElementException();
            AnyType next = current.data;
            current = current.next;
            okToRemove = true;
            return next;
        }

        //是否有上一个
        public boolean hasPrevious() {
            return cur!=begin;
        }

        //输出迭代器走过的值
        public AnyType previous() {
            if(modCount!=expectedModCount)
                throw new ConcurrentModificationException();
            if(!hasPrevious())
                throw new NoSuchElementException();
            AnyType prev = cur.data;
            cur = cur.prev;
            okToRemove = true;
            return prev;
        }

        //移除迭代器走过的值，必须和 next()/preview() 一起使用
        @Override
        public void remove() {
            if(modCount!=expectedModCount)
                throw new ConcurrentModificationException();
            if(!hasNext())
                throw new IllegalStateException();
            if(cur==end.prev){
                MyLinkedList.this.remove(current.prev);
            }else {
                MyLinkedList.this.remove(cur.next);
            }
            expectedModCount++;
            okToRemove = false;
        }
    }
}
