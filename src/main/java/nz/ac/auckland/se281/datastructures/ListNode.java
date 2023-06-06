package nz.ac.auckland.se281.datastructures;

public class ListNode<T> {
  public ListNode<T> next;
  public T data;

  // Creates an empty node.
  public ListNode() {
    next = null;
    data = null;
  }

  // Creates a node storing the specified data.
  public ListNode(T data) {
    next = null;
    this.data = data;
  }

  // Returns the node that follows this one.
  public ListNode<T> getNext() {
    return next;
  }

  // Sets the node that follows this one.
  public void setNext(ListNode<T> node) {
    next = node;
  }

  // Returns the data stored in this node.
  public T getData() {
    return data;
  }

  // Sets the data stored in this node.
  public void setData(T elem) {
    data = elem;
  }

  // Returns the string representation of the data stored in this node.
  public String toString() {
    return String.valueOf(data);
  }
}
