package tree;

import java.util.List;
import java.util.Iterator;

public interface Tree<K, V> extends Iterable<V> {
  public boolean isEmpty();
  public void add(K key, V val);
  public V get(K key);
  public boolean contains(K key);
  public Tree<K, V> lesser();
  public Tree<K, V> greater();
  public List<TreeNode<K, V>> getLeaves();
  public int size();
  public V remove(K key);
  public String toString();
  public int height();
  public Iterator<V> iterator();
  public Tree<K, V> left();
  public Tree<K, V> right();
  public K key();
  public V value();
}
