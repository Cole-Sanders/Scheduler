package edu.ncsu.csc216.pack_scheduler.catalog;

import java.io.IOException;


import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.io.CourseRecordIO;
import edu.ncsu.csc217.collections.list.SortedList;

/**
 * A class that represents a catalog for storing and interacting with a catalog of courses. 
 * Creates a catalog from an input file and facilitates editing and exporting that catalog. The only field is the 
 * catalog of courses with methods newCourseCatalog, loadCoursesFromFile, addCourseToCatalog,
 * removeCouseFromCatalog, getCourseFromCatalog, getCourseCatalog, and saveCourseCatalog. 
 * @author Cole Sanders
 *
 */
public class CourseCatalog {
	
	/** Number of catalog fields displayed in the 2D array */
	private static final int CATALOG_FIELDS = 5;
	
	/** A catalog of all the courses */
	private SortedList<Course> catalog;


	/**
	 * Constructs CourseCatalog with a empty sorted list.
	 */
	public CourseCatalog() {
		catalog = new SortedList<Course>();
	}
	
	/**
	 * Creates a blank course catalog.
	 */
	public void newCourseCatalog() {
		catalog = new SortedList<Course>();
	}
	
	/**
	 * Loads a catalog of courses from a file.
	 * @param filename the name of the file
	 */
	public void loadCoursesFromFile(String filename) {
		try {
			catalog = CourseRecordIO.readCourseRecords(filename);
		} catch (Exception e) {
			throw new IllegalArgumentException("Cannot find file.");
		}
	}
	
	/**
	 * Returns true if the course is added to the catalog. Then adds the course to the catalog. 
	 * Returns false if the course is already in the catalog. An IllegalArgumentException is thrown
	 * if the course cannot be constructed.
	 * @param name the name of the course
	 * @param title the title of the course
	 * @param section the section of the course
	 * @param credits credit hours of the course
	 * @param instructorId instructor's ID
	 * @param enrollmentCap the enrollment capacity of a course
	 * @param meetingDays meeting days for the course
	 * @param startTime start time of the course
	 * @param endTime end time of the course
	 * @return true if the course is successfully added to the catalog, false if it's already in the catalog.
	 * @throws IllegalArgumentException if course cannot be constructed
	 */
	public boolean addCourseToCatalog(String name, String title, String section, int credits, String instructorId, int enrollmentCap, String meetingDays, int startTime, int endTime) {
 		if (getCourseFromCatalog(name, section) != null) {
 			return false;
 		}
		Course course;
 		try {
			course = new Course(name, title, section, credits, instructorId, enrollmentCap, meetingDays, startTime, endTime);	
					
		} catch (Exception e) {
					throw new IllegalArgumentException("The course information is invalid.");
		}
			catalog.add(course);
			return true;
		
	}
	
	/**
	 * Removes a course from the catalog. Returns true if the course was successfully removed, false
	 * if the course was never in the catalog.
	 * @param name name of the course
	 * @param section section of the course
	 * @return true if the course was removed, false if the course wasn't in the catalog
	 */
	public boolean removeCourseFromCatalog(String name, String section) {
		if (getCourseFromCatalog(name, section) == null) {
			return false;
		}
		else {
			Course course = getCourseFromCatalog(name, section);
			catalog.remove(catalog.indexOf(course));
			return true;
		}
	}
	
	/**
	 * Searches through the catalog to find a course with the given name
	 * and section. If it cannot be found null is returned.
	 * @param name the name of the course
	 * @param section the section of the course
	 * @return the course object with the given name and section
	 */
	public Course getCourseFromCatalog(String name, String section) {
		for (int i = 0; i < catalog.size(); ++i) {
			if (catalog.get(i).getName().equals(name) && catalog.get(i).getSection().equals(section)) {
				return catalog.get(i);
			}
		}
		return null;
	}
	

	/**
	 * Creates a 2D string array of the courses in the catalog
	 * including their name, section, title, and meeting string.
	 * @return a 2D array of courses' names, sections, titles and meeting strings
	 */
	public String[][] getCourseCatalog() {
		String[][] catalogArray = new String[catalog.size()][CATALOG_FIELDS];
		if (catalog.size() == 0) {
			String[][] emptyArray = new String[0][0];
			return emptyArray;
		}
		for (int i = 0; i < catalog.size(); ++i) {
			Course course = catalog.get(i);
			catalogArray[i] = course.getShortDisplayArray();
		}
		return catalogArray;
	}



	/**
	 * Saves the catalog to the given filename.
	 * @param filename the filename where the catalog will be saved
	 * @throws IllegalArgumentException if the file cannot be saved
	 */
	public void saveCourseCatalog(String filename) {
		try {
			CourseRecordIO.writeCourseRecords(filename, catalog);
		} catch (IOException e) {
			throw new IllegalArgumentException("The file cannot be saved.");
		}
	}
}


