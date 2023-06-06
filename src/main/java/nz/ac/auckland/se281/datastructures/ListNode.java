package nz.ac.auckland.se281.datastructures;

/**
 * A node used in linked data structures to store data and references to other nodes.
 *
 * @param <T> The type of data stored in the node, utilising generic.
 */
public class ListNode<T> {
  protected ListNode<T> next;
  protected T data;

  /** Creates an empty node. */
  public ListNode() {
    next = null;
    data = null;
  }

  /**
   * Creates a node storing the specified data.
   *
   * @param data The data to be stored in the node.
   */
  public ListNode(T data) {
    next = null;
    this.data = data;
  }

  /**
   * Returns the node that follows this one.
   *
   * @return The next node.
   */
  public ListNode<T> getNext() {
    return next;
  }

  /**
   * Sets the node that follows this one.
   *
   * @param node The node to be set as the next node.
   */
  public void setNext(ListNode<T> node) {
    next = node;
  }

  /**
   * Returns the data stored in this node.
   *
   * @return The data stored in the node.
   */
  public T getData() {
    return data;
  }

  /**
   * Sets the data stored in this node.
   *
   * @param elem The data to be set in the node.
   */
  public void setData(T elem) {
    data = elem;
  }

  /**
   * Returns the string representation of the data stored in this node.
   *
   * @return The string representation of the data.
   */
  public String toString() {
    return String.valueOf(data);
  }
}
