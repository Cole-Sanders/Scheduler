/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.user;

import edu.ncsu.csc216.pack_scheduler.user.schedule.FacultySchedule;

/**
 * A class that represents a faculty member with their full name, id, email, password, and the 
 * maximum number of courses for them to teach. This class contains the necessary getters
 * and setters for the maxCourses field along with an overriding of hash code, equals, and toString
 * to fit the project description.
 * @author Cole Sanders
 *
 */
public class Faculty extends User {
	
	/** Maximum number of courses for any faculty */
	public static final int MAX_COURSES = 3;
	/** Minimum number of courses for any faculty */
	public static final int MIN_COURSES = 1;

	/** Faculty's maximum number of courses */
	private int maxCourses;
	/** Faculty's schedule */
	private FacultySchedule schedule;
	
	/**
	 * Constructs faculty's information and sets values for all that faculty's fields 
	 * @param firstName faculty's first name
	 * @param lastName faculty's last name
	 * @param id faculty's id
	 * @param email faculty's email
	 * @param password faculty's password
	 * @param maxCourses faculty's max number of courses
	 */
	public Faculty(String firstName, String lastName, String id, String email, String password, int maxCourses) {
		super(firstName, lastName, id, email, password);
		setMaxCourses(maxCourses);
		schedule = new FacultySchedule(id);
	}

	/**
	 * Gets the faculty's maximum number of courses
	 * @return the maximum number of courses
	 */
	public int getMaxCourses() {
		return maxCourses;
	}

	/**
	 * Sets the faculty's maximum number of courses. If it is less than one or more than three,
	 * an IllegalArgumentException is thrown.
	 * @param maxCourses the maximum number of courses a faculty member will teach
	 * @throws IllegalArgumentException if the number of courses is invalid
	 */
	public void setMaxCourses(int maxCourses) {
		if (maxCourses < MIN_COURSES || maxCourses > MAX_COURSES) {
			throw new IllegalArgumentException("Invalid max courses");
		}
		this.maxCourses = maxCourses;
	}

	/**
	 * Creates hashCode based on the full name, email, id, password, and maximum course number
	 * of faculty members.
	 * @return the hashCode for that faculty member
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((getEmail() == null) ? 0 : getEmail().hashCode());
		result = prime * result + ((getFirstName() == null) ? 0 : getFirstName().hashCode());
		result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
		result = prime * result + ((getLastName() == null) ? 0 : getLastName().hashCode());
		result = prime * result + maxCourses;
		result = prime * result + ((getPassword() == null) ? 0 : getPassword().hashCode());
		return result;
	}

	/**
	 * Overrides equals to depend on a faculty member's fields. If all the 
	 * fields are equivalent than two faculty members are considered equal.
	 * @return true if two faculty members are equal.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Faculty other = (Faculty) obj;
		if (!getEmail().equals(other.getEmail()))
			return false;
		if (!getFirstName().equals(other.getFirstName()))
			return false;
		if (!getId().equals(other.getId()))
			return false;
		if (!getLastName().equals(other.getLastName()))
			return false;
		if (maxCourses != other.getMaxCourses())
			return false;
		return getPassword().equals(other.getPassword());
	}

	/**
	 * Returns a comma separated value String of all faculty's fields.
	 * @return String representation of a faculty member
	 */
	@Override
	public String toString() {
		return getFirstName() + "," + getLastName() + "," + getId() + "," + getEmail()
				+ "," + getPassword() + "," + maxCourses;
	}
	
	/**
	 * Gets the faculty's schedule.
	 * @return the faculty's schedule.
	 */
	public FacultySchedule getSchedule() {
		return schedule;
	}

	/**
	 * Checks if a faculty member's schedule has more than their maximum number of courses.
	 * @return true if the schedule has more than their maximum course capacity, false otherwise.
	 */
	public boolean isOverloaded() {
		return schedule.getNumScheduledCourses() > maxCourses;
	}
}

