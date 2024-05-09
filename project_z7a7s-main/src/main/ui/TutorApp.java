package ui;

import model.Event;
import model.EventLog;
import model.Tutor;
import model.TutorDatabase;

import java.util.List;
import java.util.Scanner;

import model.exception.LogException;
import persistence.JsonReader;
import persistence.JsonWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

//represents the tutor application
//Code influced by the JsonSerizalizationDemo https://repo1.maven.org/maven2/org/json/json/20210307/json-20210307.jar
public class TutorApp {
    private static final String JSON_STORE = "./data/tutordatabase.json";
    private TutorDatabase allTutors;
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private Gui gui;

    //EFFECTS: runs the tutor app and initialises TutorDatabase and Json Stuff
    public TutorApp() throws FileNotFoundException {
        allTutors = new TutorDatabase();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        gui = new Gui(this);
        runTutorApp();
    }

    public List<Tutor> getAllTutors() {
        return allTutors.getAllTutors();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runTutorApp() {
        boolean keepGoing = true;
        String command = null;

        init();

        displayMenu();
        while (keepGoing) {
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nGoodbye!");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("v")) {
            viewTutors();
            displayTutorMenu();
        } else if (command.equals("a")) {
            addTutor(selectName(), selectFaculty());
        } else if (command.equals("m")) {
            displayMenu();
        } else if (command.equals("r")) {
            rateTutor();
        } else if (command.equals("s")) {
            displayFilteredTutors(filterByFaculty(selectForFaculty()));
        } else if (command.equals("d")) {
            saveTutorDatabase();
        } else if (command.equals("l")) {
            loadTutorDatabase();
        } else {
            System.out.println("Selection not valid...");
        }
    }


    // MODIFIES: this
    // EFFECTS: immnitializes tutorDatabase
    private void init() {
        allTutors = new TutorDatabase();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\n What would you like to do?:");
        System.out.println("\tv -> view tutors");
        System.out.println("\ta -> add tutor");
        System.out.println("\td -> save tutor database to file");
        System.out.println("\tl -> load tutor database from file");
        System.out.println("\tq -> quit");
    }

    // EFFECTS: displays the tutors in TutorDatabase
    private void viewTutors() {
        for (Tutor t : allTutors.getAllTutors()) {
            System.out.println(t.getName());
        }
    }

    // EFFECTS: displays tutor menu of options to user
    private void displayTutorMenu() {
        System.out.println("\n What would you like to do?:");
        System.out.println("\ts -> search tutors");
        System.out.println("\tr -> rate tutor");
        System.out.println("\tm -> return to main menu");
        System.out.println("\tq -> quit");
    }

    //Requires: non-empty tutor
    //Modifies: this
    //Effects: adds tutor to TutorDatabase
    public void addTutor(String name, String faculty) {
        Tutor newTutor = new Tutor(name, faculty);
        allTutors.addTutor(newTutor);
        System.out.println("new Tutor has been created");
        System.out.println("\tm -> return to main menu");
    }

    // Effects: makes input the new tutor's name
    private String selectName() {
        System.out.println("what should I call this new tutor?");
        return input.next();
    }

    // Effects: makes input the new tutor's faculty
    private String selectFaculty() {
        System.out.println("what faculty is this tutor in?");
        return input.next();
    }

    // Requires: rating is greater than 0 and less than 6
    // Modifies: this
    // Effects: assign rating to selected tutor
    private void rateTutor() {
        String selectedName = selectTutorName();
        for (Tutor t : allTutors.getAllTutors()) {
            if (isSameString(selectedName, t.getName())) {
                giveRating(t, selectRating());
            }
        }
    }

    // return input as tutor to be assigned rating
    private String selectTutorName() {
        System.out.println("what is the tutor's name?");
        return input.next();
    }

    // Requires: non-empty tutor, rating > 0 and rating < 6
    // Modifies: tutor and this
    // Effects: adds new rating to the list of ratings and prints further instructions
    public void giveRating(Tutor tutor, int rating) {
        if (6 > rating && rating > 0) {
            tutor.rate(rating);
            System.out.println("tutor has been rated");
            System.out.println(rating);
            System.out.println("\tm -> return to main menu");
            System.out.println("\tv -> continue viewing tutors");
        } else {
            System.out.println("NOT VALID");
            giveRating(tutor, selectRating());
        }
    }

    //EFFECTS: prompts user to select a rating and assigns next input as rating
    private int selectRating() {
        System.out.println("what would you like to rate tutor?");
        int rating = input.nextInt();
        return rating;
    }

    // Effects: produces true if strings are the same
    private Boolean isSameString(String expected, String name) {
        return expected.equals(name);
    }


    // Modifies: this
    // Effects: filters by Faculty
    public List<Tutor> filterByFaculty(String faculty) {
        return allTutors.filterTutors(faculty);
    }

    //EFFECTS: Selects faculty to filter by
    private String selectForFaculty() {
        System.out.println("what faculty are you searching for?");
        String selectedFaculty = input.next();
        return selectedFaculty;
    }

    // Modifies: this
    // Effects: prints out list of new FilteredTutors and provides further instructions
    private void displayFilteredTutors(List<Tutor> filteredT) {
        if (filteredT.size() == 0) {
            System.out.println("No tutors found");
            System.out.println("\ts -> to try again");
            System.out.println("\tm -> return to main menu");
        } else {
            for (Tutor t : filteredT) {
                System.out.println(t.getName());

            }
            System.out.println("\n What would you like to do?:");
            System.out.println("\tr -> rate tutor");
            System.out.println("\tm -> return to main menu");
            System.out.println("\tq -> quit");
        }
    }

    // EFFECTS: saves the Tutor Database to file
    public void saveTutorDatabase() {
        try {
            jsonWriter.open();
            jsonWriter.write(allTutors);
            jsonWriter.close();
            System.out.println("Saved " + "TutorDatabase" + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads TutorDatabase from file
    public void loadTutorDatabase() {
        try {
            allTutors = jsonReader.read();
            System.out.println("Loaded " + "TutorDatabase" + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

}



