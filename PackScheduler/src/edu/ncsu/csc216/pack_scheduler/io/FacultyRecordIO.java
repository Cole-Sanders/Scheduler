package edu.ncsu.csc216.pack_scheduler.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

import edu.ncsu.csc216.pack_scheduler.user.Faculty;
import edu.ncsu.csc216.pack_scheduler.util.LinkedList;


/**
 * Reads in faculty from an input file and writes a list of faculty to an output file. Method readFacultyRecords
 * and its helper method processFaculty are responsible for creating a linked list of faculty objects from an input file.
 * Method writeFacultyRecords takes a linked list of faculty and writes their fields to an output file. This class
 * is used as an interface between the FacutlyDirectory and Faculty classes.
 * @author Cole Sanders
 *
 */
public class FacultyRecordIO {

	/**
	 * Takes an input file and creates a linked list of faculty.
	 * @param fileName name of the input file
	 * @return a linked list of Faculty objects
	 * @throws FileNotFoundException if the file cannot be located
	 */
	public static LinkedList<Faculty> readFacultyRecords(String fileName) throws FileNotFoundException {
		Scanner fileReader = new Scanner(new FileInputStream(fileName));
		LinkedList<Faculty> facultyList = new LinkedList<Faculty>();
		while (fileReader.hasNextLine()) {
			try {
				Faculty faculty = processFaculty(fileReader.nextLine());
				facultyList.add(faculty);
			} catch (Exception e) {
				continue;
			}
			
		}
		return facultyList;
	}

	/**
	 * Processes one line from the input file into a faculty object.
	 * @param line one line from the input file
	 * @return faculty one complete faculty object to be added to the list
	 * @throws IllegalArguemntException if line cannot be processed into a faculty object
	 * @throws IllegalArgumentException if string parameters for faculty can be read as integers
	 */
	private static Faculty processFaculty(String line) {
		Scanner scan = new Scanner(line);
		scan.useDelimiter(",");
		try {
			String firstName = scan.next();
			String lastName = scan.next();
			String id = scan.next();
			String email = scan.next();
			if (scan.hasNextInt()) {
				scan.close();
				throw new IllegalArgumentException("Invalid faculty records");
			}
			String hashPW = scan.next();
			int maxCourses = scan.nextInt();
			Faculty faculty = new Faculty(firstName, lastName, id, email, hashPW, maxCourses);
			scan.close();
			return faculty;
		} catch (Exception e) {
			throw new IllegalArgumentException("Could not construct faculty");
		}
	}

	/**
	 * Writes the linked list of faculty to an output file.
	 * @param fileName name of the output file
	 * @param facultyDirectory the sorted list of students
	 * @throws IllegalArgumentException if the output file cannot be created
	 * @throws IOException if cannot write to an output file
	 */
	public static void writeFacultyRecords(String fileName, LinkedList<Faculty> facultyDirectory) throws IOException {
		PrintStream fileWriter = new PrintStream(new File(fileName));
    	try {
    		for (int i = 0; i < facultyDirectory.size(); i++) {
    			fileWriter.println(facultyDirectory.get(i).toString());
    		}
    		fileWriter.close();
    	} catch (Exception e) {
    		throw new IllegalArgumentException("Unable to write to file " + fileName);
    	}
	}

}
