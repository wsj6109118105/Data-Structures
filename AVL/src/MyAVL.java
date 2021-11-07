import java.util.logging.Level;

/** MyAVL()--->构造器
 * int Height(AvlNode<AnyType> t)------> 高度
 * void makeEmpty()-----> 清空
 * boolean isEmpty()-----> 是否为空
 * insert(AnyType x)-----> 插入
 * balance(AvlNode<AnyType> t)-----> 平衡
 * findMin()-----> 返回最小
 * findMin()-----> 返回最大
 * remove(AnyType x)-----> 移除
 * contains(AnyType x)-----> 判断是否存在
 * printTree()-----> 打印，中序遍历
 * user:lufei
 * DATE:2021/11/7
 **/
public class MyAVL<AnyType extends Comparable<? super AnyType>> {

    private static class AvlNode<AnyType> {
        AnyType element;
        AvlNode<AnyType> left;
        AvlNode<AnyType> right;
        int height;

        public AvlNode(AnyType e) {
            this(e,null,null);
        }

        public AvlNode(AnyType e,AvlNode<AnyType> lt,AvlNode<AnyType> rt) {
            element = e;
            left = lt;
            right = rt;
            height = 0;
        }
    }

    private AvlNode<AnyType> root;

    private int Height(AvlNode<AnyType> t) {
        return t==null?-1:t.height;
    }

    public MyAVL() {
        root = null;
    }

    public void makeEmpty() {
        root = null;
    }

    public boolean isEmpty() {
        return root==null;
    }

    public AvlNode<AnyType> insert(AnyType x) {
        return root = insert(x,root);
    }

    // 插入
    private AvlNode<AnyType> insert(AnyType x,AvlNode<AnyType> t) {
        if (t==null)
            return new AvlNode<>(x);

        int compareResult = x.compareTo(t.element);

        if(compareResult>0) {
            t.right = insert(x,t.right);
        }else if (compareResult<0) {
            t.left = insert(x,t.left);
        }else
            ;
        return balance(t);
    }

    private static final int ALLOWED_IMBALANCE = 1;

    // 平衡
    private AvlNode<AnyType> balance(AvlNode<AnyType> t) {
        if (t==null) {
            return t;
        }

        if (Height(t.left)-Height(t.right)>ALLOWED_IMBALANCE) {
            if (Height(t.left.left)-Height(t.left.right)>=ALLOWED_IMBALANCE)
                t = rotateWithLeftChild(t);
            else
                t = doubleWithLeftChild(t);
        }else if (Height(t.right)-Height(t.left)>ALLOWED_IMBALANCE) {
            if (Height(t.right.right)-Height(t.right.left)>=ALLOWED_IMBALANCE)
                t = rotateWithRightChild(t);
            else
                t = doubleWithRightChild(t);
        }
        t.height = Math.max(Height(t.left),Height(t.right))+1;
        return t;
    }

    // 左节点单旋
    private AvlNode<AnyType> rotateWithLeftChild(AvlNode<AnyType> t) {
        AvlNode<AnyType> k2 = t.left;
        t.left = k2.right;
        k2.right = t;
        t.height = Math.max(Height(t.left),Height(t.right))+1;
        k2.height = Math.max(Height(k2.left),t.height)+1;
        return k2;
    }

    // 左节点双旋
    private AvlNode<AnyType> doubleWithLeftChild(AvlNode<AnyType> t) {
        t.left = rotateWithRightChild(t.left);
        return rotateWithLeftChild(t);
    }

    // 右节点单旋
    private AvlNode<AnyType> rotateWithRightChild(AvlNode<AnyType> t) {
        AvlNode<AnyType> k2 = t.right;
        t.right = k2.left;
        k2.left = t;
        t.height = Math.max(Height(t.left),Height(t.right))+1;
        k2.height = Math.max(Height(k2.right),t.height)+1;
        return k2;
    }

    // 右节点双选
    private AvlNode<AnyType> doubleWithRightChild(AvlNode<AnyType> t) {
        t.right = rotateWithLeftChild(t.right);
        return rotateWithRightChild(t);
    }

    public AvlNode<AnyType> findMin() {
        if (isEmpty())
            throw new NullPointerException();
        return findMin(root);
    }

    private AvlNode<AnyType> findMin(AvlNode<AnyType> t) {
        AvlNode<AnyType> k = t;
        while (k.left!=null) {
            k = k.left;
        }
        return k;
    }

    public AvlNode<AnyType> findMax() {
        if (isEmpty())
            throw new NullPointerException();
        return findMax(root);
    }

    private AvlNode<AnyType> findMax(AvlNode<AnyType> t) {
        AvlNode<AnyType> k = t;
        while (k.right!=null) {
            k = k.right;
        }
        return k;
    }

    public AvlNode<AnyType> remove(AnyType x) {
        return remove(x,root);
    }

    private AvlNode<AnyType> remove(AnyType x,AvlNode<AnyType> t) {
        if (t==null)
            return t;
        int compareResult = x.compareTo(t.element);

        if (compareResult>0) {
            t.right = remove(x,t.right);
        }else if (compareResult<0) {
            t.left = remove(x,t.left);
        }else if (t.left!=null&&t.right!=null) {
            t.element = findMin(t.right).element;
            t.right = remove(x,t.right);
        }else
            t = t.left!=null?t.left:t.right;
        return balance(t);
    }

    public boolean contains(AnyType x) {
        return contains(x,root);
    }

    private boolean contains(AnyType x,AvlNode<AnyType> t) {
        AvlNode<AnyType> k = t;
        while (k!=null) {
            int compareResult = x.compareTo(k.element);
            if (compareResult<0) {
                k = k.left;
            }else if (compareResult>0) {
                k = k.right;
            }else
                return true;
        }
        return false;
    }

    public void printTree() {
        printTree(root);
    }

    private void printTree(AvlNode<AnyType> t) {
        while (t!=null) {
            printTree(t.left);
            System.out.println(t.element);
            printTree(t.right);
        }
    }
}
