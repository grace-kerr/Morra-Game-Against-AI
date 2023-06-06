package nz.ac.auckland.se281.datastructures;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

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

  /**
   * Constructs a graph with the given vertices and edges.
   *
   * @param vertices the set of vertices to initialise the graph with
   * @param edges the set of edges to initialise the graph with
   */
  public Graph(Set<T> vertices, Set<Edge<T>> edges) {
    // initialize the graph with the given verticies and edges
    this.vertices = vertices;
    this.edges = edges;
  }

  /**
   * Retrieves the set of root vertices in the graph. A root vertex is a vertex that has no incoming
   * edges but has at least one outgoing edge. If a vertex is part of an equivalence class, the
   * vertex with the lowest value is considered a root.
   *
   * @return the set of root vertices in the graph
   */
  public Set<T> getRoots() {
    Set<T> roots = new TreeSet<>();

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

    return orderRoots(roots);
  }

  /**
   * Orders the elements in the input set based on their integer values. The input set is sorted in
   * ascending order.
   *
   * @param inputSet the set of elements to be ordered
   * @return a Set with the elements from the input set ordered by their integer values
   */
  public Set<T> orderRoots(Set<T> inputSet) {
    List<T> tempList = new ArrayList<>(inputSet);
    Collections.sort(
        tempList,
        new Comparator<T>() {

          @Override
          public int compare(T element1, T element2) {
            // Convert elements to integers for comparison
            int int1 = Integer.parseInt(element1.toString());
            int int2 = Integer.parseInt(element2.toString());

            // Compare the integer values
            return Integer.compare(int1, int2);
          }
        });

    return new LinkedHashSet<>(tempList);
  }

  // public Set<T> orderRoots(Set<T> inputSet) {
  //   // Create a new set to store the ordered integers
  //   Set<T> orderedSet = new TreeSet<>();
  //   Set<Integer> tempSet = new TreeSet<>();

  //   // Iterate over the input set and cast each element to int
  //   for (T element : inputSet) {
  //     intValue = Integer.parseInt(element.toString());
  //     tempSet.add(intValue);
  //   }

  //   orderedSet = tempSet.sort();

  //   return orderedSet;
  // }

  /**
   * Orders the elements of the inputted set and returns them as an ArrayList.
   *
   * @param unorderedSet the set of elements to be ordered
   * @param <T> the type of elements in the set and list
   * @return an ArrayList containing the ordered elements from the set
   */
  private ArrayList<T> orderSet(Set<T> unorderedSet) {
    ArrayList<T> orderedList = new ArrayList<>(unorderedSet);
    Collections.sort(orderedList);

    return orderedList;
  }

  /**
   * Retrieves the in-degree of the inputted vertex in the graph. The in-degree of a vertex is the
   * number of edges incoming to that vertex.
   *
   * @param vertex the vertex to calculate the in-degree for
   * @return the in-degree of the inputted vertex
   */
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

  /**
   * Retrieves the out-degree of the inputted vertex in the graph. The out-degree of a vertex is the
   * number of edges outgoing from that vertex.
   *
   * @param vertex the vertex to calculate the out-degree for
   * @return the out-degree of the specified vertex
   */
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

  /**
   * Finds and returns the minimum vertex in the given equivalence class. The vertex with the lowest
   * value is determined based on the ordering of the vertices in the equivalence class.
   *
   * @param equivalenceClass the set of vertices representing an equivalence class
   * @return the vertex (with the lowest value) in the equivalence class, or null if the equivalence
   *     class is empty
   */
  private T findMinimumVertex(Set<T> equivalenceClass) {
    ArrayList<T> orderedList = orderSet(equivalenceClass);
    return orderedList.isEmpty() ? null : orderedList.get(0);
  }

  /**
   * Checks if the graph is reflexive.
   *
   * @return {@code true} if the graph is reflexive, {@code false} otherwise
   */
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

  /**
   * Compares two edges to check if they have the same source and destination vertices. If so, they
   * would be considered equal.
   *
   * @param edge1 the first edge to compare
   * @param edge2 the second edge to compare
   * @return {@code true} if the edges have the same source and destination vertices, {@code false}
   *     otherwise
   */
  public boolean compareEdges(Edge<T> edge1, Edge<T> edge2) {
    T source1 = edge1.getSource();
    T destination1 = edge1.getDestination();
    T source2 = edge2.getSource();
    T destination2 = edge2.getDestination();

    return source1.equals(source2) && destination1.equals(destination2);
  }

  /**
   * Checks if an edge is contained in a set of edges.
   *
   * @param edge the edge to look for
   * @param edgeSet the set of edges which we are looking in
   * @return {@code true} if the edge is found in the set, {@code false} otherwise
   */
  public boolean isEdgeInSet(Edge<T> edge, Set<Edge<T>> edgeSet) {
    for (Edge<T> e : edgeSet) {
      if (compareEdges(e, edge)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Checks if the graph is symmetric.
   *
   * @return {@code true} if the graph is symmetric, {@code false} otherwise
   */
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

  /**
   * Checks if the graph is transitive.
   *
   * @return {@code true} if the graph is transitive, {@code false} otherwise
   */
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

  /**
   * Checks if the graph is antisymmetric.
   *
   * @return {@code true} if the graph is antisymmetric, {@code false} otherwise
   */
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

  /**
   * Checks if the graph represents an equivalence relation.
   *
   * @return {@code true} if the graph is an equivalence relation, {@code false} otherwise
   */
  public boolean isEquivalence() {
    return isReflexive() && isSymmetric() && isTransitive();
  }

  /**
   * Retrieves the equivalence class for an inputted vertex in the graph.
   *
   * @param vertex the vertex of which we are looking for the equivalence class
   * @return a set containing the vertices in the equivalence class of the inputted vertex
   */
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

  /**
   * Retrieves the lowest value from the set of roots in the graph.
   *
   * @return the lowest root value, or {@code Integer.MAX_VALUE} if there are no roots
   */
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

  /**
   * Performs an iterative breadth-first search traversal of the graph.
   *
   * @return a list containing the vertices in the order they were visited during the IBFS traversal
   */
  public List<T> iterativeBreadthFirstSearch() {
    Set<T> visited = new HashSet<>();
    List<T> result = new ArrayList<>();
    Queue<T> queue = new Queue<>();

    Set<T> roots = getRoots();
    if (roots.isEmpty()) {
      return result;
    }

    List<T> orderedRoots = orderSet(roots);

    for (T root : orderedRoots) {
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

  /**
   * Retrieves a set of vertices that neighbour an inputted vertex in the graph.
   *
   * @param vertex the vertex that we are looking for its neighbours
   * @return a set containing the neighbouring vertices of the inputted vertex
   */
  private Set<T> getNeighbours(T vertex) {
    Set<T> neighbours = new TreeSet<>();

    // look at all edges and add the destination of the edge if the source is the vertex inputted
    for (Edge<T> edge : edges) {
      if (edge.getSource().equals(vertex)) {
        neighbours.add(edge.getDestination());
      }
    }

    return orderRoots(neighbours);
  }

  /**
   * Performs an iterative depth-first search traversal of the graph.
   *
   * @return a list containing the vertices in the order they were visited during the IDFS traversal
   */
  public List<T> iterativeDepthFirstSearch() {
    List<T> visited = new ArrayList<>();
    LinkedStack<T> stack = new LinkedStack<>();

    Set<T> roots = getRoots();

    // If there are no roots, return empty vistied list
    if (roots.isEmpty()) {
      return visited;
    }

    for (T root : roots) {
      if (!visited.contains(root)) {
        stack.push(root);

        // iterative loop for DFS traversal using a stack
        while (!stack.isEmpty()) {
          T currentVertex = stack.pop();

          if (!visited.contains(currentVertex)) {
            visited.add(currentVertex);

            Set<T> neighbours = getNeighbours(currentVertex);
            List<T> orderedNeighbours = new ArrayList<>(neighbours);

            // Adding the ordered neighbours in reverse order to implement stack correctly
            for (int i = orderedNeighbours.size() - 1; i >= 0; i--) {
              T neighbour = orderedNeighbours.get(i);
              stack.push(neighbour);
            }
          }
        }
      }
    }

    return visited;
  }

  /**
   * Performs a recursive breadth-first search traversal of the graph.
   *
   * @return a list containing the vertices in the order they were visited during the RBFS traversal
   */
  public List<T> recursiveBreadthFirstSearch() {
    Set<T> visited = new HashSet<>();
    List<T> result = new ArrayList<>();
    Queue<T> queue = new Queue<>();

    Set<T> roots = getRoots();

    // If there are no roots, return empty results list
    if (roots.isEmpty()) {
      return result;
    }

    List<T> orderedRoots = orderSet(roots);

    for (T root : orderedRoots) {
      if (!visited.contains(root)) {
        queue.enqueue(root);
        visited.add(root);

        // Recursive call to perform BFS traversal from the current root
        recursiveBreadthFirstSearch(queue, visited, result);
      }
    }

    return result;
  }

  /**
   * Performs a recursive breadth-first search (BFS) traversal starting from the given queue.
   *
   * @param queue The queue containing the vertices to be traversed.
   * @param visited The set of vertices that have been visited.
   * @param result The list to store the traversed vertices in the order of traversal.
   * @param <T> The type of vertices being traversed.
   */
  private void recursiveBreadthFirstSearch(Queue<T> queue, Set<T> visited, List<T> result) {
    // The base case: If the queue is empty, the traversal is complete
    if (queue.isEmpty()) {
      return;
    }

    T currentVertex = queue.dequeue();
    result.add(currentVertex);

    Set<T> neighbours = getNeighbours(currentVertex);
    for (T neighbour : neighbours) {
      if (!visited.contains(neighbour)) {
        queue.enqueue(neighbour);
        visited.add(neighbour);
      }
    }

    // Recursive call to continue BFS on the remaining vertices in the queue
    recursiveBreadthFirstSearch(queue, visited, result);
  }

  /**
   * Performs a recursive depth-first search (DFS) traversal starting from the roots of the graph.
   * Returns a list of visited vertices in the order they were visited.
   *
   * @return The list of visited vertices in the order of traversal.
   */
  public List<T> recursiveDepthFirstSearch() {
    List<T> visited = new ArrayList<>();
    Set<T> roots = getRoots();
    List<T> orderedRoots = orderSet(roots);

    // Perform recursive depth-first search for each ordered root
    for (T root : orderedRoots) {
      if (!visited.contains(root)) {
        recursiveDepthFirstSearch(root, visited);
      }
    }

    return visited;
  }

  /**
   * Performs a recursive depth-first search (DFS) traversal starting from the given vertex. Adds
   * visited vertices to the provided list in the order they are visited.
   *
   * @param vertex The starting vertex for the DFS traversal.
   * @param visited The list to store the visited vertices in the order of traversal.
   */
  private void recursiveDepthFirstSearch(T vertex, List<T> visited) {
    visited.add(vertex);

    Set<T> neighbours = getNeighbours(vertex);
    List<T> orderedNeighbours = orderSet(neighbours);

    // Recursively look at the neighbours, ordered, of the current vertex
    for (T neighbour : orderedNeighbours) {
      if (!visited.contains(neighbour)) {
        recursiveDepthFirstSearch(neighbour, visited);
      }
    }
  }
}
