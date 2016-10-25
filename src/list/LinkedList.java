package list;

import node.SingleLinkedNode;
import java.util.Iterator;

public class LinkedList <T> implements List<T> {
  SingleLinkedNode<T> _firstNode;
  int _size;

  public LinkedList() {
    this._size = 0;
    this._firstNode = null;
  }

  @Override
  public boolean empty() {
    return this._firstNode.getValue() == null;
  }
  @Override
  public int size() {
    return this._size;
  }

  protected SingleLinkedNode<T> getNode(int idx) {
    SingleLinkedNode<T> tmp = this._firstNode;
    for (int i = 0; i < idx; i++) {
      tmp = tmp.next();
    }
    return tmp;
  }

  @Override
  public T get(int index) {
    if (index < 0 || index >= this._size)
      throw new IndexOutOfBoundsException();
    return getNode(index).getValue();
  }

  @Override
  public int indexOf(T x) {
    SingleLinkedNode<T> currentNode = this._firstNode;
    int i = 0;
    while (currentNode.getValue() != null) {
      if (currentNode.getValue() == x) return i;
      currentNode = currentNode.next();
      i++;
    }
    return -1;
  }

  @Override
  public T remove(int index) {
    if (index < 0 || index >= this._size)
      throw new IndexOutOfBoundsException();
    this._size--;
    if (index == 0) {
      T valIndex = this._firstNode.getValue();
      this._firstNode = this._firstNode.next();
      return valIndex;
    }
    SingleLinkedNode<T> backNode = this.getNode(index-1);
    T valIndex = backNode.next().getValue();
    backNode.setNext(backNode.next().next());
    return valIndex;
  }

  public void add(T element) {
    this.add(this._size, element);
  }

  @Override
  public void add(int index, T element) {
    if (index < 0 || index > this._size)
      throw new IndexOutOfBoundsException();

    SingleLinkedNode<T> newNode = new SingleLinkedNode<T>(element);
    if (index == 0) {
      newNode.setNext(this._firstNode);
      this._firstNode = newNode;
    } else {
      SingleLinkedNode <T> lastNode = this._firstNode;
      for (int i = 0; i < index - 1; i++) {
        lastNode = lastNode.next();
      }
      newNode.setNext(lastNode.next());
      lastNode.setNext(newNode);
    }
    this._size++;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("[ ");
    SingleLinkedNode<T> currentNode = this._firstNode;
    while (currentNode.next() != null) {
      sb.append(currentNode.getValue()).append(' ');
      currentNode = currentNode.next();
    } sb.append(currentNode.getValue()).append(']');
    return sb.toString();
  }

  public Iterator<T> iterator() {
    return new LinkedLinearListIterator();
  }

  protected class LinkedLinearListIterator implements Iterator<T> {
		protected SingleLinkedNode<T> nextNode;

    // TODO: inherit javadoc
		/**
		 * Returns if the iterator has another item or not.
		 *
		 * @return	true if has another item, false if not
		 */
		public boolean hasNext() {
			return this.nextNode.next() != null;
		}

		/**
		 * Returns the next item in the list.
		 *
		 * @return	next item in list
		 */
		public T next() {
			if (!hasNext()) {
				throw new NullPointerException("No more items in iterator.");
			}
			T item = this.nextNode.getValue();
			this.nextNode = this.nextNode.next();
			return item;
		}
  }
}
