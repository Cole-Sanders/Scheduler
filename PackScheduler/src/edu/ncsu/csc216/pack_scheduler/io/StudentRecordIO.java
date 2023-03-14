package edu.ncsu.csc216.pack_scheduler.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc217.collections.list.SortedList;


/**
 * Reads in students from an input file and writes a student directory to an output file. Method readStudentRecords
 * and its helper method processStudent are responsible for creating a sorted list of student objects from an input file.
 * Method writeStudentRecords takes a sorted list of students and writes their fields to an output file. This class
 * is used as an interface between the StudentDirectory and Student classes.
 * @author Cole Sanders
 *
 */
public class StudentRecordIO {

	/**
	 * Takes an input file and creates a sorted list of students.
	 * @param fileName name of the input file
	 * @return students a sorted list of students
	 * @throws FileNotFoundException if the file cannot be located
	 */
	public static SortedList<Student> readStudentRecords(String fileName) throws FileNotFoundException {
		Scanner fileReader = new Scanner(new FileInputStream(fileName));
		SortedList<Student> students = new SortedList<Student>();
		while (fileReader.hasNextLine()) {
			try {
				Student student = processStudent(fileReader.nextLine());
				students.add(student);
			} catch (Exception e) {
				continue;
			}
			
		}
		return students;
	}

	/**
	 * Processes one line from the input file into a student.
	 * @param line one line from the input file
	 * @return student one complete student to be added to the list
	 * @throws IllegalArguemntException if line cannot be processed into a student
	 * @throws IllegalArgumentException if string parameters for student can be read as integers
	 */
	private static Student processStudent(String line) {
		Scanner scan = new Scanner(line);
		scan.useDelimiter(",");
		try {
			String firstName = scan.next();
			String lastName = scan.next();
			String id = scan.next();
			String email = scan.next();
			if (scan.hasNextInt()) {
				scan.close();
				throw new IllegalArgumentException("Invalid student records");
			}
			String hashPW = scan.next();
			if (scan.hasNext()) {
				int maxCredits = scan.nextInt();
				Student student = new Student(firstName, lastName, id, email, hashPW, maxCredits);
				scan.close();
				return student;
				
			}
			else {
				Student student = new Student(firstName, lastName, id, email, hashPW);
				scan.close();
				return student;
			}
		} catch (Exception e) {
			throw new IllegalArgumentException("Could not construct student");
		}
	}

	/**
	 * Writes the sorted list of students to an output file.
	 * @param fileName name of the output file
	 * @param studentDirectory the sorted list of students
	 * @throws IllegalArgumentException if the output file cannot be created
	 * @throws IOException if cannot write to an output file
	 */
	public static void writeStudentRecords(String fileName, SortedList<Student> studentDirectory) throws IOException {
		PrintStream fileWriter = new PrintStream(new File(fileName));
    	try {
    		for (int i = 0; i < studentDirectory.size(); i++) {
    			fileWriter.println(studentDirectory.get(i).toString());
    		}
    		fileWriter.close();
    	} catch (Exception e) {
    		throw new IllegalArgumentException("Unable to write to file " + fileName);
    	}
	}

}
