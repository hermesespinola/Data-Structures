package heap;

public interface Heap <V extends Comparable<? super V>> {
  public void clear();
  public void insert(V value);
  public boolean isEmpty();
  public boolean isFull();
  public V peek();
  public V pop();
  public int size();
  public String toString();

  public static enum Type {
    Max(1),
    Min(-1);

    private final int value;
    private Type(int value) {
      this.value = value;
    }
    public int value() {
      return value;
    }
  }

  public static <V extends Comparable<? super V>> void heapSort(V[] in, V[] out, Type type) {
    Heap<V> heap = new BinaryHeap<>(in.length, type);
    for (V el : in) {
      heap.insert(el);
    }
    for (int i = 0; i < in.length; i++) {
      out[i] = heap.pop();
    }
  }
}
