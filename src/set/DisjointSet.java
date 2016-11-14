package set;

import java.util.Arrays;
import node.KeyValueNode;
import dict.OpenAddressingDict;

@SuppressWarnings("rawtypes")
public class DisjointSet<V> {
  OpenAddressingDict<V, V> p; // array of parents
  OpenAddressingDict<V, Integer> rank; // rank of each set
  OpenAddressingDict<V, Integer> size; // size of each set
  int numSets; // number of sets

  /**
  * initialize n sets
  * @param n number of sets to initialize
  */
  @SuppressWarnings("unchecked")
  public DisjointSet(V[] elements) {
    p = new OpenAddressingDict<>(elements.length);
    rank = new OpenAddressingDict<>(elements.length);
    size = new OpenAddressingDict<>(elements.length);
    for (V element : elements) {
      p.add(element, element);
      rank.add(element, 0);
      size.add(element, 1);
    }
    numSets = elements.length;
  }

  /**
  * find the parent of the set x belongs to
  * @param x a node in a set
  * @return the parent of the set x belongs to
  */
  public V find(V x) {
    if (!p.contains(x)) {
      throw new IllegalArgumentException("value " + x + " is not in the disjoint set");
    }
    V i = x;
    while (!i.equals(p.getValue(i))) i = p.getValue(i);
    p.add(x, i);
    return p.getValue(x);
  }

  /**
  * find if nodes i and j belong to the same set
  * @param i a node
  * @param j another node
  * @return if i and j belong to the same set
  */
  public boolean isSameSet(V i, V j) {
    return find(i) == find(j);
  }

  /**
  * perform a union operation on the sets i and j belong to
  * @param i a node
  * @param j another node
  */
  public void union(V i, V j) {
    V iParent = find(i);
    V jParent = find(j);
    if (iParent != jParent) {
      if (rank.getValue(iParent) <= rank.getValue(jParent)) {
        p.add(jParent, iParent);
        size.add(iParent, size.getValue(iParent) + size.getValue(jParent));
        if (rank.getValue(iParent) == rank.getValue(jParent)) {
          rank.add(iParent, rank.getValue(iParent) + 1);
        }
      } else {
        p.add(iParent, jParent);
        size.add(jParent, size.getValue(jParent) + size.getValue(iParent));
        if (rank.getValue(iParent) == rank.getValue(jParent)) {
          rank.add(jParent, rank.getValue(jParent) + 1);
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
  public int sizeOfSet(V i) {
    return size.getValue(find(i));
  }

  public String toString() {
    return p.toString();
  }

  public static void main(String[] args) {
    DisjointSet<String> ds = new DisjointSet<>(new String[] {"Lucio", "Chuck", "Chacalaca", "Tequila", "Olote", "Palabra", "Wassaaa", "chucha", "Lulu", "Batman"});
    ds.union("Chuck", "Batman");
    ds.union("Batman", "Lucio");
    ds.union("Chacalaca", "Tequila");
    System.out.println(ds.find("Lucio"));
    System.out.println(ds.find("Batman"));
    System.out.println(ds.find("Chuck"));
    System.out.println();
    System.out.println(ds.find("Tequila"));
    System.out.println(ds.find("Wassaaa"));
    System.out.println();
    System.out.println(ds.numSets());
    System.out.println(ds.sizeOfSet("Palabra"));
    System.out.println(ds);
  }
}
