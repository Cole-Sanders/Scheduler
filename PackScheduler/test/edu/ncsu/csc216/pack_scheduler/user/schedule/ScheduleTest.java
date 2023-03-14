/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.user.schedule;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.course.Course;

/**
 * Tests the Schedule class.
 * @author Cole Sanders
 */
public class ScheduleTest {
	
	/**
	 * Tests Schedule's constructor
	 */
	@Test
	public void testSchedule() {
		Schedule s = new Schedule();
		assertEquals(0, s.getScheduledCourses().length);
		assertEquals("My Schedule", s.getTitle());
	}
	
	/**
	 * Tests addCourseToSchedule()
	 */
	@Test
	public void testaddCourseToSchedule() {
		Schedule s = new Schedule();
		assertEquals(0, s.getScheduledCourses().length);
		Course c1 = new Course("CSC216", "Software development fundamentals", "001", 3, "sesmith5", 10, "MW", 1330, 1445);
		assertTrue(s.addCourseToSchedule(c1));
		assertEquals(1, s.getScheduledCourses().length);
		try {
			Course c2 = c1;
			s.addCourseToSchedule(c2);
			fail();
		} catch (IllegalArgumentException e) {
			//Do nothing
		}
		assertEquals(1, s.getScheduledCourses().length);
	}
	
	/**
	 * test removeCourseFromSchedule()
	 */
	@Test
	public void testRemoveCourseFromSchedule() {
		Schedule s = new Schedule();
		Course c1 = new Course("CSC216", "Software development fundamentals", "001", 3, "sesmith5", 10, "MW", 1330, 1445);
		assertTrue(s.addCourseToSchedule(c1));
		Course c2 = new Course("CSC316", "Software development fundamentals", "001", 3, "sesmith5", 10, "MW", 1030, 1245);
		assertTrue(s.addCourseToSchedule(c2));
		assertTrue(s.removeCourseFromSchedule(c2));
		assertEquals(1, s.getScheduledCourses().length);
		assertFalse(s.removeCourseFromSchedule(c2));
		assertTrue(s.removeCourseFromSchedule(c1));
		assertEquals(0, s.getScheduledCourses().length);
	}
	
	/**
	 * tests resetSchedule()
	 */
	@Test
	public void testResetSchedule() {
		Schedule s = new Schedule();
		Course c1 = new Course("CSC216", "Software development fundamentals", "001", 3, "sesmith5", 10, "MW", 1330, 1445);
		assertTrue(s.addCourseToSchedule(c1));
		Course c2 = new Course("CSC316", "Software development fundamentals", "001", 3, "sesmith5", 10, "MW", 1030, 1245);
		assertTrue(s.addCourseToSchedule(c2));
		s.setTitle("Different Schedule");
		s.resetSchedule();
		assertEquals(0, s.getScheduledCourses().length);
		assertEquals("My Schedule", s.getTitle());
	}
	
	/**
	 * tests getScheduledCourses()
	 */
	@Test
	public void testGetScheduledCourses() {
		Schedule s = new Schedule();
		Course c1 = new Course("CSC216", "Software development fundamentals", "001", 3, "sesmith5", 10, "MW", 1330, 1445);
		assertTrue(s.addCourseToSchedule(c1));
		Course c2 = new Course("CSC316", "Software development fundamentals", "001", 3, "sesmith5", 10, "MW", 1030, 1245);
		assertTrue(s.addCourseToSchedule(c2));
		assertEquals("CSC316", s.getScheduledCourses()[1][0]);
		assertEquals("CSC216", s.getScheduledCourses()[0][0]);
		assertEquals("001", s.getScheduledCourses()[1][1]);
	}
	
	/**
	 * Tests setTitle()
	 */
	@Test
	public void testSetTitle() {
		Schedule s = new Schedule();
		s.setTitle("New Schedule");
		assertEquals("New Schedule", s.getTitle());
		s.setTitle("Different Schedule");
		assertEquals("Different Schedule", s.getTitle());
	}
	
	/**
	 * Tests getScheduleCredits()
	 */
	@Test
	public void testGetScheduleCredits() {
		Schedule s = new Schedule();
		Course c1 = new Course("CSC216", "Software development fundamentals", "001", 3, "sesmith5", 10, "MW", 1330, 1445);
		assertTrue(s.addCourseToSchedule(c1));
		Course c2 = new Course("CSC316", "Software development fundamentals", "001", 3, "sesmith5", 10, "MW", 1030, 1245);
		assertTrue(s.addCourseToSchedule(c2));
		assertEquals(6, s.getScheduleCredits());
	}
	
	/**
	 * Tests canAdd()
	 */
	@Test
	public void testCanAdd() {
		Schedule s = new Schedule();
		Course c1 = new Course("CSC216", "Software development fundamentals", "001", 3, "sesmith5", 10, "MW", 1330, 1445);
		assertTrue(s.canAdd(c1));
		assertTrue(s.addCourseToSchedule(c1));
		Course c2 = new Course("CSC316", "Software development fundamentals", "001", 3, "sesmith5", 10, "MW", 1030, 1245);
		assertTrue(s.canAdd(c2));
		assertTrue(s.addCourseToSchedule(c2));
		Course c3 = new Course("CSC316", "Software development fundamentals", "001", 3, "sesmith5", 10, "MW", 1030, 1245);
		assertFalse(s.canAdd(c3));
	}
	
}
