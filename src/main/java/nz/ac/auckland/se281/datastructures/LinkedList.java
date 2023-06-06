package nz.ac.auckland.se281.datastructures;

/**
 * An implementation of the List interface using a linked list data structure. This LinkedList Class
 * has only one head pointer to the start node.
 *
 * @param <T> The type of elements stored in the linked list.
 */
public class LinkedList<T> implements List<T> {
  protected Node<T> head;
  protected int size;

  /** Constructs an empty linked list. */
  public LinkedList() {
    this.head = null;
    this.size = 0;
  }

  /**
   * Adds a node with the inputted data at the beginning of the list.
   *
   * @param data The data to be prepended, of type T.
   */
  public void prepend(T data) {
    // Note -- works even if list is empty
    Node<T> n = new Node<T>(data);
    n.setNext(head);
    head = n;
  }

  /**
   * Adds a new node with the inputted data to the end of the list.
   *
   * @param data The data to be added, of type T.
   */
  public void add(T data) {
    Node<T> newNode = new Node<>(data);

    if (isEmpty()) {
      head = newNode;
    } else {
      Node<T> current = head;

      // Traverse till the last node in the list
      while (current.getNext() != null) {
        current = current.getNext();
      }

      // Add the new node to the end of the list
      current.setNext(newNode);
      newNode.setPrev(current);
    }

    size++;
  }

  /**
   * This method will get the value at the inputted index in the list.
   *
   * @param index The index of the value to get.
   * @return The value at the inputted index.
   * @throws IndexOutOfBoundsException If the index is out of bounds.
   */
  public T get(int index) {
    if (index < 0 || index > size) {
      throw new IndexOutOfBoundsException("Invalid index");
    }

    Node<T> current = head;

    // Traverse to the node at the inputted index
    for (int i = 0; i < index; i++) {
      current = current.getNext();
    }

    // Return the value of the current node
    return current.getValue();
  }

  /**
   * This method inserts a new node at the specified index in the list. The value of the new node is
   * set to the value passed in.
   *
   * @param index The index at which the new node should be inserted.
   * @param data The value to be inserted.
   * @throws IndexOutOfBoundsException If the index is out of bounds.
   */
  public void insert(int index, T data) {
    if (index < 0 || index > size) {
      throw new IndexOutOfBoundsException("Invalid index");
    }

    if (index == size) {
      add(data);
      return;
    }

    Node<T> newNode = new Node<>(data);
    // Inserting at the beginning of the list
    if (index == 0) {
      newNode.setNext(head);
      head.setPrev(newNode);
      head = newNode;
    } else {
      // Inserting value a position at any other place in the list
      Node<T> current = head;
      for (int i = 0; i < index - 1; i++) {
        current = current.getNext();
      }
      newNode.setNext(current.getNext());
      newNode.setPrev(current);
      current.getNext().setPrev(newNode);
      current.setNext(newNode);
    }

    size++;
  }

  /**
   * This method removes a node at a given position.
   *
   * @param index The index of the node that will be removed.\
   */
  public void remove(int index) {
    // first index is 0
    if (index == 0) {
      head = head.next;
    } else {
      // traverse to the node before the one we want to remove
      Node<T> current = head;
      for (int i = 0; i < index - 1; i++) {
        current = current.next;
      }

      // change its next pointer to skip past the offending node
      current.next = current.next.next;
    }
  }

  /**
   * Returns the size of the list. Calculates the size of the list, which is the number of nodes in
   * the list.
   *
   * @return the size of the list
   */
  public int size() {
    return size;
  }

  /**
   * Checks if the list is empty.
   *
   * @return {@code true} if the list is empty, {@code false} otherwise.
   */
  public boolean isEmpty() {
    return size == 0;
  }
}
