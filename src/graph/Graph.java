import list.List;
import queue.Queue;
import queue.ArrayQueue;
import stack.*;

@SuppressWarnings("unchecked")
public interface Graph <V extends Vertex<V>> {
  public void addEdge(int i, int j);
  public void removeEdge(int i, int j);
  public Vertex<V> getVertex(int vertex);
  public int vertexCount();
  public Vertex<V>[] getGraph();

  public static <V extends Vertex<V>> void BFS(Graph<V> g, int vertex) {
    for (Vertex<V> v : g.getGraph()) {
      v.setDistance(null);
      v.setPrevious(null);
    }

    Queue<Vertex<V>> q = new ArrayQueue<>(g.vertexCount());
    Vertex<V> root = g.getVertex(vertex);
    root.setDistance(0);
    q.enqueue(root);

    while (!q.empty()) {
      Vertex<V> current = q.dequeue();
      for (Vertex<V> neighbour : current.adjacentVertices()) {
        if (neighbour.distance() == null) {
          neighbour.setDistance(current.distance() + 1);
          neighbour.setPrevious((V)current);
          q.enqueue(neighbour);
        }
      }
    }
  }

  public static <V extends Vertex<V>> void DFS(Graph<V> g, int vertex) {
    for (Vertex<V> v : g.getGraph()) {
      v.setDistance(null);
      v.setPrevious(null);
    }

    Stack<Vertex<V>> s = new LinkedStack<>();
    Vertex<V> root = g.getVertex(vertex);
    root.setDistance(0);
    s.push(root);

    while (!s.empty()) {
      Vertex<V> current = s.pop();
      for (Vertex<V> neighbour : current.adjacentVertices()) {
        if (neighbour.distance() == null) {
          neighbour.setDistance(current.distance() + 1);
          neighbour.setPrevious((V)current);
          s.push(neighbour);
        }
      }
    }
  }
}
