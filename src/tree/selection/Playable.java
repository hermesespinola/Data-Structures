package tree.selection;

import node.Node;

public abstract class Playable<V extends Comparable<? super V>> implements Comparable<Playable<V>> {
  protected int parent, index;
  V value;

  public Playable(V value) {
    this.value = value;
  }

  public V value() {
    return this.value;
  }

  public Playable<V> play(Playable<V> other, SelectionTree.Type type) {
    if (this.value() == null)
      return other;
    if (other == null || other.value() == null)
      return this;
    return (this.compareTo(other) * type.value() > 0) ? this : other;
  }

  public int compareTo(Playable<V> other) {
    return this.value.compareTo(other.value);
  }

  public String toString() {
    return (new StringBuilder()).append(value).toString();
  }
}
