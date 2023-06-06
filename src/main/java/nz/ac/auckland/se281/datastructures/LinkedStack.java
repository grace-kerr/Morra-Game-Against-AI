package nz.ac.auckland.se281.datastructures;

import java.util.EmptyStackException;

public class LinkedStack<T> {
  private int length;
  private ListNode<T> top;

  // Constructor: Creates an empty stack.
  public LinkedStack() {
    length = 0;
    top = null;
  }

  // Adds the specified data to the top of this stack.
  public void push(T data) {
    ListNode<T> temp = new ListNode<>(data);
    temp.setNext(top);
    top = temp;
    length++;
  }

  // Removes the data at the top of this stack and returns a
  // reference to it. Throws an EmptyStackException if the stack
  // is empty.
  public T pop() throws EmptyStackException {
    if (isEmpty()) throw new EmptyStackException();
    T result = top.getData();
    top = top.getNext();
    length--;
    return result;
  }

  // Returns a reference to the data at the top of this stack.
  // The data is not removed from the stack. Throws an
  // EmptyStackException if the stack is empty.
  public T peek() throws EmptyStackException {
    if (isEmpty()) throw new EmptyStackException();

    return top.getData();
  }

  // Returns true if this stack is empty and false otherwise.
  public boolean isEmpty() {
    return (length == 0);
  }

  // Returns the number of elements in the stack.
  public int size() {
    return length;
  }

  // Returns a string representation of this stack.
  public String toString() {
    String result = "";
    ListNode<T> current = top;
    while (current != null) {
      result = result + current.toString() + "\n";
      current = current.getNext();
    }

    return result;
  }
}
