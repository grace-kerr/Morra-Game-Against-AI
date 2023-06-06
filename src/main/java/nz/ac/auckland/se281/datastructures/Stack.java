package nz.ac.auckland.se281.datastructures;

public interface Stack<T> {
  void push(T item);

  T pop();

  T peek();

  boolean isEmpty();

  int size();
}
