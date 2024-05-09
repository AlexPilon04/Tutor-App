package persistence;

import model.Tutor;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkTutor(String name, String faculty, Tutor tutor) {
        assertEquals(name, tutor.getName());
        assertEquals(faculty, tutor.getFaculty());
    }
}