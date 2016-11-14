package sorting;

import heap.Heap;
import queue.Queue;
import list.List;
import tree.selection.SelectionTree;

public final class Sort {
  private Sort() {
    throw new RuntimeException("Cannot instantiate Sort class");
  }

  public static <V extends Comparable<? super V>> void heapSort(V[] in, V[] out) {
    Heap.heapSort(in, out, Heap.Type.Min);
  }

  public static void radixSort(List<Integer> data, int maxNumDigits) {
    Queue.radixSort(data, maxNumDigits);
  }

  public static <T extends Comparable<? super T>> void tournamentSort(T[] in, T[] out) {
    SelectionTree.tournamentSort(in, out, SelectionTree.Type.Min);
  }
}
