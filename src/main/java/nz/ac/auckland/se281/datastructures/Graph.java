package nz.ac.auckland.se281.datastructures;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

// This class declares the constructor and methods of a graph for the graph calculator to operate
// on.

/**
 * A graph that is composed of a set of verticies and edges.
 *
 * <p>You must NOT change the signature of the existing methods or constructor of this class.
 *
 * @param <T> The type of each vertex, that have a total ordering.
 */
public class Graph<T extends Comparable<T>> {

  private Set<T> vertices;
  private Set<Edge<T>> edges;
  private Map<T, LinkedList<Edge<T>>> adjacencyMap;

  public Graph(Set<T> verticies, Set<Edge<T>> edges) {
    // initialize the graph with the given verticies and edges
    this.vertices = verticies;
    this.edges = edges;
    adjacencyMap = new HashMap<T, LinkedList<Edge<T>>>();

    // // Initialize adjacency map with empty lists for each vertex
    // for (T vertex : vertices) {
    //   adjacencyMap.put(vertex, new LinkedList<Edge<T>>());
    // }

    // // Populate adjacency map with edges
    // for (Edge<T> edge : edges) {
    //   T source = edge.getSource();
    //   adjacencyMap.get(source).add(edge);
    // }
  }

  public Set<T> getRoots() {
    // Set<T> roots = new LinkedHashSet<>();

    // for (Map.Entry<T, LinkedList<Edge<T>>> entry : adjacencyMap.entrySet()) {
    //   T vertex = entry.getKey();
    //   LinkedList<Edge<T>> edges = entry.getValue();

    //   boolean hasOutgoingEdges = false;

    //   for (Edge<T> edge : edges) {
    //     if (edge.getSource().equals(vertex)) {
    //       hasOutgoingEdges = true;
    //       break;
    //     }
    //   }

    //   if (!hasOutgoingEdges) {
    //     // Vertex has no outgoing edges
    //     continue;
    //   }

    //   boolean hasIncomingEdges = false;

    //   for (LinkedList<Edge<T>> otherEdges : adjacencyMap.values()) {
    //     for (Edge<T> edge : otherEdges) {
    //       if (edge.getDestination().equals(vertex) && !edge.getSource().equals(vertex)) {
    //         hasIncomingEdges = true;
    //         break;
    //       }
    //     }
    //     if (hasIncomingEdges) {
    //       break;
    //     }
    //   }

    //   if (hasIncomingEdges) {
    //     // Vertex has at least 1 incoming edge and 1 outgoing edge
    //     // Do something with the vertex
    //   }
    // }
    throw new UnsupportedOperationException();
  }

  public boolean isReflexive() {
    for (T vertex : vertices) {
      boolean hasSelfLoop = false;

      for (Edge<T> edge : edges) {
        if (edge.getSource().equals(vertex) && edge.getDestination().equals(vertex)) {
          hasSelfLoop = true;
          break;
        }
      }

      if (!hasSelfLoop) {
        return false;
      }
    }

    return true;
  }

  public boolean isSymmetric() {
    // TODO: Task 1.
    throw new UnsupportedOperationException();
  }

  public boolean isTransitive() {
    // TODO: Task 1.
    throw new UnsupportedOperationException();
  }

  public boolean isAntiSymmetric() {
    // TODO: Task 1.
    throw new UnsupportedOperationException();
  }

  public boolean isEquivalence() {
    // TODO: Task 1.
    throw new UnsupportedOperationException();
  }

  public Set<T> getEquivalenceClass(T vertex) {
    // TODO: Task 1.
    throw new UnsupportedOperationException();
  }

  public List<T> iterativeBreadthFirstSearch() {
    // TODO: Task 2.
    throw new UnsupportedOperationException();
  }

  public List<T> iterativeDepthFirstSearch() {
    // TODO: Task 2.
    throw new UnsupportedOperationException();
  }

  public List<T> recursiveBreadthFirstSearch() {
    // TODO: Task 3.
    throw new UnsupportedOperationException();
  }

  public List<T> recursiveDepthFirstSearch() {
    // TODO: Task 3.
    throw new UnsupportedOperationException();
  }
}
