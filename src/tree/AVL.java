package tree;

/*
* Implementation of an AVL tree extending a binary search tree with key value pairs.
*/
public class AVL<K extends Comparable<K>, V> extends BST<K, V> {
  private static class AVLNode<K extends Comparable<K>, V> extends BSTNode<K, V> {
    protected int height;

    protected AVLNode(K key, V value, AVLNode<K,V> leftNode, AVLNode<K,V> rightNode, int height) {
      super(key, value, leftNode, rightNode);
      this.height = height;
    }

    protected AVLNode(K key, V value, AVLNode<K,V> leftNode, AVLNode<K,V> rightNode) {
      super(key, value, leftNode, rightNode);
    }

    protected AVLNode(K key, V value, int height) {
      this(key, value, null, null, height);
    }

    @Override
    @SuppressWarnings("unchecked")
    public AVLNode<K,V> left() {
      return (AVLNode<K,V>)super.left();
    }

    @Override
    @SuppressWarnings("unchecked")
    public AVLNode<K,V> right() {
      return (AVLNode<K,V>)super.right();
    }

    @Override
    protected void add(K other, V val) {
      int cmp = other.compareTo(this.key);
      if (cmp < 0) {
        if (this.left() == null)
          this.setLeft(new AVLNode<K, V>(other, val, null, null));
        else
          this.left().add(other, val);
        if (Math.abs(this.left().height - this.right().height) == 2)
          if (other.compareTo(this.left().getKey()) < 0)
            singleLeftRotation(this);
          else
            doubleLeftRotation(this);
      } else if (cmp > 0) {
        if (this.right() == null)
          this.setRight(new BSTNode<K, V>(other, val, null, null));
        else
          this.right().add(other, val);
        if (Math.abs(this.right().height - this.left().height) == 2)
          if (other.compareTo(this.right().getKey()) > 0)
            singleRightRotation(this);
          else
            doubleRightRotation(this);
      } else
        this.setValue(val);
      this.height = Math.max(this.left().height, this.right().height) + 1;
    }

    private void singleLeftRotation(AVLNode<K,V> x) {

    }

    private void singleRightRotation(AVLNode<K,V> x) {

    }

    private void doubleLeftRotation(AVLNode<K,V> x) {

    }

    private void doubleRightRotation(AVLNode<K,V> x) {

    }
  }

  public AVL() {
    super();
  }

  public AVL(K rootKey, V rootValue) {
    super(rootKey, rootValue);
  }

  @Override
  public void add(K key, V val) {
    root.add(key, val);
  }
}
