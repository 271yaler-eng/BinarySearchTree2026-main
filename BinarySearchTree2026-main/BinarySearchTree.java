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
        String direction = "";
        if(root == null)
        {
            root = el;
            size++;
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
                size++;
            }
            else if(direction.equals("right"))
            {
                traversal.setRight(el);
                size++;
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
        
    }

    // Restructures this BST to be optimally balanced
    public void balance()
    {
    }
}