package tree;

import java.util.List;
import java.util.Iterator;

// TODO: remove key from all trees
public interface Tree<K, V> extends Iterable<V> {
  public boolean isEmpty();
  public void add(K key, V val);
  public V get(K key);
  public boolean contains(K key);
  public int size();
  public void remove(K key);
  public String toString();
  public int height();
  public Iterator<V> iterator();
}
