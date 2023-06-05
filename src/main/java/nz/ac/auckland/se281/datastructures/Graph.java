package nz.ac.auckland.se281.datastructures;

import java.util.HashSet;
import java.util.List;
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

  // private Map<T, LinkedList<Edge<T>>> adjacencyMap;

  public Graph(Set<T> verticies, Set<Edge<T>> edges) {
    // initialize the graph with the given verticies and edges
    this.vertices = verticies;
    this.edges = edges;
    // adjacencyMap = new HashMap<T, LinkedList<Edge<T>>>();

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

  // // a method to order a set of type T numerically
  // public orderedSet<T> order(Set<T> set) {
  //   orderedSet<T> orderedSet = new orderedSet<T>();
  //   for (T element : set) {
  //     int integer = Integer.parseInt(T.toString())
  //     orderedSet.add(element);
  //   }
  //   return orderedSet;
  // }

  public Set<T> getRoots() {
    Set<T> roots = new HashSet<>();

    // finding nodes with in-degree 0 and out-degree > 0
    for (T vertex : vertices) {
      if (getInDegree(vertex) == 0 && getInDegree(vertex) > 0) {
        roots.add(vertex);
      }
    }

    // findinf nodes that are part of an equivalence class
    for (T vertex : vertices) {
      Set<T> equivalenceClass = getEquivalenceClass(vertex);
      if (equivalenceClass.size() > 1) {
        T minimumValueNode = findMinimumValueNode(equivalenceClass);
        roots.add(minimumValueNode);
      }
    }

    return roots;
  }

  private int getInDegree(T vertex) {
    int inDegree = 0;

    for (Edge<T> edge : edges) {
      T destination = edge.getDestination();
      if (destination.equals(vertex)) {
        inDegree++;
      }
    }

    return inDegree;
  }

  private T findMinimumValueNode(Set<T> equivalenceClass) {
    return equivalenceClass.stream().min(Comparator.naturalOrder()).orElse(null);
  }

  // public Set<T> getRoots() {
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
  //   //   }
  //   // }
  //   throw new UnsupportedOperationException();
  // }

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

  public boolean compareEdges(Edge<T> edge1, Edge<T> edge2) {
    T source1 = edge1.getSource();
    T destination1 = edge1.getDestination();
    T source2 = edge2.getSource();
    T destination2 = edge2.getDestination();

    return source1.equals(source2) && destination1.equals(destination2);
  }

  public boolean isEdgeInSet(Edge<T> edge, Set<Edge<T>> edgeSet) {
    for (Edge<T> e : edgeSet) {
      if (compareEdges(e, edge)) {
        return true;
      }
    }
    return false;
  }

  public boolean isSymmetric() {
    for (Edge<T> edge : edges) {
      T source = edge.getSource();
      T destination = edge.getDestination();

      // Skip testing if source and destination are the same
      if (source.equals(destination)) {
        continue;
      }

      Edge<T> reverseEdge = new Edge<>(destination, source);

      boolean reverseEdgeExists = false;

      // Check if reverse edge exists
      for (Edge<T> testEdge : edges) {
        if (compareEdges(testEdge, reverseEdge)) {
          // Reverse edge exists
          reverseEdgeExists = true;
          break;
        }
      }

      // if reverse edge does not exist, graph is not symmetric
      if (!reverseEdgeExists) {
        return false;
      }
    }

    // All edges are symmetric
    return true;
  }

  public boolean isTransitive() {
    // iterate through all edges
    for (Edge<T> edge1 : edges) {
      T source1 = edge1.getSource();
      T destination1 = edge1.getDestination();

      for (Edge<T> edge2 : edges) {
        T source2 = edge2.getSource();
        T destination2 = edge2.getDestination();

        // return false if (v1,v2)∈E and (v2,v3)∈E hold, but (v1,v3)∉E
        if (destination1.equals(source2)) {
          Edge<T> transitiveEdge = new Edge<>(source1, destination2);
          if (!isEdgeInSet(transitiveEdge, edges)) {
            return false;
          }
        }
      }
    }
    return true;
  }

  public boolean isAntiSymmetric() {
    // iterate through all edges
    for (Edge<T> edge1 : edges) {
      T source1 = edge1.getSource();
      T destination1 = edge1.getDestination();

      for (Edge<T> edge2 : edges) {
        T source2 = edge2.getSource();
        T destination2 = edge2.getDestination();

        // return false if (v1,v2)∈E and (v2,v1)∈E hold, but the vertices are not the same
        if (destination1.equals(source2)
            && source1.equals(destination2)
            && !source1.equals(destination1)) {
          return false;
        }
      }
    }
    return true;
  }

  public boolean isEquivalence() {
    return isReflexive() && isSymmetric() && isTransitive();
  }

  public Set<T> getEquivalenceClass(T vertex) {
    Set<T> equivalenceClass = new HashSet<>();

    // only assess equivalence class, if graph is an equivalence relation
    if (isEquivalence()) {
      equivalenceClass.add(vertex);

      // add all vertices that are connected to the vertex
      for (Edge<T> edge : edges) {
        T source = edge.getSource();
        T destination = edge.getDestination();

        if (source.equals(vertex)) {
          equivalenceClass.add(destination);
        } else if (destination.equals(vertex)) {
          equivalenceClass.add(source);
        }
      }
    }

    return equivalenceClass;
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
