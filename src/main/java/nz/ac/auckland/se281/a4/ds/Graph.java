package nz.ac.auckland.se281.a4.ds;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nz.ac.auckland.se281.a4.TwitterHandle;
//*******************************
//YOU SHOUD MODIFY THE SPECIFIED 
//METHODS  OF THIS CLASS
//HELPER METHODS CAN BE ADDED
//REQUIRED LIBRARIES ARE ALREADY 
//IMPORTED -- DON'T ADD MORE
//*******************************

public class Graph {

	/**
	 * Each node maps to a list of all the outgoing edges from that node
	 */
	protected Map<Node<String>, LinkedList<Edge<Node<String>>>> adjacencyMap;
	/**
	 * root of the graph, to know where to start the DFS or BFS
	 */
	protected Node<String> root;

	/**
	 * !!!!!! You cannot change this method !!!!!!!
	 */

	/**
	 * Creates a Graph
	 * 
	 * @param edges a list of edges to be added to the graph
	 */
	public Graph(List<String> edges) {
		adjacencyMap = new LinkedHashMap<>();
		int i = 0;
		if (edges.isEmpty()) {
			throw new IllegalArgumentException("edges are empty");
		}

		for (String edge : edges) {
			String[] split = edge.split(",");
			Node<String> source = new Node<String>(split[0]);
			Node<String> target = new Node<String>(split[1]);
			Edge<Node<String>> edgeObject = new Edge<Node<String>>(source, target);

			if (!adjacencyMap.containsKey(source)) {
				adjacencyMap.put(source, new LinkedList<Edge<Node<String>>>());
			}
			adjacencyMap.get(source).append(edgeObject);

			if (i == 0) {
				root = source;
			}
			i++;
		}
	}

	/**
	 * This method returns a boolean based on whether the input sets are reflexive.
	 * 
	 * TODO: Complete this method (Note a set is not passed in as a parameter but a
	 * list)
	 * 
	 * @param set      A list of TwitterHandles
	 * @param relation A relation between the TwitterHandles
	 * @return true if the set and relation are reflexive
	 */
	public boolean isReflexive(List<String> set, List<String> relation) {
		for (String twitterHandles : set) {
			boolean check = false;
			for (String pair : relation) {
				if (pair.indexOf(twitterHandles) != pair.lastIndexOf(twitterHandles)) {
					check = true;
				}
			}
			if (!check) {
				return false;
			}
		}
		return true;
	}

	/**
	 * This method returns a boolean based on whether the input set is symmetric.
	 * 
	 * If the method returns false, then the following must be printed to the
	 * console: "For the graph to be symmetric tuple: " + requiredElement + " MUST
	 * be present"
	 * 
	 * TODO: Complete this method (Note a set is not passed in as a parameter but a
	 * list)
	 * 
	 * @param relation A relation between the TwitterHandles
	 * @return true if the relation is symmetric
	 */
	public boolean isSymmetric(List<String> relation) {
		for (String pair1 : relation) {
			boolean check = false;
			int comma = pair1.indexOf(",");
			String vertex1 = pair1.substring(0, comma);
			String vertex2 = pair1.substring(comma + 1);
			for (String pair2 : relation) {
				int comma2 = pair2.indexOf(",");
				String vertex3 = pair2.substring(0, comma2);
				String vertex4 = pair2.substring(comma2 + 1);
				if ((vertex1.equals(vertex4)) && (vertex2.equals(vertex3))) {
					check = true;
				}
			}
			if (!check) {
				System.out.println(
						"For the graph to be symmetric tuple: " + vertex2 + "," + vertex1 + " MUST be present");
				return false;
			}
		}
		return true;
	}

