// package nz.ac.auckland.se281.datastructures;

// public class LinkedListStack<T> implements Stack<T> {
//   private LinkedList<T> stack;
//   private int size;

//   public LinkedListStack() {
//     stack = new LinkedList<>();
//     size = 0;
//   }

//   public void push(T item) {
//     stack.prepend(item);
//     size++;
//   }

//   public T pop() {
//     if (isEmpty()) {
//       throw new IllegalStateException("Stack is empty");
//     }
//     T item = stack.get(0);
//     stack.remove(0);
//     // size--;
//     return item;
//   }

//   public T peek() {
//     if (isEmpty()) {
//       throw new IllegalStateException("Stack is empty");
//     }
//     return stack.get(0);
//   }

//   public boolean isEmpty() {
//     return size == 0;
//   }

//   public int size() {
//     return size;
//   }
// }
