/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course.validator;

/**
 * An exception that is thrown if an invalid transition is made when updating
 * the course name finite state machine. This exception has two instructors. The first
 * makes the exception with a custom message. The second uses a default message.
 * @author Cole Sanders
 *
 */
public class InvalidTransitionException extends Exception {
	
	/** Serialization ID */
	private static final long serialVersionUID = 1L;

	/**
	 * Creates an InvalidTransitionException from the exception superclass with a custom passed 
	 * in message.
	 * @param message the custom passed in message.
	 */
	public InvalidTransitionException(String message) {
		super(message);
	}
	
	/**
	 * Creates an InvalidTransitionException with the default message: "Invalid FSM Transition."
	 */
	public InvalidTransitionException() {
		this("Invalid FSM Transition.");
	}
}
