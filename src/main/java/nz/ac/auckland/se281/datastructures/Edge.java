package nz.ac.auckland.se281.datastructures;

/**
 * An edge in a graph that connects two verticies.
 *
 * <p>You must NOT change the signature of the constructor of this class.
 *
 * @param <T> The type of each vertex.
 */
public class Edge<T> {
  private T source;
  private T destination;

  public Edge(T source, T destination) {
    this.source = source;
    this.destination = destination;
  }

  public T getSource() {
    return source;
  }

  public T getDestination() {
    return destination;
  }

  public boolean compareEdges(Edge<T> edge1, Edge<T> edge2) {
    T source1 = edge1.getSource();
    T destination1 = edge1.getDestination();
    T source2 = edge2.getSource();
    T destination2 = edge2.getDestination();

    return source1.equals(source2) && destination1.equals(destination2);
  }
}
// This class declares the constructor and methods for an edge between a source vertex and a
// destination vertex in the graph.
