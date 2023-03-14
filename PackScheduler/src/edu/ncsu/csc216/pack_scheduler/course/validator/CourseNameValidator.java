/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course.validator;

/**
 * A class that checks whether or not a course name is valid. It has two methods, one that checks whether a given name is valid
 * and another that gets the current state of the name in the validation process.
 * @author Julie Tran
 * @author Cole Sanders
 *
 */
public class CourseNameValidator {

	/**
	 * valid end state
	 */
	private boolean validEndState;
	/** number of letters in the alphabet*/
	private int letterCount;
	/** number of digits in the course name*/
	private int digitCount;
	/** index*/
	private int idx;
	/** character*/
	private char c;
	/** state */
	private State state;
	/** letter state */
	private LetterState letterState;
	/** number state */
	private NumberState numberState;
	/** suffix state */
	private SuffixState suffixState;
	/** initial state */
	private InitialState initialState;
	/**
	 * Constructor
	 */
	public CourseNameValidator()
	{
		validEndState = false;
		letterCount = 0;
		digitCount = 0;
		idx = 0;
		try {
			letterState = new LetterState();
			numberState = new NumberState();
			suffixState = new SuffixState();
			initialState = new InitialState();
		} catch (InvalidTransitionException e) {
			//Do nothing
		}
		state = initialState;
	}
	/**
	 * Returns true if the course name is valid, based on
	 * a string matching Finite State Machine.
	 * 
	 * The course name must match the following format:
	 *      (1-4 letters)(3 digits)(optionally, a 1 letter suffix)
	 *      
	 * @param courseName the name of the course
	 * @return true if the course name is valid, or false if the course name is invalid
	 * @throws InvalidTransitionException when the FSM attempts an invalid transition
	 * @throws IllegalArgumentException if the course name is null
	 */
	public boolean isValid(String courseName) throws InvalidTransitionException 	
	{
		if(courseName == null) 
		{
			throw new IllegalArgumentException("Invalid course name.");
		}
		state = initialState;
		letterCount = 0;
		digitCount = 0;
		idx = 0;
		validEndState = false;
		//s = course name
		while(idx < courseName.length()) 
		{
			c = courseName.charAt(idx);
			if(Character.isLetter(c)) {
				state.onLetter();
			}
			else if(Character.isDigit(c)) {
				state.onDigit();
			}
			else {
				state.onOther();
			}
			idx++;
		}
		
		return validEndState;
	}
	

	/**
	 * An abstract class for the states in the validation process. This class has abstract methods of transitions
	 * between states that are implemented by concrete classes.
	 * @author Reba
	 * @author Cole Sanders
	 *
	 */
	public abstract class State
	{
		/**
		 * A state constructor 
		 */
		public State() {
			//Nothing
		}
		
		/**
		 * Check if the given input in on a letter
		 * @throws InvalidTransitionException if an invalid transition is used.
		 */
		public abstract void onLetter() throws InvalidTransitionException;
		
		/**
		 * Checks if the given input is a digit
		 * @throws InvalidTransitionException if an invalid transition is used.
		 */
		public abstract void onDigit() throws InvalidTransitionException;
		
		/**
		 * Checks to see if the current digit is on other (non alphanumerical)
		 * @return boolean
		 * @throws InvalidTransitionException if an invalid transition is used.
		 */
		public boolean onOther() throws InvalidTransitionException{
			throw new InvalidTransitionException("Course name can only contain letters and digits.");
		}
	}

	/**
	 * The initial state class in the validation process. Processes the transition from the first
	 * character in a course name.
	 * @author Reba
	 * @author Cole Sanders
	 * @throws InvalidTransitionException if an invalid transition is used.
	 */
	private class InitialState  extends State
	{
		/** InitialState constructor **/
		private InitialState() 
		{
			//Nothing to be done
		}
		
		/**
		 * Check if the given input in on a letter. If it is, letter count is incremented
		 * and the state is set to letterState.
		 */
		@Override
		public void onLetter() 
		{
			letterCount++;
			state = letterState;
		}
		
