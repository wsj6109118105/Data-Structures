import java.util.NoSuchElementException;

/**
 * user:lufei
 * DATE:2021/11/3
 **/
public class BinarySearchTree<AnyType extends Comparable<? super AnyType>> {
    private static class BinaryNode<AnyType> {
        AnyType element;
        BinaryNode<AnyType> left;
        BinaryNode<AnyType> right;

        BinaryNode( AnyType theElement ) {
            this(theElement,null,null);
        }

        BinaryNode( AnyType theElement,BinaryNode<AnyType> lt,BinaryNode<AnyType> rt ) {
            element = theElement;
            left = lt;
            right = rt;
        }
    }

    private BinaryNode<AnyType> root;

    public BinarySearchTree() {
        root = null;
    }

    public void makeEmpty() {
        root = null;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public boolean contains(AnyType x) {
        return contains(x,root);
    }

    public AnyType findMin() {
        if (isEmpty()) throw new NoSuchElementException();
        return findMin( root ).element;
    }

    public AnyType findMax() {
        if (isEmpty()) throw new NoSuchElementException();
        return findMax( root ).element;
    }

    public void insert(AnyType x) {
        root = insert(x,root);
    }

    public void remove(AnyType x) {
        root = remove(x,root);
    }

    public void printTree() {
        if (isEmpty()) {
            System.out.println("空的树");
        }else {
            printTree( root );
        }
    }

    private boolean contains(AnyType x,BinaryNode<AnyType> t) {
        if(t==null) {
            return false;
        }

        int compareResult = x.compareTo(t.element);
        BinaryNode<AnyType> cur;
        cur = t;
        while(compareResult != 0 && cur != null) {
            if (compareResult>0) {
                cur = cur.left;
                compareResult = x.compareTo(cur.element);
            }else if (compareResult<0) {
                cur = cur.right;
                compareResult = x.compareTo(cur.element);
            }
        }
        if (compareResult==0) {
            return true;
        }else {
            return false;
        }
    }

    private BinaryNode<AnyType> findMin(BinaryNode<AnyType> t) {
        if (t==null) {
            return null;
        }
        BinaryNode<AnyType> min;
        min = t;
        while (min.left!=null) {
            min = min.left;
        }
        return min;
    }

    private BinaryNode<AnyType> findMax(BinaryNode<AnyType> t) {
        if (t==null) {
            return null;
        }
        BinaryNode<AnyType> max;
        max = t;
        while (max.right!=null) {
            max = max.right;
        }
        return max;
    }

    private BinaryNode<AnyType> insert(AnyType x,BinaryNode<AnyType> t) {
        if (t==null) {
            return new BinaryNode<>(x,null,null);
        }
        int compareResult = x.compareTo(t.element);
        if (compareResult<0) {
            return insert(x,t.left);
        }else if (compareResult>0) {
            return insert(x,t.right);
        }
        return t;
    }

    private BinaryNode<AnyType> remove(AnyType x,BinaryNode<AnyType> t) {
        if (t==null)
            return t;
        int compareResult = x.compareTo(t.element);
        if (compareResult<0) {
            remove(x,t.left);
        }else if (compareResult>0) {
            remove(x,t.right);
        }else if (t.left!=null&&t.right!=null) {
            t.element = findMax(t.left).element;
            t.left = remove(t.element,t.left);
        }else
            t = t.left!=null?t.left:t.right;
        return t;
    }

    private void printTree(BinaryNode<AnyType> t) {
        if( t!=null ) {
            printTree(t.left);
            System.out.println(t.element);
            printTree(t.right);
        }
    }
}
