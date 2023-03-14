
package edu.ncsu.csc216.pack_scheduler.course;

import java.util.Objects;

import edu.ncsu.csc216.pack_scheduler.course.roll.CourseRoll;
import edu.ncsu.csc216.pack_scheduler.course.validator.CourseNameValidator;
import edu.ncsu.csc216.pack_scheduler.course.validator.InvalidTransitionException;

/**
 * A class that represents a course at NCSU with a name, title, section number, credits, instructorID, meetingDays,
 * startTime, and endTime. It contains all the necessary getters and setters for its fields as well as overriding hash code,
 * equals, and to string to fit the project description. In addition methods getShortDisplayArray and getLongDisplayArray are
 * included to output some or all of the contents of this class to a GUI. This class is a subclass of Activity.
 * @author Cole Sanders
 *
 */
public class Course extends Activity implements Comparable<Course> {
	
	/** Number of digits in a section number */
	private static final int SECTION_LENGTH = 3;
	/** Maximum number of credits */
	private static final int MAX_CREDITS = 5;
	/** Minimum number of credits */
	private static final int MIN_CREDITS = 1;
	/** Course's name. */
	private String name;
	/** Course's section. */
	private String section;
	/** Course's credit hours */
	private int credits;
	/** Course's instructor */
	private String instructorId;
	/** Class used to ensure valid course names **/
	private CourseNameValidator validator;
	/** The enrollment of a course */
	private CourseRoll roll;
	
	/**
	 * Constructs a course and sets values for all fields.
	 * @param name name of the course
	 * @param title title of the course
	 * @param section section number of the course
	 * @param credits credit hours of the course
	 * @param instructorId instructor's ID
	 * @param enrollmentCap the enrollment capacity
	 * @param meetingDays meeting days for the course
	 * @param startTime start time of the course
	 * @param endTime end time of the course
	 */
	public Course(String name, String title, String section, int credits, String instructorId, int enrollmentCap, String meetingDays,
			int startTime, int endTime) {
		super(title, meetingDays, startTime, endTime);
		setName(name);
	    setSection(section);
	    setCredits(credits);
	    setInstructorId(instructorId);
	    roll = new CourseRoll(this, enrollmentCap);
	}
	

	/**
	 * Constructs a course and sets name, title, section, credits, instructorID, enrollment capacity, and meetingDays for arranged courses.
	 * @param name name of the course
	 * @param title title of the course
	 * @param section section number of the course
	 * @param credits credit hours of the course
	 * @param instructorId instructor's ID
	 * @param enrollmentCap the enrollment capacity
	 * @param meetingDays meeting days for the course
	 */
	public Course(String name, String title, String section, int credits, String instructorId, int enrollmentCap, String meetingDays) {
		this(name, title, section, credits, instructorId, enrollmentCap, meetingDays, 0, 0);
		roll = new CourseRoll(this, enrollmentCap);
	}

	/**
	 * Returns the name of the course
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the course. If name is null or empty, has less than 5
	 * or more than 8 characters, has no space between the letters and
	 * characters, has less than one or more than four letters, and if it has
	 * anything other than three digits, an IllegalArgumentException is thrown.
	 * @param name the name to set
	 * @throws IllegalArgumentException if the name passed in is invalid
	 */
	private void setName(String name) {
		try {
			validator = new CourseNameValidator();
			if (!validator.isValid(name)) {
				throw new IllegalArgumentException();
			}
			this.name = name;
		} catch (InvalidTransitionException e) {
			throw new IllegalArgumentException("Invalid course name.");
		} catch (Exception e) {
			throw new IllegalArgumentException("Invalid course name.");
		}
	}

	/**
	 * Returns the section of the course
	 * @return the section
	 */
	public String getSection() {
		return section;
	}

