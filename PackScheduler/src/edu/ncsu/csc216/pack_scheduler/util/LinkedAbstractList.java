/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import java.util.AbstractList;

/**
 * A custom LinkedList that holds the information for the first node in the list,
 * the size of the list, and the capacity of the list. Its functionality includes 
 * adding elements to a given index, removing elements, setting elements to a given
 * value, setting the capacity, getting elements, and getting the size of the list.
 * @author Cole Sanders
 * @param <E> the generic object type of things being storied in the list.
 *
 */
public class LinkedAbstractList<E> extends AbstractList<E> {

	/** The first node in the list. */
	private ListNode front;
	/** The last node in the list. */
	private ListNode back;
	/** The size of the list. */
	private int size;
	/** The capacity of the list. */
	private int capacity;
	
	/**
	 * Initializes all the fields of LinkedAbstractList. Front is set to null, size to zero, and capacity
	 * to the passed in value of capacity.
	 * @param capacity the passed in value of capacity.
	 * @throws IllegalArgumentException if the capacity is less than zero.
	 */
	public LinkedAbstractList(int capacity) {
		front = null;
		size = 0;
		if (capacity >= 0) {
			setCapacity(capacity);
		} else {
			throw new IllegalArgumentException("Invalid capacity");
		}
	}
	
	/**
	 * Adds an element to the LinkedList at a given index.
	 * @param index the index the element will be added to.
	 * @param value the element being added
	 * @throws IllegalArgumentException if the list is full
	 * @throws NullPointerException if the passed in value is null.
	 * @throws IllegalArgumentException if the passed in value is already found in the list.
	 * @throws IndexOutOfBoundsException if the index points to a range outside the scope of the list.
	 */
	@Override
	public void add(int index, E value) {
		if (size == capacity) {
			throw new IllegalArgumentException("Full");
		}
		if (value == null) {
			throw new NullPointerException();
		}
		if (size > 0) {
			ListNode current = front;
			for (int i = 0; i < size - 1; ++i) {
				if (current.data.equals(value)) {
					throw new IllegalArgumentException("Duplicate");
				}
				current = current.next;
			}
			if (current.data.equals(value)) {
				throw new IllegalArgumentException("Duplicate");
			}
		}
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		}
		if (index == size) {
			ListNode adding = new ListNode(value);
			if (size == 0) {
				front = adding;
				back = adding;
			} else {
				back.next = adding;
				back = back.next;
			}
			++size;
		} else {
			if (size < capacity) {
				ListNode adding = new ListNode(value);
				if (size == 0) {
					front = adding;
					++size;
				} else if (size < capacity) {
					if (index == 0) {
						adding.next = front;
						front = adding;
						++size;
					} else {
						ListNode current = front;
						for (int i = 0; i < index - 1; ++i) {
							current = current.next;
						}
						ListNode adding2 = new ListNode(value, current.next);
						current.next = adding2;
						++size;
					}
				}
			}
		}
		
	}
	
	/**
	 * Removes an element from the LinkedList at a given index.
	 * @param index the index the element will be removed from.
	 * @return value of the element being removed
	 * @throws IndexOutOfBoundsException if the index points to a range outside the scope of the list.
	 */
	@Override
	public E remove(int index) {
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException();
		}
		if (index == 0 && size >= 1) {
			E firstVal = front.data;
			if (size > 1) {
				front = front.next;
			} else {
				front = null;
			}
			--size;
			return firstVal;
		} else {
			ListNode current = front;
			for (int i = 0; i < index - 1; ++i) {
				current = current.next;
			}
			ListNode remove = current.next;
			if (index < size - 1) {
				current.next = current.next.next;
			} else {
				current.next = null;
				back = current;
			}
			--size;
			return remove.data;
		}
	}
	
	
	/**
	 * Gets the element from the list at the given index.
	 * @param index the given index of the element to be fetched.
	 * @return the element being fetched
	 * @throws IndexOutOfBoundsException if the index points to a range outside the scope of the list.
	 */
	@Override
	public E get(int index) {
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException();
		}
		ListNode current = front;
		if (index == 0) {
			return front.data;
		}
		for (int i = 0; i < index - 1; ++i) {
			current = current.next;
		}
		return current.next.data;
	}

	/**
	 * Gets the size of the list.
	 * @return the size of the list.
	 */
	@Override
	public int size() {
		return size;
	}
	
	/**
	 * Sets an element in the LinkedList at a given index.
	 * @param index the index the element will be set to.
	 * @param value the element being set.
	 * @throws NullPointerException if the passed in value is null.
	 * @throws IllegalArgumentException if the passed in value is already found in the list.
	 * @throws IndexOutOfBoundsException if the index points to a range outside the scope of the list.
	 */
	@Override 
	public E set(int index, E value) {
		if (value == null) {
			throw new NullPointerException();
		}
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException();
		}
		if (size > 0) {
			ListNode current = front;
			for (int i = 0; i < size - 1; ++i) {
				if (current.data.equals(value)) {
					throw new IllegalArgumentException("Duplicate");
				}
				current = current.next;
			}
			if (current.data.equals(value)) {
				throw new IllegalArgumentException("Duplicate");
			}
		}
		ListNode current = front;
		if (index == 0) {
			E overriden = front.data;
			front.data = value;
			return overriden;
		} else {
			for (int i = 0; i < index - 1; ++i) {
				current = current.next;
			}
			E overriden = current.next.data;
			current.next.data = value;
			return overriden;
		} 
	}
	
	/**
	 * Sets the capacity of the list.
	 * @param capacity the capacity of the list.
	 * @throws IllegalArgumentException if the capacity is less than zero or the current size
	 */
	public void setCapacity(int capacity) {
		if (capacity < 0 || capacity < size) {
			throw new IllegalArgumentException("Invalid capacity.");
		}
		this.capacity = capacity;
	}
	
	/**
	 * A class that represents a single node in a linked list. Each node contains
	 * a data fields storing the desired value and a next field to reference the next
	 * node in the list.
	 * @author Cole Sanders
	 *
	 */
	private class ListNode {
		
		/** The data the ListNode is storing */
		private E data;
		/** The reference to the next ListNode */
		private ListNode next;
		
		
		/**
		 * Constructs a ListNode and initializes the data field with the parameter value.
		 * @param data the parameter value used to initialize the data field.
		 */
		public ListNode(E data) {
			this.data = data;
		}
		
		/**
		 * Constructs a ListNode and initializes the data and next fields with passed in parameter
		 * values.
		 * @param data the data used to initialize the data field.
		 * @param next the reference to the next ListNode used to initialize the next field.
		 */
		public ListNode(E data, ListNode next) {
			this.data = data;
			this.next = next;
		}
	}

}
