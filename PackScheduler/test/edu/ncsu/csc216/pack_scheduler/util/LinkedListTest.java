package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ListIterator;

import org.junit.Test;

/**
 * Tests the LinkedList class.
 * @author Cole Sanders
 */
public class LinkedListTest {
	
	/** The initial capacity of a newly created list */
	private static final int INITIAL_CAPACITY = 10;
	
	/**
	 * Tests the constructor of LinkedList
	 */
	@Test
	public void testArrayList() {
		LinkedList<String> list = new LinkedList<String>();
		assertEquals(0, list.size());
	}
	
	/**
	 * Tests add()
	 */
	@Test
	public void testAdd() {
		LinkedList<String> list = new LinkedList<String>();
		Exception exception = assertThrows(IndexOutOfBoundsException.class,
				() -> list.add(-1, "String10"));
		assertEquals(null, exception.getMessage());
		for (int i = 0; i < INITIAL_CAPACITY; ++i) {
			list.add(i, "String" + i);
		}
		Exception exception3 = assertThrows(IndexOutOfBoundsException.class,
				() -> list.add(-1, "String10"));
		assertEquals(null, exception3.getMessage());
		assertEquals("String0", list.get(0));
		assertEquals("String9", list.get(9));
		list.add(10, "String10");
		assertEquals("String10", list.get(10));
		list.add(2, "String11");
		assertEquals("String11", list.get(2));
		assertEquals("String10", list.get(11));
		Exception exception2 = assertThrows(IllegalArgumentException.class,
				() -> list.set(0, "String10"));
		assertEquals(null, exception2.getMessage());
		
	}
	
	/**
	 * Tests remove()
	 */
	@Test
	public void testRemove() {
		LinkedList<String> list = new LinkedList<String>();
		for (int i = 0; i < INITIAL_CAPACITY; ++i) {
			list.add(i, "String" + i);
		}
		assertEquals(10, list.size());
		assertEquals("String0", list.remove(0));
		assertEquals("String1", list.get(0));
		assertEquals("String3", list.get(2));
		assertEquals(9, list.size());
		assertEquals("String9", list.remove(8));
		assertEquals(8, list.size());
	}
	
	/**
	 * Tests set()
	 */
	@Test
	public void testSet() {
		LinkedList<String> list = new LinkedList<String>();
		Exception exception3 = assertThrows(IndexOutOfBoundsException.class,
				() -> list.set(0, null));
		assertEquals("Index: 0", exception3.getMessage());
		for (int i = 0; i < INITIAL_CAPACITY; ++i) {
			list.add(i, "String" + i);
		}
		assertEquals("String2", list.set(2, "String10"));
		assertEquals("String10", list.get(2));
		Exception exception = assertThrows(IllegalArgumentException.class,
				() -> list.set(0, "String10"));
		assertEquals(null, exception.getMessage());
		Exception exception2 = assertThrows(NullPointerException.class,
				() -> list.set(0, null));
		assertEquals(null, exception2.getMessage());
		
	}
	
	/**
	 * Tests LinkedListIterator.previous() method
	 */
	@Test
	public void testPrevious() {
		LinkedList<String> list = new LinkedList<String>();
		assertEquals(0, list.size());
		for (int i = 0; i < INITIAL_CAPACITY; ++i) {
			list.add(i, "String" + i);
		}
		ListIterator<String> iterator = list.listIterator(9);
		assertEquals("String8", iterator.previous());
	}
}
