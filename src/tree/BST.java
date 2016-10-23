package tree;

import java.util.Stack;
import java.util.LinkedList;
import java.util.Queue;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import node.BinaryTreeNode;
import tree.Tree;
import tree.TreeIterator;

@SuppressWarnings("rawtypes")
public class BST <K extends Comparable<K>, V> implements Tree<K, V> {
  protected static class BSTNode<K extends Comparable<K>, V> extends BinaryTreeNode<K, V> {
    protected BSTNode(K key, V val, BSTNode<K, V> left, BSTNode<K, V> right) {
      super(key, val, left, right);
    }

    protected V get(K other) {
      int cmp = other.compareTo(this.key);
      if (cmp < 0)
        return this.left() != null ? this.left().get(other) : null;
      else if (cmp > 0)
        return this.right() != null ? this.left().get(other) : null;
      return this.getValue();
    }

    protected void add(K other, V val) {
      int cmp = other.compareTo(this.key);
      if (cmp < 0)
        if (this.left() == null)
          this.setLeft(new BSTNode<K, V>(other, val, null, null));
        else
          this.left().add(other, val);
      else if (cmp > 0)
        if (this.right() == null)
          this.setRight(new BSTNode<K, V>(other, val, null, null));
        else
          this.right().add(other, val);
      else
        this.setValue(val);
    }

    public int size() {
      int ls = left() != null ? left().size() : 0,
          rs = right() != null ? right().size() : 0;
      return ls + 1 + rs;
    }

    @Override
    @SuppressWarnings("unchecked")
    public BSTNode<K,V> left() {
      return (BSTNode<K,V>)super.left();
    }

    @Override
    @SuppressWarnings("unchecked")
    public BSTNode<K,V> right() {
      return (BSTNode<K,V>)super.right();
    }

    protected V remove(K other) {
      // get the parent of and the node such that node.key == key
      BSTNode<K,V> parent = parentOf(other);
      if (parent == null) return null;
      boolean isChildLeftTree = parent.left() != null && parent.left().getKey() == key;
      BSTNode<K,V> node = isChildLeftTree ? parent.left() : parent.right();
      V v = node.getValue();

      if (node.right() != null && node.left() != null) {
        BSTNode<K,V> substitute = node.left().greater();
        node.key = substitute.key;
        node.setValue(substitute.getValue());
        node.left().remove(substitute.key);
      } else if (node.left() != null) {
        if (isChildLeftTree) parent.setLeft(node.left());
        else parent.setRight(node.left());
      } else { // if (node.right != null || node.left == null && node.right == null)
        if (isChildLeftTree) parent.setLeft(node.right());
        else parent.setRight(node.right());
      }

      return v;
    }

    protected BSTNode<K,V> parentOf(K other) {
      int cmp = other.compareTo(this.key);
      if (cmp < 0 && left() != null)
      if (left().getKey() == other) return this;
      else return left().parentOf(other);
      else if (cmp > 0 && right() != null)
      if (right().getKey() == key) return this;
      else return right().parentOf(key);
      return null; // no such key in the tree
    }

    public BSTNode<K,V> greater() {
      if (right() == null) return this;
      return right().greater();
    }

    public BSTNode<K,V> lesser() {
      if (left() == null) return this;
      return right().lesser();
    }

    protected BSTNode<K,V> subTreeGreaterThan(K other) {
      int cmp = this.key.compareTo(other);
      if (cmp > 0) return this;
      return (this.right() != null) ? right().subTreeGreaterThan(other) : null;
    }

    public StringBuilder inOrder(StringBuilder result) {
      if (this.left() != null) left().inOrder(result);
      result.append(' ').append(this.key).append(' ');
      if (this.right() != null) right().inOrder(result);
      return result;
    }

    public StringBuilder preOrder(StringBuilder result) {
      result.append(' ').append(this.key).append(' ');
      if (this.left() != null) left().preOrder(result);
      if (this.right() != null) right().preOrder(result);
      return result;
    }

