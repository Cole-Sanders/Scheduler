/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course.validator;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.Test;


/**
 * Tests the CourseNameValidatorFSM class
 * @author Cole Sanders
 */
public class CourseNameValidatorFSMTest {
	
	/**
	 * Tests the start state
	 */
	@Test
	public void testStartState() {
		CourseNameValidatorFSM cv = new CourseNameValidatorFSM();
		String validStr = "C216";
		try {
			assertTrue(cv.isValid(validStr));
		} catch (Exception e) {
			fail();
		}
		String invalidStr = "2216";
		Exception e1 = assertThrows(Exception.class,
				() -> cv.isValid(invalidStr));
		assertEquals("Course name must start with a letter.", e1.getMessage());
		
	}
	
	/**
	 * Tests the one letter state
	 */
	@Test
	public void testStateL() {
		CourseNameValidatorFSM cv = new CourseNameValidatorFSM();
		String validStr = "CS216";
		try {
			assertTrue(cv.isValid(validStr));
		} catch (Exception e) {
			fail();
		}
		String invalidStr = "C#216";
		Exception e1 = assertThrows(Exception.class,
				() -> cv.isValid(invalidStr));
		assertEquals("Course name can only contain letters and digits.", e1.getMessage());
		
	}
	
	/**
	 * Tests the two letter state
	 */
	@Test
	public void testStateLL() {
		CourseNameValidatorFSM cv = new CourseNameValidatorFSM();
		String validStr = "CSC216";
		try {
			assertTrue(cv.isValid(validStr));
		} catch (Exception e) {
			fail();
		}
		String invalidStr = "CS%216";
		Exception e1 = assertThrows(Exception.class,
				() -> cv.isValid(invalidStr));
		assertEquals("Course name can only contain letters and digits.", e1.getMessage());
		
	}
	
	/**
	 * Tests the three letter state
	 */
	@Test
	public void testStateLLL() {
		CourseNameValidatorFSM cv = new CourseNameValidatorFSM();
		String validStr = "HESF101";
		try {
			assertTrue(cv.isValid(validStr));
		} catch (Exception e) {
			fail();
		}
		String invalidStr = "HES$101";
		Exception e1 = assertThrows(Exception.class,
				() -> cv.isValid(invalidStr));
		assertEquals("Course name can only contain letters and digits.", e1.getMessage());
		
	}
	
	/**
	 * Tests the four letter state
	 */
	@Test
	public void testStateLLLL() {
		CourseNameValidatorFSM cv = new CourseNameValidatorFSM();
		String validStr = "MAET324";
		try {
			assertTrue(cv.isValid(validStr));
		} catch (Exception e) {
			fail();
		}
		String invalidStr = "MAETE324";
		Exception e1 = assertThrows(Exception.class,
				() -> cv.isValid(invalidStr));
		assertEquals("Course name cannot start with more than 4 letters.", e1.getMessage());
		
	}
	
	/**
	 * Tests the one digit state
	 */
	@Test
	public void testStateD() {
		CourseNameValidatorFSM cv = new CourseNameValidatorFSM();
		String validStr = "C213";
		try {
			assertTrue(cv.isValid(validStr));
		} catch (Exception e) {
			fail();
		}
		String invalidStr = "C2c3";
		Exception e1 = assertThrows(Exception.class,
				() -> cv.isValid(invalidStr));
		assertEquals("Course name must have 3 digits.", e1.getMessage());
		
	}
	
	/**
	 * Tests the two digit state
	 */
	@Test
	public void testStateDD() {
		CourseNameValidatorFSM cv = new CourseNameValidatorFSM();
		String validStr = "CSC216";
		try {
			assertTrue(cv.isValid(validStr));
		} catch (Exception e) {
			fail();
		}
		String invalidStr = "CSC26E";
		Exception e1 = assertThrows(Exception.class,
				() -> cv.isValid(invalidStr));
		assertEquals("Course name must have 3 digits.", e1.getMessage());
		
	}
	
	/**
	 * Tests the three digit state
	 */
	@Test
	public void testStateDDD() {
		CourseNameValidatorFSM cv = new CourseNameValidatorFSM();
		String validStr = "CSC215E";
		try {
			assertTrue(cv.isValid(validStr));
		} catch (Exception e) {
			fail();
		}
		String invalidStr = "CSC2156";
		Exception e1 = assertThrows(Exception.class,
				() -> cv.isValid(invalidStr));
		assertEquals("Course name can only have 3 digits.", e1.getMessage());
		
	}
	
	/**
	 * Tests the one suffix state
	 */
	@Test
	public void testStateS() {
		CourseNameValidatorFSM cv = new CourseNameValidatorFSM();
		String validStr = "MAE324F";
		try {
			assertTrue(cv.isValid(validStr));
		} catch (Exception e) {
			fail();
		}
		String invalidStr = "MAE452FF";
		Exception e1 = assertThrows(Exception.class,
				() -> cv.isValid(invalidStr));
		assertEquals("Course name can only have a 1 letter suffix.", e1.getMessage());
		String invalidStr2 = "MAE452F3";
		Exception e2 = assertThrows(Exception.class,
				() -> cv.isValid(invalidStr2));
		assertEquals("Course name cannot contain digits after the suffix.", e2.getMessage());
		
	}
	

}
