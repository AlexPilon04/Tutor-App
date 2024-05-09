package persistence;


import model.TutorDatabase;
import model.Tutor;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest extends JsonTest{
    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            TutorDatabase td = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyTutorDatabase() {
        JsonReader reader = new JsonReader("./data/testEmpty.json");
        try {
            TutorDatabase td = reader.read();
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralTutorDatabase() {
        JsonReader reader = new JsonReader("./data/testGeneral.json");
        try {
            TutorDatabase td = reader.read();
            List<Tutor> allTutors = td.getAllTutors();
            assertEquals(2, allTutors.size());
            checkTutor("John","Science", allTutors.get(0));
            checkTutor("Matt","Arts", allTutors.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
