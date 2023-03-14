/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course.validator;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Tests the InvalidTransitionException class
 * @author Cole Sanders
 */
public class InvalidTransitionExceptionTest {
	
	/**
	 * Tests constructor with custom message.
	 */
	@Test
	public void testCustomMessage() {
		InvalidTransitionException e = new InvalidTransitionException("Specified message");
		assertEquals("Specified message", e.getMessage());
	}
	
	/**
	 * Tests constructor with default message.
	 */
	@Test
	public void testDefaultMessage() {
		InvalidTransitionException e = new InvalidTransitionException();
		assertEquals("Invalid FSM Transition.", e.getMessage());
	}
}
