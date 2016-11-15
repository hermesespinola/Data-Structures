package sorting;

import list.List;
import heap.Heap;
import queue.Queue;
import java.util.Random;
import java.util.Arrays;
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

  public static <V extends Comparable<? super V>> void tournamentSort(V[] in, V[] out) {
    SelectionTree.tournamentSort(in, out, SelectionTree.Type.Min);
  }

  public static <V extends Comparable<? super V>> void mergeSort(V[] in, V[] temp) {
    mergeSortUtil(in, 0, in.length - 1, temp);
  }

  private static <V extends Comparable<? super V>> void mergeSortUtil(V[] in, int low, int high, V[] temp) {
    if (low >= high) return;
    int middle = low + (high - low) / 2;

    // sort the left part
    mergeSortUtil(in, low, middle, temp);

    // sort the right part
    mergeSortUtil(in, middle + 1, high, temp);

    // merge both parts
    System.arraycopy(in, low, temp, low, high - low + 1);
    int i = low; // start of left part
    int j = middle + 1; // start of right part
    int k = low;
    while (i <= middle && j <= high) {
      if (temp[i].compareTo(temp[j]) < 0) {
        in[k] = temp[i];
        i++;
      } else {
        in[k] = temp[j];
        j++;
      }
      k++;
    }

    while (i <= middle) {
      in[k] = temp[i];
      k++;
      i++;
    }
  }

  public static <V extends Comparable<? super V>> void quickSort(V[] in) {
    Random r = new Random();
    quickSortUtil(in, 0, in.length - 1, r);
  }

  private static <V extends Comparable<? super V>> void quickSortUtil(V[] in, int low, int high, Random r) {
    if (low >= high) return;

    // partition
    int pivot = r.nextInt((high - low) + 1) + low;
    int i = low;
    while (i <= high) {
      if (pivot == i) i++;
      else if (i < pivot && in[i].compareTo(in[pivot]) > 0) {
        swap(in, pivot, pivot - 1);
        if (pivot - 1 != i) swap(in, pivot, i);
        pivot -= 1;
      } else if (i > pivot && in[i].compareTo(in[pivot]) < 0) {
        swap(in, pivot, pivot + 1);
        if (pivot + 1 != i) swap(in, pivot, i);
        pivot += 1;
      } else {
        i++;
      }
    }

    // sort left part and right part
    quickSortUtil(in, low, pivot - 1, r);
    quickSortUtil(in, pivot + 1, high, r);
  }

  private static <V> void swap(V[] arr, int i, int j) {
    V temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
  }

  public static void main(String[] args) {
    Integer[] arr = new Integer[] {38, 27, 43, 3, 9, 82, 10};
    quickSort(arr);
    System.out.println(Arrays.toString(arr));
    Integer[] arr2 = new Integer[] {3, 7, 8, 5, 2, 1, 9, 5, 4};
    quickSort(arr2);
    System.out.println(Arrays.toString(arr2));

  }
}
