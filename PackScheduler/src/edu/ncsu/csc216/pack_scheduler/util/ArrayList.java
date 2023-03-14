/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import java.util.AbstractList;

/**
 * A class representing an array list of generic type. It includes the initial capacity,
 * size, and an array from which the list is based on as its fields. It also includes
 * the functionality to add an element to a certain index, get the size of the list, 
 * remove an index from an index, set an element at an index, and get an element from 
 * an index.
 * @author Cole Sanders
 * @param <E> an object of generic type
 */
public class ArrayList<E> extends AbstractList<E> {
	
	/**	The initial capacity of an array list */
	private static final int INIT_SIZE = 10;
	/**	The Array containing the array list */
	private E[] list;
	/**	The size of an array list */
	private int size;
	
	
	/**
	 * Constructs an array list of generic type E with a size of zero.
	 */
	@SuppressWarnings("unchecked")
	public ArrayList() {
		size = 0;
		Object[] objList = new Object[INIT_SIZE];
		list = (E[]) objList;
		
	}

	/**
	 * Adds a given value to the given index.
	 * @param index the index the value is being added to
	 * @param value the value being added into the array
	 * @throws NullPointerException if the given value is null
	 * @throws IndexOutOfBoundsException if the given index is less than zero or
	 * greater than the size of the array.
	 * @throws IllegalArgumentException if a duplicate element already exits in the list
	 */
	@Override
	public void add(int index, E value) {
		if (value == null) {
			throw new NullPointerException();
		}
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		}
		for (int i = 0; i < size; ++i) {
			if (value.equals(list[i])) {
				throw new IllegalArgumentException("Duplicate");
			}
		}
		if (size == list.length) {
			growArray();
		}
		++size;
		if (index < size - 1) {
			for (int i = size - 1; i > index; --i) {
				list[i] = list[i - 1];
			}
			list[index] = value;
		} else {
			list[index] = value;
		}
	}
	
	/**
	 * Doubles the array list's capacity.
	 */
	@SuppressWarnings("unchecked")
	private void growArray() {
		Object[] objList = new Object[2 * size];
		E[] newList = (E[]) objList;
		for (int i = 0; i < size; ++i) {
			newList[i] = list[i];
		}
		list = newList;
	}
	
	/**
	 * Removes an element at the given index.
	 * @param idx the index of the element to be removed.
	 * @return the element that was removed.
	 * @throws IndexOutOfBoundsException if the given index is out of range.
	 */
	@Override
	public E remove(int idx) {
		if (idx < 0 || idx >= size) {
			throw new IndexOutOfBoundsException();
		}
		if (idx < size - 1) {
			E value = list[idx];
			for (int i = idx; i < size - 1; ++i) {
				list[i] = list[i + 1];
			}
			list[size - 1] = null;
			--size;
			return value;
		} else {
			E value = list[size - 1];
			list[size - 1] = null;
			--size;
			return value;
		}
	}
	
	/**
	 * Sets a given element at a given index in the list.
	 * @param idx the index for the element to be set at
	 * @param value the element being set
	 * @return the element being replaced
	 * @throws IndexOutOfBoundsException if the given index is out of range.
	 * @throws IllegalArgumentException if a duplicate element already exits in the list
	 * @throws NullPointerException if the given value is null
	 */
	@Override
	public E set(int idx, E value) {
		if (value == null) {
			throw new NullPointerException("Null element");
		}
		for (int i = 0; i < size; ++i) {
			if (value.equals(list[i])) {
				throw new IllegalArgumentException("Duplicate");
			}
		}
		if (idx < 0 || idx >= size) {
			throw new IndexOutOfBoundsException();
		}
		E replace = list[idx];
		list[idx] = value;
		return replace;
	}
	
	/**
	 * Returns the element of the array list at the given index.
	 * @return The element at the given index.
	 * @throws IndexOutOfBoundsException if the index is outside the range of the list.
	 */
	@Override
	public E get(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		return list[index];
	}

	/**
	 * Returns the size of the array list.
	 */
	@Override
	public int size() {
		return size;
	}

}
