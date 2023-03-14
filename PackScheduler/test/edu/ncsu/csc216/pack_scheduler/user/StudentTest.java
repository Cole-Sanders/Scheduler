package edu.ncsu.csc216.pack_scheduler.user;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Tests the Student class.
 * @author Cole Sanders
 */
class StudentTest {
	/** First name of a student */
	private static final String FIRST_NAME = "Cole";
	/** Last name of a student */
	private static final String LAST_NAME = "Sanders";
	/** ID of a student */
	private static final String ID = "Mbdiowj2";
	/** Student email */
	private static final String EMAIL = "Cgsandem3@gmail.com";
	/** Student password */
	private static final String PASSWORD = "Manta";
	/** Student's max credits */
	private static final int MAX_CREDITS = 15;
	/** Invalid first name */
	private static final String INVALID_FIRST_NAME = null;
	/** Invalid student last name */
	private static final String INVALID_LAST_NAME = null;
	/** Invalid student ID */
	private static final String INVALID_ID = null;
	/** Invalid student email */
	private static final String INVALID_EMAIL = "Jimmy.@com";
	/** Invalid student password */
	private static final String INVALID_PASSWORD = "";
	/** Invalid student max credits */
	private static final int INVALID_MAX_CREDITS = -3;

	/**
	 * Tests hashCode().
	 */
	@Test
	void testHashCode() {
		User s1 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD);
		User s2 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD);
		User s3 = new Student("Jim", LAST_NAME, ID, EMAIL, PASSWORD);
		assertEquals(s2.hashCode(), s1.hashCode());
		assertNotSame(s2.hashCode(), s3.hashCode());
	}
	
	/**
	 * Tests constructing a Student including maxCredtis.
	 */
	@Test
	void testStudentStringStringStringStringStringInt() {
		Student s = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS);
		
		assertAll( 
				() -> assertEquals(FIRST_NAME, s.getFirstName()), 
				() -> assertEquals(LAST_NAME, s.getLastName()),
				() -> assertEquals(ID, s.getId()), 
				() -> assertEquals(EMAIL, s.getEmail()),
				() -> assertEquals(PASSWORD, s.getPassword()),
				() -> assertEquals(MAX_CREDITS, s.getMaxCredits()));
		
		Exception e1 = assertThrows(IllegalArgumentException.class,
				() -> new Student(INVALID_FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS));
		assertEquals("Invalid first name", e1.getMessage());
		
		Exception e2 = assertThrows(IllegalArgumentException.class,
				() -> new Student(FIRST_NAME, INVALID_LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS));
		assertEquals("Invalid last name", e2.getMessage());
		
		Exception e3 = assertThrows(IllegalArgumentException.class,
				() -> new Student(FIRST_NAME, LAST_NAME, INVALID_ID, EMAIL, PASSWORD, MAX_CREDITS));
		assertEquals("Invalid id", e3.getMessage());
		
		Exception e4 = assertThrows(IllegalArgumentException.class,
				() -> new Student(FIRST_NAME, LAST_NAME, ID, INVALID_EMAIL, PASSWORD, MAX_CREDITS));
		assertEquals("Invalid email", e4.getMessage());
		
		Exception e5 = assertThrows(IllegalArgumentException.class,
				() -> new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, INVALID_PASSWORD, MAX_CREDITS));
		assertEquals("Invalid password", e5.getMessage());
		
		Exception e6 = assertThrows(IllegalArgumentException.class,
				() -> new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, INVALID_MAX_CREDITS));
		assertEquals("Invalid max credits", e6.getMessage());
	}
	
	/**
	 * Tests constructing a Student not including maxCredtis.
	 */
	@Test
	void testStudentStringStringStringStringString() {
		User s = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD);
		
		assertAll( 
				() -> assertEquals(FIRST_NAME, s.getFirstName()), 
				() -> assertEquals(LAST_NAME, s.getLastName()),
				() -> assertEquals(ID, s.getId()), 
				() -> assertEquals(EMAIL, s.getEmail()),
				() -> assertEquals(PASSWORD, s.getPassword()));
		
		Exception e1 = assertThrows(IllegalArgumentException.class,
				() -> new Student(INVALID_FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD));
		assertEquals("Invalid first name", e1.getMessage());
		
		Exception e2 = assertThrows(IllegalArgumentException.class,
				() -> new Student(FIRST_NAME, INVALID_LAST_NAME, ID, EMAIL, PASSWORD));
		assertEquals("Invalid last name", e2.getMessage());
		
		Exception e3 = assertThrows(IllegalArgumentException.class,
				() -> new Student(FIRST_NAME, LAST_NAME, INVALID_ID, EMAIL, PASSWORD));
		assertEquals("Invalid id", e3.getMessage());
		
		Exception e4 = assertThrows(IllegalArgumentException.class,
				() -> new Student(FIRST_NAME, LAST_NAME, ID, INVALID_EMAIL, PASSWORD));
		assertEquals("Invalid email", e4.getMessage());
		
		Exception e5 = assertThrows(IllegalArgumentException.class,
				() -> new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, INVALID_PASSWORD));
		assertEquals("Invalid password", e5.getMessage());
	}
	
	/**
	 * Tests setEmail().
	 */
	@Test
	void testSetEmail() {
		User s = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD);
		s.setEmail("Lancaster@gmail.com");
		assertEquals("Lancaster@gmail.com", s.getEmail());
		Exception e = assertThrows(IllegalArgumentException.class,
				() -> s.setEmail(INVALID_EMAIL));
		assertEquals("Invalid email", e.getMessage());
		assertEquals("Lancaster@gmail.com", s.getEmail());
		Exception e2 = assertThrows(IllegalArgumentException.class,
				() -> s.setEmail(null));
		assertEquals("Invalid email", e2.getMessage());
		Exception e3 = assertThrows(IllegalArgumentException.class,
				() -> s.setEmail("cgsande"));
		assertEquals("Invalid email", e3.getMessage());
		Exception e4 = assertThrows(IllegalArgumentException.class,
				() -> s.setEmail("cgsande@ncsu"));
		assertEquals("Invalid email", e4.getMessage());
	}

	/**
	 * Tests setPassword().
	 */
	@Test
	void testSetPassword() {
		User s = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD);
		s.setPassword("password");
		assertEquals("password", s.getPassword());
		Exception e = assertThrows(IllegalArgumentException.class,
						() -> s.setPassword(""));
		assertEquals("Invalid password", e.getMessage());
		assertEquals("password", s.getPassword()); 
	}

	/**
	 * Tests setMaxCredits().
	 */
	@Test
	void testSetMaxCredits() {
		Student s = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS);
		s.setMaxCredits(17);
		assertEquals(17, s.getMaxCredits());
		Exception e = assertThrows(IllegalArgumentException.class,
				() -> s.setMaxCredits(19));
		assertEquals("Invalid max credits", e.getMessage());
		assertEquals(17, s.getMaxCredits());
	}

	/**
	 * Tests setFirstName().
	 */
	@Test
	void testSetFirstName() {
		User s = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD);
		s.setFirstName("Bob");
		assertEquals("Bob", s.getFirstName());
		Exception e = assertThrows(IllegalArgumentException.class,
						() -> s.setFirstName(""));
		assertEquals("Invalid first name", e.getMessage());
		assertEquals("Bob", s.getFirstName()); 
	}

	/**
	 * Tests setLastName().
	 */
	@Test
	void testSetLastName() {
		User s = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD);
		s.setLastName("Lancaster");
		assertEquals("Lancaster", s.getLastName());
		Exception e = assertThrows(IllegalArgumentException.class,
						() -> s.setLastName(""));
		assertEquals("Invalid last name", e.getMessage());
		assertEquals("Lancaster", s.getLastName()); 
	}

	/**
	 * Tests equals().
	 */
	@Test
	void testEqualsObject() {
		User s1 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD);
		User s2 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD);
		User s3 = new Student("Jim", LAST_NAME, ID, EMAIL, PASSWORD);
		User s4 = null;
		User s5 = new Student("Jim", LAST_NAME, "Jimbo", EMAIL, PASSWORD);
		User s6 = new Student("Jim", LAST_NAME, ID, EMAIL, "WrongPW");
		User s7 = new Student("Jim", LAST_NAME, ID, "jim@ncsu.edu", PASSWORD);
		User s8 = new Student("Jim", "Fish", ID, EMAIL, PASSWORD);
		User s9 = new Student("Jim", "Fish", ID, EMAIL, PASSWORD, 7);
		assertTrue(s1.equals(s2));
		assertTrue(!s1.equals(s3));
		assertFalse(s1.equals(s4));
		assertFalse(s3.equals(s5));
		assertFalse(s3.equals(s6));
		assertFalse(s3.equals(s7));
		assertFalse(s3.equals(s8));
		assertTrue(s3.equals(s3));
		assertFalse(s9.equals(s8));
	}

	/**
	 * Tests toString().
	 */
	@Test
	void testToString() {
		User s = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD);
		assertEquals("Cole,Sanders,Mbdiowj2,Cgsandem3@gmail.com," + s.getPassword() + ",18", s.toString());
	}
	
	/**
	 * Tests compareTo()
	 */
	@Test
	void testCompareTo() {
		Student s1 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD);
		Student s2 = new Student("Sam", "Sanders", "smdean1", EMAIL, PASSWORD);
		Student s3 = new Student("Cole", "Sanders", "jfcricket2", EMAIL, PASSWORD);
		Student s4 = new Student("Cole", "Sanders", "jfcricket2", EMAIL, PASSWORD);
		Student s5 = new Student("Cole", "Sanders", "jfcsoelfie", EMAIL, PASSWORD);
		Student s6 = new Student("Cole", "Sanderss", "jfcs232lfie", EMAIL, PASSWORD);
		Student s7 = new Student("Cole", "Sander", "jfcsoie", EMAIL, PASSWORD);
		Student s8 = new Student("Abe", "Sander", "jfcsoie", EMAIL, PASSWORD);
		Student s9 = new Student("Abe", "Sander", "jacsoie2", EMAIL, PASSWORD);
		
		assertEquals(s1.compareTo(s2), -s2.compareTo(s1));
		assertEquals(s1.compareTo(s3), -s3.compareTo(s1));
		assertEquals(-1, s1.compareTo(s2));
		assertEquals(-1, s1.compareTo(s3));
		assertEquals(1, s2.compareTo(s3));
		assertEquals(0, s4.compareTo(s3));
		assertEquals(-1, s4.compareTo(s5));
		assertEquals(1, s6.compareTo(s5));
		assertEquals(-1, s7.compareTo(s5));
		assertEquals(-1, s8.compareTo(s7));
		assertEquals(-1, s9.compareTo(s8));
	}

}
