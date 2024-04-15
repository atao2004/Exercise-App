package ui.gui;


import model.Exercise;
import model.ExerciseList;
import model.WorkoutProgram;
import persistence.JsonReader;
import persistence.JsonWriter;
import ui.pages.SavePage;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.io.IOException;
import java.util.List;

// Panel which displays all the saved so far exercises
public class SavePanel extends JPanel {
    private static final String JSON_STORE = "./data/workout.json";
    WorkoutProgram workoutProgram;
    private JsonReader jsonReader;

    // EFFECTS: constructs save buttonPanel
    public SavePanel(SavePage savePage, ExerciseList session) throws IOException {
        jsonReader = new JsonReader(JSON_STORE);
        try {
            workoutProgram = jsonReader.read();
        } catch (IOException e) {
            System.out.println("Data couldn't be loaded");
            workoutProgram = new WorkoutProgram("My Workout Program");
        }
        setLayout(new GridLayout(0, 1));
        setBackground(Styling.SIDEMENU_COLOR);

        setBorder(BorderFactory.createCompoundBorder(BorderFactory.createSoftBevelBorder(BevelBorder.RAISED),
                BorderFactory.createEmptyBorder(15, 25, 15, 25)));


        JLabel sessionName = new JLabel("Exercises:");
        sessionName.setFont(Styling.SUBTITLE_FONT);
        add(sessionName);

        List<ExerciseList> sessions = workoutProgram.getPastSessions();
        for (ExerciseList exx : sessions) {
            JLabel session1 = new JLabel(exx.toString());
            session1.setFont(Styling.MAIN_FONT);
            add(session1);

        }

    }
}

        /*
    public void addExercises(ExerciseList ex) {
        for (Exercise exe : ex.getExercises()) {
            JLabel exercise = new JLabel(exe.toString());
            exercise.setFont(Styling.MAIN_FONT);
            add(exercise);
        }
    }
    */

