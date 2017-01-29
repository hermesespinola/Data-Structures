package heap;

import list.CircularLinkedList;
import list.ArrayLinearList;
import java.util.ListIterator;
import list.List;

public class FibonacciHeap <V extends Comparable<? super V>> implements Heap<V> {

  /**
  * set of heap-ordered trees
  */
  CircularLinkedList<FibonacciNode> roots;
  int rank, minIdx, size;
  FibonacciNode min;

  public FibonacciHeap() {
    clear();
  }

  public void clear() {
    this.roots = new CircularLinkedList<>();
    this.rank = -1;
    this.minIdx = -1;
    this.size = 0;
  }

  public void insert(V value) {
    FibonacciNode inserted = new FibonacciNode(value);
    roots.add(inserted);
    size++;
    if (roots.size() == 1) {
      rank = 0;
      minIdx = 0;
    } else {
      if (inserted.val.compareTo(min.val) < 0) {
        min = inserted;
        minIdx = roots.size() - 1;
      }
    }
  }


  public boolean isEmpty() {
    return size == 0;
  }

  public V peek() {
    return min.val;
  }

  @SuppressWarnings("unchecked")
  public V pop() {
    // The children of the min root
    List<FibonacciNode> children = min.children;
    roots.remove(minIdx); // remove the min node
    V pop = min.val;
    for (FibonacciNode c : children) {
      roots.add(c); // add its children to the root set
    }

    // update min value
    ListIterator<FibonacciNode> it = roots.iterator();
    while (it.hasNext()) {
      int idx = it.nextIndex();
      FibonacciNode root = it.next();
      if (min == null || root.val.compareTo(min.val) < 0) {
        min = root;
        minIdx = idx;
      }
    }

    int trees = roots.size(); // number of trees in the heap

    // array to see if there are roots with the same rank
    FibonacciNode[] rootsOfRank = (FibonacciNode[]) new Object[(int)Math.ceil(Math.log(trees) / Math.log(2))];

    for (FibonacciNode root : roots) {
      int r = root.rank();
      if (rootsOfRank[r] == null) {
        rootsOfRank[r] = root;
      } else {
        if (root.val.compareTo(rootsOfRank[r].val) < 0) {
          root.addChild(rootsOfRank[r]);
          roots.remove(rootsOfRank);
        } else {
          rootsOfRank[r].addChild(root);
        }
        rootsOfRank[r] = null;
      }
    }

    return pop;
  }

  public int size() {
    return this.size;
  }

  // public String toString();


  class FibonacciNode {

    FibonacciNode parent;
    List<FibonacciNode> children;
    int size;
    V val;
    boolean mark;

    {
      mark = false;
      children = new ArrayLinearList<>();
    }

    FibonacciNode(V val) {
      this.val = val;
      this.parent = null;
      this.size = 1;
    }

    void addChild(FibonacciNode node) {
      children.add(node);
      this.size++;
    }

    int rank() {
      return children.size();
    }
  }
}
