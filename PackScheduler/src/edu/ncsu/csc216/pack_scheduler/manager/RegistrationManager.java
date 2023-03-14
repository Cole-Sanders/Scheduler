package edu.ncsu.csc216.pack_scheduler.manager;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Properties;

import edu.ncsu.csc216.pack_scheduler.catalog.CourseCatalog;
import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.course.roll.CourseRoll;
import edu.ncsu.csc216.pack_scheduler.directory.FacultyDirectory;
import edu.ncsu.csc216.pack_scheduler.directory.StudentDirectory;
import edu.ncsu.csc216.pack_scheduler.user.Faculty;
import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc216.pack_scheduler.user.User;
import edu.ncsu.csc216.pack_scheduler.user.schedule.Schedule;

/**
 * A class that manages the registration of courses. Implements the singlton design pattern to keep track of a single
 * instance of it as well as a single instance of some of its fields: course catalog and student directory. Its other two 
 * fields keep track of the registrar and current user of the manager. This class allows a user to log in or log out,
 * as well as fetch the current user and clear the current student directory and course catalog.
 * @author Cole Sanders
 *
 */
public class RegistrationManager {
	
	/**	Maintains the single instance of RegistrationManager */
	private static RegistrationManager instance;
	/**	A catalog of courses */
	private CourseCatalog courseCatalog;
	/**	A student directory */
	private StudentDirectory studentDirectory;
	/**	The registrar using RegistrationManager */
	private User registrar;
	/**	The current user of RegistrationManager */
	private User currentUser;
	/**	A faculty directory */
	private FacultyDirectory facultyDirectory;
	/** Hashing algorithm */
	private static final String HASH_ALGORITHM = "SHA-256";
	/**	Name of the properties file */
	private static final String PROP_FILE = "registrar.properties";

	/**
	 * Constructs RegistrationManager
	 */
	private RegistrationManager() {
		createRegistrar();
		courseCatalog = new CourseCatalog();
		studentDirectory = new StudentDirectory();
		facultyDirectory = new FacultyDirectory();
		
	}
	
	/**
	 * Creates a registrar from the properties file.
	 */
	private void createRegistrar() {
		Properties prop = new Properties();
		
		try (InputStream input = new FileInputStream(PROP_FILE)) {
			prop.load(input);
			
			String hashPW = hashPW(prop.getProperty("pw"));
			
			registrar = new Registrar(prop.getProperty("first"), prop.getProperty("last"), prop.getProperty("id"), prop.getProperty("email"), hashPW);
		} catch (IOException e) {
			System.out.print(e);
			throw new IllegalArgumentException("Cannot create registrar.");
		}
	}
	
	/**
	 * Hashes a password using the SHAß26 algorithm.
	 * @param pw password being hashed.
	 * @return the hashed password.
	 */
	private String hashPW(String pw) {
		try {
			MessageDigest digest1 = MessageDigest.getInstance(HASH_ALGORITHM);
			digest1.update(pw.getBytes());
			return Base64.getEncoder().encodeToString(digest1.digest());
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalArgumentException("Cannot hash password");
		}
	}
	
	/**
	 * Gets the current instance of RegistrationManager. If the current instance is null then the method
	 * creates a new instance and returns it.
	 * @return the single instance of RegistrationManager.
	 */
	public static RegistrationManager getInstance() {
		  if (instance == null) {
			instance = new RegistrationManager();
		}
		return instance;
	}
	
	/**
	 * Gets a course catalog.
	 * @return the course catalog.
	 */
	public CourseCatalog getCourseCatalog() {
		return courseCatalog;
	}
	
	/**
	 * Gets a student directory.
	 * @return the student directory.
	 */
	public StudentDirectory getStudentDirectory() {
		return studentDirectory;
	}
	
	/**
	 * Gets a faculty directory.
	 * @return the faculty directory.
	 */
	public FacultyDirectory getFacultyDirectory() {
		return facultyDirectory;
	}

	/**
	 * Sets current user as either a student or the registrar
	 * if both their hashed passwords and id matched the passed in values.
	 * @param id the passed in id
	 * @param password the passed in password
	 * @return true if a user is successfully logged in, false if not.
	 * @throws IllegalArgumentException user doesnt exist
	 */
	public boolean login(String id, String password) {
		if (currentUser != null) {
			return false;
		}
		String localHashPW = hashPW(password);
		if (registrar.getId().equals(id)) {
			
			if (registrar.getPassword().equals(localHashPW)) {
				currentUser = registrar;
					return true;
			}
			else 
			{
				return false;
			}
		}
		Student s = studentDirectory.getStudentById(id);
		if (s == null) {
			Faculty f = facultyDirectory.getFacultyById(id);
			if (f == null) {
				throw new IllegalArgumentException("User doesn't exist.");
			}
			if (f.getPassword().equals(localHashPW)) {
				currentUser = f;
				return true;
			}
		} else if (s.getPassword().equals(localHashPW)) {
			currentUser = s;
			return true;
		}	
		
			
		return false;
	}

