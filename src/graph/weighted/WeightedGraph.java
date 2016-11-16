package graph.weighted;

import graph.Vertex;
import graph.Graph;

@SuppressWarnings("unchecked")
public interface WeightedGraph <V extends Vertex<V>> extends Graph<V> {
  public void addEdge(int i, int j, float weight);
}
