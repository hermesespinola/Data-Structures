package graph.undirected;

import matrix.TriangularMatrix;
import list.ArrayLinearList;
import graph.Graph;
import list.List;

public class AdjacencyMatrix implements Graph<AMVertex> {
  TriangularMatrix<Boolean> matrix;
  AMVertex[] vertices;
  private int vertexCount; // matrix.size = vertexCount * vertexCount

  public AdjacencyMatrix(int vertexCount) {
    this.vertexCount = vertexCount;
    this.matrix = new TriangularMatrix<Boolean>(vertexCount, false);
    this.vertices = new AMVertex[vertexCount];
    for (int i = 0; i < vertexCount; i++) {
      vertices[i] = new AMVertex(i, this);
    }
  }

  public void addEdge(int i, int j) {
    if (i >= 0 && i < vertexCount && j > 0 && j < vertexCount) {
      matrix.set(i, j, true);
    }
  }

  public void removeEdge(int i, int j) {
    if (i >= 0 && i < vertexCount && j > 0 && j < vertexCount) {
      matrix.remove(i, j);
    }
  }

  public String toString() {
    StringBuilder sb = new StringBuilder().append(" |");
    for (int i = 0; i < matrix.numRows(); i++) {
      sb.append(i).append('|');
    }
    sb.append('\n');
    for (int i = 0; i < matrix.numRows(); i++) {
      sb.append(i).append('|');
      for (int j = 0; j < matrix.numColumns(); j++) {
        sb.append(matrix.get(i, j) ? 1 : 0).append(' ');
      }
      sb.append('\n');
    }
    return sb.toString();
  }

  public int vertexCount() {
    return this.vertexCount;
  }

  public AMVertex getVertex(int vertex) {
    return this.vertices[vertex];
  }

  public AMVertex[] getGraph() {
    return this.vertices;
  }

  public static void main(String[] args) {
    int vertexCount = 10;
    Graph<AMVertex> graph = new AdjacencyMatrix(vertexCount);
    graph.addEdge(0,0); // no self-loops allowed
    for (int i = 0; i < vertexCount; i += 2) {
      for (int j = 1; j < vertexCount; j += 2) {
        graph.addEdge(i, j);
      }
    }
    System.out.println(graph);
    Graph.BFS(graph, 3);
    System.out.println();
    Graph.DFS(graph, 3);
  }
}
