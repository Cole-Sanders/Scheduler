/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

/**
 * Represents a queue which allows elements to be added from the back
 * and removed from the front. Also provides functionality to determine if the
 * queue is empty, get the queues size, and set the capacity of the queue.
 * @author Cole Sanders
 * @param <E> the element type for the queue
 *
 */
public interface Queue<E> {
	
	/**
	 * Adds an element to the back of the queue.
	 * @param element element to be added
	 * @throws IllegalArgumentException if the queue is full
	 */
	void enqueue(E element);
	
	/**
	 * Removes an element from the front of the queue.
	 * @return the element at the front of the queue.
	 */
	E dequeue();
	

	/**
	 * Returns true if the queue is empty
	 * @return true if the queue is empty
	 */
	boolean isEmpty();
	
	/**
	 * Returns the number of elements in the queue
	 * @return the size of the queue
	 */
	int size();
	
	/**
	 * Sets the Queue's capacity
	 * @param capacity the capacity of the queue
	 * @throws IllegalArgumentException if the parameter is negative
	 * or less than the number of elements in the queue.
	 */
	void setCapacity(int capacity);
}
