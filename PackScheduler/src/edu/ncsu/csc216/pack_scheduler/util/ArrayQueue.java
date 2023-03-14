/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import java.util.NoSuchElementException;

/**
 * Represents an array based queue which allows elements to be added from the back
 * and removed from the front. Also provides functionality to determine if the
 * queue is empty, get the queues size, and set the capacity of the queue.
 * @author Cole Sanders
 * @param <E> the element type for the queue
 */
public class ArrayQueue<E> implements Queue<E> {

	/** An array based list of elements */
	private ArrayList<E> list;
	/** The capacity of the queue */
	private int capacity;
	
	/**
	 * Constructs an ArrayQueue and initializes its fields.
	 * @param capacity the capacity of the queue
	 * @throws IllegalArgumentException if the parameter is negative.
	 */
	public ArrayQueue(int capacity) {
		list = new ArrayList<E>();
		setCapacity(capacity);
	}
	
	/**
	 * Adds an element to the back of the queue.
	 * @param element element to be added
	 * @throws IllegalArgumentException if the queue is full
	 */
	@Override
	public void enqueue(E element) {
		if (list.size() == capacity) {
			throw new IllegalArgumentException("Full");
		}
		list.add(element);
	}

	/**
	 * Removes an element from the front of the queue.
	 * @return the element at the front of the queue.
	 * @throws NoSuchElementException if the queue is empty
	 */
	@Override
	public E dequeue() {
		if(isEmpty()) {
			throw new NoSuchElementException();
		}
		return list.remove(0);
	}

	/**
	 * Returns true if the queue is empty
	 * @return true if the queue is empty
	 */
	@Override
	public boolean isEmpty() {
		return list.size() == 0;
	}

	/**
	 * Returns the number of elements in the queue
	 * @return the size of the queue
	 */
	@Override
	public int size() {
		return list.size();
	}

	/**
	 * Sets the Queue's capacity
	 * @param capacity the capacity of the queue
	 * @throws IllegalArgumentException if the parameter is negative
	 * or less than the number of elements in the queue.
	 */
	@Override
	public void setCapacity(int capacity) {
		if (capacity < 0 || capacity < list.size()) {
			throw new IllegalArgumentException("Invalid capacity.");
		}
		this.capacity = capacity;
		
	}
	
}
