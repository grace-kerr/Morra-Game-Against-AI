package nz.ac.auckland.se281.datastructures;

/**
 * The Linked List Class Has only one head pointer to the start node Nodes are indexed starting from
 * 0. List goes from 0 to size-1
 *
 * @author Partha Roop
 */
public class LinkedList<T> implements List<T> {
  protected Node<T> head;
  protected int size;

  public LinkedList() {
    this.head = null;
    this.size = 0;
  }

  // locate node should be a helper method to help with insert and delete

  // Key methods of the List interface

  /**
   * This method adds a node with specified data as the start node of the list
   *
   * @param data: an integer, which is the value of the Node
   * @return void
   */
  public void prepend(T data) {
    // Note -- works even if list is empty
    Node<T> n = new Node<T>(data);
    n.setNext(head);
    head = n;
  }

  /**
   * This method adds a node with specified data as the end node of the list
   *
   * @param data: an integer, which is the value of the Node
   * @return void
   */
  public void add(T data) {
    Node<T> newNode = new Node<>(data);

    if (isEmpty()) {
      head = newNode;
    } else {
      Node<T> current = head;
      while (current.getNext() != null) {
        current = current.getNext();
      }
      current.setNext(newNode);
      newNode.setPrev(current);
    }

    size++;
  }

  /**
   * This method fetches the value of a node at a given position
   *
   * @param pos: an integer, which is the position
   * @return the value at the position pos
   */
  public T get(int index) {
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException("Invalid index");
    }

    Node<T> current = head;
    for (int i = 0; i < index; i++) {
      current = current.getNext();
    }

    return current.getValue();
  }

  /**
   * This method fetches the value of a node at a given position
   *
   * @param pos: an integer, which is the position
   * @return the value at the position pos
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
    if (index == 0) {
      newNode.setNext(head);
      head.setPrev(newNode);
      head = newNode;
    } else {
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
   * This method removes a node at a given position
   *
   * @param pos: an integer, which is the position
   * @return void
   */
  public void remove(int index) {
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException("Invalid index");
    }

    if (index == 0) {
      head = head.getNext();
      if (head != null) {
        head.setPrev(null);
      }
    } else {
      Node<T> current = head;
      for (int i = 0; i < index - 1; i++) {
        current = current.getNext();
      }
      Node<T> removedNode = current.getNext();
      current.setNext(removedNode.getNext());
      if (current.getNext() != null) {
        current.getNext().setPrev(current);
      }
    }

    size--;
  }

  /**
   * This method returns the size of a list
   *
   * @param
   * @return the size of the list
   */
  public int size() {
    return size;
  }

  public boolean isEmpty() {
    return size == 0;
  }

  @Override
  public void append(T item) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'append'");
  }
}
