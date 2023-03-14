package edu.ncsu.csc216.pack_scheduler.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;
import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.manager.RegistrationManager;
import edu.ncsu.csc217.collections.list.SortedList;

/**
 * Reads Course records from text files.  Writes a set of CourseRecords to a file.
 * 
 * @author Cole Sanders
 * @author Sarah Heckman
 */
public class CourseRecordIO {

    /**
     * Reads course records from a file and generates a list of valid Courses.  Any invalid
     * Courses are ignored.  If the file to read cannot be found or the permissions are incorrect
     * a File NotFoundException is thrown.
     * @param fileName file to read Course records from
     * @return a list of valid Courses
     * @throws FileNotFoundException if the file cannot be found or read
     */
	public static SortedList<Course> readCourseRecords(String fileName) throws FileNotFoundException {
	    Scanner fileReader = new Scanner(new FileInputStream(fileName));  
	    SortedList<Course> courses = new SortedList<Course>(); 
	    while (fileReader.hasNextLine()) { 
	        try { 
	            Course course = readCourse(fileReader.nextLine()); 

	          
	            boolean duplicate = false;
	           
	            for (int i = 0; i < courses.size(); i++) {
	               
	                Course current = courses.get(i);
	                
	                if (course.getName().equals(current.getName()) &&
	                        course.getSection().equals(current.getSection())) {
	                
	                    duplicate = true;
	                    break; 
	                }
	            }
	            
	            if (!duplicate) {
	                courses.add(course);
	            } 
	        } catch (IllegalArgumentException e) {
	            //The line is invalid b/c we couldn't create a course, skip it!
	        }
	    }
	    
	    fileReader.close();
	    
	    return courses;
	}
	
	/**
	 * Processes one line of the input file into a Course object. If there are too
	 * many elements in a line or a course is constructed with invalid values,
	 * an IllegalArgumentException is thrown. Sets the course instructor to null
	 * unless one matching the id in the file is already in the factory directory.
	 * @param line one line from the input file
	 * @return A course object created from the line of input
	 * @throws IllegalArgumentException if there are too many elements in a line
	 * or invalid values are used to construct a course
	 */
	private static Course readCourse(String line) {
		Scanner scan = new Scanner(line);
		scan.useDelimiter(",");
		
		try {
			String name = scan.next();
			String title = scan.next();
			String section = scan.next();
			int credits = scan.nextInt();
			String instructorId = scan.next();
			int enrollmentCap = scan.nextInt();
			String meetingDays = scan.next();
			
			if ("A".equals(meetingDays)) {
				if (scan.hasNext()) {
					scan.close();
					throw new IllegalArgumentException("");
				}
				else {
					Course course = new Course(name, title, section, credits, null, enrollmentCap, meetingDays);
					scan.close();
					if (RegistrationManager.getInstance().getFacultyDirectory().getFacultyById(instructorId) != null) {
						RegistrationManager.getInstance().getFacultyDirectory().getFacultyById(instructorId).getSchedule().addCourseToSchedule(course);
					}
					
					return course;
				}
			}
			else {
				int startTime = scan.nextInt();
				int endTime = scan.nextInt();
				if (scan.hasNext()) {
					scan.close();
					throw new IllegalArgumentException("");
				}
				scan.close();
				Course course = new Course(name, title, section, credits, null, enrollmentCap, meetingDays, startTime, endTime); 
				if (RegistrationManager.getInstance().getFacultyDirectory().getFacultyById(instructorId) != null) {
					RegistrationManager.getInstance().getFacultyDirectory().getFacultyById(instructorId).getSchedule().addCourseToSchedule(course);
				}
				return course;
			}
		} catch (Exception e) {
			throw new IllegalArgumentException("");
		}
	}
	
	/**
	 * Writes the given list of Courses to an output file.
	 * @param fileName file to write schedule of Courses to
	 * @param courses array list of Courses to write
	 * @throws IOException if cannot write to file
	 */
	public static void writeCourseRecords(String fileName, SortedList<Course> courses) throws IOException {
		PrintStream fileWriter = new PrintStream(new File(fileName));
	
		for (int i = 0; i < courses.size(); i++) {
		    fileWriter.println(courses.get(i).toString());
		}
	
		fileWriter.close();
	    
	}
    
    
    

}