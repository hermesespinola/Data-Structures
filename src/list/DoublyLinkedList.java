package list;

import java.util.ListIterator;
import java.util.NoSuchElementException;
import node.DoublyLinkedNode;

public class DoublyLinkedList<T> implements List<T>  {
  int size;
  DoublyLinkedNode<T> first;
  DoublyLinkedNode<T> last;

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
    return getNode(index).getValue();
  }

  private DoublyLinkedNode<T> getNode(int index) {
    DoublyLinkedNode<T> n = this.first;
    while (index > 0) {
      n = n.next();
      index--;
    }
    return n;
  }

  private void addFirst(T value) {
    DoublyLinkedNode<T> newNode = new DoublyLinkedNode<T>(value, this.first);
    if (this.empty()) {
      this.last = newNode;
    } else {
      this.first.setPrevious(newNode);
    }
    this.first = newNode;
    this.size++;
  }

  private void addLast(T value) {
    DoublyLinkedNode<T> newNode = new DoublyLinkedNode<T>(value, null, this.last);
    if (this.empty()) {
      this.first = newNode;
    } else {
      this.last.setNext(newNode);
    }
    this.last = newNode;
    this.size++;
  }

  public int indexOf(T x) {
    return -1;
  }

  public T remove(int index) {
    checkIndex(index);
    DoublyLinkedNode<T> toRemove = getNode(index);
    toRemove.previous().setNext(toRemove.next());
    toRemove.next().setPrevious(toRemove.previous());
    size--;
    T r = toRemove.getValue();
    toRemove = null;
    return r;
  }

  public void add(int index, T element) {
    checkIndex(index);
    DoublyLinkedNode<T> nextNode = getNode(index);
    DoublyLinkedNode<T> newNode = new DoublyLinkedNode<T>(element, nextNode, nextNode.previous());
    nextNode.previous().setNext(newNode);
    nextNode.setPrevious(newNode);
    size++;
  }

  public void output() {
    System.out.println(this);
  }

  public String toString() {
    StringBuilder sb = new StringBuilder("[ ");
    DoublyLinkedNode<T> node = this.first;
    while (node != null) {
      sb.append(node.getValue()).append(' ');
      node = node.next();
    }
    sb.append(']');
    return sb.toString();
  }

  class DoublyLinkedListIterator implements ListIterator<T> {
    DoublyLinkedNode<T> next;
    DoublyLinkedNode<T> lastVisited = null;
    int nIndex;

    public DoublyLinkedListIterator() {
      this.next = first;
      this.nIndex = 0;
    }

    public DoublyLinkedListIterator(int index) {
      if (index == size) {
        this.next = null;
      } else {
        this.next = getNode(index);
      }
      this.nIndex = index;
    }

    public void add(T el){};
    public void set(T el){};
    public void remove(T el){};
    public void remove(){};
    public int previousIndex() {
      return this.nIndex - 1;
    }

    public int nextIndex() {
      return this.nIndex;
    }

    public T previous() {
      if (!this.hasPrevious())
        throw new NoSuchElementException();
      if (this.next == null)
        this.next = last;
      else
        this.next = this.next.previous();

      this.lastVisited = this.next;
      this.nIndex--;
      return lastVisited.getValue();
    }

    public T next() {
      if (this.hasNext()) {
        lastVisited = this.next;
        this.nIndex++;
        this.next = this.next.next();
        return this.lastVisited.getValue();
      }
      else throw new NoSuchElementException();
    }

    public boolean hasNext() {
      return this.next != null;
    }

    public boolean hasPrevious() {
      return this.nIndex > 0;
    }
  }
}