	/**
	 * Sets the section of the course. If section is null or is not three digits an
	 * IllegalArgumentException is thrown.
	 * @param section the section to set
	 * @throws IllegalArgumentException if section parameter is invalid
	 */
	public void setSection(String section) {
		if (section == null || section.length() != SECTION_LENGTH) {
			throw new IllegalArgumentException("Invalid section.");
		}
		for (int i = 0; i < section.length(); ++i) {
			if (!Character.isDigit(section.charAt(i))) {
				throw new IllegalArgumentException("Invalid section.");
			}
		}
		this.section = section;
	}

	/**
	 * Returns the credit hours of the course
	 * @return the credits
	 */
	public int getCredits() {
		return credits;
	}

	/**
	 * Sets the credit hours of the course. If the credits are less than one or greater
	 * than five, an IllegalArgumentException is thrown.
	 * @param credits the credits to set
	 * @throws IllegalArgumentException if section parameter is invalid
	 */
	public void setCredits(int credits) {
		if (credits < MIN_CREDITS || credits > MAX_CREDITS) {
			throw new IllegalArgumentException("Invalid credits.");
		}
		this.credits = credits;
	}

	/**
	 * Returns the instructor's ID
	 * @return the instructorId
	 */
	public String getInstructorId() {
		return instructorId;
	}

	/**
	 * Sets the instructors ID.
	 * @param instructorId the instructorId to set
	 * @throws IllegalArgumentException if the instructor id is invalid.
	 */
	public void setInstructorId(String instructorId) {
		if ("".equals(instructorId)) {
			throw new IllegalArgumentException("Invalid instructor id.");
		}
		this.instructorId = instructorId;
	}

	
	

	/**
	 * Creates hashCode for Course with all fields that don't already exist within Activity
	 * @return hashCode for Course
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(credits, instructorId, name, section);
		return result;
	}

	/**
	 * Compares the equality of two Course objects by comparing all of their fields not already in the Activity class.
	 * @param obj Object being compared
	 * @return true if all the fields of the two objects are equal
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		return credits == other.credits && Objects.equals(instructorId, other.instructorId)
				&& Objects.equals(name, other.name) && Objects.equals(section, other.section);
	}

	/**
	 * Returns a comma separated value String of all Course fields.
	 * @return String representation of Course
	 */
	@Override
	public String toString() {
	    if ("A".equals(getMeetingDays())) {
	        return name + "," + getTitle() + "," + section + "," + credits + "," + instructorId + "," + roll.getEnrollmentCap() + "," + getMeetingDays();
	    }
	    return name + "," + getTitle() + "," + section + "," + credits + "," + instructorId + "," + roll.getEnrollmentCap() + "," + getMeetingDays() + "," + getStartTime() + "," + getEndTime(); 
	}


	/**
	 * Creates a shorter string array listing the course's name, section, title, meeting string, and enrollment capacity.
	 * Overrides getShortDisplayArray in Activity class because some course specific fields need to be displayed.
	 * @return an array string listing some of the information about the course detailed above
	 */
	@Override
	public String[] getShortDisplayArray() {
		String[] shortDisplayArray = new String[5];
		shortDisplayArray[0] = name;
		shortDisplayArray[1] = section;
		shortDisplayArray[2] = getTitle();
		shortDisplayArray[3] = getMeetingString();
		String s = "";
		s += roll.getOpenSeats();
		shortDisplayArray[4] = s;
		return shortDisplayArray;
	}


	/**
	 * Creates a longer string array listing the course's name, section, title, credits, instructorId,
	 * meeting string, and an empty string that will be used in the Event class. Overrides getLongDisplayArray
	 * in Activity class because some course specific fields need to be displayed.
	 * @return an array string listing all the course details plus an empty string
	 */
	@Override
	public String[] getLongDisplayArray() {
		String[] longDisplayArray = new String[7];
		longDisplayArray[0] = name;
		longDisplayArray[1] = section;
		longDisplayArray[2] = getTitle();
		String creditsString = "";
		creditsString += credits;
		longDisplayArray[3] = creditsString;
		longDisplayArray[4] = instructorId;
		longDisplayArray[5] = getMeetingString();
		longDisplayArray[6] = "";
		return longDisplayArray;
	}


