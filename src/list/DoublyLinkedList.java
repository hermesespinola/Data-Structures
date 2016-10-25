package list;

import java.util.ListIterator;
import java.util.NoSuchElementException;

public class DoublyLinkedList<T> implements List<T>  {
  int size;
  DNode<T> first;
  DNode<T> last;

  public DoublyLinkedList() {
    this.first = null;
    this.last = null;
    this.size = 0;
  }

  public ListIterator<T> iterator() {
    return new DoublyLinkedListIterator();
  }

  private void checkIndex(int index) {
    if (index < 0 || index > size) {
      throw new IndexOutOfBoundsException();
    }
  }

  public ListIterator<T> getIterator(int index) {
    checkIndex(index);
    return new DoublyLinkedListIterator(index);
  }

  public boolean empty() {
    return this.size == 0;
  }

  public int size() {
    return this.size;
  }

  public T get(int index) {
    checkIndex(index);
    return getNode(index).value;
  }

  private DNode<T> getNode(int index) {
    DNode<T> n = this.first;
    while (index > 0) {
      n = n.next;
      index--;
    }
    return n;
  }

  private void addFirst(T value) {
    DNode<T> newNode = new DNode<T>(null, value, this.first);
    if (this.empty()) {
      this.last = newNode;
    } else {
      this.first.prev = newNode;
    }
    this.first = newNode;
    this.size++;
  }

  private void addLast(T value) {
    DNode<T> newNode = new DNode<T>(this.last, value, null);
    if (this.empty()) {
      this.first = newNode;
    } else {
      this.last.next = newNode;
    }
    this.last = newNode;
    this.size++;
  }

  public int indexOf(T x) {
    return -1;
  }

  public T remove(int index) {
    checkIndex(index);
    DNode<T> toRemove = getNode(index);
    toRemove.prev.next = toRemove.next;
    toRemove.next.prev = toRemove.prev;
    size--;
    T r = toRemove.value;
    toRemove = null;
    return r;
  }

  public void add(int index, T element) {
    checkIndex(index);
    DNode<T> nextNode = getNode(index);
    DNode<T> newNode = new DNode<T>(nextNode.prev, element, nextNode);
    nextNode.prev.next = newNode;
    nextNode.prev = newNode;
    size++;
  }

  public void output() {
    System.out.println(this);
  }

  public String toString() {
    StringBuilder sb = new StringBuilder('[');
    DNode<T> node = this.first;
    while (node != null) {
      sb.append(" [");
      sb.append(node.value);
      sb.append("] ");
      node = node.next;
    }
    sb.append(']');
    return sb.toString();
  }

  private static class DNode<T> {
    T value;
    DNode<T> next;
    DNode<T> prev;

    public DNode(DNode<T> prev, T value, DNode<T> next) {
      this.value = value;
      this.prev = prev;
      this.next = next;
    }
  }

  class DoublyLinkedListIterator implements ListIterator<T> {
    DNode<T> next;
    DNode<T> ultimoVisitado = null;
    int nextIndex;

    public DoublyLinkedListIterator() {
      this.next = first;
      this.nextIndex = 0;
    }

    public DoublyLinkedListIterator(int index) {
      if (index == size) {
        this.next = null;
      } else {
        this.next = getNode(index);
      }
      this.nextIndex = index;
    }

    public void add(T el){};
    public void set(T el){};
    public void remove(T el){};
    public void remove(){};
    public int previousIndex() {
      return this.nextIndex - 1;
    }

    public int nextIndex() {
      return this.nextIndex;
    }

    public T previous() {
      if (!this.hasPrevious())
        throw new NoSuchElementException();
      if (this.next == null)
        this.next = last;
      else
        this.next = this.next.prev;

      this.ultimoVisitado = this.next;
      this.nextIndex--;
      return ultimoVisitado.value;
    }

    public T next() {
      if (this.hasNext()) {
        ultimoVisitado = this.next;
        this.nextIndex++;
        this.next = this.next.next;
        return this.ultimoVisitado.value;
      }
      else throw new NoSuchElementException();
    }

    public boolean hasNext() {
      return this.next != null;
    }

    public boolean hasPrevious() {
      return this.nextIndex > 0;
    }
  }
}
