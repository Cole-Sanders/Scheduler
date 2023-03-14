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
public class CourseNameValidatorTest {

	/**
	 * Tests the start state
	 */
	@Test
	public void testStartState() {
		
		try {
			CourseNameValidator cv = new CourseNameValidator();
			String validStr = "CSC216";
			assertTrue(cv.isValid(validStr));
			
			String invalidStr = "2216";
			Exception e1 = assertThrows(Exception.class,
					() -> cv.isValid(invalidStr));
			assertEquals("Course name must start with a letter.", e1.getMessage());
			
		} catch (Exception e) {
			fail();
		}
		
	}
	
	/**
	 * Tests the one letter state
	 */
	@Test
	public void testStateL() {
	
		try {
			CourseNameValidator cv = new CourseNameValidator();
			String validStr = "CS216";
			assertTrue(cv.isValid(validStr));
			String invalidStr = "C#216";
			Exception e1 = assertThrows(Exception.class,
					() -> cv.isValid(invalidStr));
			assertEquals("Course name can only contain letters and digits.", e1.getMessage());
			
		} catch (Exception e) {
			fail();
		}
		
	}
	
	/**
	 * Tests the two letter state
	 */
	@Test
	public void testStateLL() {
		
		try {
			CourseNameValidator cv = new CourseNameValidator();
			String validStr = "CSC216";
			assertTrue(cv.isValid(validStr));
			String invalidStr = "CS%216";
			Exception e1 = assertThrows(Exception.class,
					() -> cv.isValid(invalidStr));
			assertEquals("Course name can only contain letters and digits.", e1.getMessage());
			
		} catch (Exception e) {
			fail();
		}
		
	}
	
	/**
	 * Tests the three letter state
	 */
	@Test
	public void testStateLLL() {
		
		try {
			CourseNameValidator cv = new CourseNameValidator();
			String validStr = "HESF101";
			assertTrue(cv.isValid(validStr));
			String invalidStr = "HES$101";
			Exception e1 = assertThrows(Exception.class,
					() -> cv.isValid(invalidStr));
			assertEquals("Course name can only contain letters and digits.", e1.getMessage());
			
		} catch (Exception e) {
			fail();
		}
		
	}
	
	/**
	 * Tests the four letter state
	 */
	@Test
	public void testStateLLLL() {
		
		try {
			CourseNameValidator cv = new CourseNameValidator();
			String validStr = "MAET324";
			assertTrue(cv.isValid(validStr));
			String invalidStr = "MAETE324";
			Exception e1 = assertThrows(Exception.class,
					() -> cv.isValid(invalidStr));
			assertEquals("Course name cannot start with more than 4 letters.", e1.getMessage());
			
		} catch (Exception e) {
			fail();
		}
		
	}
	
	/**
	 * Tests the one digit state
	 */
	@Test
	public void testStateD() {
		
		try {
			CourseNameValidator cv = new CourseNameValidator();
			String validStr = "E115";
			assertTrue(cv.isValid(validStr));
			String invalidStr = "C2c3";
			Exception e1 = assertThrows(Exception.class,
					() -> cv.isValid(invalidStr));
			assertEquals("Course name must have 3 digits.", e1.getMessage());
			
		} catch (Exception e) {
			fail();
		}
		
	}
	
	/**
	 * Tests the two digit state
	 */
	@Test
	public void testStateDD() {
		
		
		try {
			CourseNameValidator cv = new CourseNameValidator();
			String validStr = "CSC216";
			assertTrue(cv.isValid(validStr));
			String invalidStr = "CSC26E";
			Exception e1 = assertThrows(Exception.class,
					() -> cv.isValid(invalidStr));
			assertEquals("Course name must have 3 digits.", e1.getMessage());
			
		} catch (Exception e) {
			fail();
		}
		
	}
	
	/**
	 * Tests the three digit state
	 */
	@Test
	public void testStateDDD() {
		
		try {
			CourseNameValidator cv = new CourseNameValidator();
			String validStr = "CSC215E";
			assertTrue(cv.isValid(validStr));
			String invalidStr = "CSC2156";
			Exception e1 = assertThrows(Exception.class,
					() -> cv.isValid(invalidStr));
			assertEquals("Course name can only have 3 digits.", e1.getMessage());
			
		} catch (Exception e) {
			fail();
		}
		
	}
	
	/**
	 * Tests the one suffix state
	 */
	@Test
	public void testStateS() {
		
		try {
			CourseNameValidator cv = new CourseNameValidator();
			String validStr = "MAE324F";
			assertTrue(cv.isValid(validStr));
			String invalidStr = "MAE452FF";
			Exception e1 = assertThrows(Exception.class,
					() -> cv.isValid(invalidStr));
			assertEquals("Course name can only have a 1 letter suffix.", e1.getMessage());
			String invalidStr2 = "MAE452F3";
			Exception e2 = assertThrows(Exception.class,
					() -> cv.isValid(invalidStr2));
			assertEquals("Course name cannot contain digits after the suffix.", e2.getMessage());
			
		} catch (Exception e) {
			fail();
		}
		
	}
	

}

		