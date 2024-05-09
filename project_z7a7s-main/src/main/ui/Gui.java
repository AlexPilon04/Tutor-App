package ui;

import model.EventLog;
import model.Tutor;
import model.exception.LogException;
import java.awt.event.WindowListener;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import ui.TutorApp;
import java.util.List;
import model.Event;
import model.EventLog;


import javax.swing.*;
import javax.swing.JPanel;
import java.awt.*;

//Represents a GUI for the tutorApp
//Code influenced by https://stackoverflow.com/questions/6578205/swing-jlabel-text-change-on-the-running-application
//Code Influenced by https://docs.oracle.com/javase/tutorial/uiswing/examples/components/BorderDemoProject/src/components/BorderDemo.java
//Code influced by the AlarmSystem https://github.students.cs.ubc.ca/CPSC210/AlarmSystem.git

public class Gui extends JFrame implements ActionListener {
    private JPanel panel;
    private JLabel tutors;
    private JCheckBox checkBox;
    private JTextField facultyField;
    private JTextField ratingName;
    private JTextField ratingNum;
    private JTextField addName;
    private JTextField addFaculty;
    private TutorApp app;
    private JButton catButton;
    private MyListener listener;

    //Creates a Gui for given tutorApp
    public Gui(TutorApp ta) {
        super("TutorDatabase");
        this.app = ta;
        panel = new JPanel();
        panel.setLayout(null);

        listener = new MyListener();
        createRateButton();
        createLoadButton();
        createSaveButton();
        createAddButton();
        createFilterCheckBox();
        createTextFields();
        createCatButton();

        tutors = new JLabel("No tutors exist");
        tutors.setFont(new Font("Arial", Font.BOLD, 30));
        tutors.setBounds(200,   100, 800, 300);
        panel.add(tutors);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.add(panel);
        this.addWindowListener(listener);
        setSize(1000, 700);
        setVisible(true);
    }

