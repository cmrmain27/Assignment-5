/*
* INFO I-211/CSCI C-202
* BinarySearchTree.java
* Purpose: This program is designed to read in a dictionary as a Binary Search 
* tree according the first letters of each word, read in a file, check to see if 
* each word in the file is found in the dictionary, and increments counters 
* depending on words found, words not found, comparisons found, and 
* comparisons not found. This program applies the use of Binary Search Trees to a 
* real world problem and shows the difference in efficiency compared to using a 
* Linked List to solve this real world problem. 
* 
* Dr. Hettiarachchi
* Cody Main 
* November 3-8, 2016
 */


package Program5;

import java.io.*;
import java.util.*;

public class BinarySearchTree<E extends Comparable<E>> extends AbstractTree<E> {

    protected TreeNode<E> root;
    protected int size = 0;

    /**
     * Create a default binary tree
     */
    public BinarySearchTree() {
    }

    /**
     * Create a binary tree from an array of objects
     */
    public BinarySearchTree(E[] objects) {
        for (int i = 0; i < objects.length; i++) {
            insert(objects[i]);
        }
    }

    /**
     * Returns true if the element is in the tree
     */
    public boolean search(E e) {
        TreeNode<E> current = root; // Start from the root
        while (current != null) {
            if (e.compareTo(current.element) < 0) {
                current = current.left;
            } else if (e.compareTo(current.element) > 0) {
                current = current.right;
            } else // element matches current.element
            {
                return true; // Element is found
            }
        }
        return false;
    }

    /**
     * @requires an element of generic type E and an single-dimensional array 
     * of integers. 
     * @ensures the element will return true or false depending on if the 
     * element of type E is found in the node of the Binary Search Tree. 
     * The integer array will implement regardless of the return. 
     */
    public boolean search(E e, int[] count) {
        TreeNode<E> current = root; // Start from the root
        int i = 0; 
        
        while (current != null) {
            i++;
            count[0] = i;    
            if (e.compareTo(current.element) < 0) {
                current = current.left;
            } else if (e.compareTo(current.element) > 0) {
                current = current.right;
            } else // element matches current.element
            {
                return true; // Element is found
            }
        }
        return false;
    }
    
    
    
    /**
     * Insert element o into the binary tree Return true if the element is
     * inserted successfully. Uses an iterative algorithm
     */
    public boolean insert(E e) {
        if (root == null) {
            root = createNewNode(e); // Create a new root
        } else {
            // Locate the parent node
            TreeNode<E> parent = null;
            TreeNode<E> current = root;
            while (current != null) {
                if (e.compareTo(current.element) < 0) {
                    parent = current;
                    current = current.left;
                } else if (e.compareTo(current.element) > 0) {
                    parent = current;
                    current = current.right;
                } else {
                    return false; // Duplicate node not inserted
                }
            }
            // Create the new node and attach it to the parent node
            if (e.compareTo(parent.element) < 0) {
                parent.left = createNewNode(e);
            } else {
                parent.right = createNewNode(e);
            }
        }
        size++;
        return true; // Element inserted
    }

    protected TreeNode<E> createNewNode(E e) {
        return new TreeNode<E>(e);
    }

    /**
     * Inorder traversal from the root
     */
    public void inorder() {
        inorder(root);
    }

    /**
     * Inorder traversal from a subtree
     */
    protected void inorder(TreeNode<E> root) {
        if (root == null) {
            return;
        }
        inorder(root.left);
        System.out.print(root.element + " ");
        inorder(root.right);
    }

    /**
     * Postorder traversal from the root
     */
    public void postorder() {
        postorder(root);
    }

    /**
     * Postorder traversal from a subtree
     */
    protected void postorder(TreeNode<E> root) {
        if (root == null) {
            return;
        }
        postorder(root.left);
        postorder(root.right);
        System.out.print(root.element + " ");
    }

    /**
     * Preorder traversal from the root
     */
    public void preorder() {
        preorder(root);
    }

    /**
     * Preorder traversal from a subtree
     */
    protected void preorder(TreeNode<E> root) {
        if (root == null) {
            return;
        }
        System.out.print(root.element + " ");
        preorder(root.left);
        preorder(root.right);
    }

    /**
     * Inner class tree node
     */
    public static class TreeNode<E extends Comparable<E>> {

        E element;
        TreeNode<E> left;
        TreeNode<E> right;

