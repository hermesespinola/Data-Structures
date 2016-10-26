package matrix;

public interface Matrix<V>  {
  public V get(int i, int j);
  public V remove(int i, int j);
  public void add(int i, int j, V value);
  public int numColumns();
  public int numRows();
}
