package nz.ac.auckland.se281.a4.ds;

import java.util.EmptyStackException;
//*******************************
//YOU SHOUD MODIFY THE SPECIFIED 
//METHODS  OF THIS CLASS
//HELPER METHODS CAN BE ADDED
//REQUIRED LIBRARIES ARE ALREADY 
//IMPORTED -- DON'T ADD MORE
//*******************************

public class NodesStackAndQueue<T> {

	private Node<T> head; // You should use this variable in your methods

	public NodesStackAndQueue() {
		head = null;
	}

	/**
	 * Checks if the stack / queue is empty
	 * 
	 * @return true if the stack / queue is empty
	 */
	public boolean isEmpty() {
		return (head == null);
	}

	/**
	 * Push operation refers to inserting an element in the Top of the stack. TODO:
	 * Complete this method
	 * 
	 * @param element the element to be "pushed"
	 */
	public void push(T element) {
		// Create new node
		Node<T> n = new Node<T>(element);
		// Checks if stack/queue is empty
		if (isEmpty()) {
			head = n;
		} else {
			Node<T> currentNode = head;
			while (currentNode.getNext() != null) {
				currentNode = currentNode.getNext();
			}
			currentNode.setNext(n);
		}
	}

	/**
	 * pop an element from the top of the stack (removes and returns the tope
	 * element) TODO: Complete this method (Note: You may have to change the return
	 * type)
	 * 
	 * @return object of the top element
	 * @throws EmptyStackException if the stack is empty
	 */
	public T pop() throws EmptyStackException {
		// Checks if there is no head
		if (head == null) {
			throw new EmptyStackException();
		}
		// Initialise a tail variable using peek method
		T tail = peek();
		Node<T> currentNode = head;
		if (currentNode.getNext() == null) {
			head = null;
		} else {
			while (currentNode.getNext().getNext() != null) {
				currentNode = currentNode.getNext();
			}
			currentNode.setNext(null);
		}
		return tail;
	}

	/**
	 * get the element from the top of the stack without removing it TODO: Complete
	 * this method (Note: You may have to change the return type)
	 *
	 * @return the value of the top element
	 * @throws EmptyStackException if the stack is empty
	 */
	public T peek() throws EmptyStackException {
		// Checks if there is no head
		if (head == null) {
			throw new EmptyStackException();
		}
		// Sets head as current node to be checked
		Node<T> currentNode = head;
		while (currentNode.getNext() != null) {
			currentNode = currentNode.getNext();
		}
		return currentNode.getValue();
	}

	/**
	 * append an element at the end of the queue TODO: Complete this method
	 *
	 * @param element the element to be appended
	 */
	public void append(T element) {
		// Create a new node
		Node<T> n = new Node<T>(element);
		// Checks if stack/queue is empty
		if (isEmpty()) {
			head = n;
		} else {
			Node<T> currentNode = head;
			while (currentNode.getNext() != null) {
				currentNode = currentNode.getNext();
			}
			currentNode.setNext(n);
		}
	}

	/**
	 * Finds the head of the stack/queue and removes it if there exists one
	 */
	public void removeHead() {
		// Checks if head has a next node
		if (head.getNext() == null) {
			head = null;
		} else {
			head = head.getNext();
		}
	}

	/**
	 * Returns the value of the node which is the head of the stack/queue
	 * 
	 * @return T generic type for the value of the head
	 */
	public T getHead() {
		return head.getValue();
	}
}
