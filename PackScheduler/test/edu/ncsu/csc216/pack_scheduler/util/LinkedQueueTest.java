package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.NoSuchElementException;

import org.junit.Test;

/**
 * Tests LinkedQueue class
 * @author Cole Sanders
 *
 */
public class LinkedQueueTest {
	
	/**
	 * Tests constructor
	 */
	@Test
	public void testLinkedQueue() {
		LinkedQueue<String> queue = new LinkedQueue<String>(10);
		assertTrue(queue.isEmpty());
		assertEquals(0, queue.size());
	}
	
	/**
	 * Tests enqueue() method
	 */
	@Test
	public void testEnqueue() {
		LinkedQueue<String> queue = new LinkedQueue<String>(2);
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
		LinkedQueue<String> queue = new LinkedQueue<String>(2);
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
		LinkedQueue<String> queue = new LinkedQueue<String>(2);
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
		LinkedQueue<String> queue = new LinkedQueue<String>(2);
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
		LinkedQueue<String> queue = new LinkedQueue<String>(0);
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
