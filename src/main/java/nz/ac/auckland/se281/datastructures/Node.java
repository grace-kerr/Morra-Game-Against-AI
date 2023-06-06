package nz.ac.auckland.se281.datastructures;

/**
 * A node used in linked data structures to store a value and maintain references to other nodes.
 *
 * @param <T> The type of value stored in the node.
 */
public class Node<T> {
  protected T value;
  protected Node<T> next;
  protected Node<T> prev;

  /** Constructs an empty node. */
  public Node() {}

  /**
   * Constructs a node storing the inputted value.
   *
   * @param value The value to be stored in the node.
   */
  public Node(T value) {
    this.value = value;
    this.next = null;
    this.prev = null;
  }

  /**
   * Constructs a node storing the inputted value and referring to the given next node.
   *
   * @param value The value to be stored in the node.
   * @param next The next node.
   */
  public Node(T value, Node<T> next) {
    this.value = value;
    this.next = next;
  }

  /**
   * Sets the next node.
   *
   * @param n The node to be set as the next node.
   */
  public void setNext(Node<T> n) {
    next = n;
  }

  /**
   * Sets the value stored in this node.
   *
   * @param value The value to be set in the node.
   */
  public void setValue(T value) {
    this.value = value;
  }

  /**
   * Returns the next node.
   *
   * @return The next node.
   */
  public Node<T> getNext() {
    return next;
  }

  /**
   * Returns the value stored in this node.
   *
   * @return The value stored in the node.
   */
  public T getValue() {
    return value;
  }

  /**
   * Returns the previous node.
   *
   * @return The previous node.
   */
  public Node<T> getPrev() {
    return prev;
  }

  /**
   * Sets the previous node.
   *
   * @param prev The node to be set as the previous node.
   */
  public void setPrev(Node<T> prev) {
    this.prev = prev;
  }

  /**
   * Returns the string representation of the value stored in this node.
   *
   * @return The string representation of the value.
   */
  @Override
  public String toString() {
    return value.toString();
  }
}
