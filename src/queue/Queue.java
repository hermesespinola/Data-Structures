package queue;

public interface Queue<V> {
  public boolean empty();
  public V front();
  public V rear();
  public V dequeue();
  public void enqueue(V object);
  public int size();
}
