package nz.ac.auckland.se281.datastructures;

public class DoubleNode<T> extends Node<T> {
  private Node<T> prev;

  public DoubleNode(T value) {
    super(value);
    this.prev = null;
  }

  public DoubleNode(T value, Node<T> next, Node<T> prev) {
    super(value, next);
    this.prev = prev;
  }

  public Node<T> getPrev() {
    return prev;
  }

  public void setPrev(DoubleNode<T> prev) {
    this.prev = prev;
  }
}
