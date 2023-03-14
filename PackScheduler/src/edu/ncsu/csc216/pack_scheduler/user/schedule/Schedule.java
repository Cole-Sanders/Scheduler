/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.user.schedule;

import edu.ncsu.csc216.pack_scheduler.course.ConflictException;
import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.util.ArrayList;

/**
 * A class representing a schedule of classes. This class contains the name of the schedule
 * and an array list of courses. Its functionality allows for a user to add and remove courses,
 * reset the fields of the schedule, get information about the scheduled courses, and reset the
 * title of the schedule.
 * @author Cole Sanders
 *
 */
public class Schedule {
	/** The title of the schedule */
	private String title;
	/** The array list of courses that constitutes the schedule */
	private ArrayList<Course> schedule;
	
	/**
	 * Constructor for the Schedule class. Creates an empty array list of courses
	 * for the schedule and sets the title to "My Schedule".
	 */
	public Schedule() {
		schedule = new ArrayList<Course>();
		setTitle("My Schedule");
	}
	
	/**
	 * Adds a course to the end of the schedule.
	 * @param course the course to be added.
	 * @return true if the course was successfully added.
	 * @throws IllegalArgumentException if the course is already in the schedule
	 * @throws IllegalArgumentException if there is a conflict with the course
	 * @throws NullPointerException if the course is null
	 */
	public boolean addCourseToSchedule(Course course) {
		for (int i = 0; i < schedule.size(); ++i) {
			if (course.isDuplicate(schedule.get(i))) {
				throw new IllegalArgumentException("You are already enrolled in " + course.getName());
			}
			try {
			course.checkConflict(schedule.get(i));
			} catch (ConflictException e) {
				throw new IllegalArgumentException("The course cannot be added due to a conflict.");
			}
		}
		schedule.add(course);
		return true;
	}
	
	/**
	 * Removes a given course from the schedule.
	 * @param course the course to be removed.
	 * @return true if the course is removed from the schedule,
	 * false if the course cannot be found in the schedule.
	 */
	public boolean removeCourseFromSchedule(Course course) {
		if(course == null) {
			return false;
		}
		for (int i = 0; i < schedule.size(); ++i) {
			if (course.equals(schedule.get(i))) {
				schedule.remove(i);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Resets the schedule to a new array list of courses and resets
	 * the title to "My Schedule".
	 */
	public void resetSchedule() {
		schedule = new ArrayList<Course>();
		setTitle("My Schedule");
	}
	
	/**
	 * Gets the information of the courses in the schedule in a 2D array.
	 * Each row constitutes a course with its name, section, title, and meeting string
	 * information making up the four columns.
	 * @return A 2D array of information about courses in the schedule.
	 */
	public String[][] getScheduledCourses() {
		String[][] courses = new String[schedule.size()][5];
		for (int i = 0; i < schedule.size(); ++i) {
			courses[i] = schedule.get(i).getShortDisplayArray();
		}
		return courses;
	}
	
	/**
	 * Sets the schedule title.
	 * @param title the passed in title of the schedule.
	 * @throws IllegalArgumentException if the passed in title is null.
	 */
	public void setTitle(String title) {
		if (title == null) {
			throw new IllegalArgumentException("Title cannot be null.");
		}
		this.title = title;
	}
	
	/**
	 * Gets the schedule title
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * Gets the total number of credit hours in a schedule.
	 * @return the cumulative credits
	 */
	public int getScheduleCredits() {
		int sum = 0;
		for (int i = 0; i < schedule.size(); ++i) {
			sum += schedule.get(i).getCredits();
		}
		return sum;
	}
	
	/**
	 * Checks if a given course can be added to the schedule. If the course is null,
	 * is already in the schedule, or conflicts with another element in the schedule,
	 * it cannot be added.
	 * @param c the course under inspection.
	 * @return true if it can be added to the schedule, false if it can't.
	 */
	public boolean canAdd(Course c) {
		if (c == null) {
			return false;
		}
		for (int i = 0; i < schedule.size(); ++i) {
			if (schedule.get(i).isDuplicate(c)) {
				return false;
			} 
			try {
				c.checkConflict(schedule.get(i));
			} catch (ConflictException e) {
				return false;
			}
		}
		return true;
	}
}
