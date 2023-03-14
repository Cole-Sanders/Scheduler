package edu.ncsu.csc217.collections.list;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.Test;

/**
 * Tests SortedList class
 * @author Cole Sanders
 *
 */
public class SortedListTest {

	/**
	 * Tests that a list is constructed correctly
	 */
	@Test
	public void testSortedList() {
		SortedList<String> list = new SortedList<String>();
		assertEquals(0, list.size());
		assertFalse(list.contains("apple"));
		assertTrue(list.isEmpty());
		
		
		//TODO Test that the list grows by adding at least 11 elements
		//Remember the list's initial capacity is 10
		assertTrue(list.add("apple"));
		assertTrue(list.add("pair"));
		assertTrue(list.add("banana"));
		assertTrue(list.add("orange"));
		assertTrue(list.add("kiwi"));
		assertTrue(list.add("catalope"));
		assertTrue(list.add("watermelon"));
		assertTrue(list.add("onion"));
		assertTrue(list.add("tomato"));
		assertTrue(list.add("pie"));
		assertTrue(list.add("seeds"));
		assertEquals(11, list.size());
		
	}

	/**
	 * Tests adding objects to the list
	 */
	@Test
	public void testAdd() {
		SortedList<String> list = new SortedList<String>();
		
		list.add("banana");
		assertEquals(1, list.size());
		assertEquals("banana", list.get(0));
		
		//TODO Test adding to the front, middle and back of the list
		list.add("apple");
		assertEquals(2, list.size());
		assertEquals("apple", list.get(0));
		
		list.add("apricot");
		assertEquals(3, list.size());
		assertEquals("apricot", list.get(1));
		
		list.add("fish");
		assertEquals(4, list.size());
		assertEquals("fish", list.get(3));
		
		//TODO Test adding a null element
		assertThrows(NullPointerException.class,
				() -> list.add(null));
		
		//TODO Test adding a duplicate element
		assertThrows(IllegalArgumentException.class,
				() -> list.add("fish"));
		
		
	}
	
	/**
	 * Tests boundary cases of the get method
	 */
	@Test
	public void testGet() {
		SortedList<String> list = new SortedList<String>();
		
		//Since get() is used throughout the tests to check the
		//contents of the list, we don't need to test main flow functionality
		//here.  Instead this test method should focus on the error 
		//and boundary cases.
		
		//TODO Test getting an element from an empty list
		assertThrows(IndexOutOfBoundsException.class,
				() -> list.get(0));
		
		//TODO Add some elements to the list
		list.add("apple");
		list.add("banana");
		list.add("blueberry");
		list.add("pair");
		
		//TODO Test getting an element at an index < 0
		assertThrows(IndexOutOfBoundsException.class,
				() -> list.get(-1));
		
		//TODO Test getting an element at size
		assertEquals(4, list.size());
		assertThrows(IndexOutOfBoundsException.class,
				() -> list.get(4));
		
	}
	
	/**
	 * Tests remove method
	 */
	@Test
	public void testRemove() {
		SortedList<String> list = new SortedList<String>();
		
		//TODO Test removing from an empty list
		assertEquals(0, list.size());
		assertThrows(IndexOutOfBoundsException.class,
				() -> list.remove(0));
		
		//TODO Add some elements to the list - at least 4
		list.add("apple");
		list.add("banana");
		list.add("blueberry");
		list.add("pair");
		
		//TODO Test removing an element at an index < 0
		assertThrows(IndexOutOfBoundsException.class,
				() -> list.remove(-1));
		
		//TODO Test removing an element at size
		assertEquals(4, list.size());
		assertThrows(IndexOutOfBoundsException.class,
				() -> list.remove(4));
		
		//TODO Test removing a middle element
		list.remove(2);
		assertEquals(list.get(1), "banana");
		assertEquals(list.get(2), "pair");
		
		//TODO Test removing the last element
		list.remove(2);
		assertEquals(list.get(0), "apple");
		assertEquals(list.get(1), "banana");
		
		//TODO Test removing the first element
		list.remove(0);
		assertEquals(list.get(0), "banana");
		
		//TODO Test removing the last element
		list.remove(0);
		assertTrue(list.isEmpty());
	}
	
