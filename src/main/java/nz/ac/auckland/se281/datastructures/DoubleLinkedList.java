package nz.ac.auckland.se281.datastructures;

/**
 * The `DoubleLinkedList` class is an implementation of a doubly linked list, which extends the
 * `LinkedList` class. It provides methods to prepend, append, insert, and remove elements from the
 * list.
 *
 * @param <T> the type of elements stored in the list
 */
public class DoubleLinkedList<T> extends LinkedList<T> {
  private Node<T> tail;

  /** Constructs an empty doubly linked list. The head and tail references are set to null. */
  public DoubleLinkedList() {
    super();
    this.tail = null;
  }

  /**
   * Adds an element to the beginning of the list (at the head).
   *
   * @param data the element to prepend to the list
   */
  @Override
  public void prepend(T data) {
    Node<T> newNode = new Node<>(data);
    if (isEmpty()) {
      // If the list is empty, set the new node as both the head and the tail
      head = newNode;
      tail = newNode;
    } else {
      // Set the previous node of the current head as the new node
      newNode.setNext(head);

      // Set the new node as the new head of the list
      head.setPrev(newNode);
      head = newNode;
    }
    size++;
  }

  /**
   * Adds an element to the end of the list (at the tail).
   *
   * @param data the element to append to the list
   */
  @Override
  public void add(T data) {
    Node<T> newNode = new Node<>(data);
    if (isEmpty()) {
      // Adding to an empty list
      head = newNode;
      tail = newNode;
    } else {
      // Adding to the end of the list
      newNode.setPrev(tail);
      tail.setNext(newNode);
      tail = newNode;
    }
    size++;
  }

  /**
   * Inserts an element at the specified index in the list.
   *
   * @param index the index at which to insert the element
   * @param data the element to be inserted
   * @throws IndexOutOfBoundsException if the index is out of range
   */
  @Override
  public void insert(int index, T data) {
    if (index < 0 || index > size) {
      throw new IndexOutOfBoundsException("Invalid index");
    }

    // Inserting at the end of the list
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
      // Inserting in the middle of the list
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
   * Removes the element at the specified index from the list.
   *
   * @param index the index of the element to be removed
   * @throws IndexOutOfBoundsException if the index is out of range
   */
  @Override
  public void remove(int index) {
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException("Invalid index");
    }

    if (index == 0) {
      // Removing the element at the head of the list
      head = head.getNext();
      if (head != null) {
        head.setPrev(null);
      } else {
        tail = null;
      }
    } else if (index == size - 1) {
      // Removing the element at the tail of the list
      tail = tail.getPrev();
      tail.setNext(null);
    } else {
      // Removing an element in the middle of the list
      Node<T> current = head;
      for (int i = 0; i < index; i++) {
        current = current.getNext();
      }
      Node<T> prevNode = current.getPrev();
      Node<T> nextNode = current.getNext();
      prevNode.setNext(nextNode);
      nextNode.setPrev(prevNode);
    }

    size--;
  }
}
