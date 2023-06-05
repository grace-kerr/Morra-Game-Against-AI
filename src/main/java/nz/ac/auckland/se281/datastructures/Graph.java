package nz.ac.auckland.se281.datastructures;

import java.util.ArrayList;
import java.util.Collections;
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

  public Set<T> getRoots() {
    Set<T> roots = new HashSet<>();

    for (T vertex : vertices) {
      if (getInDegree(vertex) == 0 && getOutDegree(vertex) > 0) {
        roots.add(vertex);
      }

      // find roots that are part of an equivalence class
      if (!getEquivalenceClass(vertex).isEmpty()) {
        Set<T> equivalenceClass = getEquivalenceClass(vertex);
        T minimumVertex = findMinimumVertex(equivalenceClass);
        roots.add(minimumVertex);
      }
    }

    return roots;
  }

  private int getInDegree(T vertex) {
    int inDegree = 0;

    // add to indegree count if vertex is a destination of an edge
    for (Edge<T> edge : edges) {
      T destination = edge.getDestination();
      if (destination.equals(vertex)) {
        inDegree++;
      }
    }

    return inDegree;
  }

  private int getOutDegree(T vertex) {
    int outDegree = 0;

    // add to outdegree count if vertex is a source of an edge
    for (Edge<T> edge : edges) {
      T source = edge.getSource();
      if (source.equals(vertex)) {
        outDegree++;
      }
    }

    return outDegree;
  }

  private T findMinimumVertex(Set<T> equivalenceClass) {
    ArrayList<T> orderedList = orderSet(equivalenceClass);
    return orderedList.isEmpty() ? null : orderedList.get(0);
  }

  private ArrayList<T> orderSet(Set<T> unorderedSet) {
    ArrayList<T> orderedList = new ArrayList<>(unorderedSet);
    Collections.sort(orderedList);

    return orderedList;
  }

  public boolean isReflexive() {
    for (T vertex : vertices) {
      boolean hasSelfLoop = false;

      // check if vertex has a self loop
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

  public int getLowestRootValue() {
    Set<T> roots = getRoots();

    // set the highest number, and lower it if a lower number is found
    int lowestValue = Integer.MAX_VALUE;
    for (T root : roots) {
      int value = Integer.parseInt(root.toString());
      if (value < lowestValue) {
        lowestValue = value;
      }
    }

    return lowestValue;
  }

  public List<T> iterativeBreadthFirstSearch() {
    Set<T> visited = new HashSet<>();
    List<T> result = new ArrayList<>();
    Queue<T> queue = new Queue<>();

    Set<T> roots = getRoots();
    if (roots.isEmpty()) {
      return result;
    }

    for (T root : roots) {
      queue.enqueue(root);
      visited.add(root);
    }

    while (!queue.isEmpty()) {
      T currentVertex = queue.dequeue();
      result.add(currentVertex);

      // look at all connect vertices to the current vertex
      Set<T> neighbours = getNeighbours(currentVertex);
      for (T neighbour : neighbours) {
        if (!visited.contains(neighbour)) {
          queue.enqueue(neighbour);
          visited.add(neighbour);
        }
      }
    }

    return result;
  }

  private Set<T> getNeighbours(T vertex) {
    Set<T> neighbours = new HashSet<>();

    // look at all edges and add the destination of the edge if the source is the vertex inputted
    for (Edge<T> edge : edges) {
      if (edge.getSource().equals(vertex)) {
        neighbours.add(edge.getDestination());
      }
    }

    return neighbours;
  }

  private Exception IllegalStateException(String string) {
    return null;
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
