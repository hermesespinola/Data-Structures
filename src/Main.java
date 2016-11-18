import graph.weighted.directed.*;
import graph.Graph;

public class Main {
  public static void main(String[] args) {
    int vertexCount = 10;
    AdjacencyMatrix graph = new AdjacencyMatrix(vertexCount);
    graph.addEdge(0,0, 10);
    for (int i = 0; i < vertexCount; i += 2) {
      for (int j = 1; j < vertexCount; j += 2) {
        graph.addEdge(i, j, i);
      }
    }
    System.out.println(graph);
    Graph.BFS(graph, 3);
    System.out.println();
    Graph.DFS(graph, 3);
  }
}
