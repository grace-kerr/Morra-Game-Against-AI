package nz.ac.auckland.se281.datastructures;

public class DNode<T> extends Node<T> {
  private Node<T> prev;

  public DNode(T value) {
    super(value);
    this.prev = null;
  }

  public DNode(T value, Node<T> next, Node<T> prev) {
    super(value, next);
    this.prev = prev;
  }

  public Node<T> getPrev() {
    return prev;
  }

  public void setPrev(Node<T> prev) {
    this.prev = prev;
  }
}