    public StringBuilder postOrder(StringBuilder result) {
      if (this.left() != null) left().preOrder(result);
      if (this.right() != null) right().preOrder(result);
      result.append(' ').append(this.getKey()).append(' ');
      return result;
    }

    public int height() {
      int lh = left() != null ? left().height() : 0,
          rh = right() != null ? right().height() : 0;
      return 1 + Math.max(lh, rh);
    }
  }

  BSTNode<K, V> root;

  public BST() {
    this.root = null;
  }

  public BST(K key, V val) {
    this.root = new BSTNode<K, V>(key, val, null, null);
  }

  public boolean isEmpty() {
    return root == null;
  }

  public V get(K key) {
    return root.get(key);
  }

  public void put(K key, V val) {
    BSTNode<K, V> current = root;
    while (current != null) {
      int cmp = key.compareTo(current.getKey());
      if (cmp > 0) {
        if (current.right() == null) {
          current.setRight(new BSTNode<K, V>(key, val, null, null));
          return;
        }
        current = current.right();
      } else if (cmp < 0) {
        if (current.left() == null) {
          current.setLeft(new BSTNode<K, V>(key, val, null, null));
          return;
        }
        current = current.left();
      } else {
        current.setValue(val);
        return;
      }
    }
  }

  public void add(K key, V val) {
    root.add(key, val);
  }

  public V remove(K key) {
    return root.remove(key);
  }

  public boolean contains(K key) {
    return get(key) != null;
  }

  protected StringBuilder inOrder(StringBuilder result) {
    return root.inOrder(result);
  }

  public String inOrder() {
    return inOrder(new StringBuilder()).toString();
  }

  protected StringBuilder preOrder(StringBuilder result) {
    return root.preOrder(result);
  }

  public String preOrder() {
    return preOrder(new StringBuilder()).toString();
  }

  protected StringBuilder postOrder(StringBuilder result) {
    return root.postOrder(result);
  }

  public String postOrder() {
    return postOrder(new StringBuilder()).toString();
  }

  public String preOrderIt() {
    StringBuilder result = new StringBuilder();
    Stack<BSTNode<K, V>> nodeStack = new Stack<BSTNode<K, V>> ();

    nodeStack.push(root);
    while (!nodeStack.empty()) {
      BSTNode<K, V> currentNode = nodeStack.pop();
      if (currentNode != null) {
        result.append(' ').append(currentNode.getKey()).append(' ');
        nodeStack.push(currentNode.right());
        nodeStack.push(currentNode.left());
      }
    }
    return result.toString();
  }

  public String levelOrder() {
    Queue<BSTNode<K,V>> currentLevel = new LinkedList<>();
    StringBuilder result = new StringBuilder();
    BSTNode<K, V> node = null;
    currentLevel.add(root);
    do {
      node = currentLevel.remove();
      result.append(' ').append(node.getKey()).append(' ');
      if (node.left() != null)
        currentLevel.add(node.left());
      if (node.right() != null)
        currentLevel.add(node.right());
    } while (!currentLevel.isEmpty());
    return result.toString();
  }

  public int height() {
    return root.height();
  }

  public int size() {
    return root.size();
  }

  public String toString() {
    return inOrder();
  }

  @Override
  public Iterator<V> iterator() {
    Iterator<V> it = new TreeIterator<K, V>(root);
    return it;
  }

  public static void main(String[] args) {
    BST<Integer, String> bst = new BST<>(3, "tres");
    bst.add(2, "dos");
    bst.put(4, "cuatro");
    bst.put(1, "uno");
    bst.add(0, "cero");
    bst.put(6, "seis");
    bst.put(9, "nueve");
    bst.add(8, "ocho");
    bst.put(5, "cinco");
    bst.put(7, "siete");

    // Iterator
    for (String val : bst) {
      System.out.println(val);
    }
  }
}
