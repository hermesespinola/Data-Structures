package graph;

import list.List;

public interface Vertex<V extends Vertex<V>> {
  public void setPrevious(V v);
  public V previous();
  public Integer distance();
  public void setDistance(Integer d);
  public List<V> adjacentVertices();
  public int getValue();
  public boolean equals(Object other);
}
