/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.EmptyStackException;

import org.junit.Test;

/**
 * Tests ArrayStack class
 * @author Cole Sanders
 *
 */
public class ArrayStackTest {
	
	/**
	 * Tests constructor
	 */
	@Test
	public void testArrayStack() {
		ArrayStack<String> stack = new ArrayStack<String>(10);
		assertTrue(stack.isEmpty());
		assertEquals(0, stack.size());
	}
	
	/**
	 * Tests push() method
	 */
	@Test
	public void testPush() {
		ArrayStack<String> stack = new ArrayStack<String>(2);
		stack.push("String0");
		assertEquals("String0", stack.pop());
		stack.push("String1");
		stack.push("String2");
		assertEquals("String2", stack.pop());
		stack.push("String3");
		Exception e = assertThrows(IllegalArgumentException.class,
				() -> stack.push("String4"));
		assertEquals("Full", e.getMessage());
	}
	
	/**
	 * Tests pop() method
	 */
	@Test
	public void testPop() {
		ArrayStack<String> stack = new ArrayStack<String>(2);
		stack.push("String0");
		assertEquals("String0", stack.pop());
		stack.push("String1");
		stack.push("String2");
		assertEquals("String2", stack.pop());
		assertEquals("String1", stack.pop());
		Exception e = assertThrows(EmptyStackException.class,
				() -> stack.pop());
		assertEquals(null, e.getMessage());
	}
	
	/**
	 * Tests isEmpty() method
	 */
	@Test
	public void testIsEmpty() {
		ArrayStack<String> stack = new ArrayStack<String>(2);
		assertTrue(stack.isEmpty());
		stack.push("String0");
		assertFalse(stack.isEmpty());
		stack.pop();
		assertTrue(stack.isEmpty());
	}
	
	/**
	 * Tests size() method
	 */
	@Test
	public void testSize() {
		ArrayStack<String> stack = new ArrayStack<String>(2);
		assertEquals(0, stack.size());
		stack.push("String0");
		assertEquals(1, stack.size());
		stack.pop();
		assertEquals(0, stack.size());
	}
	
	/**
	 * Tests setCapacity() method
	 */
	@Test
	public void testSetCapacity() {
		ArrayStack<String> stack = new ArrayStack<String>(0);
		Exception e = assertThrows(IllegalArgumentException.class,
				() -> stack.push("String1"));
		assertEquals("Full", e.getMessage());
		stack.setCapacity(1);
		try {
			stack.push("String0");
		} catch (Exception e2) {
			fail();
		}
		Exception e3 = assertThrows(IllegalArgumentException.class,
				() -> stack.push("String1"));
		assertEquals("Full", e3.getMessage());
	}
}
