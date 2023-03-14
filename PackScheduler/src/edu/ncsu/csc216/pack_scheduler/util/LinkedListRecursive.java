/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;



/**
 * A class that represents a LinkedList implemented through recursive algorithms. This list stores the 
 * data for its size and the reference to the first node in the list. It has the functionality to check
 * if an element is in the list, if the list is empty, to get the size of the list, to add and remove 
 * elements, as well as to get and to set elements.
 * @param <E> the argument type for the list.
 * @author Cole Sanders
 *
 */
public class LinkedListRecursive<E> {
	/** The reference to the first node in the list */
	private ListNode front;
	/** The size of the list */
	private int size;
	
	/**
	 * Constructs a LinkedListRecursive and initializes its fields to represent
	 * an empty list.
	 */
	public LinkedListRecursive() {
		front = null;
		size = 0;
	}
	
	/**
	 * Returns true if the list is empty.
	 * @return true if the list is empty.
	 */
	public boolean isEmpty() {
		return size == 0;
	}
	
	/**
	 * Gets the size of the list.
	 * @return an integer representing the size of the list.
	 */
	public int size() {
		return size;
	}
	
	/**
	 * Adds an element to the end of the list.
	 * @param data the element being added.
	 * @return true if the element was successfully added.
	 * @throws IllegalArgumentException if the element already exists in the list.
	 * @throws NullPointerException if the element is null.
	 */
	public boolean add(E data) {
		if (contains(data)) {
			throw new IllegalArgumentException();
		}
		if (data == null) {
			throw new NullPointerException();
		}
		if (isEmpty()) {
			front = new ListNode(data, null);
			++size;
			return true;
		} else {
			front.add(size - 1, data);
			++size;
			return true;
		}
	}
	
	/**
	 * Adds an element to the given index in the list.
	 * @param index the index the element is being added to.
	 * @param data the element being added.
	 * @return true if the element was successfully added.
	 * @throws IllegalArgumentException if the element already exists in the list.
	 * @throws NullPointerException if the element is null.
	 * @throws IndexOutOfBoundsException if the index is invalid.
	 */
	public boolean add(int index, E data) {
		if (contains(data)) {
			throw new IllegalArgumentException();
		}
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		}
		if (data == null) {
			throw new NullPointerException();
		}
		if (index == 0) {
			if (front == null) {
				front = new ListNode(data, null);
			} else {
				front = new ListNode(data, front);
			}
			++size;
			return true;
		} else {
			front.add(index - 1, data);
			++size;
			return true;
		}
	}
	
	/**
	 * Gets the element at the given index.
	 * @param index the index of the element being fetched.
	 * @return the element being fetched.
	 * @throws IndexOutOfBoundsException if the index is invalid.
	 */
	public E get(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		return front.get(index);
	}
	
	/**
	 * Removes the given element from the list. Returns true if the element was
	 * successfully removed and false if it wasn't found in the list.
	 * @param data the data being removed.
	 * @return true if the data was removed.
	 */
	public boolean remove(E data) {
		if (data == null) {
			return false;
		}
		if (size == 0) {
			return false;
		}
		if (!contains(data)) {
			return false;
		}
		if (data == front.data) {
			front = front.next;
			--size;
			return true;
		} else {
			--size;
			return front.remove(data);
		}
	}
	
	/**
	 * Removes a element at the given index.
	 * @param index the index of the element to be removed.
	 * @return the removed element
	 * @throws IndexOutOfBoundsException if the index is outside the bounds of the list.
	 */
	public E remove(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		if (index == 0) {
			E data = front.data;
			front = front.next;
			--size;
			return data;
		}
		--size;
		return front.remove(index - 1);
	}
	
	/**
	 * Sets a given element to the given index in the list and returns the element being
	 * replaced.
	 * @param index the index in the list to set the new element.
	 * @param data the element being set.
	 * @return the replaced element.
	 * @throws IndexOutOfBoundsException if the index is out of range of the list.
	 * @throws IllegalArgumentException if the list is empty.
	 * @throws IllegalArgumentException if the data is a duplicate.
	 * @throws NullPointerException if the element is null.
	 */
	public E set(int index, E data) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		if (data == null) {
			throw new NullPointerException();
		}
		if (contains(data)) {
			throw new IllegalArgumentException();
		}
		if (front == null) {
			throw new IllegalArgumentException();
		}
		return front.set(index, data);
	}
	
	/**
	 * Checks if a given element is in the list.
	 * @param data the element being checked.
	 * @return true if it is found in the list, false if not.
	 */
	public boolean contains(E data) {
		if (isEmpty()) {
			return false;
		}
		return front.contains(data);
	}
	
	
	/**
	 * Represents a node in the list storing the data, and the reference to the next nodes in the list.
	 * @author Cole Sanders
	 *
	 */
	private class ListNode {
		
		/** The data contained in a node */
		public E data;
		/** The reference to the next node in the list */
		public ListNode next;
		
		/**
		 * Constructs a ListNode and initializes the data and next node fields.
		 * @param data the data contained in the constructed node.
		 * @param next the reference to the next node in the list.
		 */
		public ListNode(E data, ListNode next) {
			this.data = data;
			this.next = next;
		}
		
		/**
		 * Adds an element to the given index.
		 * @param index the index in the list.
		 * @param data the data being added to the list.
		 */
		public void add(int index, E data) {
			if (index ==  0) {
				ListNode add = new ListNode(data, next);
				next = add;
			} else {
				next.add(index - 1, data);
			}
		}
		
		/**
		 * Gets an element at the given index in the list.
		 * @param index the index of the element being fetched.
		 * @return the element being fetched.
		 */
		public E get(int index) {
			if (index == 0) {
				return data;
			} else if (next != null){
				return next.get(index - 1);
			} else {
				return null;
			}
		}
		
		/**
		 * Removes an element from the given index in the list.
		 * @param index the index of the element to be removed.
		 * @return the removed element.
		 */
		public E remove(int index) {
			if (index == 0) {
				E removedData = next.data;
				next = next.next;
				return removedData;
			} else {
				return next.remove(index - 1);
			}
		}
		
		/**
		 * Removes the given element from the list.
		 * @param data the element being removed.
		 * @return true if the element was successfully removed.
		 */
		public boolean remove(E data) {
			if (next.data.equals(data)) {
				next = next.next;
				return true;
			} else {
				return next.remove(data);
			}
		}
		
		/**
		 * Sets the given element to the given index.
		 * @param index the index where the element will be set.
		 * @param data the element to be set.
		 * @return the previous element at the given index.
		 */
		public E set(int index, E data) {
			if (index == 0) {
				E returned = this.data;
				this.data = data;
				return returned;
			}
			return next.set(index - 1, data);
		}
		
		/**
		 * Checks if a element exists in the list.
		 * @param data the element being checked.
		 * @return true if the element is in the list, false otherwise.
		 */
		public boolean contains(E data) {
			if (data.equals(this.data)) {
				return true;
			} else if (this.data == null) {
				return false;
			}
			if (next == null) {
				return false;
			} else {
				return next.contains(data);
			}
		}
	}
}
