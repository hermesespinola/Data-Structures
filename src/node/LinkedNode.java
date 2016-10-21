public class LinkedNode<V> extends Node<V> {
  LinkedNode next;

  public LinkedNode(V val) {
    super(val);
    this.next = null;
  }

  public LinkedNode(V val, LinkedNode<V> next) {
    super(val);
    this.next = next;
  }

  public LinkedNode<V> getNext() {
    return this.next;
  }

  protected void setNext(LinkedNode<V> next) {
    this.next = next;
  }
}
