package nz.ac.auckland.se281.datastructures;

/**
 * An interface representing a stack data structure.
 *
 * @param <T> The type of elements stored in the stack.
 */
public interface Stack<T> {
  /**
   * Pushes an item onto the top of the stack.
   *
   * @param item The item to be pushed onto the stack.
   */
  void push(T item);

  /**
   * Removes and returns the item at the top of the stack.
   *
   * @return The item at the top of the stack.
   * @throws IllegalStateException If the stack is empty.
   */
  T pop();

  /**
   * Returns the item at the top of the stack without removing it.
   *
   * @return The item at the top of the stack.
   * @throws IllegalStateException If the stack is empty.
   */
  T peek();

  /**
   * Checks if the stack is empty.
   *
   * @return {@code true} if the stack is empty, {@code false} otherwise.
   */
  boolean isEmpty();

  /**
   * Returns the number of elements in the stack.
   *
   * @return The size of the stack.
   */
  int size();
}
