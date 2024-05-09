package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

//Code influced by the JsonSerizalizationDemo https://repo1.maven.org/maven2/org/json/json/20210307/json-20210307.jar
//represents a database that stores a list of all the tutors
public class TutorDatabase implements Writable {
    private List<Tutor> allTutors; // list of all tutors in the database

    // Effects: create a new TutorDatabase with no tutors in it
    public TutorDatabase() {
        allTutors = new ArrayList();
    }

    // Effects: get all the tutors currently in the database
    public List<Tutor> getAllTutors() {
        return allTutors;
    }

    // Requires: non empty tutor
    // Effects: add a tutor to the database
    public void addTutor(Tutor newTutor) {
        EventLog.getInstance().logEvent(new Event("New Tutor " + newTutor.getName() + " has been added"));
        allTutors.add(newTutor);
    }

    //Requires: valid faculty String
    //Effects: filters the tutors by given faculty
    public List<Tutor> filterTutors(String faculty) {
        List<Tutor> filteredT = new ArrayList<>();
        for (Tutor t : allTutors) {
            if (isSameString(faculty, t.getFaculty())) {
                filteredT.add(t);
            }
        }
        EventLog.getInstance().logEvent(new Event("Tutors have been filtered by : " + faculty));
        return filteredT;
    }

    // Effects: produces true if strings are the same
    private Boolean isSameString(String expected, String name) {
        return expected.equals(name);
    }


    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("allTutors", allTutorsToJson());
        EventLog.getInstance().logEvent(new Event("Tutors were saved"));
        return json;
    }

    // EFFECTS: returns things in this workroom as a JSON array
    private JSONArray allTutorsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Tutor t : allTutors) {
            jsonArray.put(t.toJson());
        }

        return jsonArray;
    }
}


