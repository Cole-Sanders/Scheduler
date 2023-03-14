package edu.ncsu.csc216.pack_scheduler.course;

import java.util.Objects;


/**
 * A class representing a student's activity in their schedule containing the title, days it meets, the start time and the end time.
 * Its methods include the gets and setters for title, start time, and end time, as well as getMeetingString, hashCode, equals,
 * getShortDisplayArray, and getLongDisplayArray. This class is an abstracted superclass of both the Event class and the Course class.
 * It is used to represent an activity that could be either an Event or a Course.
 * @author Cole Sanders
 *
 */
public abstract class Activity implements Conflict {

	/** Upper limit for hours in a day */
	private static final int UPPER_HOUR = 24;
	/** Upper limit for minutes in an hour */
	private static final int UPPER_MIN = 60;
	/** The number one hundred */
	private static final int HUNDRED = 100;
	/** Number of hours from midnight to noon */
	private static final int MIDNIGHT_NOON = 12;
	/** The number of minutes where the clock shows double digits */
	private static final int DOUBLE_DIGITS = 10;
	/** Activity's title. */
	private String title;
	/** Activity's meeting days */
	private String meetingDays;
	/** Activity's starting time */
	private int startTime;
	/** Activity's ending time */
	private int endTime;

	/** Constructs an activity and sets the value for all fields
	 * @param title title of the activity
	 * @param meetingDays the days the activity occurs
	 * @param startTime the start time of the activity
	 * @param endTime the end time of the activity
	 */
	public Activity(String title, String meetingDays, int startTime, int endTime) {
        super();
        setTitle(title);
        setMeetingDaysAndTime(meetingDays, startTime, endTime);
    }
	
	/**
	 * Gets the title of the course
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the title of the activity. If title is null or an empty string
	 * an IllegalArgumentException is thrown.
	 * @param title the title to set
	 * @throws IllegalArgumentException if title parameter is invalid
	 */
	public void setTitle(String title) {
		if (title == null || title.length() == 0) {
			throw new IllegalArgumentException("Invalid title.");
		}
		this.title = title;
	}

	/**
	 * Returns the activity's meeting days
	 * @return the meetingDays
	 */
	public String getMeetingDays() {
		return meetingDays;
	}

	/**
	 * Returns the start time of the activity
	 * @return the startTime
	 */
	public int getStartTime() {
		return startTime;
	}

	/**
	 * Returns the end time of the activity
	 * @return the endTime
	 */
	public int getEndTime() {
		return endTime;
	}

	/**
	 * Sets the meeting days, start times, and end times of an activity. If day is not a non repeating
	 * valid character, if the start and end times are not valid, or if the start time is after the end time
	 *  an IllegalArgumentException is thrown.
	 * @param meetingDays the days the activity occurs
	 * @param startTime the time the activity occurs
	 * @param endTime the time the activity ends
	 * @throws IllegalArgumentException if any parameters are invalid
	 */
	public void setMeetingDaysAndTime(String meetingDays, int startTime, int endTime) {
		if (meetingDays == null || meetingDays.length() == 0) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}
		int startTimeHour = startTime / HUNDRED;
		int startTimeMin = startTime % HUNDRED;
		int endTimeHour = endTime / HUNDRED;
		int endTimeMin = endTime % HUNDRED;
		
