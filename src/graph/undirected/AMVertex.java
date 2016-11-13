package graph.undirected;

import matrix.TriangularMatrix;
import list.ArrayLinearList;
import graph.Vertex;
import list.List;

class AMVertex implements Vertex<AMVertex> {
  int value;
  AMVertex previous = null;
  Integer distance = null;
  AdjacencyMatrix graph;

  public AMVertex(int value, AdjacencyMatrix graph) {
    this.value = value;
    if (graph == null) {
      throw new NullPointerException("null graph");
    }
    this.graph = graph;
  }

  public void setPrevious(AMVertex v) {
    this.previous = v;
  }
  public AMVertex previous() {
    return this.previous;
  }

  public Integer distance() {
    return this.distance;
  }

  public void setDistance(Integer d) {
    this.distance = d;
  }

  public List<AMVertex> adjacentVertices() {
    List<AMVertex> adjacencyList = new ArrayLinearList<>(graph.vertexCount() - 1);
    for (int j = 0; j < graph.vertexCount(); j++) {
      if (graph.matrix.get(this.value, j)) {
        adjacencyList.add(graph.getVertex(j));
      }
    }
    return adjacencyList;
  }

  public int getValue() {
    return this.value;
  }

  public String toString() {
    return Integer.toString(this.value);
  }

  public boolean equals(Object other) {
    if (other == null) return false;
    if (other == this) return true;
    if (!(other instanceof AMVertex)) return false;
    return this.value == ((AMVertex)other).value;
  }

  @Override
  public int hashCode() {
    return ((Integer)this.value).hashCode();
  }
}
