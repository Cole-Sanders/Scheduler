package edu.ncsu.csc216.pack_scheduler.user;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.user.schedule.Schedule;

/**
 * A class that represents a student with their full name, id, email, password, and the 
 * maximum number of credits for them to take. This class contains the necessary getters
 * and setters for its fields along with an overriding of hash code, equals, and toString
 * to fit the project description. This class will be used to create student objects that will 
 * go into an array of students in the StudentDirectory class.
 * @author Cole Sanders and Julie Tran
 *
 */
public class Student extends User implements Comparable<Student> {
	
	/** Maximum number of credits for any student */
	public static final int MAX_CREDITS = 18;
	/** Minimum number of credits for any student */
	public static final int MIN_CREDITS = 3;
	
	/** Student's first name */
	private String firstName;
	/** Student's last name */
	private String lastName;
	/** Student's ID */
	private String id;
	/** Student's email */
	private String email;
	/** Student's password */
	private String password;
	/** Student's maximum number of credits */
	private int maxCredits;
	/** Student's schedule */
	private Schedule schedule;
	
	/**
	 * Constructs student's information and sets values for all that student's fields 
	 * @param firstName Student's first name
	 * @param lastName Student's last name
	 * @param id Student's id
	 * @param email Student's email
	 * @param password student's password
	 * @param maxCredits Student's max number of credits
	 */
	public Student(String firstName, String lastName, String id, String email, String password, int maxCredits) {
		super(firstName, lastName, id, email, password);
		setMaxCredits(maxCredits);
		schedule = new Schedule();
	}
	
	
	/**
	 * Constructs student's information and sets values for all that student's fields except for maxCredits.
	 * maxCredits is initialized to eighteen.
	 * @param firstName Student's first name
	 * @param lastName Student's last name
	 * @param id Student's id
	 * @param email Student's email
	 * @param password student's password
	 */
	public Student(String firstName, String lastName, String id, String email, String password) {
		this(firstName, lastName, id, email, password, MAX_CREDITS);
		schedule = new Schedule();
	}
	
	/**
	 * Gets the student's email
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets a students email. If email is empty, null, has no '@' sign, has no '.', or if the first index of '@'
	 * is greater than the last index of '.', an IllegalArguemtnException is thrown.
	 * @param email the email to set
	 * @throws IllegalArgumentException if the passed in email is invalid
	 */
	public void setEmail(String email) {
		if (email == null || email.length() == 0) {
			throw new IllegalArgumentException("Invalid email");
		}
		if (!email.contains("@")) {
			throw new IllegalArgumentException("Invalid email");
		}
		if (!email.contains(".")) {
			throw new IllegalArgumentException("Invalid email");
		}
		if (email.lastIndexOf(".") < email.indexOf("@")) {
			throw new IllegalArgumentException("Invalid email");
		}
		this.email = email;
	}

	/**
	 * Gets the student's password.
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the student's password. If the password is empty or null, and Illegal Argument Exception is thrown.
	 * @param password the password to set
	 * @throws IllegalArgumentException if the password passed in is empty or null
	 */
	public void setPassword(String password) {
		if (password == null || password.length() == 0) {
			throw new IllegalArgumentException("Invalid password");
		}
		this.password = password;
	}

	/**
	 * Gets the student' maximum number of credits
	 * @return the maxCredits
	 */
	public int getMaxCredits() {
		return maxCredits;
	}

	/**
	 * Sets the students maximum number of credits. If it is less than three or more than eighteen,
	 * an IllegalArgumentException is thrown.
	 * @param maxCredits the maxCredits to set
	 * @throws IllegalArgumentException if credits are invalid
	 */
	public void setMaxCredits(int maxCredits) {
		if (maxCredits < MIN_CREDITS || maxCredits > MAX_CREDITS) {
			throw new IllegalArgumentException("Invalid max credits");
		}
		this.maxCredits = maxCredits;
	}

	/**
	 * Sets the student's first name.
	 * @param firstName the firstName to set
	 * @throws IllegalArgumentException if the passed in first name is null or an empty string
	 */
	public void setFirstName(String firstName) {
		if (firstName == null || firstName.length() == 0) {
			throw new IllegalArgumentException("Invalid first name");
		}
		this.firstName = firstName;
	}

	/**
	 * Sets the student's last name.
	 * @param lastName the lastName to set
	 * @throws IllegalArgumentException if the passed in last name is null or an empty string
	 */
	public void setLastName(String lastName) {
		if (lastName == null || lastName.length() == 0) {
			throw new IllegalArgumentException("Invalid last name");
		}
		this.lastName = lastName;
	}

