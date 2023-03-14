/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import java.util.EmptyStackException;

/**
 * Represents an array list based stack where elements can be added or removed from the 
 * top, the size can be fetched, whether it is empty can be determined,
 * and the capacity can be set.
 * @param <E> the element type for the stack
 * @author Cole Sanders
 *
 */
public class ArrayStack<E> implements Stack<E> {
	
	/** An array based list of elements */
	private ArrayList<E> list;
	/** The capacity of the list */
	private int capacity;
	
	/**
	 * Constructs an ArrayStack and initializes its fields.
	 * @param capacity the capacity of the stack
	 * @throws IllegalArgumentException if the parameter is negative.
	 */
	public ArrayStack(int capacity) {
		list = new ArrayList<E>();
		setCapacity(capacity);
	}

	/**
	 * Adds an element to the top of the stack
	 * @param element element being added
	 * @throws IllegalArgumentException if the stack is at capacity
	 */
	@Override
	public void push(E element) {
		if (list.size() == capacity) {
			throw new IllegalArgumentException("Full");
		}
		list.add(element);
	}

	/**
	 * Removes and returns the top element in the stack.
	 * @return the top element in the stack
	 * @throws EmptyStackException if the stack is empty
	 */
	@Override
	public E pop() {
		if(isEmpty()) {
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
		if (capacity < 0 || capacity < list.size()) {
			throw new IllegalArgumentException("Invalid capacity.");
		}
		this.capacity = capacity;
	}
}
