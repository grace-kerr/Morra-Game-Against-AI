package nz.ac.auckland.se281.datastructures;

public class Node<T> {
  private T value;
  private Node<T> next;
  private Node<T> prev;

  // constructor
  public Node() {}

  public Node(T value) {
    this.value = value;
    this.next = null;
    this.prev = null;
  }

  public Node(T value, Node<T> next) {
    this.value = value;
    this.next = next;
  }

  // getters and setters

  public void setNext(Node<T> n) {
    next = n;
  }

  public void setValue(T value) {
    this.value = value;
  }

  public Node<T> getNext() {
    return next;
  }

  public T getValue() {
    return value;
  }

  public Node<T> getPrev() {
    return prev;
  }

  public void setPrev(Node<T> prev) {
    this.prev = prev;
  }

  @Override
  public String toString() {
    return value.toString();
  }
}
