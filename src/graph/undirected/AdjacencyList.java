package graph.undirected;

import graph.*;
import list.LinkedList;

public class AdjacencyList implements Graph<ALVertex> {
  ALVertex[] matrix;
  private int vertexCount;

  public AdjacencyList(int vertexCount) {
    this.vertexCount = vertexCount;
    this.matrix = new ALVertex[vertexCount];
    for (int i = 0; i < vertexCount; i++) {
      this.matrix[i] = new ALVertex(i);
    }
  }

  public void addEdge(int i, int j) {
    if (i >= 0 && i < vertexCount && j > 0 && j < vertexCount) {
      if (i == j) return;
      ALVertex v1 = matrix[i];
      ALVertex v2 = matrix[2];
      this.matrix[i].connectVertex(this.matrix[j]);
      this.matrix[j].connectVertex(this.matrix[i]);
    } else throw new IndexOutOfBoundsException();
  }

  public void removeEdge(int i, int j) {
    if (i >= 0 && i < vertexCount && j > 0 && j < vertexCount) {
      if (i == j) return;
      this.matrix[i].removeAdjacentVertex(this.matrix[j]);
      this.matrix[j].removeAdjacentVertex(this.matrix[i]);
    } else throw new IndexOutOfBoundsException();
  }

  public ALVertex getVertex(int vertex) {
    if (vertex >= 0 && vertex < vertexCount) {
      return this.matrix[vertex];
    } else throw new IndexOutOfBoundsException();
  }

  public int vertexCount() {
    return this.vertexCount;
  }

  public ALVertex[] getGraph() {
    return this.matrix;
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append('{').append('\n');
    for (ALVertex v : matrix) {
      sb.append("  ").append(v).append('\n');
    }
    return sb.append('}').toString();
  }

  public static void main(String[] args) {
    Graph<ALVertex> g = new AdjacencyList(10);
    g.addEdge(1, 4);
    g.addEdge(1, 6);
    g.addEdge(4, 6);
    g.addEdge(0, 9);
    g.addEdge(4, 9);
    g.addEdge(8, 3);
    g.addEdge(7, 2);
    g.addEdge(6, 5);
    System.out.println(g);
    Graph.BFS(g, 4);
    System.out.println(g);
    Graph.DFS(g, 4);
    System.out.println(g);
  }
}

class ALVertex implements Vertex<ALVertex> {
  int value;
  Integer distance = null;
  ALVertex previous = null;
  LinkedList<ALVertex> adjacentVertices;

  public ALVertex(int value) {
    this.value = value;
    this.adjacentVertices = new LinkedList<>();
  }

  public ALVertex(int value, LinkedList<ALVertex> adjacentVertices) {
    this.value = value;
    this.adjacentVertices = adjacentVertices;
  }

  public void connectVertex(ALVertex other) {
    this.adjacentVertices.add(0, other);
  }

  public void removeAdjacentVertex(ALVertex other) {
    this.adjacentVertices.remove(adjacentVertices.indexOf(other));
  }

  public LinkedList<ALVertex> adjacentVertices() {
    return this.adjacentVertices;
  }

  public ALVertex previous() {
    return this.previous;
  }

  public void setPrevious(ALVertex prev) {
    this.previous = prev;
  }

  public Integer distance() {
    return this.distance;
  }

  public void setDistance(Integer d) {
    this.distance = d;
  }

  public int getValue() {
    return this.value;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder().append(this.value).append(": [ ");
    for (ALVertex v : adjacentVertices) {
      sb.append(v.value).append(' ');
    }
    return sb.append(']').toString();
  }

  @Override
  public boolean equals(Object other) {
    if (other == null) return false;
    if (other == this) return true;
    if (!(other instanceof ALVertex)) return false;
    return this.value == ((ALVertex)other).value;
  }

  @Override
  public int hashCode() {
    return ((Integer)this.value).hashCode();
  }
}