	/**
	 * Sets the sthudent's id
	 * @param id the id to set
	 * @throws IllegalArgumentException if the id is null or an empty string
	 */
	public void setId(String id) {
		if (id == null || id.length() == 0) {
			throw new IllegalArgumentException("Invalid id");
		}
		this.id = id;
	}

	
	/**
	 * Gets the student's first name
	 * @return firstName the first name
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * gets the student's last name
	 * @return lastName the last name
	 */
	public String getLastName() {
		return lastName;
	}
	
	/**
	 * Gets the student's id
	 * @return id the student's id
	 */
	public String getId() {
		return id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + maxCredits;
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (maxCredits != other.maxCredits)
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		return true;
	}

	/**
	 * Returns a comma separated value String of all student's fields.
	 * @return String representation of a student
	 */
	@Override
	public String toString() {
		return firstName + "," + lastName + "," + id + "," + email
				+ "," + password + "," + maxCredits;
	}


	/**
	 * Orders current instance of student against passed in student s. Ordering is done based on last
	 * name first, then first name, and finally id.
	 * @param s student ordering is checked against
	 * @return -1 if current instance of student is less than passed in instance, 0 if equal, and 1
	 * if greater than.
	 */
	@Override
	public int compareTo(Student s) {
		if (this.getLastName().equals(s.getLastName()) && this.getFirstName().equals(s.getFirstName()) && this.getId().equals(s.getId())) {
			return 0;
		}
		
		if (lastName.length() < s.getLastName().length()) {
			for (int i = 0; i < lastName.length(); ++i) {
				if (lastName.charAt(i) > s.getLastName().charAt(i)) {
					return 1;
				}
				if (lastName.charAt(i) < s.getLastName().charAt(i)) {
					return -1;
				}
			}
			return -1;
		}
		else if (lastName.length() > s.getLastName().length()){
			for (int i = 0; i < s.getLastName().length(); ++i) {
				if (lastName.charAt(i) > s.getLastName().charAt(i)) {
					return 1;
				}
				if (lastName.charAt(i) < s.getLastName().charAt(i)) {
					return -1;
				}
			}
			return 1;
		}
		else {
			for (int i = 0; i < s.getLastName().length(); ++i) {
				if (lastName.charAt(i) > s.getLastName().charAt(i)) {
					return 1;
				}
				if (lastName.charAt(i) < s.getLastName().charAt(i)) {
					return -1;
				}
			}
		}
		
		
		if (firstName.length() < s.getFirstName().length()) {
			for (int i = 0; i < firstName.length(); ++i) {
				if (firstName.charAt(i) > s.getFirstName().charAt(i)) {
					return 1;
				}
				if (firstName.charAt(i) < s.getFirstName().charAt(i)) {
					return -1;
				}
			}
			return -1;
		}
		else if (firstName.length() > s.getFirstName().length()){
			for (int i = 0; i < s.getFirstName().length(); ++i) {
				if (firstName.charAt(i) > s.getFirstName().charAt(i)) {
					return 1;
				}
				if (firstName.charAt(i) < s.getFirstName().charAt(i)) {
					return -1;
				}
			}
			return 1;
		}
		else {
			for (int i = 0; i < s.getFirstName().length(); ++i) {
				if (firstName.charAt(i) > s.getFirstName().charAt(i)) {
					return 1;
				}
				if (firstName.charAt(i) < s.getFirstName().charAt(i)) {
					return -1;
				}
			}
		}
		
		if (id.length() < s.getId().length()) {
			for (int i = 0; i < id.length(); ++i) {
				if (id.charAt(i) > s.getId().charAt(i)) {
					return 1;
				}
				if (id.charAt(i) < s.getId().charAt(i)) {
					return -1;
				}
			}
			return -1;
		}
		else if (id.length() > s.getId().length()){
			for (int i = 0; i < s.getId().length(); ++i) {
				if (id.charAt(i) > s.getId().charAt(i)) {
					return 1;
				}
				if (id.charAt(i) < s.getId().charAt(i)) {
					return -1;
				}
			}
			return 1;
		}
		else {
			for (int i = 0; i < s.getId().length(); ++i) {
				if (id.charAt(i) > s.getId().charAt(i)) {
					return 1;
				}
				if (id.charAt(i) < s.getId().charAt(i)) {
					return -1;
				}
			}
		}
		return 0;
		
	}
	
	/**
	 * Gets the student's schedule.
	 * @return schedule the students schedule
	 */
	public Schedule getSchedule() {
		return schedule;
	}


	/**
	 * Checks if a course can be added to a students schedule.
	 * @param course the course under inspection.
	 * @return False if the course is null, conflicts with another element of the
	 * schedule, is already in the schedule, or there is no room for it. True otherwise.
	 */
	public boolean canAdd(Course course) {
		return schedule.canAdd(course) && maxCredits - schedule.getScheduleCredits() >= course.getCredits();
	}
	
	

}
