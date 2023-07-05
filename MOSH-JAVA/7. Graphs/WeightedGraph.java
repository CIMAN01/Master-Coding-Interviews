
import java.nio.file.Path;
import java.util.*;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Stack;

//
public class WeightedGraph {

    // inner class
    private class Path {
        // fields
        private List<String> nodes = new ArrayList<>();
        // constructor
        public void add(String node) {
            nodes.add(node);
        }

        @Override
        public String toString() {
            return nodes.toString();
        }
    }

    // inner Node class
    private class Node {
        // fields
        private String label;
        private List<Edge> edges = new ArrayList<>();
        // constructor
        public Node(String label) {
            this.label = label;
        }

        @Override
        public String toString() {
            return label;
        }

        public void addEdge(Node to, int weight) {
            edges.add(new Edge(this, to, weight));
        }

        public List<Edge> getEdges() {
            return edges;
        }
    }

    // inner Edge class
    private class Edge {
        // fields
        private Node from;
        private Node to;
        private int weight;
        // constructor
        public Edge(Node from, Node to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return from + "->" + to; // A->B
        }
    }

    // inner NodeEntry class
    private class NodeEntry {
        // fields
        private Node node;
        private int priority;
        // constructor
        public NodeEntry(Node node, int priority) {
            this.node = node;
            this.priority = priority;
        }
    }

    // outer field(s)
    private Map<String, Node> nodes = new HashMap<>();

    // Add a node
    public void addNode(String label) {
        nodes.putIfAbsent(label, new Node(label));
    }

    // Add an edge
    public void addEdge(String from, String to, int weight) {
        Node fromNode = nodes.get(from);

        if (fromNode == null) {
            throw new IllegalArgumentException();
        }

        Node toNode = nodes.get(to);

        if (toNode == null) {
            throw new IllegalArgumentException();
        }

        fromNode.addEdge(toNode, weight);
        toNode.addEdge(fromNode, weight);
    }

    public void print() {
        for (var node : nodes.values()) {
            List<Edge> edges = node.getEdges();
            if (!edges.isEmpty())
                System.out.println(node + " is connected to " + edges);
        }
    }

    // Dijkstra's Shortest Path Algorithm
    public Path getShortestPath(String from, String to) {
        Node fromNode = nodes.get(from); // starting node
        // handle a null starting node
        if (fromNode == null) {
            throw new IllegalArgumentException();
        }
        Node toNode = nodes.get(to); // target node
        // handle a null target node
        if (toNode == null) {
            throw new IllegalArgumentException();
        }
        // create a hashmap to keep track of distances (needed for creating a table)
        Map<Node, Integer> distances = new HashMap<>();
        // assign the max value to each node in the table
        for (var node : nodes.values()) {
            distances.put(node, Integer.MAX_VALUE);
        }
        // set the distance of the starting node to itself to 0
        distances.replace(fromNode, 0);
        // create a hash-map to keep track of previous nodes (needed for creating a table)
        Map<Node, Node> previousNodes = new HashMap<>();
        // create a set for keeping track of the visited nodes (needed for creating a table)
        Set<Node> visited = new HashSet<>();
        // Create a priority queue and determine which node object should be moved to the front of the queue
        PriorityQueue<NodeEntry> queue = new PriorityQueue<>(Comparator.comparingInt(nodeEntry -> nodeEntry.priority));
        // add the starting node to the queue
        queue.add(new NodeEntry(fromNode, 0));
        // perform a breadth first traversal or BFS as long as the queue is not empty
        while (!queue.isEmpty()) {
            Node current = queue.remove().node; // at every iteration, remove an item from the queue and store it
            visited.add(current); // add node to the list of visited nodes
            // look at all the node's unvisited neighbors
            for (WeightedGraph.Edge edge : current.getEdges()) {
                // skip this node if already visited and move on to the next neighbor
                if (visited.contains(edge.to)) {
                    continue;
                }
                // calculate a new distance
                int newDistance = distances.get(current) + edge.weight;
                // compare new distance with what's already recorded in the table
                if (newDistance < distances.get(edge.to)) {
                    // if a smaller distance is found, update the table
                    distances.replace(edge.to, newDistance); // replace the distance (update table)
                    previousNodes.put(edge.to, current); // replace the previous node (update table)
                    queue.add(new NodeEntry(edge.to, newDistance)); // push neighbor to the queue (moves to the front of the queue)
                }
            }
        }
        // here the queue is now empty and the distance table has been updated
        return buildPath(previousNodes, toNode); // return the shortest path to the target node
    }

