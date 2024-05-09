package persistence;

import model.Event;
import model.EventLog;
import model.Tutor;
import model.TutorDatabase;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

//Code influced by the JsonSerizalizationDemo https://repo1.maven.org/maven2/org/json/json/20210307/json-20210307.jar
// Represents a reader that reads tutorDatabase from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads tutorDatabase from file and returns it;
    // throws IOException if an error occurs reading data from file
    public TutorDatabase read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        EventLog.getInstance().logEvent(new Event("Tutors were loaded"));
        return parseTutorDatabase(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses tutorDatabase from JSON object and returns it
    private TutorDatabase parseTutorDatabase(JSONObject jsonObject) {
        //String name = jsonObject.getString();
        TutorDatabase tb = new TutorDatabase();
        addTutors(tb, jsonObject);
        return tb;
    }

    // MODIFIES: td
    // EFFECTS: parses tutors from JSON object and adds them to tutorDatabase
    private void addTutors(TutorDatabase td, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("allTutors");
        for (Object json : jsonArray) {
            JSONObject nextTutor = (JSONObject) json;
            addTutor(td, nextTutor);
        }
    }

    // MODIFIES: td
    // EFFECTS: parses tutor from JSON object and adds it to tutorDatabase
    private void addTutor(TutorDatabase td, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String faculty = jsonObject.getString("faculty");
        Tutor tutor = new Tutor(name, faculty);
        td.addTutor(tutor);
    }
}