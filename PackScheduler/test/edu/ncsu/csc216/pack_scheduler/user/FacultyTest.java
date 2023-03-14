/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.user;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Tests the Faculty class.
 * @author Cole Sanders
 *
 */
public class FacultyTest {
	/** First name of faculty */
	private static final String FIRST_NAME = "Cole";
	/** Last name of faculty */
	private static final String LAST_NAME = "Sanders";
	/** ID of a faculty */
	private static final String ID = "Mbdiowj2";
	/** Faculty email */
	private static final String EMAIL = "Cgsandem3@gmail.com";
	/** Faculty password */
	private static final String PASSWORD = "Manta";
	/** Student's max credits */
	private static final int MAX_COURSES = 3;
	/** Invalid first name */
	private static final String INVALID_FIRST_NAME = null;
	/** Invalid faculty last name */
	private static final String INVALID_LAST_NAME = null;
	/** Invalid faculty ID */
	private static final String INVALID_ID = null;
	/** Invalid faculty email */
	private static final String INVALID_EMAIL = "Jimmy.@com";
	/** Invalid faculty password */
	private static final String INVALID_PASSWORD = "";
	/** Invalid faculty max credits */
	private static final int INVALID_MAX_COURSES = -3;

	/**
	 * Tests hashCode().
	 */
	@Test
	void testHashCode() {
		User s1 = new Faculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_COURSES);
		User s2 = new Faculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_COURSES);
		User s3 = new Faculty("Jim", LAST_NAME, ID, EMAIL, PASSWORD, MAX_COURSES);
		assertEquals(s2.hashCode(), s1.hashCode());
		assertNotSame(s2.hashCode(), s3.hashCode());
	}
	
	/**
	 * Tests constructing a Faculty.
	 */
	@Test
	void testStudentStringStringStringStringStringInt() {
		Faculty s = new Faculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_COURSES);
		
		assertAll( 
				() -> assertEquals(MAX_COURSES, s.getMaxCourses()));
		
		Exception e1 = assertThrows(IllegalArgumentException.class,
				() -> new Faculty(INVALID_FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_COURSES));
		assertEquals("Invalid first name", e1.getMessage());
		
		Exception e2 = assertThrows(IllegalArgumentException.class,
				() -> new Faculty(FIRST_NAME, INVALID_LAST_NAME, ID, EMAIL, PASSWORD, MAX_COURSES));
		assertEquals("Invalid last name", e2.getMessage());
		
		Exception e3 = assertThrows(IllegalArgumentException.class,
				() -> new Faculty(FIRST_NAME, LAST_NAME, INVALID_ID, EMAIL, PASSWORD, MAX_COURSES));
		assertEquals("Invalid id", e3.getMessage());
		
		Exception e4 = assertThrows(IllegalArgumentException.class,
				() -> new Faculty(FIRST_NAME, LAST_NAME, ID, INVALID_EMAIL, PASSWORD, MAX_COURSES));
		assertEquals("Invalid email", e4.getMessage());
		
		Exception e5 = assertThrows(IllegalArgumentException.class,
				() -> new Faculty(FIRST_NAME, LAST_NAME, ID, EMAIL, INVALID_PASSWORD, MAX_COURSES));
		assertEquals("Invalid password", e5.getMessage());
		
		Exception e6 = assertThrows(IllegalArgumentException.class,
				() -> new Faculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, INVALID_MAX_COURSES));
		assertEquals("Invalid max courses", e6.getMessage());
	}

	/**
	 * Tests setMaxCourses().
	 */
	@Test
	void testSetMaxCourses() {
		Faculty s = new Faculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_COURSES);
		s.setMaxCourses(1);
		assertEquals(1, s.getMaxCourses());
		Exception e = assertThrows(IllegalArgumentException.class,
				() -> s.setMaxCourses(19));
		assertEquals("Invalid max courses", e.getMessage());
		assertEquals(1, s.getMaxCourses());
	}

	/**
	 * Tests equals().
	 */
	@Test
	void testEqualsObject() {
		User s1 = new Faculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_COURSES);
		User s2 = new Faculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_COURSES);
		User s3 = new Faculty("Jim", LAST_NAME, ID, EMAIL, PASSWORD, MAX_COURSES);
		User s4 = null;
		User s5 = new Faculty("Jim", LAST_NAME, "Jimbo", EMAIL, PASSWORD, MAX_COURSES);
		User s6 = new Faculty("Jim", LAST_NAME, ID, EMAIL, "WrongPW", MAX_COURSES);
		User s7 = new Faculty("Jim", LAST_NAME, ID, "jim@ncsu.edu", PASSWORD, MAX_COURSES);
		User s8 = new Faculty("Jim", "Fish", ID, EMAIL, PASSWORD, MAX_COURSES);
		User s9 = new Faculty("Jim", "Fish", ID, EMAIL, PASSWORD, 2);
		assertTrue(s1.equals(s2));
		assertTrue(!s1.equals(s3));
		assertFalse(s1.equals(s4));
		assertFalse(s3.equals(s5));
		assertFalse(s3.equals(s6));
		assertFalse(s3.equals(s7));
		assertFalse(s3.equals(s8));
		assertTrue(s3.equals(s3));
		assertFalse(s9.equals(s8));
		assertFalse(s9.equals(s5));
	}

	/**
	 * Tests toString().
	 */
	@Test
	void testToString() {
		User s = new Faculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_COURSES);
		assertEquals("Cole,Sanders,Mbdiowj2,Cgsandem3@gmail.com," + s.getPassword() + ",3", s.toString());
	}

}
