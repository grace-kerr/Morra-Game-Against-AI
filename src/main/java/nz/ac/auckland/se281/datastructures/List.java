package nz.ac.auckland.se281.datastructures;

/**
 * A list interface. Lists typically allow elements to be added, inserted, and removed at different
 * positions.
 *
 * @param <T> the type of elements in the list
 */
public interface List<T> {
  public void add(T item);

  public void prepend(T item);

  public T get(int pos) throws InvalidPositionException;

  public void insert(int pos, T data) throws InvalidPositionException;

  public void remove(int pos) throws InvalidPositionException;

  public int size();
}
