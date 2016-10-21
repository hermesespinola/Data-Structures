package node;

public class KeyValueNode<K, V> extends Node<V> {
  K key;

  public KeyValueNode(K key, V val) {
    super(val);
    this.key = key;
  }

  protected void setKey(K key) {
    this.key = key;
  }

  protected K getKey() {
    return this.key;
  }
}