	/**
	 * This method returns a boolean based on whether the input set is transitive.
	 * 
	 * If the method returns false, then the following must be printed to the
	 * console: "For the graph to be transitive tuple: " + requiredElement + " MUST
	 * be present"
	 * 
	 * TODO: Complete this method (Note a set is not passed in as a parameter but a
	 * list)
	 * 
	 * @param relation A relation between the TwitterHandles
	 * @return true if the relation is transitive
	 */
	public boolean isTransitive(List<String> relation) {
		for (String pair1 : relation) {
			String vertex1 = pair1.substring(0, pair1.indexOf(","));
			String vertex2 = pair1.substring(pair1.indexOf(",") + 1);
			for (String pair2 : relation) {
				boolean check = false;
				String vertex3 = pair2.substring(0, pair2.indexOf(","));
				String vertex4 = pair2.substring(pair2.indexOf(",") + 1);
				if (vertex2.equals(vertex3)) {
					for (String pair3 : relation) {
						String vertex5 = pair3.substring(0, pair3.indexOf(","));
						String vertex6 = pair3.substring(pair3.indexOf(",") + 1);
						if ((vertex1.equals(vertex5)) && (vertex4.equals(vertex6))) {
							check = true;
						}
					}
					if (!check) {
						System.out.println("For the graph to be transitive tuple: " + vertex1 + "," + vertex4
								+ " MUST be present");
						return false;
					}
				}
			}
		}
		return true;
	}

	/**
	 * This method returns a boolean based on whether the input sets are
	 * anti-symmetric TODO: Complete this method (Note a set is not passed in as a
	 * parameter but a list)
	 * 
	 * @param set      A list of TwitterHandles
	 * @param relation A relation between the TwitterHandles
	 * @return true if the set and relation are anti-symmetric
	 */
	public boolean isEquivalence(List<String> set, List<String> relation) {
		return (isReflexive(set, relation) && isSymmetric(relation) && isTransitive(relation));
	}

	/**
	 * This method returns a List of the equivalence class
	 * 
	 * If the method can not find the equivalence class, then The following must be
	 * printed to the console: "Can't compute equivalence class as this is not an
	 * equivalence relation"
	 * 
	 * TODO: Complete this method (Note a set is not passed in as a parameter but a
	 * list)
	 * 
	 * @param node     A "TwitterHandle" in the graph
	 * @param set      A list of TwitterHandles
	 * @param relation A relation between the TwitterHandles
	 * @return List that is the equivalence class
	 */
	public List<String> computeEquivalence(String node, List<String> set, List<String> relation) {
		if (!isEquivalence(set, relation)) {
			System.out.println("Can't compute equivalence class as this is not an equivalence relation");
			return null;
		}
		List<String> list = new ArrayList<String>();
		System.out.println();
		for (String pair : relation) {
			String vertex1 = pair.substring(0, pair.indexOf(","));
			String vertex2 = pair.substring(pair.indexOf(",") + 1);
			if (vertex1.equals(node) || vertex2.equals(node)) {
				if (!list.contains(vertex1)) {
					list.add(vertex1);
				}
				if (!list.contains(vertex2)) {
					list.add(vertex2);
				}
			}
		}
		return list;
	}

	/**
	 * This method returns a List nodes using the BFS (Breadth First Search)
	 * algorithm
	 * 
	 * @return List of nodes (as strings) using the BFS algorithm
	 */
	public List<Node<String>> breadthFirstSearch() {
		return breadthFirstSearch(root, false);
	}

	/**
	 * This method returns a List nodes using the BFS (Breadth First Search)
	 * algorithm
	 * 
	 * @param start A "TwitterHandle" in the graph
	 * @return List of nodes (as strings) using the BFS algorithm
	 */
	public List<Node<String>> breadthFirstSearch(Node<String> start, boolean rooted) {// name to breadthFirstSearch
		// Create a list to return and a queue
		List<Node<String>> listNodes = new ArrayList<Node<String>>();
		NodesStackAndQueue<Node<String>> exploreQueue = new NodesStackAndQueue<Node<String>>();

		boolean startCheck = true;

		for (Node<String> currentNode : adjacencyMap.keySet()) {
			if (startCheck) {
				currentNode = start;
				startCheck = false;
			}

			exploreQueue.append(currentNode);

			while (!exploreQueue.isEmpty()) {
				currentNode = exploreQueue.getHead();
				exploreQueue.removeHead();
				if (!listNodes.contains(currentNode)) {
					listNodes.add(currentNode);
				}
				// loop through every outgoing edge of current node
				if (adjacencyMap.get(currentNode) != null) {
					for (int i = 0; i < adjacencyMap.get(currentNode).size(); i++) {
						// if list doesn't contain adj vertex of current edge, add to list and queue
						if (!listNodes.contains(adjacencyMap.get(currentNode).get(i).getTarget())
								&& (adjacencyMap.get(currentNode).get(i).getTarget() != currentNode)) {
							exploreQueue.append(adjacencyMap.get(currentNode).get(i).getTarget());
						}
					}
				}
			}
			if (rooted) {
				break;
			}
		}
		// Adds start node into list and queue if it's not already inside

		return listNodes;
	}

