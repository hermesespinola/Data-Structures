package node;

public class Node<T> {
  T value;

  public Node(T value) {
    this.value = value;
  }

  public void setValue(T value) {
    this.value = value;
  }

  public T getValue() {
    return this.value;
  }

  public String toString() {
    return this.value.toString();
  }
}