    //EFFECTS: checks for action commands and does corresponding thing
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Rate")) {
            giveARating();
        } else if (e.getActionCommand().equals("Load")) {
            app.loadTutorDatabase();
            tutors.setText(updateTutorLabels(app.getAllTutors()));
        } else if (e.getActionCommand().equals("Save")) {
            app.saveTutorDatabase();
        } else if (e.getActionCommand().equals("Filter")) {
            if (validFaculty(facultyField.getText())) {
                tutors.setText(updateTutorLabels(app.filterByFaculty(facultyField.getText())));
                facultyField.setText("Faculty");
                catButton.setVisible(true);
            } else {
                checkBox.setSelected(false);
                facultyField.setText("Not found");
                catButton.setVisible(false);
            }
        } else if (e.getActionCommand().equals("add")) {
            app.addTutor(addName.getText(), addFaculty.getText());
            tutors.setText(updateTutorLabels(app.getAllTutors()));
            addName.setText("Name");
            addFaculty.setText("Faculty");
        }
    }

    //EFFECTS: returns a list of all the tutors in given list of tutors
    public String updateTutorLabels(List<Tutor> tutors) {
        String labels = "";
        for (Tutor t : tutors) {
            labels = labels + "<html>" +  t.getName() + " - " + t.getFaculty() + "<br>";
        }
        return labels;
    }

    //MODIFIES: tutor t
    //EFFECTS: give rating assuming tutor and rating both valid
    private void giveARating() {
        if (!validTutor(ratingName.getText())) {
            ratingName.setText("Not Found");
        } else if (!validRating(ratingNum.getText())) {
            ratingNum.setText("Not Int");
        } else {
            app.giveRating(findTutor(ratingName.getText()), Integer.valueOf(ratingNum.getText()));
        }
    }


    //EFFECTS: creates a button that loads the state
    private void createLoadButton() {
        JButton loadButton = new JButton("Load");
        loadButton.setBounds(550,500,125,50);
        loadButton.setActionCommand("Load");
        loadButton.addActionListener(this);
        panel.add(loadButton);
    }

    //EFFECTS: creates a button that saves the state
    private void createSaveButton() {
        JButton saveButton = new JButton("Save");
        saveButton.setBounds(350,500,125,50);
        saveButton.setActionCommand("Save");
        saveButton.addActionListener(this);
        panel.add(saveButton);
    }

    //EFFECTS: creates a button that rates a tutor
    private void createRateButton() {
        JButton rateButton = new JButton("Rate");
        rateButton.setBounds(550,200,125,45);
        rateButton.setActionCommand("Rate");
        rateButton.addActionListener(this);
        panel.add(rateButton);
    }

    //EFFECTS: creates a cat button
    private void createCatButton() {
        ImageIcon catImage = new ImageIcon("./Cat.gif");
        catButton = new JButton(catImage);
        catButton.setBounds(800,500,100,100);
        panel.add(catButton);
        catButton.setVisible(false);
    }

    //EFFECTS: creates a button that filters tutors
    private void createFilterCheckBox() {
        checkBox = new JCheckBox("Filter");
        checkBox.setBounds(550,270,125,50);
        checkBox.setFont(new Font("Arial", Font.BOLD, 20));
        checkBox.setActionCommand("Filter");
        checkBox.addActionListener(this);
        panel.add(checkBox);
    }


    //EFFECTS: creates a button that adds tutors
    private void createAddButton() {
        JButton addButton = new JButton("add");
        addButton.setBounds(550,125,125,50);
        addButton.setActionCommand("add");
        addButton.addActionListener(this);
        panel.add(addButton);
    }

    //EFFECTS: returns true if inputed text field is valid string of int [1-5]
    private Boolean validRating(String text) {
        Boolean valid = false;
        if (text.equals("1")) {
            valid = true;
        } else if (text.equals("2")) {
            valid = true;
        } else if (text.equals("3")) {
            valid = true;
        } else if (text.equals("4")) {
            valid = true;
        } else if (text.equals("5")) {
            valid = true;
        }
        return valid;
    }

    //EFFECTS: returns true if inputed text field is name of tutor
    private Boolean validTutor(String name) {
        List<Tutor> ts = app.getAllTutors();
        Boolean valid = false;
        for (Tutor t : ts) {
            if (t.getName().equals(name)) {
                valid = true;
            }
        }
        return valid;
    }

    private Tutor findTutor(String name) {
        List<Tutor> ts = app.getAllTutors();
        Tutor tut = null;
        for (Tutor t : ts) {
            if (t.getName().equals(name)) {
                tut = t;
            }
        }
        return tut;
    }

    //EFFECTS: returns true if inputed text field is faculty of tutor
    private Boolean validFaculty(String faculty) {
        List<Tutor> ts = app.getAllTutors();
        Boolean valid = false;
        for (Tutor t : ts) {
            if (t.getFaculty().equals(faculty)) {
                valid = true;
            }
        }
        return valid;
    }

    //EFFECTS: creates all required text fields and adds them to the panel
    private void createTextFields() {
        facultyField = new JTextField(50);
        ratingName = new JTextField(50);
        ratingNum = new JTextField(50);
        addName = new JTextField(50);
        addFaculty = new JTextField(50);
        facultyField.setText("Faculty");
        ratingName.setText("Name");
        ratingNum.setText("[1-5]");
        addName.setText("Name");
        addFaculty.setText("Faculty");
        facultyField.setBounds(700, 270, 100, 40);
        ratingName.setBounds(700, 200, 100, 40);
        ratingNum.setBounds(825, 200, 60, 40);
        addName.setBounds(700,130, 100, 40);
        addFaculty.setBounds(825,130, 100, 40);
        panel.add(facultyField);
        panel.add(ratingName);
        panel.add(ratingNum);
        panel.add(addName);
        panel.add(addFaculty);
    }

}
