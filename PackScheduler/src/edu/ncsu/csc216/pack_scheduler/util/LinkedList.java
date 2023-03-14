/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import java.util.AbstractSequentialList;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * A class that represents a linked list. It contains the data for the nodes at the front
 * and back of the list as well as the list's size. This class extends the AbstractSequentialList
 * class and overrides some of its methods to add new functionality. This includes adding and setting 
 * methods that check for duplicates and a size method that returns the size of the list.
 * @author Cole Sanders
 * @param <E> the generic object type for LinkedList
 * 
 */
public class LinkedList<E> extends AbstractSequentialList<E> {
	/** The first ListNode in the list */
	private ListNode front;
	/** The last ListNode in the list */
	private ListNode back;
	/** The size of the list */
	private int size;
	
	/**
	 * Creates a LinkedList and initializes the front and back fields with ListNodes
	 * with null data and the size field to zero.
	 */
	public LinkedList() {
		front = new ListNode(null);
		back = new ListNode(null);
		front.next = back;
		back.prev = front;
		size = 0;
	}
	

	/**
	 * Creates a ListIterator placed between the element before the index
	 * given by the parameter value and the element at the index given by the
	 * parameter value.
	 * @param index the index of the element in the list the ListIterator will be 
	 * initialized to be in front of.
	 * @throws IndexOutOfBoundsException if the index is invalid.
	 */
	@Override
	public ListIterator<E> listIterator(int index) {
		ListIterator<E> listIterator = new LinkedListIterator(index);
		return listIterator;
	}

	/**
	 * Gets the size of the list.
	 * @return size the size of the list.
	 */
	@Override
	public int size() {
		return size;
	}
	
	/**
	 * Adds an element to a given index in the list.
	 * @param index the index in the list the element is being added to.
	 * @param element the element being added to the list.
	 * @throws IllegalArgumentException if the element is already in the list.
	 */
	@Override
	public void add(int index, E element) {
		if(contains(element)) {
			throw new IllegalArgumentException();
		}
		super.add(index, element);
	}

	


	/**
	 * Sets an element to the given index in the list.
	 * @param index the index in the list the element is being set at.
	 * @param element the element being set to the list.
	 * @throws IllegalArgumentException if the element is already in the list.
	 */
	@Override
	public E set(int index, E element) {
		if(contains(element)) {
			throw new IllegalArgumentException();
		}
		return super.set(index, element);
	}




	/**
	 * A class representing an iterator for a LinkedList. It stores the previous and next elements
	 * from its position in the list as well as their indexes and the last element retrieved from the
	 * list. This class has the functionality to check if there is a previous or next element, move
	 * forward or backwards in the list, fetch the indexes of the previous and next elements, remove,
	 * set, and add elements.
	 * @author Cole Sanders
	 *
	 */
	private class LinkedListIterator implements ListIterator<E> {
		/** Reference to the previous node in the list */
		public ListNode previous;
		/** Reference to the next node in the list */
		public ListNode next;
		/** Index of the previous node in the list */
		public int previousIndex;
		/** Index of the next node in the list */
		public int nextIndex;
		/** Reference to the last node retrieved */
		private ListNode lastRetrieved;
		
		/**
		 * Constructs a LinkedListIterator and initializes its fields. It is placed
		 * between the element at the given index and the element before the given
		 * index.
		 * @param index the index of the element to be returned if the next() method was called.
		 * @throws IndexOutOfBoundsException if the parameter index is out of range of the list.
		 */
		public LinkedListIterator(int index) {
			if (size == 0) {
				if (index != 0) {
					throw new IndexOutOfBoundsException();
				}
				previous = front;
				next = back;
			} else {
				if (index < 0 || index > size){
					throw new IndexOutOfBoundsException();
				}
				ListNode current = front;
				for (int i = -1; i < index - 1; ++i) {
					current = current.next;
				}
				previous = current;
				next = current.next;
			}
			previousIndex = index - 1;
			nextIndex = index;
			lastRetrieved = null;
		}
		
