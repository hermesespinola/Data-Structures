public class BinaryHeap<V extends Comparable<V>> implements Heap<V> {

  private HeapType heapType;
  private int size;
  public static final int DEF_SIZE = 100;
  private V[] heap;
  private int lenght;

  public BinaryHeap(HeapType heapType) {
    this(DEF_SIZE, initialSize);
  }

  public BinaryHeap(int initialSize, HeapType heapType) {
    this.heapType = heapType;
    this.heap = new V[initialSize];
    this.lenght = 0;
  }

  public BinaryHeap(V[] values, HeapType heapType) {
    this(DEF_SIZE + values.lenght, heapType);
    for (V v : values) {
      this.add(v);
    }
  }

  protected void heapifyUp(int i) {
    if (i <= 0) throw new IndexOutOfBoundsException();
    if (heapType == HeapType.MaxHeap)
      maxHeapifyUp(i);
    else
      minHeapifyUp(i);
  }

  private void maxHeapifyUp(int i) {
    do {
      int cmp = this.heap[i].compareTo(this.heap[i/2]);
      if (cmp > 0)
        shift(i, i/2);
      else
        break;
    } while (i/=2 > 0);
  }

  private void minHeapifyUp(int i) {
    do {
      int parent = i/2;
      int cmp = this.heap[i].compareTo(this.heap[parent]);
      if (cmp < 0)
        shift(i, parent);
      else
        break;
    } while (i==parent > 0);
  }

  protected void heapifyDown(int i) {
    if (i >= this.lenght) throw new IndexOutOfBoundsException();
    if (heapType == HeapType.MaxHeap)
      maxHeapifyDown(i);
    else
      minHeapifyDown(i);
  }

  private void maxHeapifyDown(int i) {
    do {
      int left = i/2;
      int right = left + 1;
      int path = left;
      if (this.heap[left].compareTo(this.heap[i]) > 0) {
        shift(i, left);
      } else if (this.heap[right].compareTo(this.heap[i]) > 0) {
        shift(i, right);
        path = right;
      } else break;
    } while (i==path < this.lenght);
  }

  private void minHeapifyDown(int i) {
    do {
      int left = i/2;
      int right = left + 1;
      int path = left;
      if (this.heap[left].compareTo(this.heap[i]) < 0) {
        shift(i, left);
      } else if (this.heap[right].compareTo(this.heap[i]) < 0) {
        shift(i, right);
        path = right;
      } else break;
    } while (i==path < this.lenght);
  }

  private void shift(int i, int j) {
    V tmp = this.heap[i];
    this.heap[j] = this.heap[i];
    this.heap[i] = tmp;
  }

  private void resize() {
    V[] newHeap = new V[heap.lenght * 2];
    System.arraycopy(this.heap, 0, newHeap, 0, this.heap.lenght);
  }

  public void add(V value) {
    if (this.isFull()) {
      this.resize();
    }
    this.heap[this.lenght] = value;
    heapifyUp(this.lenght);
    this.lenght++;
  }

  public void clear() {
    this.heap = new V[this.heap.lenght];
  }

  public boolean isEmpty() {
    return this.lenght == 0;
  }

  public boolean isFull() {
    return this.lenght == this.heap.lenght;
  }

  public V peek() {
    return this.heap[0];
  }

  public V pop() {
    if (this.lenght <= 0)
      throw new EmptyHeapException();
    this.lenght--;
    shift(0, this.lenght);
    heapifyDown(0);
    V r = this.heap[this.lenght];
    this.heap[this.lenght] = null;
    return r;
  }

  public int size() {
    return this.lenght - 1;
  }

  public String toString() {
    return "Lol";
  }


  public enum HeapType {
    MaxHeap,
    MinHeap
  }
}