		/**
		 * Checks if the given input is a digit.
		 * @throws InvalidTransitionException if the given input is a digit.
		 */
		@Override
		public void onDigit() throws InvalidTransitionException 	
		{
			throw new InvalidTransitionException("Course name must start with a letter.");
		}
	}
	
	/**
	 * The letter state class in the validation process. Processes the transition from the letter state
	 * to the other states.
	 * @author Reba
	 * @author Cole Sanders
	 * @throws InvalidTransitionException if an invalid transition is used.
	 */
	private class LetterState extends State
	{
		/** max prefix letters*/
		private static final int MAX_PREFIX_LETTERS = 4;
		/** LetterState constructor **/
		private LetterState() throws InvalidTransitionException
		{
			//Nothing here to do 
		}
		/**
		 * Check if the given input in on a letter. If it is, letter count is incremented
		 * and the state is set to letterState.
		 * @throws InvalidTransitionException if the letter count is greater than four.
		 */
		@Override
		public void onLetter() throws InvalidTransitionException
		{
			letterCount++;
			if (letterCount > MAX_PREFIX_LETTERS) {
				throw new InvalidTransitionException("Course name cannot start with more than 4 letters.");
			}
			state = letterState;
		}
		/**
		 * Checks if the given input is a digit. If it is digit count is incremented
		 * and the state is set to number state.
		 */
		@Override
		public void onDigit ()
		{
			digitCount++;
			state = numberState;
		}
	}

	/**
	 * The suffix state class in the validation process. Processes the transition from the suffix state
	 * to the other states.
	 * @author Reba
	 * @author Cole Sanders
	 * @throws InvalidTransitionException if an invalid transition is used.
	 */
	private class SuffixState extends State
	{
		/** SuffixState constructor **/
		private SuffixState() 
		{
			
		}
		/**
		 * Check if the given input in on a letter. If it is, an InvalidTransitionException is thrown.
		 * @throws InvalidTransitionException if the input is on a letter.
		 */
		@Override
		public void onLetter() throws InvalidTransitionException 
		{
			throw new InvalidTransitionException("Course name can only have a 1 letter suffix.");
		}
		/**
		 * Checks if the given input is a digit.
		 * @throws InvalidTransitionException if the given input is a digit.
		 */
		@Override
		public void onDigit() throws InvalidTransitionException 	
		{
			throw new InvalidTransitionException("Course name cannot contain digits after the suffix.");
		}
	}
	
	/**
	 * The suffix state class in the validation process. Processes the transition from the suffix state
	 * to the other states.
	 * @author Reba
	 * @author Cole Sanders
	 * @throws InvalidTransitionException if an invalid transition is used.
	 */
	private class NumberState extends State
	{
		/**
		 * Course number length
		 */
		private static final int COURSE_NUMBER_LENGTH = 3;
		/** NumberState constructor **/
		private NumberState() 
		{
			//Do nothing here
		}
		/**
		 * Check if the given input in on a letter. If it is, the state is set to suffixState.
		 * @throws InvalidTransitionException if there are less than three digits proceeding the letter.
		 */
		public void onLetter() throws InvalidTransitionException 
		{
			if (digitCount < 3) {
				throw new InvalidTransitionException("Course name must have 3 digits.");
			}
			validEndState = true;
			state = suffixState;
		}
		/**
		 * Check if the given input in on a digit. The digit count is incremented. If there are three digits total
		 * then validEndState is set to true, if there are more than that an InvalidTransitionException is thrown.
		 * @throws InvalidTransitionException if the digit count is greater than three.
		 */
		public void onDigit() throws InvalidTransitionException
		{
			digitCount++;
			if (digitCount > COURSE_NUMBER_LENGTH) {
				throw new InvalidTransitionException("Course name can only have 3 digits.");
			}
			if (digitCount == COURSE_NUMBER_LENGTH) {
				validEndState = true;
			}
		}
		
	}
	/**
	 * returns the state the Course is in currently
	 * @return state current state of the CourseNameValidator
	 */
	public State getState() {
		return state;

	}

	
}
