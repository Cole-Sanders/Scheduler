package edu.ncsu.csc216.pack_scheduler.util;

import java.util.EmptyStackException;

/**
 * Represents a linked list based stack where elements can be added or removed from the 
 * top, the size can be fetched, whether it is empty can be determined,
 * and the capacity can be set.
 * @param <E> the element type for the stack
 * @author Cole Sanders
 *
 */
public class LinkedStack<E> implements Stack<E> {
	
	/** A linked list of elements */
	private LinkedAbstractList<E> list;
	
	/**
	 * Constructs a LinkedStack and initializes its field.
	 * @param capacity the capacity of the stack
	 * @throws IllegalArgumentException if the parameter is negative.
	 */
	public LinkedStack(int capacity) {
		list = new LinkedAbstractList<E>(capacity);
	}

	/**
	 * Adds an element to the top of the stack
	 * @param element element being added
	 * @throws IllegalArgumentException if the stack is at capacity
	 */
	@Override
	public void push(E element) {
		list.add(element);
	}

	/**
	 * Removes and returns the top element in the stack.
	 * @return the top element in the stack
	 * @throws EmptyStackException if the stack is empty
	 */
	@Override
	public E pop() {
		if (isEmpty()) {
			throw new EmptyStackException();
		}
		return list.remove(list.size() - 1);
	}

	/**
	 * Returns true if the stack has no element
	 * @return true for an empty stack, false for a non-empty stack
	 */
	@Override
	public boolean isEmpty() {
		return list.size() == 0;
	}

	/**
	 * Returns the size of the stack
	 * @return the number of elements in the stack
	 */
	@Override
	public int size() {
		return list.size();
	}

	/**
	 * Sets the stack's capacity
	 * @param capacity the capacity to be set
	 * @throws IllegalArgumentException if the parameter is negative or 
	 * less than the number of elements in the stack.
	 */
	@Override
	public void setCapacity(int capacity) {
		list.setCapacity(capacity);
	}
}
