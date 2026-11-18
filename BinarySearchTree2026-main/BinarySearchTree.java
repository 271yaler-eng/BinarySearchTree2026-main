import java.util.*;

/**
 * This BinarySearchTree object defines a reference based binary search tree
 * 
 * @author  
 * @version 
 */

public class BinarySearchTree<T extends Comparable<T>> implements BSTInterface<T>
{
    protected BinaryNode<T> root;      // reference to the root of this BST

    // Creates an empty Binary Search Tree object
    public BinarySearchTree()
    {
        root = null;
    }

    // Returns true if this BST is empty; otherwise, returns false.
    public boolean isEmpty()
    {
        return root == null;
    }

    // Returns the number of elements in this BST.
    public int size()
    {
        BinaryNode<T> traversal = root;
        return size(traversal);
    }

    private int size(BinaryNode<T> tree)
    {
        int sum = 0;
        if(tree != null)
        {
            int left = size(tree.getLeft());
            int right = size(tree.getRight());
            sum = left + right + 1;
        }
        return sum;
    }

    // Adds element to this BST. The tree retains its BST property.
    public void add (T element)
    {
        BinaryNode<T> el = new BinaryNode<T>(element);
        BinaryNode<T> traversal = root;
        String direction = "";
        if(root == null)
        {
            root = el;
        }
        else
        {
            while(direction.equals(""))
            {
                if(element.compareTo(traversal.getInfo()) < 0)
                {
                    if(traversal.getLeft() == null)
                    {
                        direction = "left";
                    }
                    else
                    {
                        traversal = traversal.getLeft();
                    }
                }
                else if(element.compareTo(traversal.getInfo()) > 0)
                {
                    if(traversal.getRight() == null)
                    {
                        direction = "right";
                    }
                    else
                    {
                        traversal = traversal.getRight();
                    }
                }
                else
                {
                    break;
                }
            }
            if(direction.equals("left"))
            {
                traversal.setLeft(el);
            }
            else if(direction.equals("right"))
            {
                traversal.setRight(el);
            }
        }
    }

    // Returns true if this BST contains an element e such that 
    // e.compareTo(element) == 0; otherwise, returns false.
    public boolean contains (T element)
    {
        BinaryNode<T> e = root;
        if(e == null)
        {
            return false;
        }
        else if(e.getInfo().compareTo(element) == 0)
        {
            return true;
        }
        else
        {
            while(e != null)
            {
                if(e.getInfo().compareTo(element) == 0)
                {
                    return true;
                }
                if(element.compareTo(e.getInfo()) < 0)
                {
                    e = e.getLeft();
                }
                else if(element.compareTo(e.getInfo()) > 0)
                {
                    e = e.getRight();
                }
            }
            return false;
        }
    }

    // Returns a graphical representation of the tree
    public String toString()
    {
        return toString(root, 0);
    }

    private String toString(BinaryNode<T> tree, int level)
    {
        String str = "";
        if (tree != null)
        {
            str += toString(tree.getRight(), level + 1);
            for (int i = 1; i <= level; ++i)
                str = str + "| ";
            str += tree.getInfo().toString() + "\n";
            str += toString(tree.getLeft(), level + 1);
        }
        return str;
    }

    // Returns a list of elements from a preorder traversal
    public List<T> preorderTraverse()
    {
        ArrayList<T> elements = new ArrayList<>();
        BinaryNode<T> traversal = root;
        if(root == null)
        {
            return elements;
        }
        preorderTraverse(traversal, elements);
        return elements;
    }

    private void preorderTraverse(BinaryNode<T> tree, List<T> list)
    {
        if(tree != null)
        {
            list.add(tree.getInfo());
            preorderTraverse(tree.getLeft(), list);
            preorderTraverse(tree.getRight(), list);
        }
    }

    // Returns a list of elements from an inorder traversal
    public List<T> inorderTraverse()
    {
        BinaryNode<T> e = root;
        ArrayList<T> elements = new ArrayList<>();
        if(e == null)
        {
            return elements;
        }
        inorderTraverse(e, elements);
        return elements;
    }
    
    private void inorderTraverse(BinaryNode<T> tree, List<T> list)
    {
        if(tree != null)
        {
            inorderTraverse(tree.getLeft(), list);
            list.add(tree.getInfo());
            inorderTraverse(tree.getRight(), list);
        }
    }

