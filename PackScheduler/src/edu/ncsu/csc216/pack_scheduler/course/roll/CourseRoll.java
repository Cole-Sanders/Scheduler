/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course.roll;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc216.pack_scheduler.util.LinkedAbstractList;
import edu.ncsu.csc216.pack_scheduler.util.LinkedQueue;

/**
 * A class that keeps track of a courses enrollment. Its data includes a LinkedAbstractList of enrolled
 * students and the enrollment capacity for the course. Its functionality includes enrolling students in 
 * a course, dropping students from a course, setting the enrollment capacity, determining if a student
 * can be enrolled in a course, and getting the number of available seats in a course.
 * @author Cole Sanders
 *
 */
public class CourseRoll {
	/** A LinkedAbstractList containing the student enrolled in a class */
	private LinkedAbstractList<Student> roll;
	/** The enrollment capacity of a course roll */
	private int enrollmentCap;
	/** A linked list based queue of students on the wait list */
	private LinkedQueue<Student> waitlist;
	/** The course that a CourseRoll belongs to */
	private Course course;
	/** The minimum enrollment capacity */
	private static final int MIN_ENROLLMENT = 10;
	/** The maximum enrollment capacity */
	private static final int MAX_ENROLLMENT = 250;
	
	/**
	 * Constructs a CourseRoll and initializes the enrollment capacity with the passed in 
	 * integer and the course roll with an empty LinkedAbstractList of equal capacity.
	 * @param c the course that this CourseRoll belongs to
	 * @param enrollmentCap the passed in value for enrollment capacity
	 * @throws IllegalArgumentException if the passed in course is null
	 * @throws IllegalArgumentException if the the enrollment capacity is less than 10 or
	 * greater than 250.
	 */
	public CourseRoll(Course c, int enrollmentCap) {
		setCourse(c);
		if (course == null) {
			throw new IllegalArgumentException();
		}
		waitlist = new LinkedQueue<Student>(10);
		setEnrollmentCap(enrollmentCap);
		roll = new LinkedAbstractList<Student>(enrollmentCap);
		
	}
	
	/**
	 * Sets the value of course to the parameter value
	 * @param c a course that is being set
	 * @throws IllegalArgumentException if the course parameter is null
	 */
	private void setCourse(Course c) {
		if (c == null) {
			throw new IllegalArgumentException("Invalid course");
		}
		course = c;
	}
	
	/**
	 * Enrolls a given student into the course. If the course is full the student
	 * is added to a waitlist.
	 * @param s the student being enrolled.
	 * @throws IllegalArgumentException if s is null, the waitlist is full, the
	 * student is already enrolled in the course, or there is an error when
	 * attempting to add the student to the course.
	 */
	public void enroll(Student s) {
		if (s == null) {
			throw new IllegalArgumentException();
		}
		if (roll.size() == enrollmentCap) {
				waitlist.enqueue(s);
		} else {
			for (int i = 0; i < roll.size(); ++i) {
				if (roll.get(i).equals(s)) {
					throw new IllegalArgumentException();
				}
			}
			try {
				roll.add(roll.size(), s);
			} catch (Exception e) {
				throw new IllegalArgumentException();
			}
		}
	}
	
	/**
	 * Gets the number of open seats left in a class.
	 * @return the number of empty seats.
	 */
	public int getOpenSeats() {
		return enrollmentCap - roll.size();
	}
	
	/**
	 * Gets the enrollment capacity.
	 * @return the enrollment capacity.
	 */
	public int getEnrollmentCap() {
		return enrollmentCap;
	}
	
	/**
	 * Sets the enrollment capacity with a passed in value if the course roll hasn't been 
	 * initialized or if it has and the new enrollment capacity is greater than the number
	 * of students enrolled.
	 * @param enrollmentCap the passed in value for enrollment capacity.
	 * @throws IllegalArgumentException if the enrollment capacity is less than 10
	 * or greater than 250.
	 */
	public void setEnrollmentCap(int enrollmentCap) {
		if (enrollmentCap < MIN_ENROLLMENT || enrollmentCap > MAX_ENROLLMENT) {
			throw new IllegalArgumentException("Invalid enrollment capacity");
		}
		if (roll == null) {
			this.enrollmentCap = enrollmentCap;
		} else if (roll != null && enrollmentCap >= roll.size()){
			this.enrollmentCap = enrollmentCap;
			roll.setCapacity(enrollmentCap);
		} else {
			throw new IllegalArgumentException();
		}
	}
	
	/**
	 * Drops a given student from the enrollment roll then adds the next student
	 * on the waitlist to the roll. If the student is on the waitlist then it
	 * drops them from the waitlist.
	 * @param s the student being dropped.
	 * @throws IllegalArgumentException if the student is null or there is
	 * an error trying to remove the student.
	 */
	public void drop(Student s) {
		if (s == null) {
			throw new IllegalArgumentException("Invalid student");
		}
		
		try {
			for (int i = 0; i < roll.size(); ++i) {
				if (s.equals(roll.get(i))) {
					roll.remove(i);
					if (waitlist.size() > 0) {
						Student student = waitlist.dequeue();
						enroll(student);
						student.getSchedule().addCourseToSchedule(course);
					}
				}
			}
			int size = waitlist.size();
			for (int i = 0; i < size; ++i) {
				Student student = waitlist.dequeue();
				if (!student.equals(s)) {
					waitlist.enqueue(student);
				}
			}
		} catch (Exception e) {
			throw new IllegalArgumentException();
		}
	}
	
	/**
	 * Determines if a student can enroll in the course roll. Returns true if yes,
	 * false if no.
	 * @param s student under inspection
	 * @return false if the course is full or the student is already enrolled, true otherwise.
	 */
	public boolean canEnroll(Student s) {
		int size = waitlist.size();
		boolean inWaitlist = false;
		for (int i = 0; i < size; ++i) {
			Student student = waitlist.dequeue();
			if (student.equals(s)) {
				inWaitlist = true;
			}
			waitlist.enqueue(student);
		}
		if (inWaitlist) {
			return false;
		}
		if (roll.size() >= enrollmentCap && waitlist.size() >= 10) {
			return false;
		}
		for (int i = 0; i < roll.size(); ++i) {
			if (roll.get(i).equals(s)) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Gets the number of students on the waitlist.
	 * @return the number of students on the waitlist
	 */
	public int getNumberOnWaitlist() {
		return waitlist.size();
	}
}
