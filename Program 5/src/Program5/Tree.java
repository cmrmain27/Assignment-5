/*
* INFO I-211/CSCI C-202
* Tree.java
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


public interface Tree<E extends Comparable<E>> {

  /** Return true if the element is in the tree */
  public boolean search(E e);

  /** Insert element o into the binary tree
   * Return true if the element is inserted successfully */
  public boolean insert(E e);

  /** Delete the specified element from the tree
   * Return true if the element is deleted successfully */
  public boolean delete(E e);

  /** Inorder traversal from the root*/
  public void inorder();

  /** Postorder traversal from the root */
  public void postorder();

  /** Preorder traversal from the root */
  public void preorder();

  /** Get the number of nodes in the tree */
  public int getSize();

  /** Return true if the tree is empty */
  public boolean isEmpty();

  /** Return an iterator to traverse elements in the tree */
  public java.util.Iterator iterator();

}

