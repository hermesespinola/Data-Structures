package list;

import node.DoublyLinkedNode;
import java.util.ListIterator;

public class CircularLinkedList<T> extends DoublyLinkedList<T> {

  private static int modulus(int x, int mod) {
    return ((x % mod) + mod) % mod;
  }

  @Override
  protected DoublyLinkedNode<T> getNode(int index) {
    return super.getNode(modulus(index, super.size));
  }

  @Override
  public T get(int index) {
    return this.getNode(index).getValue();
  }

  @Override
  public ListIterator<T> getIterator(int index) {
    return new DoublyLinkedListIterator(modulus(index, super.size));
  }

  @Override
  public T remove(int index) {
    return super.remove(modulus(index, super.size));
  }

  @Override
  public void add(int index, T element) {
    super.add(modulus(index, super.size), element);
  }
}
