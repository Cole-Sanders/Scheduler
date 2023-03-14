/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course;

/**
 * A java interface in the Activity hierarchy that compares the current instance to the passed
 * in instance. Contains the chceckConflict method.
 * @author Cole Sanders
 */
public interface Conflict {
	
	/**
	 * Checks if an activity conflicts with the existing activity. It is considered conflicting the times overlap
	 * on any day of the week.
	 * @param possibleConflictingActivity the activity checked
	 * @throws ConflictException if the checked activity conflicts with the existing activity
	 */
	void checkConflict(Activity possibleConflictingActivity) throws ConflictException;

}
