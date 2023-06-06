package nz.ac.auckland.se281.datastructures;

import java.util.EmptyStackException;

/**
 * A linked implementation of a stack data structure.
 *
 * @param <T> the type of elements stored in the stack
 */
public class LinkedStack<T> {
  private int length;
  private ListNode<T> top;

  /** Constructs an empty stack. */
  public LinkedStack() {
    length = 0;
    top = null;
  }

  /**
   * Pushes an element onto the top of the stack.
   *
   * @param data the element to be pushed onto the stack
   */
  public void push(T data) {
    ListNode<T> temp = new ListNode<>(data);
    temp.setNext(top);
    top = temp;
    length++;
  }

  /**
   * Removes and returns the element at the top of the stack.
   *
   * @return the element removed from the top of the stack
   * @throws EmptyStackException if the stack is empty
   */
  public T pop() throws EmptyStackException {
    if (isEmpty()) {
      throw new EmptyStackException();
    }
    // Returns the element at the top of the stack after removing it from the stack
    T result = top.getData();
    top = top.getNext();
    length--;
    return result;
  }

  /**
   * Returns the element at the top of the stack without removing it.
   *
   * @return the element at the top of the stack
   * @throws EmptyStackException if the stack is empty
   */
  public T peek() throws EmptyStackException {
    if (isEmpty()) {
      throw new EmptyStackException();
    }

    return top.getData();
  }

  /**
   * Checks if the stack is empty.
   *
   * @return true if the stack is empty, false otherwise
   */
  public boolean isEmpty() {
    return (length == 0);
  }

  /**
   * Returns the number of elements in the stack.
   *
   * @return the size of the stack
   */
  public int size() {
    return length;
  }

  /**
   * Returns a string representation of the stack.
   *
   * @return a string representation of the stack
   */
  public String toString() {
    StringBuilder result = new StringBuilder();
    ListNode<T> current = top;
    while (current != null) {
      result.append(current.toString()).append("\n");
      current = current.getNext();
    }

    return result.toString();
  }
}