        public TreeNode(E e) {
            element = e;
        }
    }

    /**
     * Get the number of nodes in the tree
     */
    public int getSize() {
        return size;
    }

    /**
     * Returns the root of the tree
     */
    public TreeNode getRoot() {
        return root;
    }

    
    
    /**
     * Returns an ArrayList containing elements in the path from the root
     * leading to the specified element, returns an empty ArrayList if no such
     * element exists.
     */
    public ArrayList<E> path(E e) {
        //java.util.ArrayList<E> list = new java.util.ArrayList<>();
        
        ArrayList<E> list = new ArrayList<>();
        TreeNode<E> current = root; // Start from the root

        while (current != null) {
              
            list.add(current.element);
            
            if ((current.left == null) && (current.right == null) && (current.element != e)) {
                list.clear();
                break;
            }

            if (e.compareTo(current.element) < 0) {
                current = current.left;
                //list.add(current.element);
            } else if (e.compareTo(current.element) > 0) {
                current = current.right;
               // list.add(current.element);
            } else {
                break;
                
            }
        }
        return list; // Return an array of elements
    }

    
    
    /**
     * Client Call that keeps the root unable to be accessed
     */
    protected int getNumberOfLeaves() {
        return getNumberOfLeaves(root);
    }

    /* Returns the number of leaf nodes in this tree, returns 0 if tree is empty*/
    public int getNumberOfLeaves(TreeNode<E> node) {
        if ((node.left == null) && (node.right == null)) {
            return 1;
        }

        if ((node.left != null) && (node.right != null)) {
            return getNumberOfLeaves(node.left) + getNumberOfLeaves(node.right);
        } else if (node.right != null) {
            return getNumberOfLeaves(node.right);
        } else {
            return getNumberOfLeaves(node.left);
        }
    }

    
    
    /**
     * Takes the current values of current and list and add the elements of the 
     * left subtree in preorder format.
     */
    protected void leftSubTree(TreeNode<E> current, ArrayList<E> list) 
    {
        if (current == null) 
        {
            return;
        }

        list.add(current.element);

        leftSubTree(current.left, list);

        leftSubTree(current.right, list);
    }

    /* Returns an ArrayList containing all elements in preorder of the 
    specified element’s left sub-tree, returns an empty ArrayList if no  
    such element exists. */
    public ArrayList<E> leftSubTree(E e) 
    {
        java.util.ArrayList<E> list = new java.util.ArrayList<>();
        TreeNode<E> current = root; // Start from the root

        while (current != null) 
        {
            if (e.compareTo(current.element) < 0) 
            {
                current = current.left;
            } 
            
            else if (e.compareTo(current.element) > 0) 
            {
                current = current.right;
            } 
            
            else 
            {
                leftSubTree(current.left, list);
                list.add(0, current.element);
                break;
            }
        }
        return list;
    }

    
    
    /**
     * Takes the current values of current and list and add the elements of the 
     * right subtree in preorder format.
     */
    protected void rightSubTree(TreeNode<E> current, ArrayList<E> list) 
    {
        if (current == null) 
        {
            return;
        }

        list.add(current.element);

        leftSubTree(current.left, list);

        leftSubTree(current.right, list);
    }

    /* 
    * Returns an ArrayList containing all elements in preorder of the 
    * specified element’s right sub-tree, returns an empty ArrayList if no  
    * such element exists. 
    */
    public ArrayList<E> rightSubTree(E e) 
    {
        java.util.ArrayList<E> list = new java.util.ArrayList<>();
        TreeNode<E> current = root; // Start from the root

        while (current != null) 
        {
            if (e.compareTo(current.element) < 0) 
            {
                current = current.left;
            } 
            
            else if (e.compareTo(current.element) > 0) 
            {
                current = current.right;
            } 
            
            else 
            {
                leftSubTree(current.right, list);
                list.add(0, current.element);
                break;
            }
        }
        return list;
    }
    
    
    
    /* Returns the inorder predecessor of the specified element, returns null 
    if tree is empty or element 'e' is not in the tree. */
    public E inorderPredecessor(E e) {
        TreeNode<E> current = root; // Start from the root

        while (current != null) {
            if (e.compareTo(current.element) < 0) {
                current = current.left;
            } else if (e.compareTo(current.element) > 0) {
                current = current.right;
            } else if (current.left != null) {
                current = current.left;

                while (current.right != null) {
                    current = current.right;
                }
                break;
            } else {
                System.out.println("There is no inorder predecessor for "
                        + "this node.");
                return null;
            }
        }
        System.out.print("The Inorder Predecessor of " + e + " is: ");
        return current.element;
    }
    
    
    
