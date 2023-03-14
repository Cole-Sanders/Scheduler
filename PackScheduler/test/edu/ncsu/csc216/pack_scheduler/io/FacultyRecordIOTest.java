/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.io;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.user.Faculty;
import edu.ncsu.csc216.pack_scheduler.util.LinkedList;

/**
 * Tests the FacultyRecordIO class.
 * @author Cole Sanders
 *
 */
public class FacultyRecordIOTest {
	
	/**
	 * Tests the readFacultyRecords() method.
	 */
	@Test
	public void testReadFacultyRecords() {
		LinkedList<Faculty> faculty = new LinkedList<Faculty>();
		try {
			faculty = FacultyRecordIO.readFacultyRecords("test-files/faculty_records.txt");
		} catch(Exception e) {
			fail();
		}
		assertEquals(8, faculty.size());
		assertEquals(faculty.get(0).toString(), "Ashely,Witt,awitt,mollis@Fuscealiquetmagna.net,MMlS+rEiw/l1nwKm2Vw3WLJGtP7iOZV7LU/uRuJhcMQ=,2");
		assertEquals(faculty.get(1).toString(), "Fiona,Meadows,fmeadow,pharetra.sed@et.org,MMlS+rEiw/l1nwKm2Vw3WLJGtP7iOZV7LU/uRuJhcMQ=,3");
		assertEquals(faculty.get(2).toString(), "Brent,Brewer,bbrewer,sem.semper@orcisem.co.uk,MMlS+rEiw/l1nwKm2Vw3WLJGtP7iOZV7LU/uRuJhcMQ=,1");
		
		LinkedList<Faculty> faculty2 = new LinkedList<Faculty>();
		try {
			faculty2 = FacultyRecordIO.readFacultyRecords("test-files/invalid_faculty_records.txt");
		} catch(Exception e) {
			fail();
		}
		assertEquals(0, faculty2.size());
	}
	
	/**
	 * Tests the writeFacultyRecords() method.
	 */
	@Test
	public void testWriteFacultyRecords() {
		LinkedList<Faculty> faculty = new LinkedList<Faculty>();
		try {
			faculty = FacultyRecordIO.readFacultyRecords("test-files/faculty_records.txt");
		} catch(Exception e) {
			fail();
		}
		try {
			FacultyRecordIO.writeFacultyRecords("test-files/actual_full_faculty_records.txt", faculty);
		} catch(Exception e) {
			fail();
		}
		checkFiles("test-files/expected_full_faculty_records.txt", "test-files/actual_full_faculty_records.txt");
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
