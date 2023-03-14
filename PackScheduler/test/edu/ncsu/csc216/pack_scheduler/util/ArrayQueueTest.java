package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.NoSuchElementException;

import org.junit.Test;

/**
 * Tests ArrayQueue class
 * @author Cole Sanders
 *
 */
public class ArrayQueueTest {
	
	/**
	 * Tests constructor
	 */
	@Test
	public void testArrayQueue() {
		ArrayQueue<String> queue = new ArrayQueue<String>(10);
		assertTrue(queue.isEmpty());
		assertEquals(0, queue.size());
	}
	
	/**
	 * Tests enqueue() method
	 */
	@Test
	public void testEnqueue() {
		ArrayQueue<String> queue = new ArrayQueue<String>(2);
		queue.enqueue("String0");
		assertEquals("String0", queue.dequeue());
		queue.enqueue("String1");
		queue.enqueue("String2");
		assertEquals("String1", queue.dequeue());
		queue.enqueue("String3");
		Exception e = assertThrows(IllegalArgumentException.class,
				() -> queue.enqueue("String4"));
		assertEquals("Full", e.getMessage());
	}
	
	/**
	 * Tests dequeue() method
	 */
	@Test
	public void testDequeue() {
		ArrayQueue<String> queue = new ArrayQueue<String>(2);
		queue.enqueue("String0");
		assertEquals("String0", queue.dequeue());
		queue.enqueue("String1");
		queue.enqueue("String2");
		assertEquals("String1", queue.dequeue());
		assertEquals("String2", queue.dequeue());
		Exception e = assertThrows(NoSuchElementException.class,
				() -> queue.dequeue());
		assertEquals(null, e.getMessage());
	}
	
	/**
	 * Tests isEmpty() method
	 */
	@Test
	public void testIsEmpty() {
		ArrayQueue<String> queue = new ArrayQueue<String>(2);
		assertTrue(queue.isEmpty());
		queue.enqueue("String0");
		assertFalse(queue.isEmpty());
		queue.dequeue();
		assertTrue(queue.isEmpty());
	}
	
	/**
	 * Tests size() method
	 */
	@Test
	public void testSize() {
		ArrayQueue<String> queue = new ArrayQueue<String>(2);
		assertEquals(0, queue.size());
		queue.enqueue("String0");
		assertEquals(1, queue.size());
		queue.dequeue();
		assertEquals(0, queue.size());
	}
	
	/**
	 * Tests setCapacity() method
	 */
	@Test
	public void testSetCapacity() {
		ArrayQueue<String> queue = new ArrayQueue<String>(0);
		Exception e = assertThrows(IllegalArgumentException.class,
				() -> queue.enqueue("String1"));
		assertEquals("Full", e.getMessage());
		queue.setCapacity(1);
		try {
			queue.enqueue("String0");
		} catch (Exception e2) {
			fail();
		}
		Exception e3 = assertThrows(IllegalArgumentException.class,
				() -> queue.enqueue("String1"));
		assertEquals("Full", e3.getMessage());
	}
}
