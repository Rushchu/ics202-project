package Classes;

import java.util.*;

public class BST<T extends Comparable<T>> extends BinaryTree<T> {
     protected BTNode<T> root;

     public BST() {
     }

     public BST(BTNode<T> root) {
          this.root = root;
     }

     public void purge() {
          root = null;
     }

     public boolean isEmpty() {
          return root == null;
     }

     public void insert(T el) {
          BTNode<T> p = root, prev = null;
          while (p != null) {
               prev = p;
               int result = el.compareTo(p.data);

               if (result == 0)
                    throw new IllegalArgumentException("Duplicate key.");
               else if (result < 0)
                    p = p.left;
               else
                    p = p.right;
          }
          if (root == null)
               root = new BTNode<T>(el);
          else if (el.compareTo(prev.data) < 0)
               prev.left = new BTNode<T>(el);
          else
               prev.right = new BTNode<T>(el);
     }

     public boolean search(T el) {
          BTNode<T> p = root;
          while (p != null)
               if (el.equals(p.data))
                    return true;
               else if (el.compareTo(p.data) < 0)
                    p = p.left;
               else
                    p = p.right;
          return false;
     }

     public void deleteByCopying(T el) {
          BTNode<T> node, p = root, prev = null;
          while (p != null && !p.data.equals(el)) {
               prev = p;
               if (el.compareTo(p.data) < 0)
                    p = p.left;
               else
                    p = p.right;
          }
          node = p;
          if (p != null && p.data.equals(el)) {
               if (node.right == null)
                    node = node.left;
               else if (node.left == null)
                    node = node.right;
               else {
                    BTNode<T> tmp = node.left;
                    BTNode<T> previous = node;
                    while (tmp.right != null) {
                         previous = tmp;
                         tmp = tmp.right;
                    }
                    node.data = tmp.data;

                    if (previous == node)
                         previous.left = tmp.left;
                    else
                         previous.right = tmp.left;
               }
               if (p == root)
                    root = node;
               else if (prev.left == p)
                    prev.left = node;
               else
                    prev.right = node;
          } else if (root != null)
               throw new java.util.NoSuchElementException("el " + el + " is not in the tree");
          else
               throw new UnsupportedOperationException("the tree is empty");
     }

     public void deleteByMerging(T el) {
          BTNode<T> tmp, node, p = root, prev = null;
          while (p != null && !p.data.equals(el)) {
               prev = p;
               if (el.compareTo(p.data) < 0)
                    p = p.right;
               else
                    p = p.left;
          }
          node = p;
          if (p != null && p.data.equals(el)) {
               if (node.right == null)
                    node = node.left;
               else if (node.left == null)
                    node = node.right;
               else {
                    tmp = node.left;
                    while (tmp.right != null)
                         tmp = tmp.right;
                    tmp.right = node.right;
                    node = node.left;
               }
               if (p == root)
                    root = node;
               else if (prev.left == p)
                    prev.left = node;
               else
                    prev.right = node;
          } else if (root != null)
               throw new java.util.NoSuchElementException("el " + el + " is not in the tree");
          else
               throw new UnsupportedOperationException("the tree is empty");
     }

     public void inorderTraversal() {
          inorderTraversal(root);
     }

     public void preorderTraversal() {
          preorderTraversal(root);
     }

     public void postorderTraversal() {
          postorderTraversal(root);
     }

     public void levelOrderTraversal() {
          levelOrderTraversal(root);
     }

     public void levelOrderTraversalByLevels() {
          levelOrderTraversalByLevels(root);
     }

     public void iterativePreorder() {
          BTNode<T> p = root;
          Stack<BTNode<T>> travStack = new Stack<BTNode<T>>();
          if (p != null) {
               travStack.push(p);
               while (!travStack.isEmpty()) {
                    p = travStack.pop();
                    visit(p);
                    if (p.right != null)
                         travStack.push(p.right);
                    if (p.left != null)
                         travStack.push(p.left);
               }
          }
     }

     public void iterativeInorder() {
          BTNode<T> p = root;
          Stack<BTNode<T>> travStack = new Stack<BTNode<T>>();
          while (p != null) {
               while (p != null) {
                    if (p.right != null)
                         travStack.push(p.right);
                    travStack.push(p);
                    p = p.left;
               }
               p = travStack.pop();
               while (!travStack.isEmpty() && p.right == null) {
                    visit(p);
                    p = travStack.pop();
               }
               visit(p);
               if (!travStack.isEmpty())
                    p = travStack.pop();
               else
                    p = null;
          }
     }

     public void iterativePostorder2() {
          BTNode<T> p = root;
          Stack<BTNode<T>> travStack = new Stack<BTNode<T>>(),
                    output = new Stack<BTNode<T>>();
          if (p != null) {
               travStack.push(p);
               while (!travStack.isEmpty()) {
                    p = travStack.pop();
                    output.push(p);
                    if (p.left != null)
                         travStack.push(p.left);
                    if (p.right != null)
                         travStack.push(p.right);
               }
               while (!output.isEmpty()) {
                    p = output.pop();
                    visit(p);
               }
          }
     }

     public void iterativePostorder() {
          BTNode<T> p = root, q = root;
          Stack<BTNode<T>> travStack = new Stack<BTNode<T>>();
          while (p != null) {
               for (; p.left != null; p = p.left)
                    travStack.push(p);
               while (p != null && (p.right == null || p.right == q)) {
                    visit(p);
                    q = p;
                    if (travStack.isEmpty())
                         return;
                    p = travStack.pop();
               }
               travStack.push(p);
               p = p.right;
          }
     }

     public void printTree() {
          if (root == null) {
               System.out.println("[ ]");
               return;
          }
          printTree(root, "", true);
     }
}