    /**
     * Returns true if BinarySearchTree tree1 is structurally identical to
     * BinarySearchTree tree2, otherwise returns false
     */
    protected boolean sameTree (TreeNode<E> root1, TreeNode<E> root2)
    {
        if ((root1 == null) && (root2 == null))
        {
            return true;
        }
        
        else if ((root1 != null) && (root2 != null))
        {
            return ((root1.element.compareTo(root2.element) == 0) 
                    && (sameTree(root1.left, root2.left))
                    && (sameTree(root1.right, root2.right)));
        }
        
        else 
        {
            return false;
        }
    }
    
    /**
     * This method calls the protected sameTree method so that the nodes of the 
     * subtrees can be successfully compared. 
     */
    public boolean sameTree(BinarySearchTree tree1, BinarySearchTree tree2)
    {
        return sameTree(tree1.root, tree2.root);
    }
    
    
    
    /**
     * Delete an element from the binary tree. Return true if the element is
     * deleted successfully Return false if the element is not in the tree
     */
    public boolean delete(E e) {
        // Locate the node to be deleted and also locate its parent node
        TreeNode<E> parent = null;
        TreeNode<E> current = root;
        while (current != null) {
            if (e.compareTo(current.element) < 0) {
                parent = current;
                current = current.left;
            } else if (e.compareTo(current.element) > 0) {
                parent = current;
                current = current.right;
            } else {
                break; // Element is in the tree pointed by current
            }
        }
        if (current == null) {
            return false; // Element is not in the tree
        }    // Case 1: current has no left children
        if (current.left == null) {
            // Connect the parent with the right child of the current node
            if (parent == null) {
                root = current.right;
            } else if (e.compareTo(parent.element) < 0) {
                parent.left = current.right;
            } else {
                parent.right = current.right;
            }
        } else {
            // Case 2 & 3: The current node has a left child
            // Locate the rightmost node in the left subtree of
            // the current node and also its parent
            TreeNode<E> parentOfRightMost = current;
            TreeNode<E> rightMost = current.left;

            while (rightMost.right != null) {
                parentOfRightMost = rightMost;
                rightMost = rightMost.right; // Keep going to the right
            }
            // Replace the element in current by the element in rightMost
            current.element = rightMost.element;

            // Eliminate rightmost node
            if (parentOfRightMost.right == rightMost) {
                parentOfRightMost.right = rightMost.left;
            } else // Special case: parentOfRightMost == current
            {
                parentOfRightMost.left = rightMost.left;
            }
        }
        size--;
        return true; // Element inserted
    }

    /**
     * Obtain an iterator. Use inorder.
     */
    public java.util.Iterator iterator() {
        return inorderIterator();
    }

    /**
     * Obtain an inorder iterator
     */
    public java.util.Iterator inorderIterator() {
        return new InorderIterator();
    }

    // Inner class InorderIterator
    class InorderIterator implements java.util.Iterator {
        // Store the elements in a list

        private java.util.ArrayList<E> list = new java.util.ArrayList<E>();
        private int current = 0; // Point to the current element in list

        public InorderIterator() {
            inorder(); // Traverse binary tree and store elements in list
        }

        /**
         * Inorder traversal from the root
         */
        private void inorder() {
            inorder(root);
        }

        /**
         * Inorder traversal from a subtree
         */
        private void inorder(TreeNode<E> root) {
            if (root == null) {
                return;
            }
            inorder(root.left);
            list.add(root.element);
            inorder(root.right);
        }

        /**
         * Next element for traversing?
         */
        public boolean hasNext() {
            if (current < list.size()) {
                return true;
            }
            return false;
        }

        /**
         * Get the current element and move cursor to the next
         */
        public Object next() {
            return list.get(current++);
        }

        /**
         * Remove the current element and refresh the list
         */
        public void remove() {
            delete(list.get(current)); // Delete the current element
            list.clear(); // Clear the list
            inorder(); // Rebuild the list
        }
    }

    /**
     * Remove all elements from the tree
     */
    public void clear() {
        root = null;
        size = 0;
    }
}