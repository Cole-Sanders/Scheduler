/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

/**
 * Represents a stack where elements can be added or removed from the 
 * top, the size can be fetched, whether it is empty can be determined,
 * and the capacity can be set.
 * @param <E> the element type for the stack
 * @author Cole Sanders
 *
 */
public interface Stack<E> {
	
	/**
	 * Adds an element to the top of the stack
	 * @param element element being added
	 * @throws IllegalArgumentException if the stack is at capacity
	 */
	void push(E element);
	
	/**
	 * Removes and returns the top element in the stack.
	 * @return the top element in the stack
	 */
	E pop();
	
	/**
	 * Returns true if the stack has no element
	 * @return true for an empty stack, false for a non-empty stack
	 */
	boolean isEmpty();
	
	/**
	 * Returns the size of the stack
	 * @return the number of elements in the stack
	 */
	int size();
	
	/**
	 * Sets the stack's capacity
	 * @param capacity the capacity to be set
	 * @throws IllegalArgumentException if the parameter is negative or 
	 * less than the number of elements in the stack.
	 */
	void setCapacity(int capacity);
}
