package nz.ac.auckland.se281.datastructures;

/**
 * A node that contains a value and maintains references to the next and previous nodes.
 *
 * @param <T> the type of value stored in the node
 */
public class DoubleNode<T> extends Node<T> {
  private Node<T> prev;

  /**
   * Constructs a DoubleNode with the specified value and sets the previous node reference to null.
   *
   * @param value the value to be stored in the node
   */
  public DoubleNode(T value) {
    super(value);
    this.prev = null;
  }

  /**
   * Constructs a DoubleNode with the specified value, next node reference, and previous node
   * reference.
   *
   * @param value the value to be stored in the node
   * @param next the reference to the next node
   * @param prev the reference to the previous node
   */
  public DoubleNode(T value, Node<T> next, Node<T> prev) {
    super(value, next);
    this.prev = prev;
  }

  /**
   * Returns the previous node. If the node is the head of the list, the previous node reference
   *
   * @return the previous node
   */
  public Node<T> getPrev() {
    return prev;
  }

  /**
   * Sets the previous node reference.
   *
   * @param prev the reference to the previous node
   */
  public void setPrev(DoubleNode<T> prev) {
    this.prev = prev;
  }
}