    // builds a path (shortest) by implementing a stack
    private Path buildPath(Map<Node, Node> previousNodes, Node toNode) {
        Stack<Node> stack = new Stack<>(); // create a new stack
        stack.push(toNode); // add ending node or target node to the stack
        Node previous = previousNodes.get(toNode); // get the target node's previous node
        // find all the previous nodes and add them to the stack
        while (previous != null) {
            stack.push(previous);
            previous = previousNodes.get(previous); // update previous to the current node's previous node
        }
        // create a path object
        Path path = new Path();
        // as long as the stack is not empty, pop each item and add it to the path
        while (!stack.isEmpty()) {
            path.add(stack.pop().label); // use label field to get a string
        }

        return path;
    }

    // detect a cycle in a graph
    public boolean hasCycle() {
        Set<Node> visited = new HashSet<>();
        // iterate over each node in the graph
        for (Node node : nodes.values()) {
            // if node hasn't been visited before, begin a DFS from this node
            if (!visited.contains(node) && hasCycle(node, null, visited)) {
                return true; // cycle detected
            }
        }

        return false; // no cycle detected after having visited all other nodes
    }

    // detect a cycle in a graph - implementation (recursive)
    private boolean hasCycle(Node node, Node parent, Set<Node> visited) {
        // add node to the visited set
        visited.add(node);
        // look at all the neighbors of the node
        for (WeightedGraph.Edge edge : node.getEdges()) {
            // if current neighbor is where the node came from, ignore it and get another neighbor
            if (edge.to == parent) {
                continue;
            }
            // if the current neighbor has previously been visited or if there is a cycle for this node, it means a cycle has been detected
            if (visited.contains(edge.to) || hasCycle(edge.to, node, visited)) {
                return true; // cycle detected
            }
        }
        // visit all the other neighbors
        return false; // cycle not detected after having visited all neighbors
    }

    // Prim's Algorithm: Extend the tree by adding the smallest connected edge (greedy algorithm)
    // A spanning tree should have exactly N-1 Edges (a tree is a graph without a cycle)
    public WeightedGraph getMinimumSpanningTree() {
        // create a new tree
        WeightedGraph tree = new WeightedGraph();
        // check if graph is empty and return an empty tree
        if (nodes.isEmpty()) {
            return tree;
        }
        // create a priority queue to add the smallest weight among edges that are connected to the nodes of the tree
        // (order edges based weight so that the min edge is placed at front of the queue)
        PriorityQueue<Edge> edges = new PriorityQueue<>(Comparator.comparingInt(edge -> edge.weight));
        // pick a node from the graph and add all of its edges to the priority queue
        Node startNode = nodes.values().iterator().next(); // pick a node from the graph as a starting point
        edges.addAll(startNode.getEdges()); // add all the edges of the node
        tree.addNode(startNode.label); // add node to the spanning tree
        // if queue is empty return an empty tree
        if (edges.isEmpty()) {
            return tree;
        }
        // repeat steps as long as tree doesn't contain all the nodes of the graph
        while (tree.nodes.size() < nodes.size()) {
            WeightedGraph.Edge minEdge = edges.remove(); // pick the minimum weight (edge) from the queue
            Node nextNode = minEdge.to; //
            // check if edge (min weight) is connected to a node that currently exists in the tree
            if (tree.containsNode(nextNode.label)) {
                continue; // pick another edge
            }
            // otherwise add target node and the edge to the tree
            tree.addNode(nextNode.label); // add target node to the tree
            tree.addEdge(minEdge.from.label, nextNode.label, minEdge.weight); // add edge (edge plus weight) to the tree
            // add all the edges connected to the target node to our priority queue
            // (in the next iteration all the edges can be compared in order to pick the one with the lowest weight)
            for (WeightedGraph.Edge edge : nextNode.getEdges()) {
                // only look for edges that connect to a node that hasn't been visited so far
                if (!tree.containsNode(edge.to.label)) {
                    edges.add(edge); // add an edge to the priority queue
                }
            }
        }

        return tree; // tree is complete
    }

    public boolean containsNode(String label) {
        return nodes.containsKey(label);
    }


    // TEST
    public static void main(String[] args) {
        // create a new graph
        WeightedGraph graph = new WeightedGraph();
        // add nodes to the graph
        graph.addNode("A");
        graph.addNode("B");
        graph.addNode("C");
        graph.addNode("D");
        // add edges to the graph
//        graph.addEdge("A", "B", 1);
//        graph.addEdge("B", "C", 2);
//        graph.addEdge("A", "C", 10);
//        graph.addEdge("A", "B", 0);
//        graph.addEdge("B", "C", 0);
//        graph.addEdge("C", "A", 0);
        graph.addEdge("A", "B", 3);
        graph.addEdge("B", "D", 4);
        graph.addEdge("C", "D", 5);
        graph.addEdge("A", "C", 1);
        graph.addEdge("B", "C", 2);
        // get the shortest path
        Path path = graph.getShortestPath("A", "C");
        // print the result to the console
//        System.out.println(path);
//        System.out.println(graph.hasCycle());
        WeightedGraph tree = graph.getMinimumSpanningTree();
        tree.print();
    }

}