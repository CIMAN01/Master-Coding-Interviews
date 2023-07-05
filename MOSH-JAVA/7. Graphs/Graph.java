
import java.util.*;
import java.util.HashMap;
import java.util.Queue;
import java.util.Stack;

public class Graph {

    // Inner class
    private class Node {
        // inner field
        private String label;

        // inner constructor
        public Node(String label) {
            this.label = label;
        }

        @Override
        public String toString() {
            return label;
        }
    }

    // Outer fields
    private Map<String, Node> nodes = new HashMap<>();
    private Map<Node, List<Node>> adjacencyList = new HashMap<>();


    public void addNode(String label) {
        Node node = new Node(label);
        nodes.putIfAbsent(label, node);
        adjacencyList.putIfAbsent(node, new ArrayList<>()); // null check
    }


    public void addEdge(String from, String to) {
        Node fromNode = nodes.get(from);

        if (fromNode == null) {
            throw new IllegalArgumentException();
        }

        Node toNode = nodes.get(to);

        if (toNode == null) {
            throw new IllegalArgumentException();
        }

        adjacencyList.get(fromNode).add(toNode);
    }


    public void removeNode(String label) {
        Node node = nodes.get(label);

        if (node == null) {
            return;
        }

        for (Node n : adjacencyList.keySet()) {
            adjacencyList.get(n).remove(node); // remove target node from list (remove links)
        }
        // remove node from adjacency list and the list of nodes
        adjacencyList.remove(node);
        nodes.remove(node);
    }

    public void removeEdge(String from, String to) {
        Node fromNode = nodes.get(from);
        Node toNode = nodes.get(to);
        // check if nodes exists
        if (fromNode == null || toNode == null) {
            return;
        }
        // remove the target from list
        adjacencyList.get(fromNode).remove(toNode);
    }


    // DFS Traversal using Recursion
    public void traverseDFSRecursive(String source) {
        Node node = nodes.get(source);
        // handle an exception where the node is null
        if (node == null) {
            return;
        }

        traverseDFSRecursive(node, new HashSet<>());
    }

    // DFS Traversal using Recursion - Implementation
    private void traverseDFSRecursive(Node source, Set<Node> visited) {
        System.out.println(source); // print the source
        visited.add(source); // add the source to the visited hashset
        // visit all the current node's unvisited neighbors
        for (Node node : adjacencyList.get(source)) {
            // if the current node has not yet been visited, perform a recursive DFS traversal
            if (!visited.contains(node)) {
                traverseDFSRecursive(node, visited);
            }
        }
    }

    // DFS Traversal - Iterative Approach
    public void traverseDFSIterative(String source) {
        Node node = nodes.get(source);
        // handle an exception where the node is null
        if (node == null) {
            return;
        }
        // create a hashset for nodes visited
        Set<Node> visited = new HashSet<>();
        // create a stack and push the source node to it
        Stack<Node> stack = new Stack<>();
        stack.push(node);
        // as long as the stack is not empty, visit the current node and all of its unvisited neighbors
        while (!stack.isEmpty()) {
            Node current = stack.pop(); // pop the item from the stack and store it
            // if this item has been visited before, pop another node or item from the stack
            if (visited.contains(current)) {
                continue;
            }
            // otherwise, everytime a node is reached:
            System.out.println(current); // print the current node
            visited.add(current); // add it to the hashset of visited nodes
            // and finally, visit all the current node's unvisited neighbors
            for (Node neighbour : adjacencyList.get(current)) {
                // push each unvisited neighbor onto the stack
                if (!visited.contains(neighbour)) {
                    stack.push(neighbour);
                }
            }
        }
    }

    // Breadth First Traversal
    public void traverseBFS(String source) {
        var node = nodes.get(source);
        // check if node exists
        if (node == null) {
            return;
        }
        // create a new hash-set for nodes visited
        Set<Node> visited = new HashSet<>();
        // create a queue add the source to it
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(node);
        // as long as we have items (nodes) in the queue
        while (!queue.isEmpty()) {
            // remove current node/item in the front of the queue and store it
            Node current = queue.remove();
            // if this current node/item has been visited before, remove another item from the queue
            if (visited.contains(current)) {
                continue;
            }
            // otherwise, print the current node/item to the console
            System.out.println(current);
            visited.add(current); // add current item to the visited set
            // finally, look at all the current node's unvisited neighbors
            for (Node neighbour : adjacencyList.get(current)) {
                // if this node hasn't been visited before, add it to the back of the queue
                if (!visited.contains(neighbour)) {
                    queue.add(neighbour);
                }
            }
        }
    }

