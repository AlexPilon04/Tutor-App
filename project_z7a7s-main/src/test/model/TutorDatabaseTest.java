package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TutorDatabaseTest {
    private Tutor testTutor1;
    private Tutor testTutor2;
    private TutorDatabase testDatabase;

    @BeforeEach
    void runBefore() {
        testDatabase = new TutorDatabase();
        testTutor1 = new Tutor("1","Science");
        testTutor2 = new Tutor("2","Business");
    }

    @Test
    void testTutorDatabase() {
        List<Tutor> testTutors = testDatabase.getAllTutors();
        assertEquals(0, testTutors.size());
    }

    @Test
    void testAddTutor() {
        testDatabase.addTutor(testTutor1);
        List<Tutor> testTutors = testDatabase.getAllTutors();
        assertEquals(1,testTutors.size());
        assertEquals(testTutor1, testTutors.get(0));
    }

    @Test
    void testAddMultipleTutors() {
        testDatabase.addTutor(testTutor1);
        testDatabase.addTutor(testTutor2);
        List<Tutor> testTutors = testDatabase.getAllTutors();
        assertEquals(2,testTutors.size());
        assertEquals(testTutor1, testTutors.get(0));
        assertEquals(testTutor2, testTutors.get(1));
    }

    @Test
    void testAddMultipleSameTutors() {
        testDatabase.addTutor(testTutor1);
        testDatabase.addTutor(testTutor1);
        testDatabase.addTutor(testTutor2);
        List<Tutor> testTutors = testDatabase.getAllTutors();
        assertEquals(3, testTutors.size());
        assertEquals(testTutor1, testTutors.get(0));
        assertEquals(testTutor1, testTutors.get(1));
        assertEquals(testTutor2, testTutors.get(2));
    }

 }
