/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course;

/**
 * An exception that is thrown by conflict when two activities overlap in their scheduled times. Contains two constructors, one
 * creates an exception with a custom message and the other with a default message.
 * @author Cole Sanders
 *
 */
public class ConflictException extends Exception {

	/** ID used for serialization. */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructs a ConflictException using the Exception superclass and a custom message
	 * @param message a custom message that is thrown to the user by the exception
	 */
	public ConflictException(String message) {
		super(message);
	}

	
	/**
	 * Constructs a ConflictException with the default message "Schedule conflict."
	 */
	public ConflictException() {
		this("Schedule conflict.");
	}
	
}
