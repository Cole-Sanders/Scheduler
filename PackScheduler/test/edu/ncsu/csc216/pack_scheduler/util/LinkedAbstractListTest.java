/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.Test;

/**
 * Tests the LinkedAbstractList class.
 * @author Cole Sanders
 */
public class LinkedAbstractListTest {
	/** The initial capacity of some lists */
	private static final int INITIAL_CAPACITY = 10;
	
	/**
	 * Tests the constructor of LinkedAbstractList
	 */
	@Test
	public void testLinkedAbstractList() {
		LinkedAbstractList<String> list = new LinkedAbstractList<String>(20);
		assertEquals(0, list.size());
	}
	
	/**
	 * Tests add()
	 */
	@Test
	public void testAdd() {
		LinkedAbstractList<String> list = new LinkedAbstractList<String>(20);
		for (int i = 0; i < INITIAL_CAPACITY; ++i) {
			list.add(i, "String" + i);
		}
		assertEquals("String9", list.get(9));
		list.add(10, "String10");
		assertEquals("String10", list.get(10));
		list.add(2, "String11");
		assertEquals("String11", list.get(2));
		assertEquals("String10", list.get(11));
		list.add(0, "String12");
		assertEquals("String12", list.get(0));
		list.add(0, "String13");
		list.add(0, "String14");
		list.add(0, "String15");
		list.add(0, "String16");
		list.add(0, "String17");
		list.add(0, "String18");
		list.add(0, "String19");
		
		
	}
	
	/**
	 * Tests remove()
	 */
	@Test
	public void testRemove() {
		LinkedAbstractList<String> list = new LinkedAbstractList<String>(20);
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
		LinkedAbstractList<String> list = new LinkedAbstractList<String>(20);
		for (int i = 0; i < INITIAL_CAPACITY; ++i) {
			list.add(i, "String" + i);
		}
		assertEquals("String2", list.set(2, "String10"));
		assertEquals("String10", list.get(2));
		
	}
	
	/**
	 * Tests setCapacity()
	 */
	@Test
	public void testSetCapacity() {
		LinkedAbstractList<String> list = new LinkedAbstractList<String>(20);
		Exception e = assertThrows(IllegalArgumentException.class,
				() -> list.setCapacity(-1));
		assertEquals("Invalid capacity.", e.getMessage());
	}

}
