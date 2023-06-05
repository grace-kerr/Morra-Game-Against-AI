package nz.ac.auckland.se281.datastructures;

public class Queue<T> {
  private DLinkedList<T> list;

  public Queue() {
    list = new DLinkedList<>();
  }

  public void enqueue(T data) {
    list.add(data);
  }

  public T dequeue() {
    if (isEmpty()) {
      throw new IllegalStateException("Queue is empty");
    }
    T data = list.get(0);
    list.remove(0);
    return data;
  }

  public T peek() {
    if (isEmpty()) {
      throw new IllegalStateException("Queue is empty");
    }
    return list.get(0);
  }

  public int size() {
    return list.size();
  }

  public boolean isEmpty() {
    return list.isEmpty();
  }
}
