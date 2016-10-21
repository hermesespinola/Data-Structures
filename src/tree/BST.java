package tree;

import java.util.Stack;
import java.util.LinkedList;
import java.util.Queue;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import tree.Tree;
import tree.TreeIterator;

@SuppressWarnings("rawtypes")
public class BST <K extends Comparable<K>, V> implements Tree<K, V> {
  TreeNode<K, V> data;
  BST<K, V> leftTree;
  BST<K, V> rightTree;

  public BST(K key, V val) {
    this.data = new TreeNode<K, V>(key, val);
  }

  public boolean isEmpty() {
    return data == null;
  }

  public V get(K key) {
    int cmp = key.compareTo(data.key);
    if (cmp < 0)
      return leftTree != null ? leftTree.get(key) : null;
    else if (cmp > 0)
      return rightTree != null ? rightTree.get(key) : null;
    return data.val;
  }

  public void put(K key, V val) {
    BST<K, V> current = this;
    while (current != null) {
      int cmp = key.compareTo(current.data.key);
      if (cmp > 0) {
        if (current.rightTree == null) {
          current.rightTree = new BST<K, V>(key, val);
          return;
        }
        current = current.rightTree;
      } else if (cmp < 0) {
        if (current.leftTree == null) {
          current.leftTree = new BST<K, V>(key, val);
          return;
        }
        current = current.leftTree;
      } else {
        current.data.val = val;
        return;
      }
    }
  }

  public K key() {
    return this.data.key;
  }

  public V value() {
    return this.data.val;
  }

  public void add(K key, V val) {
    int cmp = key.compareTo(data.key);
    if (cmp < 0)
      if (leftTree == null)
        leftTree = new BST<K, V>(key, val);
      else
        leftTree.add(key, val);
    else if (cmp > 0)
      if (rightTree == null)
        rightTree = new BST<K, V>(key, val);
      else
        rightTree.add(key, val);
    else
      data.val = val;
  }

  public V remove(K key) {
    // get the parent of and the node such that node.key == key
    BST<K,V> parent = parentOf(key);
    if (parent == null) return null;
    boolean isChildLeftTree = parent.leftTree != null && parent.leftTree.data.key == key;
    BST<K,V> node = isChildLeftTree ? parent.leftTree : parent.rightTree;
    V v = node.data.val;

    if (node.leftTree != null && node.rightTree != null) {
      BST<K,V> substitute = node.leftTree.greater();
      node.remove(substitute.data.key);
      node.data = substitute.data;
    } else if (node.leftTree != null) {
      if (isChildLeftTree) parent.leftTree = node.leftTree;
      else parent.rightTree = node.leftTree;
    } else { // if (node.rightTree != null || node.leftTree == null && node.rightTree == null)
      if (isChildLeftTree) parent.leftTree = node.rightTree;
      else parent.rightTree = node.rightTree;
    }

    return v;
  }

  public BST<K,V> parentOf(K key) {
    int cmp = key.compareTo(data.key);
    if (cmp < 0 && leftTree != null)
      if (leftTree.data.key == key) return this;
      else return leftTree.parentOf(key);
    else if (cmp > 0 && rightTree != null)
      if (rightTree.data.key == key) return this;
      else return rightTree.parentOf(key);
    return null; // no such key in the tree
  }

  public boolean contains(K key) {
    return get(key) != null;
  }

  protected StringBuilder inOrder(StringBuilder result) {
    if (leftTree != null) leftTree.inOrder(result);
    result.append(" ").append(data.key).append(" ");
    if (rightTree != null) rightTree.inOrder(result);
    return result;
  }

  public String inOrder() {
    return inOrder(new StringBuilder()).toString();
  }

  protected StringBuilder preOrder(StringBuilder result) {
    result.append(" ").append(data.key).append(" ");
    if (leftTree != null) leftTree.preOrder(result);
    if (rightTree != null) rightTree.preOrder(result);
    return result;
  }

  public String preOrder() {
    return preOrder(new StringBuilder()).toString();
  }

  protected StringBuilder postOrder(StringBuilder result) {
    if (leftTree != null) leftTree.preOrder(result);
    if (rightTree != null) rightTree.preOrder(result);
    result.append(" ").append(data.key).append(" ");
    return result;
  }

  public String postOrder() {
    return postOrder(new StringBuilder()).toString();
  }

  public String preOrderIt() {
    StringBuilder result = new StringBuilder();
    Stack<BST<K, V>> nodeStack = new Stack<BST<K, V>> ();

    nodeStack.push(this);
    while (!nodeStack.empty()) {
      BST<K, V> currentNode = nodeStack.pop();
      if (currentNode != null) {
        result.append(' ').append(currentNode.data.key).append(' ');
        nodeStack.push(currentNode.rightTree);
        nodeStack.push(currentNode.leftTree);
      }
    }
    return result.toString();
  }