		if (startTime > endTime) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}
		
		if (startTimeHour < 0 || startTimeHour >= UPPER_HOUR) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}
		if (startTimeMin < 0 || startTimeMin >= UPPER_MIN) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}
		if (endTimeHour < 0 || endTimeHour >= UPPER_HOUR) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}
		if (endTimeMin < 0 || endTimeMin >= UPPER_MIN) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}
		this.meetingDays = meetingDays;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	/**
	 * Creates a string with meeting days and start and end times in standard time. If meeting day is set to
	 * "A" then the string equals "Arranged.
	 * @return meetingString a string with the meeting days and times or "Arranged" if the meeting day is set to "A"
	 */
	public String getMeetingString() {
		if (!getMeetingDays().equals("A")) {
			String meetingString = "";
			meetingString += getMeetingDays();
			meetingString += " ";
			int firstTime = getStartTime();
			int secondTime = getEndTime();
			String timeSuffix = "";
			int startTimeHour = firstTime / HUNDRED;
			int startTimeMin = firstTime % HUNDRED;
			int endTimeHour = secondTime / HUNDRED;
			int endTimeMin = secondTime % HUNDRED;
			if (startTimeHour == 0) {
				timeSuffix = "AM";
				startTimeHour = MIDNIGHT_NOON;
			}
			else if (startTimeHour < MIDNIGHT_NOON) {
				timeSuffix = "AM";
			}
			else if (startTimeHour == MIDNIGHT_NOON) {
				timeSuffix = "PM";
			}
			else {
				timeSuffix = "PM";
				startTimeHour = startTimeHour - MIDNIGHT_NOON;
			}
			meetingString += startTimeHour + ":";
			if (startTimeMin < DOUBLE_DIGITS) {
				meetingString += "0" + startTimeMin;
			}
			else {
				meetingString += startTimeMin;
			}
			meetingString += timeSuffix + "-";
			if (endTimeHour == 0) {
				timeSuffix = "AM";
				endTimeHour = MIDNIGHT_NOON;
			}
			else if (endTimeHour < MIDNIGHT_NOON) {
				timeSuffix = "AM";
			}
			else if (endTimeHour == MIDNIGHT_NOON) {
				timeSuffix = "PM";
			}
			else {
				timeSuffix = "PM";
				endTimeHour = endTimeHour - MIDNIGHT_NOON;
			}
			meetingString += endTimeHour + ":";
			if (endTimeMin < DOUBLE_DIGITS) {
				meetingString += "0" + endTimeMin;
			}
			else {
				meetingString += endTimeMin;
			}
			meetingString += timeSuffix;
			return meetingString;
		}
		else {
			return "Arranged";
		}
		
	}
	
	/**
	 * Creates hashCode for Activity with all fields
	 * @return hashCode for Activity
	 */
	@Override
	public int hashCode() {
		return Objects.hash(endTime, meetingDays, startTime, title);
	}
	
	
	/**
	 * Compares the equality of two Activity objects by comparing all of their fields
	 * @param obj Object being compared
	 * @return true if all the fields of the two objects are equal
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Activity other = (Activity) obj;
		return endTime == other.endTime && Objects.equals(meetingDays, other.meetingDays)
				&& startTime == other.startTime && Objects.equals(title, other.title);
	}
	
	/**
	 * Delegates retrieving information about some of the subclass fields for GUI display to subclasses
	 * Event and Course.
	 * @return a string array listing information about the fields of the subclasses
	 */
	public abstract String[] getShortDisplayArray();
	
	/**
	 * Delegates retrieving information about all of the subclass fields for GUI display to subclasses
	 * Event and Course.
	 * @return a string array listing information about the fields of the subclasses
	 */
	public abstract String[] getLongDisplayArray();
	
	/**
	 * Compares if two activities are duplicates. Checks events against events and courses against courses.
	 * @param activity activity to compare
	 * @return true if they are duplicates, otherwise false
	 */
	public abstract boolean isDuplicate(Activity activity);

	/**
	 * Tests if an Activity overlaps with the current instance of Activity at the same time on the same days. If so,
	 * a ConflictException is thrown.
	 * @param possibleConflictingActivity the Activity compared against the current activity
	 * @throws ConflictException if there is a conflict between the two Activities
	 */
	@Override
	public void checkConflict(Activity possibleConflictingActivity) throws ConflictException {
		boolean sameDays = false;
		for (int i = 0; i < possibleConflictingActivity.getMeetingDays().length(); ++i) {
			for (int j = 0; j < meetingDays.length(); ++j) {
				if (possibleConflictingActivity.getMeetingDays().charAt(i) == meetingDays.charAt(j)) {
					sameDays = true;
				}
				if ('A' == meetingDays.charAt(j)) {
					sameDays = false;
				}
			}
			if ('A' == possibleConflictingActivity.getMeetingDays().charAt(i)) {
				sameDays = false;
			}
		}
		
			if (sameDays) {
				if (endTime >= possibleConflictingActivity.getStartTime() && startTime <= possibleConflictingActivity.getStartTime()) {
					throw new ConflictException();
				}
				if (possibleConflictingActivity.getEndTime() >= startTime && possibleConflictingActivity.getStartTime() <= startTime) {
					throw new ConflictException();
				}
		}
	}

	
	

}