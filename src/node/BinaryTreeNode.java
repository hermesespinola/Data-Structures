public class BinaryTreeNode<K, V> extends KeyValueNode<K, V> {
  BinaryTreeNode<K, V> left;
  BinaryTreeNode<K, V> right;

  public BinaryTreeNode<K, V> left() {
    return this.left;
  }

  public BinaryTreeNode<K, V> right() {
    return this.right;
  }
}
