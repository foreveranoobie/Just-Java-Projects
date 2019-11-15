package ua.nure.storozhuk.SummaryTask4.tests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.BeforeClass;
import org.junit.Test;

import ua.nure.storozhuk.SummaryTask4.sql.entity.Course;

/**
 * Test case for checking StudentCourses.class checking subject and
 * datesDifference orders
 * 
 * @author Alex
 *
 */
public class StudentsCourseTest {
	private static ArrayList<Course> test;

	@BeforeClass
	public static void initList() {
		test = new ArrayList<>();
		test.add(new Course(0, "Algebra", "2017-05-20", "2018-02-25", 2, "teacher"));
		test.add(new Course(0, "Programming", "2018-05-20", "2018-10-25", 2, "teacher"));
		test.add(new Course(0, "Logic", "2018-05-20", "2018-12-25", 2, "teacher"));
	}

	@Test
	public void checkSubjectSort() {
		String[] ascOrder = { "Algebra", "Logic", "Programming" };
		String[] descOrder = { "Programming", "Logic", "Algebra" };
		Collections.sort(test, Course.subjectComparator());
		for (int m = 0; m < 3; m++) {
			assertEquals(test.get(m).getSubject(), ascOrder[m]);
		}
		Collections.sort(test, Course.subjectDescComparator());
		for (int m = 0; m < 3; m++) {
			assertEquals(test.get(m).getSubject(), descOrder[m]);
		}
	}

	@Test
	public void checkTermSort() {
		String[] descOrder = { "Algebra", "Logic", "Programming" };
		String[] ascOrder = { "Programming", "Logic", "Algebra" };
		Collections.sort(test, Course.termComparator());
		for (int m = 0; m < 3; m++) {
			assertEquals(test.get(m).getSubject(), ascOrder[m]);
		}
		Collections.sort(test, Course.termDescComparator());
		for (int m = 0; m < 3; m++) {
			assertEquals(test.get(m).getSubject(), descOrder[m]);
		}
	}
}