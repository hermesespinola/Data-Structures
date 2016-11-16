package graph.unweighted;

import graph.Vertex;
import graph.Graph;

@SuppressWarnings("unchecked")
public interface UnweightedGraph <V extends Vertex<V>> extends Graph<V> {
  public void addEdge(int i, int j);
}
