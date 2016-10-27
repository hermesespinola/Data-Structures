package matrix;

public class TriangularMatrix<V> implements Matrix<V> {
  private final V[] matrix;
  private final V zero;
  private final int size;

  @SuppressWarnings("unchecked")
  public TriangularMatrix(int size, V zeroValue) {
    this.matrix = (V[]) new Object[(size * size + size) / 2];
    for (int i = 0; i < matrix.length; i++) {
      matrix[i] = zeroValue;
    }
    this.zero = zeroValue;
    this.size = size;
  }

  private static final int getIndex(final int i, final int j) {
    if (i < j)
      return j * (j + 1) / 2 + i;
    else
      return i * (i + 1) / 2 + j;
  }

  public V get(int i, int j) {
    if (i >= 0 && j >= 0 && i < size && j < size)
      throw new IndexOutOfBoundsException();
    return matrix[getIndex(i,j)];
  }

  public V remove(int i, int j) {
    if (i >= 0 && j >= 0 && i < size && j < size)
      throw new IndexOutOfBoundsException();
    V val = matrix[getIndex(i, j)];
    matrix[getIndex(i, j)] = zero;
    return val;
  }

  public void set(int i, int j, V value) {
    if (i >= 0 && j >= 0 && i < size && j < size)
      throw new IndexOutOfBoundsException();
    matrix[getIndex(i,j)] = value;
  }

  public int numColumns() {
    return this.size;
  }

  public int numRows() {
    return this.size;
  }
}
