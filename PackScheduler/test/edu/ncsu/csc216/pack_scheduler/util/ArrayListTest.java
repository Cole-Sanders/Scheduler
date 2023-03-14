/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Tests the ArrayList class.
 * @author Cole Sanders
 */
public class ArrayListTest {
	/** The initial capacity of a newly created list */
	private static final int INITIAL_CAPACITY = 10;
	
	/**
	 * Tests the constructor of ArrayList
	 */
	@Test
	public void testArrayList() {
		ArrayList<String> list = new ArrayList<String>();
		assertEquals(0, list.size());
	}
	
	/**
	 * Tests add()
	 */
	@Test
	public void testAdd() {
		ArrayList<String> list = new ArrayList<String>();
		for (int i = 0; i < INITIAL_CAPACITY; ++i) {
			list.add(i, "String" + i);
		}
		assertEquals("String9", list.get(9));
		list.add(10, "String10");
		assertEquals("String10", list.get(10));
		list.add(2, "String11");
		assertEquals("String11", list.get(2));
		assertEquals("String10", list.get(11));
		
	}
	
	/**
	 * Tests remove()
	 */
	@Test
	public void testRemove() {
		ArrayList<String> list = new ArrayList<String>();
		for (int i = 0; i < INITIAL_CAPACITY; ++i) {
			list.add(i, "String" + i);
		}
		assertEquals(10, list.size());
		assertEquals("String0", list.remove(0));
		assertEquals("String1", list.get(0));
		assertEquals(9, list.size());
		assertEquals("String9", list.remove(8));
		assertEquals(8, list.size());
	}
	
	/**
	 * Tests set()
	 */
	@Test
	public void testSet() {
		ArrayList<String> list = new ArrayList<String>();
		for (int i = 0; i < INITIAL_CAPACITY; ++i) {
			list.add(i, "String" + i);
		}
		assertEquals("String2", list.set(2, "String10"));
		assertEquals("String10", list.get(2));
		
	}
}
