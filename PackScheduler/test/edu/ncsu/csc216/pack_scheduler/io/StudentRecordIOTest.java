package edu.ncsu.csc216.pack_scheduler.io;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc217.collections.list.SortedList;

/**
 * Tests the StudentRecordIO class.
 * @author Cole Sanders
 * @author Sarah Heckman
 *
 */
class StudentRecordIOTest {
	/** Valid student string 1 */
	private String validStudent6 = "Zahir,King,zking,orci.Donec@ametmassaQuisque.com,pw,15";
	/** Valid student string 2 */
	private String validStudent8 = "Cassandra,Schwartz,cschwartz,semper@imperdietornare.co.uk,pw,4";
	/** Valid student string 3 */
	private String validStudent4 = "Shannon,Hansen,shansen,convallis.est.vitae@arcu.ca,pw,14";
	/** Valid student string 4 */
	private String validStudent0 = "Demetrius,Austin,daustin,Curabitur.egestas.nunc@placeratorcilacus.co.uk,pw,18";
	/** Valid student string 5 */
	private String validStudent2 = "Raymond,Brennan,rbrennan,litora.torquent@pellentesquemassalobortis.ca,pw,12";
	/** Valid student string 6 */
	private String validStudent3 = "Emerald,Frost,efrost,adipiscing@acipsumPhasellus.edu,pw,3";
	/** Valid student string 7 */
	private String validStudent1 = "Lane,Berg,lberg,sociis@non.org,pw,14";
	/** Valid student string 8 */
	private String validStudent9 = "Griffith,Stone,gstone,porta@magnamalesuadavel.net,pw,17";
	/** Valid student string 9 */
	private String validStudent5 = "Althea,Hicks,ahicks,Phasellus.dapibus@luctusfelis.com,pw,11";
	/** Valid student string 10 */
	private String validStudent7 = "Dylan,Nolan,dnolan,placerat.Cras.dictum@dictum.net,pw,5";

	/** An array of the valid student strings */
	private String [] validStudents = {validStudent0, validStudent1, validStudent2, validStudent3, validStudent4, validStudent5,
	        validStudent6, validStudent7, validStudent8, validStudent9};
	
	/** Hashed student password */
	private String hashPW;
	/** Hash algorithm */
	private static final String HASH_ALGORITHM = "SHA-256";

	
	/**
	 * Hashes the passwords in the valid student strings for security reasons.
	 */
	@BeforeEach
	public void setUp() {
	    try {
	        String password = "pw";
	        MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
	        digest.update(password.getBytes());
	        hashPW = Base64.getEncoder().encodeToString(digest.digest());
	        
	        for (int i = 0; i < validStudents.length; i++) {
	            validStudents[i] = validStudents[i].replace(",pw,", "," + hashPW + ",");
	        }
	    } catch (NoSuchAlgorithmException e) {
	        fail("Unable to create hash during setup");
	    }
	}
	
	
	/**
	 * Tests readStudentRecords()
	 */
	@Test
	public void testReadStudentRecords() {
		try {
			SortedList<Student> students = StudentRecordIO.readStudentRecords("test-files/student_records.txt");
			assertEquals(students.size(), 10);
			for(int i = 0; i < students.size(); ++i) {
				assertEquals(students.get(i).toString(), validStudents[i]);
			}
		} catch (FileNotFoundException e) {
			fail("Could not read file");
		}
		try {
			SortedList<Student> students = new SortedList<Student>();
			students = StudentRecordIO.readStudentRecords("test-files/invalid_student_records.txt");
			assertEquals(students.size(), 0);
		} catch (Exception e) {
			fail("Could not read file");
		}
		assertThrows(FileNotFoundException.class,
				() -> StudentRecordIO.readStudentRecords("No_Such_File"));
	}
	
	
	/**
	 * Tests writeStudentRecords()
	 */
	@Test
	public void testWriteStudentRecords() {
		try {
			SortedList<Student> students1 = StudentRecordIO.readStudentRecords("test-files/student_records.txt");
			StudentRecordIO.writeStudentRecords("test-files/actual_student_records.txt", students1);
		} catch(IOException e) {
			fail("Could not write to file");
		}
		checkFiles("test-files/expected_full_student_records.txt", "test-files/actual_student_records.txt");
	}

	/**
	 * Tests writeStudentRecords() to a file without permission to access it
	 */
	@Test
	public void testWriteStudentRecordsNoPermissions() {
		SortedList<Student> students2 = new SortedList<Student>();
		students2.add(new Student("Zahir", "King", "zking", "orci.Donec@ametmassaQuisque.com", hashPW, 15));
		
		Exception exception = assertThrows(IOException.class, 
				() -> StudentRecordIO.writeStudentRecords("/home/sesmith5/actual_student_records.txt", students2));
		assertEquals("/home/sesmith5/actual_student_records.txt (No such file or directory)", exception.getMessage());
	}

	/**
	 * Helper method to compare two files for the same contents
	 * @param expFile expected output
	 * @param actFile actual output
	 */
	private void checkFiles(String expFile, String actFile) {
		try (Scanner expScanner = new Scanner(new FileInputStream(expFile));
			 Scanner actScanner = new Scanner(new FileInputStream(actFile));) {
			
			while (expScanner.hasNextLine()  && actScanner.hasNextLine()) {
				String exp = expScanner.nextLine();
				String act = actScanner.nextLine();
				assertEquals(exp, act); 
			}
			if (expScanner.hasNextLine()) {
				fail("The expected results expect another line " + expScanner.nextLine());
			}
			if (actScanner.hasNextLine()) {
				fail("The actual results has an extra, unexpected line: " + actScanner.nextLine());
			}
			
			expScanner.close();
			actScanner.close();
		} catch (IOException e) {
			fail("Error reading files.");
		}
	}
	
	
}
