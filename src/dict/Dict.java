package dict;

import java.util.Iterator;

public interface Dict<K,V> {
  // TODO: change name to set
  public void add(K key, V value);
  public V remove(K key);
  // TODO: change name to get
  public V getValue(K key);
  public boolean contains(K key);
  public Iterable<K> keys();
  public Iterable<V> values();
  public boolean isEmpty();
  // TODO: change name to size
  public int getSize();
  public void clear();
}
