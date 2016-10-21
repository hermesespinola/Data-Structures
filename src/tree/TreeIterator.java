package tree;

import java.util.Stack;
import node.BinaryTreeNode;
import java.util.Iterator;

public class TreeIterator<K, V> implements Iterator<V> {
  Stack<Tree<K, V>> stack;

  public TreeIterator(Tree<K, V> root) {
    stack = new Stack<Tree<K, V>>();
    while (root != null) {
      stack.push(root);
      root = root.left();
    }
  }

  public boolean hasNext() {
    return !stack.isEmpty();
  }

  public V next() {
    Tree<K, V> node = stack.pop();
    V result = node.value();
    if (node.right() != null) {
      node = node.right();
      while (node != null) {
        stack.push(node);
        node = node.left();
      }
    }
    return result;
  }
}
