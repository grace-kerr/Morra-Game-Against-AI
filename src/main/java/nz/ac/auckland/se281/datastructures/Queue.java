package nz.ac.auckland.se281.datastructures;

/**
 * A generic queue implementation using a double-linked list.
 *
 * @param <T> the type of elements stored in the queue
 */
public class Queue<T> {
  private DoubleLinkedList<T> list;

  public Queue() {
    list = new DoubleLinkedList<>();
  }

  /**
   * Adds the inputted element to the end of the queue. The element will be added after all the
   * others.
   *
   * @param data the element to be added to the queue
   */
  public void enqueue(T data) {
    list.add(data);
  }

  /**
   * This method removes and returns the element at the front of the queue.
   *
   * @return The element at the front of the queue, of tyep T.
   * @throws IllegalStateException If the queue is empty.
   */
  public T dequeue() {
    if (isEmpty()) {
      throw new IllegalStateException("Queue is empty");
    }

    // Returns the element at the front of the queue after removing it from the queue
    T data = list.get(0);
    list.remove(0);
    return data;
  }

  /**
   * Finds and returns the element at the front of the queue. The element will not be removed.
   *
   * @return The element at the front of the queue, of type T.
   * @throws IllegalStateException If the queue is empty.
   */
  public T peek() {
    if (isEmpty()) {
      throw new IllegalStateException("Queue is empty");
    }
    return list.get(0);
  }

  /**
   * Returns the number of elements in the queue. This method is O(1) time complexity.
   *
   * @return the number of elements in the queue
   */
  public int size() {
    return list.size();
  }

  /**
   * Checks if the queue is empty. This method is O(1) time complexity.
   *
   * @return true if the queue is empty, false otherwise
   */
  public boolean isEmpty() {
    return list.isEmpty();
  }
}
