package model;

import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

//Code influced by the JsonSerizalizationDemo https://repo1.maven.org/maven2/org/json/json/20210307/json-20210307.jar
//Represents a Tutor that has a name, rating, faculty, rating, coursesOffered and availability
public class Tutor implements Writable {
    private String name;                  // name of tutor
    private String faculty;               // faculty of tutor
    private List<Integer> rating;         // list of ratings (1-5) for tutor
    //private List<String> coursesOffered;  // courses that tutor can teach
    //private List<String> availability;   // time slot availability of tutor

    // Effects: Creates tutor with given name and faculty
    public Tutor(String tutorName, String tutorfaculty) {
        name = tutorName;
        faculty = tutorfaculty;
        rating = new ArrayList();
        //coursesOffered = new ArrayList();
        //availability = new ArrayList();
    }

    public String getName() {
        return this.name;
    }

    public String getFaculty() {
        return this.faculty;
    }

    public int getRating() {
        return calculateRating(rating);
    }

    //Requires: List<Integer> with 5 >= Integer >= 1
    //Effects: calculates average rating of tutor
    private int calculateRating(List<Integer> rating) {
        int numRatings = rating.size();
        int totalRating = 0;
        if (0 == rating.size()) {
            return 0;
        } else {
            for (Integer x : rating) {
                totalRating = x + totalRating;
            }
            return totalRating / numRatings;
        }
    }

    //public List<String> getCoursesOffered() {
    //    return coursesOffered;
    //}

    //public List<String> getAvailability() {
   /*     return availability;
    }*/

    //EFFECTS: adds new Rating to list of other ratings
    public void rate(Integer newRating) {
        EventLog.getInstance().logEvent(new Event(this.getName() + " Has been rated " + newRating));
        rating.add(newRating);
    }

    //EFFECTS: adds new courseOffered to coursesOffered
    //public void addCourse(String newCourse) {
    //    coursesOffered.add(newCourse);
    //}

    //EFFECTS: add new time to availability
    //public void addTimeSlot(String newTimeSlot) {
    //    availability.add(newTimeSlot);
    //}

    @Override
    //BASED ON JSON DEMO APP
    //EFFECTS: returns Tutor as JSON object
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("faculty", faculty);
        json.put("rating", rating);
    //    json.put("coursesOffered", coursesOffered);
    //    json.put("availablility", availability);
        return json;
    }
}



