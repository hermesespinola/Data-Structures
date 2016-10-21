package node;

abstract class Node<T> {
  T value;

  protected Node(T value) {
    this.value = value;
  }

  protected void setValue(T value) {
    this.value = value;
  }

  protected T getValue() {
    return this.value;
  }
}