    // Returns a list of elements from a postorder traversal
    public List<T> postorderTraverse()
    {
        BinaryNode<T> e = root;
        ArrayList<T> elements = new ArrayList<>();
        if(e == null)
        {
            return elements;
        }
        postorderTraverse(e, elements);
        return elements;
    }

    private void postorderTraverse(BinaryNode<T> tree, List<T> list)
    {
        if(tree != null)
        {
            postorderTraverse(tree.getLeft(), list);
            postorderTraverse(tree.getRight(), list);
            list.add(tree.getInfo());
        }
    }

    // Removes an element e from this BST such that e.compareTo(element) == 0
    public void remove (T element)
    {
        BinaryNode<T> e = root;
        BinaryNode<T> parent = root;
        if(e == null)
        {
            root = null;
        }
        else if(e.getInfo().compareTo(element) == 0 && e.getRight() == null && e.getLeft() == null)
        {
            root = null;
        }
        else if(e.getInfo().compareTo(element) == 0 && e.getRight() != null && e.getLeft() == null)
        {
            root = root.getRight();
        }
        else if(e.getInfo().compareTo(element) == 0 && e.getRight() == null && e.getLeft() != null)
        {
            root = root.getLeft();
        }
        else
        {
            List<T> list = inorderTraverse();
            boolean caniterate = false;
            for(int i = 0; i < list.size(); i++)
            {
                if(list.get(i).compareTo(element) == 0)
                {
                    caniterate = true;
                }
            }
            while(e != null && caniterate)
            {
                if(element.compareTo(e.getInfo()) < 0)
                {
                    parent = e;
                    e = e.getLeft();
                }
                else if(element.compareTo(e.getInfo()) > 0)
                {
                    parent = e;
                    e = e.getRight();
                }
                else if(e.getInfo().compareTo(element) == 0 && e.getRight() == null && e.getLeft() == null)
                { 
                    if(parent.getRight() == e)
                    {
                        parent.setRight(null);
                        break;
                    }
                    if(parent.getLeft() == e)
                    {
                        parent.setLeft(null);
                        break;
                    }
                }
                else if(e.getInfo().compareTo(element) == 0 && e.getLeft() == null && e.getRight() != null)
                {
                    if(parent.getRight() == e)
                    {
                        parent.setRight(e.getRight());
                        break;
                    }
                    else if(parent.getLeft() == e)
                    {
                        parent.setLeft(e.getRight());
                        break;
                    }
                }
                else if(e.getInfo().compareTo(element) == 0 && e.getLeft() != null && e.getRight() == null)
                {
                    if(parent.getRight() == e)
                    {
                        parent.setRight(e.getLeft());
                        break;
                    }
                    else if(parent.getLeft() == e)
                    {
                        parent.setLeft(e.getLeft());
                        break;
                    }
                }
                else if(e.getInfo().compareTo(element) == 0 && e.getLeft() != null && e.getRight() != null)
                {
                    BinaryNode<T> successor = e.getRight();
                    BinaryNode<T> p = e;
                    while(successor.getLeft() != null)
                    {
                        p = successor;
                        successor = successor.getLeft();
                    }
                    e.setInfo(successor.getInfo());
                    if(successor.getRight() != null)
                    {
                        if(p.getLeft() == successor)
                        {
                            p.setLeft(successor.getRight());
                            break;
                        }
                        else if(p.getRight() == successor)
                        {
                            p.setRight(successor.getRight());
                            break;
                        }
                    }
                    else if(successor.getRight() == null)
                    {
                        if(p.getRight() == successor)
                        {
                            p.setRight(null);
                        }
                        else if(p.getLeft() == successor)
                        {
                            p.setLeft(null);
                        }
                    }
                    break;
                }
            }
        }
    }

    // Restructures this BST to be optimally balanced
    public void balance()
    {
        List<T> list = inorderTraverse();
        root = balance(list, 0, list.size()-1);
    }

    private BinaryNode<T> balance(List<T> list, int start, int end)
    {
        if(list.size() == 0 || start > end)
        {
            return null;
        }
        int middle = (start + end) / 2;
        BinaryNode<T> midNode = new BinaryNode<>(list.get(middle));
        midNode.setLeft(balance(list, start, middle -1));
        midNode.setRight(balance(list, middle + 1, end));
        return midNode;
        // 1. Base case: no elements remain.

        // 2. Find the middle index.

        // 3. Create a node containing the middle element.

        // 4. Recursively build its left subtree.

        // 5. Recursively build its right subtree.

        // 6. Return the node.
    }
}