	/**
	 * Logs the current student user out.
	 */
	public void logout() {
		
		currentUser = null; 
	}
	
	/**
	 * Gets the current user.
	 * @return the current user.
	 */
	public User getCurrentUser() {
		return currentUser;
	}
	
	/**
	 * Resets the courseCatalog and studentDirectory fields to new
	 * CourseCatalogs and StudentDirectories.
	 */
	public void clearData() {
		courseCatalog.newCourseCatalog();
		studentDirectory.newStudentDirectory();
		facultyDirectory.newFacultyDirectory();
	}
	
	/**
	 * A class representing a Registrar with a given name, id, email, and password.
	 * @author Cole Sanders
	 */
	private static class Registrar extends User {
		/**
		 * Creates a registrar user.
		 * @param firstName the first name of the registrar
		 * @param lastName the last name of the registrar
		 * @param id the id of the registrar
		 * @param email the email of the registrar
		 * @param hashPW the hashed password of the registrar
		 */
		public Registrar(String firstName, String lastName, String id, String email, String hashPW) {
			super(firstName, lastName, id, email, hashPW);
		}
	}
	
	/**
	 * Returns true if the logged in student can enroll in the given course.
	 * @param c Course to enroll in
	 * @return true if enrolled
	 */
	public boolean enrollStudentInCourse(Course c) {
	    if (currentUser == null) {
	        throw new IllegalArgumentException("Illegal Action");
	    }
	    if (!(currentUser instanceof Student)) {
	    	throw new IllegalArgumentException("Illegal Action");
	    }
	    try {
	        Student s = (Student)currentUser;
	        Schedule schedule = s.getSchedule();
	        CourseRoll roll = c.getCourseRoll();
	        
	        if (s.canAdd(c) && roll.canEnroll(s)) {
	            schedule.addCourseToSchedule(c);
	            roll.enroll(s);
	            return true;
	        }
	        
	    } catch (IllegalArgumentException e) {
	        return false;
	    }
	    return false;
	}

	/**
	 * Returns true if the logged in student can drop the given course.
	 * @param c Course to drop
	 * @return true if dropped
	 */
	public boolean dropStudentFromCourse(Course c) {
	    if (currentUser == null) {
	        throw new IllegalArgumentException("Illegal Action");
	    }
	    if (!(currentUser instanceof Student)) {
	    	throw new IllegalArgumentException("Illegal Action");
	    }
	    try {
	        Student s = (Student)currentUser;
	        c.getCourseRoll().drop(s);
	        return s.getSchedule().removeCourseFromSchedule(c);
	    } catch (IllegalArgumentException e) {
	        return false; 
	    }
	}

	/**
	 * Resets the logged in student's schedule by dropping them
	 * from every course and then resetting the schedule.
	 */
	public void resetSchedule() {
	    if (currentUser == null) {
	        throw new IllegalArgumentException("Illegal Action");
	    }
	    if (!(currentUser instanceof Student)) {
	    	throw new IllegalArgumentException("Illegal Action");
	    }
	    try {
	        Student s = (Student)currentUser;
	        Schedule schedule = s.getSchedule();
	        String [][] scheduleArray = schedule.getScheduledCourses();
	        for (int i = 0; i < scheduleArray.length; i++) {
	            Course c = courseCatalog.getCourseFromCatalog(scheduleArray[i][0], scheduleArray[i][1]);
	            c.getCourseRoll().drop(s);
	        }
	        schedule.resetSchedule();
	    } catch (IllegalArgumentException e) {
	        //do nothing 
	    }
	}
	
	/**
	 * If the current user is the registrar then the given course is added to
	 * the given faculty's schedule. If the course is added the method returns true
	 * if not it returns false.
	 * @param course the course being added.
	 * @param faculty the faculty member being assigned the new course.
	 * @return true if the course was added, false if not.
	 */
	public boolean addFacultyToCourse(Course course, Faculty faculty) {
		if (currentUser != null && currentUser == registrar) {
			faculty.getSchedule().addCourseToSchedule(course);
			return true;
		} else {
			throw new IllegalArgumentException();
		}
	}
	
	/**
	 * If the current user is the registrar then the given course is removed from
	 * the given faculty's schedule. If the course is removed the method returns true
	 * if not it returns false.
	 * @param course the course being removed.
	 * @param faculty the faculty member who schedule is being updated.
	 * @return true if the course was removed, false if not.
	 */
	public boolean removeFacultyFromCourse(Course course, Faculty faculty) {
		if (currentUser != null && currentUser == registrar) {
			faculty.getSchedule().removeCourseFromSchedule(course);
			return true;
		} else {
			throw new IllegalArgumentException();
		}
	}
	
	/**
	 * Resets a given faculty's schedule.
	 * @param faculty the faculty who's schedule is being cleared.
	 */
	public void resetFacultySchedule(Faculty faculty) {
		if (currentUser != null && currentUser == registrar) {
			faculty.getSchedule().resetSchedule();
		}  else {
			throw new IllegalArgumentException();
		}
	}
}
