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
    protected int size;  

    // Creates an empty Binary Search Tree object
    public BinarySearchTree()
    {
        root = null;
        size = 0;
    }

    // Returns true if this BST is empty; otherwise, returns false.
    public boolean isEmpty()
    {
        return size == 0;
    }

    // Returns the number of elements in this BST.
    public int size()
    {
        return size;
    }

    // Adds element to this BST. The tree retains its BST property.
    public void add (T element)
    {
        BinaryNode<T> el = new BinaryNode<T>(element);
        BinaryNode<T> traversal = root;
        if(root == null)
        {
            root = el;
        }
        else
        {
            while(traversal.getLeft() != null && traversal.getRight() != null)
            {
                if(element.compareTo(traversal.getInfo()) == -1)
                {
                    traversal = traversal.getLeft();
                }
                else if(element.compareTo(traversal.getInfo()) == 1)
                {
                    traversal = traversal.getRight();
                }
            }
            if(element.compareTo(traversal.getInfo()) == -1)
            {
                traversal.setLeft(el);
            }
            else if(element.compareTo(traversal.getInfo()) == 1)
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
        int count = 0;
        if(root.getInfo() == null)
        {
            return false;
        }
        else
        {
            while(count < size)
            {
                if(e.getInfo().compareTo(element) == 0)
                {
                    return true;
                }
                if(element.compareTo(e.getInfo()) == -1)
                {
                    e = e.getLeft();
                }
                else if(element.compareTo(e.getInfo()) == 1)
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
        return null;
    }

    // Returns a list of elements from an inorder traversal
    public List<T> inorderTraverse()
    {
        return null;
    }

    // Returns a list of elements from a postorder traversal
    public List<T> postorderTraverse()
    {
        return null;
    }

    // Removes an element e from this BST such that e.compareTo(element) == 0
    public void remove (T element)
    {
    }

    // Restructures this BST to be optimally balanced
    public void balance()
    {
    }
}