	/**
	 * Sets the meeting days, start times, and end times of an event. If meetingDays is null,
	 * an empty string or if the days are not non repeating valid characters, an IllegalArgumentException
	 * is thrown. Overrides Activity setMeetingDaysAndTime because valid course meeting days are different
	 * from valid event meeting days.
	 * @param meetingDays the days the course meets
	 * @param startTime the time the course meets
	 * @param endTime the time the course ends
	 * @throws IllegalArgumentException if any parameters are invalid
	 */
	@Override
	public void setMeetingDaysAndTime(String meetingDays, int startTime, int endTime) {
		if (meetingDays == null || meetingDays.length() == 0) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}
		if ("A".equals(meetingDays)) {
			if (startTime != 0 || endTime != 0) {
				throw new IllegalArgumentException("Invalid meeting days and times.");
			}
			super.setMeetingDaysAndTime(meetingDays, 0, 0);
		}
		else {
			int numM = 0;
			int numT = 0;
			int numW = 0;
			int numH = 0;
			int numF = 0;
			for (int i = 0; i < meetingDays.length(); ++i) {
				if (meetingDays.charAt(i) == 'M') {
					numM += 1;
				}
				else if (meetingDays.charAt(i) == 'T') {
					numT += 1;
				}
				else if (meetingDays.charAt(i) == 'W') {
					numW += 1;
				}
				else if (meetingDays.charAt(i) == 'H') {
					numH += 1;
				}
				else if (meetingDays.charAt(i) == 'F') {
					numF += 1;
				}
				else {
					throw new IllegalArgumentException("Invalid meeting days and times.");
				}
			}
			if (numM > 1 || numT > 1 || numW > 1 || numH > 1 || numF > 1) {
				throw new IllegalArgumentException("Invalid meeting days and times.");
			}
			super.setMeetingDaysAndTime(meetingDays, startTime, endTime);
		}
	}
	
	
	/**
	 * Compares if two courses are duplicates. Overrides the Activity isDuplicate method to check
	 * for duplicates between two courses specifically.
	 * @param activity potential duplicate course
	 * @return true if the courses have the same name, otherwise false
	 */
	@Override
	public boolean isDuplicate(Activity activity) {
		if (activity instanceof Course) {
			Course course = (Course) activity; 
			return course.getName().equals(name);
		}
		else {
			return false;
		}
	}
	
	/**
	 * Orders current instance of Course against passed in Course c. Ordering is done based on name
	 * and then section.
	 * @param c course ordering is checked against
	 * @return -1 if current instance of Course is less than passed in instance, 0 if equal, and 1
	 * if greater than.
	 */
	public int compareTo(Course c) {
		if (this.getName().equals(c.getName()) && this.getSection().equals(c.getSection())) {
			return 0;
		}
		
		if (name.length() < c.getName().length()) {
			for (int i = 0; i < name.length(); ++i) {
				if (name.charAt(i) > c.getName().charAt(i)) {
					return 1;
				}
				if (name.charAt(i) < c.getName().charAt(i)) {
					return -1;
				}
			}
			return -1;
		}
		else if (name.length() > c.getName().length()){
			for (int i = 0; i < c.getName().length(); ++i) {
				if (name.charAt(i) > c.getName().charAt(i)) {
					return 1;
				}
				if (name.charAt(i) < c.getName().charAt(i)) {
					return -1;
				}
			}
			return 1;
		}
		else {
			for (int i = 0; i < c.getName().length(); ++i) {
				if (name.charAt(i) > c.getName().charAt(i)) {
					return 1;
				}
				if (name.charAt(i) < c.getName().charAt(i)) {
					return -1;
				}
			}
		}
		
		
		
		for (int i = 0; i < section.length(); ++i) {
			if (section.charAt(i) > c.getSection().charAt(i)) {
				return 1;
			}
			if (section.charAt(i) < c.getSection().charAt(i)) {
				return -1;
			}
		}
			return -1;
		
		
		
	}
	
	/**
	 * Gets the enrollment for a course.
	 * @return the enrollment for the course.
	 */
	public CourseRoll getCourseRoll() {
		return roll;
	}

}
