package nz.ac.auckland.se281.a4.ds;

import java.util.NoSuchElementException;
//*******************************
//YOU SHOUD MODIFY THE SPECIFIED 
//METHODS  OF THIS CLASS
//HELPER METHODS CAN BE ADDED
//REQUIRED LIBRARIES ARE ALREADY 
//IMPORTED -- DON'T ADD MORE
//THIS CLASS ALSO HAS TO BE MADE 
//GENERIC
//*******************************

/**
 * The Linked List Class Has only one head pointer to the start node (head)
 * Nodes are indexed starting from 0. The list goes from 0 to size-1.
 *
 * @author Partha Roop
 */
public class LinkedList<T> {
	// the head of the linked list
	private Node<T> head;

	/**
	 * Constructor for LinkedList
	 */
	public LinkedList() {
		head = null;
	}

	/**
	 * This method returns a reference to a node whose position is at pos TODO:
	 * Complete this method
	 * 
	 * @param pos: an integer specifying the position of the node to be located
	 * @return Node: the reference to the Node at position pos
	 * @throws InvalidPositionException if position is less than 0 or greater than
	 *                                  size-1
	 * @throws NoSuchElementException   if the element does not exist in the
	 *                                  LinkedList
	 */
	private Node<T> locateNode(int pos) throws InvalidPositionException, NoSuchElementException {
		// Initialise a node as the head
		Node<T> node = head;
		for (int i = 0; i < pos; i++) {
			node = node.getNext();
		}
		return node;
	}

	/**
	 * This method adds a node with specified data as the start node of the list
	 * TODO: Complete this method
	 *
	 * @param element a parameter, which is the value of the node to be prepended
	 */
	public void prepend(T element) {
		// Create new node
		Node<T> n = new Node<T>(element);
		n.setNext(head);
		head = n;
	}

	/**
	 * This method adds a node with specified data as the end node of the list TODO:
	 * Complete this method
	 *
	 * @param element a parameter, which is the value of the node to be appended
	 */

	// Note this method has been refactored using the helper methods
	// I will do this as a small ACP exercise in class
	public void append(T element) {
		// Create new node
		Node<T> t = new Node<T>(element);
		if (head == null) {
			head = t;
		} else {
			getTail().setNext(t);
		}

	}

	/**
	 * This method gets the value of a node at a given position TODO: Complete this
	 * method
	 *
	 * @param pos an integer, which is the position
	 * @return the value at the position pos
	 * @throws InvalidPositionException if position is less than 0 or greater than
	 *                                  size-1
	 */
	public T get(int pos) throws InvalidPositionException {
		return locateNode(pos).getValue();
	}

	/**
	 * This method adds an node at a given position in the List TODO: Complete this
	 * method
	 * 
	 * @param pos:     an integer, which is the position
	 * @param element: the element to insert
	 * @throws InvalidPositionException if position is less than 0 or greater than
	 *                                  size-1
	 */
	public void insert(int pos, T element) throws InvalidPositionException {
		// Create new node
		Node<T> n = new Node<T>(element);
		if (pos == size()) {
			append(element);
		}
		Node<T> node = locateNode(pos - 1);
		n.setNext(node.getNext());
		node.setNext(n);
	}

	/**
	 * This method removes an node at a given position TODO: Complete this method
	 *
	 * @param pos: an integer, which is the position
	 */
	public void remove(int pos) throws InvalidPositionException {
		// Checks if position is at 0
		if (pos == 0) {
			head = locateNode(pos).getNext();
		} else {
			Node<T> node = locateNode(pos - 1);
			node.setNext(node.getNext().getNext());
		}
	}

	/**
	 * This method returns the size of the Linked list TODO: Complete this method
	 *
	 * @return the size of the list
	 */
	public int size() {
		Node<T> currentNode = head;
		// Checks if there is no head
		if (head == null) {
			return 0;
		} else {
			int size = 1;
			// Loops through to the end of the list
			while (currentNode.getNext() != null) {
				currentNode = currentNode.getNext();
				size++;
			}
			return size;
		}
	}

	/**
	 * This method is used for printing the data in the list from head till the last
	 * node
	 *
	 */
	public void print() {
		Node<T> node = head;
		while (node != null) {
			System.out.println(node);
			node = node.getNext();
		}
	}

	/**
	 * Finds the tail of the linked list and returns it as a node type
	 * 
	 * @return Node<T> a type node with generic type
	 */
	private Node<T> getTail() {
		Node<T> currentNode = this.head;
		while (currentNode.getNext() != null) {
			currentNode = currentNode.getNext();
		}
		return currentNode;
	}
}