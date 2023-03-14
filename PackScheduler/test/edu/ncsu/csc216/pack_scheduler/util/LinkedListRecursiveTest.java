/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;


import org.junit.Test;

/**
 * Tests the LinkedListRecursive class.
 * @author Cole Sanders
 *
 */
public class LinkedListRecursiveTest {
	
	/** The capacity of a brief list */
	private static final int BRIEF_LIST = 10;
	
	/**
	 * Tests the constructor of LinkedListRecursive
	 */
	@Test
	public void testLinkedListRecursive() {
		LinkedListRecursive<String> list = new LinkedListRecursive<String>();
		assertEquals(0, list.size());
	}
	
	/**
	 * Tests add()
	 */
	@Test
	public void testAdd() {
		LinkedListRecursive<String> list = new LinkedListRecursive<String>();
		Exception exception = assertThrows(IndexOutOfBoundsException.class,
				() -> list.add(-1, "String10"));
		assertEquals(null, exception.getMessage());
		for (int i = 0; i < BRIEF_LIST; ++i) {
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
		list.add("String12");
		assertEquals("String12", list.get(12));
		
	}
	
	/**
	 * Tests remove()
	 */
	@Test
	public void testRemove() {
		LinkedListRecursive<String> list = new LinkedListRecursive<String>();
		for (int i = 0; i < BRIEF_LIST; ++i) {
			list.add(i, "String" + i);
		}
		assertEquals(10, list.size());
		assertEquals("String0", list.remove(0));
		assertEquals("String1", list.get(0));
		assertEquals("String3", list.get(2));
		assertEquals(9, list.size());
		assertEquals("String9", list.remove(8));
		assertEquals(8, list.size());
		assertTrue(list.remove("String5"));
		assertEquals(7, list.size());
	}
	
	/**
	 * Tests set()
	 */
	@Test
	public void testSet() {
		LinkedListRecursive<String> list = new LinkedListRecursive<String>();
		Exception exception3 = assertThrows(IndexOutOfBoundsException.class,
				() -> list.set(0, null));
		assertEquals(null, exception3.getMessage());
		for (int i = 0; i < BRIEF_LIST; ++i) {
			list.add(i, "String" + i);
		}
		assertEquals("String2", list.set(2, "String10"));
		assertEquals("String10", list.get(2));
		Exception exception2 = assertThrows(NullPointerException.class,
				() -> list.set(0, null));
		assertEquals(null, exception2.getMessage());
		
	}
	
}
