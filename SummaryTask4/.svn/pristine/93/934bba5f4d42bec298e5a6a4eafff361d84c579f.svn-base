package ua.nure.storozhuk.SummaryTask4.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.BeforeClass;
import org.junit.Test;

import ua.nure.storozhuk.SummaryTask4.sql.entity.Journal;

/**
 * Test case for checking the subject and marks sorting in ascending and
 * descending orders
 * 
 */
public class JournalTest {
	public static ArrayList<Journal> test;

	@BeforeClass
	public static void initTest() {
		test = new ArrayList<Journal>();
		test.add(new Journal(0, 0, 3, "Algebra"));
		test.add(new Journal(0, 0, 1, "Algorithms"));
		test.add(new Journal(0, 0, 5, "Programming"));
	}

	@Test
	public void subjects() {
		Collections.sort(test, Journal.subjectComparator());
		String[] subjSort = { "Algebra", "Algorithms", "Programming" };
		for (int m = 0; m < 3; m++) {
			assertEquals(test.get(m).getSubject(), subjSort[m]);
		}
		Collections.sort(test, Journal.subjectDescComparator());
		String[] subjDescSort = { "Programming", "Algorithms", "Algebra" };
		for (int m = 0; m < 3; m++) {
			assertEquals(test.get(m).getSubject(), subjDescSort[m]);
		}
	}

	@Test
	public void marks() {
		Collections.sort(test, Journal.markComparator());
		int[] marks = { 1, 3, 5 };
		int[] descMarks = { 5, 3, 1 };
		for (int m = 0; m < 3; m++) {
			assertEquals(test.get(m).getMark(), marks[m]);
		}
		Collections.sort(test, Journal.markDescComparator());
		for (int m = 0; m < 3; m++) {
			assertEquals(test.get(m).getMark(), descMarks[m]);
		}
	}

}
