package set;

import java.util.Arrays;
import node.Node;

@SuppressWarnings("rawtypes")
public class DisjointSet<V extends Node<Integer>> {
  V[] p; // array of parents
  int[] rank; // rank of each set
  int[] size; // size of each set
  int numSets; // number of sets

  public DisjointSet(int nSets) {
    this.makeSet(nSets);
  }

  /**
  * initialize n sets
  * @param n number of sets to initialize
  */
  @SuppressWarnings("unchecked")
  public void makeSet(int n) {
    p = (V[]) new Node[n];
    for (int i = 0; i < n; i++) {
      p[i] = (V) new Node<Integer>(i);
    }
    rank = new int[n];
    size = new int[n];
    for (int i = 0; i < n; i++) {
      size[i] = 1;
    }
    numSets = n;
  }

  /**
  * find the parent of the set x belongs to
  * @param x a node in a set
  * @return the parent of the set x belongs to
  */
  public V find(int x) {
    if (x < 0 || x >= numSets) {
      throw new IndexOutOfBoundsException();
    }
    int i = x;
    while (i != p[i].getValue()) i = p[i].getValue();
    p[x].setValue(i);
    return p[x];
  }

  /**
  * find if nodes i and j belong to the same set
  * @param i a node
  * @param j another node
  * @return if i and j belong to the same set
  */
  public boolean isSameSet(int i, int j) {
    return find(i) == find(j);
  }

  /**
  * perform a union operation on the sets i and j belong to
  * @param i a node
  * @param j another node
  */
  public void union(int i, int j) {
    V iParent = find(i);
    V jParent = find(j);
    if (iParent.getValue() != jParent.getValue()) {
      if (rank[iParent.getValue()] <= rank[jParent.getValue()]) {
        jParent.setValue(iParent.getValue());
        size[iParent.getValue()] += size[jParent.getValue()];
        if (rank[iParent.getValue()] == rank[jParent.getValue()]) {
          rank[iParent.getValue()] += 1;
        }
      } else {
        iParent.setValue(jParent.getValue());
        size[jParent.getValue()] += size[iParent.getValue()];
        if (rank[iParent.getValue()] == rank[jParent.getValue()]) {
          rank[jParent.getValue()] += 1;
        }
      }
      numSets--;
    }
  }

  public int numSets() {
    return numSets;
  }

  /**
  * find the size of the set i belongs to
  * @param i a node
  * @return the size of the set i belongs to
  */
  public int sizeOfSet(int i) {
    return size[find(i).getValue()];
  }

  public String toString() {
    return Arrays.toString(p);
  }

  public static void main(String[] args) {
    DisjointSet<Node<Integer>> ds = new DisjointSet<>(10);
    ds.union(2, 6);
    ds.union(1, 2);
    System.out.println(ds);
    System.out.println(ds.numSets());
    System.out.println(ds.sizeOfSet(6));
  }
}
