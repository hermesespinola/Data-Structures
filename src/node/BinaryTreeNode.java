package node;

public class BinaryTreeNode<K extends Comparable<K>, V> extends KeyValueNode<K, V> {
  BinaryTreeNode<K, V> left;
  BinaryTreeNode<K, V> right;

  public BinaryTreeNode(K key, V val, BinaryTreeNode<K, V> left, BinaryTreeNode<K, V> right) {
    super(key, val);
    this.left = left;
    this.right = right;
  }

  public BinaryTreeNode<K, V> left() {
    return this.left;
  }

  public BinaryTreeNode<K, V> right() {
    return this.right;
  }
}
