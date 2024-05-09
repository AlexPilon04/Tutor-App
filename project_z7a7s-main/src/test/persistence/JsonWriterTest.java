package persistence;

import model.Tutor;
import model.TutorDatabase;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonWriterTest extends JsonTest {
    @Test
    void testWriterInvalidFile() {
        try {
            TutorDatabase td = new TutorDatabase();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyTutorDatabase() {
        try {
            TutorDatabase td = new TutorDatabase();
            JsonWriter writer = new JsonWriter("./data/testEmpty.json");
            writer.open();
            writer.write(td);
            writer.close();

            JsonReader reader = new JsonReader("./data/testEmpty.json");
            td = reader.read();
            assertEquals(0, td.getAllTutors().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralTutorDatabase() {
        try {
            TutorDatabase td = new TutorDatabase();
            td.addTutor(new Tutor("John", "Science"));
            td.addTutor(new Tutor("Matt", "Arts"));
            JsonWriter writer = new JsonWriter("./data/testGeneral.json");
            writer.open();
            writer.write(td);
            writer.close();

            JsonReader reader = new JsonReader("./data/testGeneral.json");
            td = reader.read();
            List<Tutor> allTutors = td.getAllTutors();
            assertEquals(2, allTutors.size());
            checkTutor("John", "Science",allTutors.get(0));
            checkTutor("Matt", "Arts", allTutors.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
