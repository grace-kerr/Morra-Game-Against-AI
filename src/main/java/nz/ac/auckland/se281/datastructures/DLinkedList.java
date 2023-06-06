package nz.ac.auckland.se281.datastructures;

public class DLinkedList<T> extends LinkedList<T> {
  private Node<T> tail;

  public DLinkedList() {
    super();
    this.tail = null;
  }

  @Override
  public void prepend(T data) {
    DNode<T> newNode = new DNode<>(data);
    if (isEmpty()) {
      head = newNode;
      tail = newNode;
    } else {
      newNode.setNext(head);
      head.setPrev(newNode);
      head = newNode;
    }
    size++;
  }

  @Override
  public void add(T data) {
    DNode<T> newNode = new DNode<>(data);
    if (isEmpty()) {
      head = newNode;
      tail = newNode;
    } else {
      newNode.setPrev(tail);
      tail.setNext(newNode);
      tail = newNode;
    }
    size++;
  }

  @Override
  public void insert(int index, T data) {
    if (index < 0 || index > size) {
      throw new IndexOutOfBoundsException("Invalid index");
    }

    if (index == size) {
      add(data);
      return;
    }

    DNode<T> newNode = new DNode<>(data);
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

  @Override
  public void remove(int index) {
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException("Invalid index");
    }

    if (index == 0) {
      head = head.getNext();
      if (head != null) {
        head.setPrev(null);
      } else {
        tail = null;
      }
    } else if (index == size - 1) {
      tail = tail.getPrev();
      tail.setNext(null);
    } else {
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