	/**
	 * tests indexOf() method
	 */
	@Test
	public void testIndexOf() {
		SortedList<String> list = new SortedList<String>();
		
		//TODO Test indexOf on an empty list
		assertTrue(list.isEmpty());
		assertEquals(-1, list.indexOf("pair"));
		
		//TODO Add some elements
		list.add("apple");
		list.add("banana");
		list.add("blueberry");
		list.add("pair");
		
		//TODO Test various calls to indexOf for elements in the list
		assertEquals(0, list.indexOf("apple"));
		assertEquals(1, list.indexOf("banana"));
		assertEquals(2, list.indexOf("blueberry"));
		assertEquals(3, list.indexOf("pair"));
		
		//and not in the list
		assertEquals(-1, list.indexOf("tree"));
		assertEquals(-1, list.indexOf("acorn"));
		assertEquals(-1, list.indexOf("mushroom"));
		
		//TODO Test checking the index of null
		assertThrows(NullPointerException.class,
				() -> list.indexOf(null));
		
	}
	
	/**
	 * Tests clearing the list
	 */
	@Test
	public void testClear() {
		SortedList<String> list = new SortedList<String>();

		//TODO Add some elements
		list.add("apple");
		list.add("banana");
		list.add("blueberry");
		list.add("pair");
		
		//TODO Clear the list
		list.clear();
		
		//TODO Test that the list is empty
		assertTrue(list.isEmpty());
	}

	/**
	 * Tests isEmpty() method
	 */
	@Test
	public void testIsEmpty() {
		SortedList<String> list = new SortedList<String>();
		
		//TODO Test that the list starts empty
		assertTrue(list.isEmpty());
		
		//TODO Add at least one element
		list.add("apple");
		
		//TODO Check that the list is no longer empty
		assertFalse(list.isEmpty());
	}

	/**
	 * tests contains() method
	 */
	@Test
	public void testContains() {
		SortedList<String> list = new SortedList<String>();
		
		//TODO Test the empty list case
		assertFalse(list.contains("apple"));
		
		//TODO Add some elements
		list.add("apple");
		list.add("banana");
		list.add("blueberry");
		list.add("pair");
		
		//TODO Test some true and false cases
		assertTrue(list.contains("apple"));
		assertTrue(list.contains("pair"));
		assertTrue(list.contains("banana"));
		assertFalse(list.contains("appel"));
		assertFalse(list.contains("tree"));
		assertFalse(list.contains("fruit"));
	}
	
	/**
	 * tests equals() method
	 */
	@Test
	public void testEquals() {
		SortedList<String> list1 = new SortedList<String>();
		SortedList<String> list2 = new SortedList<String>();
		SortedList<String> list3 = new SortedList<String>();
		
		//TODO Make two lists the same and one list different
		list1.add("apple");
		list1.add("pair");
		list2.add("apple");
		list2.add("pair");
		list3.add("fruit");
		list3.add("tree");
		
		
		//TODO Test for equality and non-equality
		assertTrue(list1.equals(list2));
		assertFalse(list1.equals(list3));
		
	}
	
	/**
	 * tests hashCode() method
	 */
	@Test
	public void testHashCode() {
		SortedList<String> list1 = new SortedList<String>();
		SortedList<String> list2 = new SortedList<String>();
		SortedList<String> list3 = new SortedList<String>();
		
		//TODO Make two lists the same and one list different
		list1.add("apple");
		list1.add("pair");
		list2.add("apple");
		list2.add("pair");
		list3.add("fruit");
		list3.add("tree");
		
		//TODO Test for the same and different hashCodes
		assertEquals(list1.hashCode(), list2.hashCode());
		assertNotSame(list1.hashCode(), list3.hashCode());
		
	}

}
 