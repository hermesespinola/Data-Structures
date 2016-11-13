package set;

import java.util.Arrays;

public class DisjointSet {
  int[] p; // array of parents
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
  public void makeSet(int n) {
    p = new int[n];
    for (int i = 0; i < n; i++) {
      p[i] = i;
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
  public int find(int x) {
    if (x < 0 || x >= numSets) {
      throw new IndexOutOfBoundsException();
    }
    int i = x;
    while (i != p[i]) i = p[i];
    p[x] = i;
    return i;
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
    int iParent = find(i);
    int jParent = find(j);
    if (iParent != jParent) {
      if (rank[iParent] <= rank[jParent]) {
        p[jParent] = iParent;
        size[iParent] += size[jParent];
        if (rank[iParent] == rank[jParent]) {
          rank[iParent] += 1;
        }
      } else {
        p[iParent] = jParent;
        size[jParent] += size[iParent];
        if (rank[iParent] == rank[jParent]) {
          rank[jParent] += 1;
        }
      }
      numSets--;
    }
  }

  public int numDisjointSets() {
    return numSets;
  }

  /**
  * find the size of the set i belongs to
  * @param i a node
  * @return the size of the set i belongs to
  */
  public int sizeOfSet(int i) {
    return size[find(i)];
  }

  public String toString() {
    return Arrays.toString(p);
  }

  public static void main(String[] args) {
    DisjointSet ds = new DisjointSet(10);
    ds.union(2, 6);
    ds.union(1, 2);
    System.out.println(ds);
    System.out.println(ds.numDisjointSets());
    System.out.println(ds.sizeOfSet(6));
  }
}