		/**
		 * Returns true if the next ListNode's data isn't null.
		 * @return true if there is a next element in the list.
		 */
		@Override
		public boolean hasNext() {
			return next != null && next.data != null;
		}

		/**
		 * Gets the data in the next ListNode.
		 * @throws NoSuchElementException if the data is null.
		 */
		@Override
		public E next() {
			if (next.data == null) {
				throw new NoSuchElementException();
			}
			E data = next.data;
			lastRetrieved = next;
			previous = previous.next;
			next = next.next;
			++previousIndex;
			++nextIndex;
			return data;
		}

		/**
		 * Returns true if the previous ListNode's data isn't null.
		 * @return true if the data of the previous list node isn't null.
		 */
		@Override
		public boolean hasPrevious() {
			return previous != null && previous.data != null;
		}

		/**
		 * Gets the data in the previous ListNode.
		 * @throws NoSuchElementException if the data is null.
		 * @return the data of the previous ListNode.
		 */
		@Override
		public E previous() {
			if (previous.data == null) {
				throw new NoSuchElementException();
			}
			E data = previous.data;
			lastRetrieved = previous;
			previous = previous.prev;
			next = next.prev;
			--previousIndex;
			--nextIndex;
			return data;
		}

		/**
		 * Gets the value of the next index.
		 * @return the index of the next ListNode.
		 */
		@Override
		public int nextIndex() {
			return nextIndex;
		}

		/**
		 * Gets the value of the previous index.
		 * @return the index of the previous ListNode.
		 */
		@Override
		public int previousIndex() {
			return previousIndex;
		}

		@Override
		public void remove() {
			if (lastRetrieved == null) {
				throw new IllegalStateException();
			}
			ListNode before = lastRetrieved.prev;
			ListNode after = lastRetrieved.next;
			before.next = before.next.next;
			after.prev = after.prev.prev;
			--size;
			lastRetrieved = null;
		}

		/**
		 * Replaces the last retrieved value from previous() or next() methods
		 * with the parameter element if neither remove() or add(E) methods have 
		 * been called in the meantime.
		 * @throws IllegalStateException if remove() or add(E) has been called since the
		 * last call to previous() or next().
		 * @throws NullPointerException if the parameter value is null.
		 */
		@Override
		public void set(E e) {
			if (lastRetrieved == null) {
				throw new IllegalStateException();
			}
			if (e == null) {
				throw new NullPointerException();
			}
			lastRetrieved.data = e;
		}

		/**
		 * Adds an ListNode with the data matching the passed in parameter element between
		 * the previous and next ListNodes in the list. The iterator is then placed between
		 * the added node and the next node.
		 * @throws NullPointerException if the parameter is null.
		 */
		@Override
		public void add(E e) {
			if (e == null) {
				throw new NullPointerException();
			}
			ListNode add = new ListNode(e, previous, next);
			previous.next = add;
			next.prev = add;
			previous = previous.next;
			++previousIndex;
			++nextIndex;
			lastRetrieved = null;
			++size;
		}
		
	}
	
	/**
	 * Represents a node in the list storing the data, and reference to the previous and 
	 * next nodes in the list.
	 * @author Cole Sanders
	 *
	 */
	private class ListNode {
		/** The data contained in a node */
		public E data;
		/** The reference to the next node in the list */
		public ListNode next;
		/** The reference to the previous node in the list */
		public ListNode prev;
		
		/**
		 * Constructs a new ListNode and initializes the data field.
		 * @param data the data contained in the constructed node.
		 */
		public ListNode(E data) {
			this.data = data;
		}
		
		/**
		 * Constructs a ListNode and initializes the data, previous, and next node fields.
		 * @param data the data contained in the constructed node.
		 * @param prev the reference to the previous node in the list.
		 * @param next the reference to the next node in the list.
		 */
		public ListNode(E data, ListNode prev, ListNode next) {
			this.data = data;
			this.prev = prev;
			this.next = next;
		}
	}

}
