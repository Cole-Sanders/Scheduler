/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course.roll;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.user.Student;

/**
 * Tests the CourseRoll class.
 * @author Cole Sanders
 */
public class CourseRollTest {
	
	/**
	 * Tests the constructor
	 */
	@Test
	public void testCourseRoll() {
		Course c = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 50, "A");
		CourseRoll cr = c.getCourseRoll();
		assertEquals(50, cr.getEnrollmentCap());
		assertEquals(50, cr.getOpenSeats());
	}
	
	/**
	 * Tests setEnrollmentCap()
	 */
	@Test
	public void testSetEnrollmentCap() {
		Course c = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 50, "A");
		CourseRoll cr = c.getCourseRoll();
		assertEquals(50, cr.getEnrollmentCap());
		cr.setEnrollmentCap(30);
		assertEquals(30, cr.getEnrollmentCap());
		Exception exception = assertThrows(IllegalArgumentException.class,
				() -> cr.setEnrollmentCap(9));
		assertEquals("Invalid enrollment capacity", exception.getMessage());
	}
	
	/**
	 * Tests enroll()
	 */
	@Test
	public void testEnroll() {
		Course c = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 50, "A");
		CourseRoll cr = c.getCourseRoll();
		Student s = new Student("Firstname", "Lastname", "ID", "email@gmai.com", "pw");
		cr.enroll(s);
		assertEquals(49, cr.getOpenSeats());
		Student s2 = new Student("Firstname2", "Lastname", "ID", "email@gmai.com", "pw");
		Student s3 = new Student("Firstname3", "Lastname", "ID", "email@gmai.com", "pw");
		cr.enroll(s2);
		cr.enroll(s3);
		assertEquals(47, cr.getOpenSeats());
	}
	
	/**
	 * Tests drop()
	 */
	@Test
	public void testDrop() {
		Course c = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 50, "A");
		CourseRoll cr = c.getCourseRoll();
		Student s = new Student("Firstname", "Lastname", "ID", "email@gmai.com", "pw");
		Student s2 = new Student("Firstname2", "Lastname", "ID", "email@gmai.com", "pw");
		Student s3 = new Student("Firstname3", "Lastname", "ID", "email@gmai.com", "pw");
		cr.enroll(s);
		cr.enroll(s2);
		cr.enroll(s3);
		assertEquals(47, cr.getOpenSeats());
		cr.drop(s);
		assertEquals(48, cr.getOpenSeats());
		cr.drop(s3);
		assertEquals(49, cr.getOpenSeats());
		cr.drop(s2);
		assertEquals(50, cr.getOpenSeats());
		Exception exception = assertThrows(IllegalArgumentException.class,
				() -> cr.drop(null));
		assertEquals("Invalid student", exception.getMessage());
		Course c2 = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 10, "A");
		CourseRoll cr2 = c2.getCourseRoll();
		for (int i = 0; i < 12; ++i) {
			cr2.enroll(new Student("Firstname" + i, "Lastname" + i, "ID" + i, i + "email@gmai.com", "pw" + i));
		}
		cr2.drop(new Student("Firstname" + 11, "Lastname" + 11, "ID" + 11, 11 + "email@gmai.com", "pw" + 11));
	}
	
	/**
	 * Tests canEnroll()
	 */
	@Test
	public void testCanEnroll() {
		Course c = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 10, "A");
		CourseRoll cr = c.getCourseRoll();
		for (int i = 0; i < 10; ++i) {
			Student s = new Student("Firstname" + i, "Lastname", "ID", "email@gmai.com", "pw");
			assertTrue(cr.canEnroll(s));
			cr.enroll(s);
		}
		Student s11 = new Student("Firstname10", "Lastname", "ID", "email@gmai.com", "pw");
		assertTrue(cr.canEnroll(s11));
		cr.enroll(s11);
		assertFalse(cr.canEnroll(s11));
		
		Student s1 = new Student("Firstname0", "Lastname", "ID", "email@gmai.com", "pw");
		cr.drop(s1);
		Student s2 = new Student("Firstname1", "Lastname", "ID", "email@gmai.com", "pw");
		assertFalse(cr.canEnroll(s2));
	}
}
