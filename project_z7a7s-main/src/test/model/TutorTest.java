package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TutorTest {
    private Tutor testTutor1;

    @BeforeEach
    void runBefore() {
        testTutor1 = new Tutor("1","Science");
    }

    @Test
    void testTutor() {
        //List<String> courses = testTutor1.getCoursesOffered();
        //List<String> availability = testTutor1.getAvailability();
        assertEquals("1", testTutor1.getName());
        assertEquals("Science", testTutor1.getFaculty());
        //assertEquals(0, courses.size());
        //assertEquals(0, availability.size());
        assertEquals(0, testTutor1.getRating());
    }

    @Test
    void testRate() {
        testTutor1.rate(4);
        assertEquals(4,testTutor1.getRating());
    }

    @Test
    void testMultipleRate() {
        testTutor1.rate(4);
        testTutor1.rate(5);
        assertEquals(4,testTutor1.getRating());
    }

//    @Test
//    void testAddCourse() {
//        testTutor1.addCourse("CPSC 210");
//        List<String> courses = new ArrayList(testTutor1.getCoursesOffered());
//        assertEquals(1, courses.size());
//        assertEquals("CPSC 210", courses.get(0));
//    }
//
//    @Test
//    void testMultipleAddCourse() {
//        testTutor1.addCourse("CPSC 210");
//        testTutor1.addCourse("CPSC 121");
//        List<String> courses = new ArrayList(testTutor1.getCoursesOffered());
//        assertEquals(2, courses.size());
//        assertEquals("CPSC 210", courses.get(0));
//        assertEquals("CPSC 121", courses.get(1));
//    }
//    @Test
//    void testAddAvailability() {
//        testTutor1.addTimeSlot("17:00-18:00");
//        List<String> timeSlots = new ArrayList(testTutor1.getAvailability());
//        assertEquals(1, timeSlots.size());
//        assertEquals("17:00-18:00", timeSlots.get(0));
//    }
//
//    @Test
//    void testMultipleAddAvailability() {
//        testTutor1.addTimeSlot("17:00-18:00");
//        testTutor1.addTimeSlot("18:00-19:00");
//        List<String> timeSlots = new ArrayList(testTutor1.getAvailability());
//        assertEquals(2, timeSlots.size());
//        assertEquals("17:00-18:00", timeSlots.get(0));
//        assertEquals("18:00-19:00", timeSlots.get(1));
//    }
}