	/**
	 * This method returns a List nodes using the DFS (Depth First Search) algorithm
	 * 
	 * @return List of nodes (as strings) using the DFS algorithm
	 */
	public List<Node<String>> depthFirstSearch() {
		return depthFirstSearch(root, false);
	}

	/**
	 * This method returns a List nodes using the DFS (Depth First Search) algorithm
	 * 
	 * @param start A "TwitterHandle" in the graph
	 * @return List of nodes (as strings) using the DFS algorithm
	 */
	public List<Node<String>> depthFirstSearch(Node<String> start, boolean rooted) {
		// Create a list to return and a queue
		List<Node<String>> listNodes = new ArrayList<Node<String>>();
		NodesStackAndQueue<Node<String>> exploreStack = new NodesStackAndQueue<Node<String>>();

		boolean startCheck = true;

		// loop through keys in the map
		for (Node<String> currentNode : adjacencyMap.keySet()) {
			if (startCheck) {
				currentNode = start;
				startCheck = false;
			}

			// Push key node into stack
			exploreStack.push(currentNode);

			// loop until stack is empty
			while (!exploreStack.isEmpty()) {
				currentNode = exploreStack.pop();
				if (!listNodes.contains(currentNode)) {
					listNodes.add(currentNode);
				}
				// loop through every outgoing edge of current node
				if (adjacencyMap.get(currentNode) != null) {
					for (int i = 0; i < adjacencyMap.get(currentNode).size(); i++) {
						// if list doesn't contain adj vertex of current edge, add to list and queue
						if (!listNodes.contains(adjacencyMap.get(currentNode).get(i).getTarget())
								&& (adjacencyMap.get(currentNode).get(i).getTarget() != currentNode)) {
							exploreStack.push(adjacencyMap.get(currentNode).get(i).getTarget());
						}
					}
				}
			}
			if (rooted) {
				break;
			}
		}

		return listNodes;
	}

	/**
	 * @return returns the set of all nodes in the graph
	 */
	public Set<Node<String>> getAllNodes() {

		Set<Node<String>> out = new HashSet<>();
		out.addAll(adjacencyMap.keySet());
		for (Node<String> n : adjacencyMap.keySet()) {
			LinkedList<Edge<Node<String>>> list = adjacencyMap.get(n);
			for (int i = 0; i < list.size(); i++) {
				out.add(list.get(i).getSource());
				out.add(list.get(i).getTarget());
			}
		}
		return out;
	}

	/**
	 * @return returns the set of all edges in the graph
	 */
	protected Set<Edge<Node<String>>> getAllEdges() {
		Set<Edge<Node<String>>> out = new HashSet<>();
		for (Node<String> n : adjacencyMap.keySet()) {
			LinkedList<Edge<Node<String>>> list = adjacencyMap.get(n);
			for (int i = 0; i < list.size(); i++) {
				out.add(list.get(i));
			}
		}
		return out;
	}

	/**
	 * @return returns the set of twitter handles in the graph
	 */
	public Set<TwitterHandle> getUsersFromNodes() {
		Set<TwitterHandle> users = new LinkedHashSet<TwitterHandle>();
		for (Node<String> n : getAllNodes()) {
			users.add(new TwitterHandle(n.getValue()));
		}
		return users;
	}

}