  public String levelOrder() {
    Queue<BST<K,V>> currentLevel = new LinkedList<>();
    StringBuilder result = new StringBuilder();
    BST<K, V> node = null;
    currentLevel.add(this);
    do {
      node = currentLevel.remove();
      result.append(" ").append(node.data.key).append(" ");
      if (node.leftTree != null)
        currentLevel.add(node.leftTree);
      if (node.rightTree != null)
        currentLevel.add(node.rightTree);
    } while (!currentLevel.isEmpty());
    return result.toString();
  }

  protected StringBuilder descending(StringBuilder result) {
    if (rightTree != null) rightTree.descending(result);
    result.append(" ").append(data.toString()).append(" ");
    if (leftTree != null) leftTree.descending(result);
    return result;
  }

  public String descending() {
    return descending(new StringBuilder()).toString();
  }

  protected void nodesAtLevel(int n, ArrayList<TreeNode<K, V>> target) {
    if (n == 0)
      target.add(data);
    if (leftTree != null) leftTree.nodesAtLevel(n-1, target);
    if (rightTree != null) rightTree.nodesAtLevel(n-1, target);
  }

  public ArrayList<TreeNode<K, V>> getNodesAtLevel(int n) {
    ArrayList<TreeNode<K, V>> nodes = new ArrayList<>();
    nodesAtLevel(n, nodes);
    return nodes;
  }

  public String atLevel(int n) {
    return getNodesAtLevel(n).toString();
  }

  public BST<K,V> greater() {
    if (rightTree == null) return this;
    return rightTree.greater();
  }

  public BST<K,V> lesser() {
    if (leftTree == null) return this;
    return rightTree.lesser();
  }

  public String greaterThan(K key) {
    BST<K,V> greaterSubTree = subTreeGreaterThan(key);
    return greaterSubTree != null ? greaterSubTree.descending() : null;
  }

  public BST<K,V> subTreeGreaterThan(K key) {
    int cmp = data.key.compareTo(key);
    if (cmp > 0) return this;
    return (rightTree != null) ? rightTree.subTreeGreaterThan(key) : null;
  }

  protected void getLeavesUtil(ArrayList<TreeNode<K, V>> target) {
    if (leftTree != null) leftTree.getLeavesUtil(target);
    if (rightTree != null) rightTree.getLeavesUtil(target);
    if (leftTree == null && rightTree == null) target.add(data);
  }

  public List<TreeNode<K, V>> getLeaves() {
    ArrayList<TreeNode<K, V>> leaves = new ArrayList<>();
    getLeavesUtil(leaves);
    return leaves;
  }

  public int howManyLeaves() {
    return getLeaves().size();
  }

  public String leaves() {
    return getLeaves().toString();
  }

  public int height() {
    int lh = leftTree != null ? leftTree.height() : 0,
        rh = rightTree != null ? rightTree.height() : 0;
    return 1 + Math.max(lh, rh);
  }

  public int size() {
    int ls = leftTree != null ? leftTree.size() : 0,
        rs = rightTree != null ? rightTree.size() : 0;
    return ls + 1 + rs;
  }

  public String toString() {
    return inOrder();
  }

  public BST<K, V> left() {
    return this.leftTree;
  }

  public BST<K, V> right() {
    return this.rightTree;
  }

  @Override
  public Iterator<V> iterator() {
    Iterator<V> it = new TreeIterator<K, V>(this);
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

    System.out.println("height = " + bst.height());
    System.out.println("# nodes: " + bst.size());
    System.out.println("# leaves: " + bst.howManyLeaves());
    System.out.println("leaves = " + bst.leaves());

    System.out.println('\n');
    for (int i = 0; i <= bst.size(); i++) {
      System.out.println(i + ": " + bst.get(i));
    }

    System.out.println("\nInorder:\t" + bst.inOrder());
    System.out.println("preorder:\t" + bst.preOrderIt());
    System.out.println("postorder:\t" + bst.postOrder());
    System.out.println("Levelorder:\t" + bst.levelOrder());
    System.out.println("descending:\t" + bst.descending());

    System.out.println("\nNodes in each level: ");
    for (int level = 0; level < bst.height(); level++) {
      System.out.println(bst.atLevel(level));
    }

    // Iterator
    for (String val : bst) {
      System.out.println(val);
    }

    // System.out.println("\ngreaterThan 6: " + bst.greaterThan(6));
    // System.out.println("\nremove 7");
    // System.out.println(bst.remove(7)); // 0 degree node
    // System.out.println("\nremove 9");
    // System.out.println(bst.remove(9)); // 1 degree node left = 8, now node(9) = node(8)
    //
    // System.out.println("Add 7");
    // bst.add(7, "siete");
    // System.out.println("\nremove 6");
    // System.out.println(bst.remove(6)); // 2 degree node left = 5, right = 8
    //
    // System.out.println("height = " + bst.height());
    // System.out.println("# nodes: " + bst.size());
    // System.out.println("# leaves: " + bst.howManyLeaves());
    // System.out.println("leaves = " + bst.leaves());
    //
    // System.out.println("\nNodes in each level: ");
    // for (int level = 0; level < bst.height(); level++) {
    //   System.out.println(bst.atLevel(level));
    // }
  }
}
