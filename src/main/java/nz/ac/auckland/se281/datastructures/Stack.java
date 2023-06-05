package nz.ac.auckland.se281.datastructures;

public class Stack<T> {
  private LinkedList<T> stack;

  public Stack() {
    stack = new LinkedList<>();
  }

  public void push(T item) {
    stack.prepend(item);
  }

  public T pop() {
    if (isEmpty()) {
      throw new IllegalStateException("Stack is empty");
    }
    T item = stack.get(0);
    stack.remove(0);
    return item;
  }

  public T peek() {
    if (isEmpty()) {
      throw new IllegalStateException("Stack is empty");
    }
    return stack.get(0);
  }

  public boolean isEmpty() {
    return stack.isEmpty();
  }

  public int size() {
    return stack.size();
  }
}