    // Topological Sort
    public List<String> topologicalSort() {
        Stack<Node> stack = new Stack<>();
        Set<Node> visited = new HashSet<>();

        // visit every node in the graph
        for (Node node : nodes.values()) {
            topologicalSort(node, visited, stack);
        }

        List<String> sorted = new ArrayList<>();

        // pop all the items from the stack and put them in a list
        while (!stack.empty()) {
            sorted.add(stack.pop().label); // .label needed for a List<String>
        }

        return sorted;
    }

    // Topological Sort - Implementation using recursion
    private void topologicalSort(Node node, Set<Node> visited, Stack<Node> stack) {
        // make sure not to visit the same node twice
        if (visited.contains(node)) {
            return;
        }

        visited.add(node); // add the node to the set of nodes

        // recursively visit all the children/neighbors of the current node
        for (Node neighbour : adjacencyList.get(node)) {
            topologicalSort(neighbour, visited, stack);
        }

        stack.push(node); // push the node to the stack after having visited all the node's children/neighbors
    }


    // Detect a cycle in a graph
    public boolean hasCycle() {
        // need three hash-sets to detect a cycle
        Set<Node> visiting = new HashSet<>(); // while visiting and not having visited all nodes yet
        Set<Node> visited = new HashSet<>(); // all the nodes that have been visited
        Set<Node> all = new HashSet<>(); // all the nodes that exist in the graph
        // start by adding all nodes that exist in the graph to the set "all"
        all.addAll(nodes.values());

        // cycle through all nodes in the set "all"
        while (!all.isEmpty()) {
            Node current = all.iterator().next(); // iterate over a set of objects
            // check if the graph contains a cycle
            if (hasCycle(current, all, visiting, visited)) {
                return true; // cycle found
            }
        }

        return false; // no cycle found
    }

    // Detect a cycle in a graph - Implements hasCycle() recursively
    private boolean hasCycle(Node node, Set<Node> all, Set<Node> visiting, Set<Node> visited) {
        // move node between sets (move node from all to visiting)
        all.remove(node);
        visiting.add(node);

        // visit all the node's neighbors
        for (Node neighbour : adjacencyList.get(node)) {
            // if neighbor has been visited, visit another one
            if (visited.contains(neighbour)) {
                continue;
            }
            // if neighbor already exists in visiting set there is a cycle
            if (visiting.contains(neighbour)) {
                return true; // cycle found
            }
            // otherwise, recursively visit other neighbors
            if (hasCycle(neighbour, all, visiting, visited)) {
                return true; // cycle found
            }
        }
        // move node from visiting to visited (have not found a cycle for the current node after having visited all its neighbors)
        visiting.remove(node);
        visited.add(node);

        return false; // cycle not found
    }


    // Print
    public void print() {
        for (Node source : adjacencyList.keySet()) {
            List<Node> targets = adjacencyList.get(source);
            // print the nodes if the list of nodes is not empty
            if (!targets.isEmpty()) {
                System.out.println(source + " is connected to " + targets);
            }
        }
    }


    // Test
    public static void main(String[] args) {
        // create new graph
        Graph graph = new Graph();
        // add some nodes
//        graph.addNode("A");
//        graph.addNode("B");
//        graph.addNode("C");
//        graph.addNode("D");
//        graph.addNode("X");
//        graph.addNode("A");
//        graph.addNode("B");
//        graph.addNode("P");
        graph.addNode("A");
        graph.addNode("B");
        graph.addNode("C");
        // add edges
//        graph.addEdge("A", "B");
//        graph.addEdge("B", "D");
//        graph.addEdge("D", "C");
//        graph.addEdge("A", "C");
//        graph.addEdge("A", "C");
//        graph.addEdge("X", "A");
//        graph.addEdge("X", "B");
//        graph.addEdge("A", "P");
//        graph.addEdge("B", "P");
        graph.addEdge("A", "B");
        graph.addEdge("B", "C");
        graph.addEdge("C", "A");
        // perform a traversal
//        graph.traverseBFS("C");
        // print the graph
//        graph.print();
//        graph.traverseDFSRecursive("A");
//        graph.traverseDFSIterative("C");
//        var list = graph.topologicalSort();
//        System.out.println(list);
        System.out.println(graph.hasCycle()); // true
    }


}

















