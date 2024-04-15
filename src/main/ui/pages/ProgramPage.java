package ui.pages;

import model.Exercise;
import model.ExerciseList;
import persistence.JsonWriter;
import ui.gui.Gui;
import ui.gui.SessionPanel;
import ui.gui.Styling;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Optional;

//import static com.sun.tools.javac.util.LayoutCharacters.TAB;
//import static sun.tools.jconsole.inspector.XDataViewer.dispose;
//import static ui.console.ExerciseApp.JSON_STORE;

// Displays the workout overview page
public class ProgramPage extends JPanel implements Page, ActionListener {
    public static final String TITLE = "My Workout Program";
    private static final String JSON_STORE = "./data/workout.json";
    private static final int TAB = 4;
    private Gui app;

    // MODIFIES: this
    // EFFECTS: instantiates my workoutProgram container
    public ProgramPage(Gui application) {
        this.app = application;
        setBackground(Styling.BACKGROUND_COLOR);


        setLayout(new GridBagLayout());


        initializeTitle();
        initializeSessions();
        initializeAddSessionButton();
    }


    // MODIFIES: this
    // EFFECTS: adds title to this
    private void initializeTitle() {
        JLabel title = new JLabel(app.getWorkoutProgram().getName());
        title.setFont(Styling.TITLE_FONT);
        add(title, createTitleConstraints());
    }

    // EFFECTS: returns constraints for title
    private GridBagConstraints createTitleConstraints() {
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(30, 30, 23, 10);
        c.gridx = 0;
        c.gridy = 0;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.NORTHWEST;
        c.weightx = 0.5;
        c.weighty = 0;
        return c;
    }

    // MODIFIES: this
    // EFFECTS: for each session create session buttonPanel
    private void initializeSessions() {
        List<ExerciseList> sessions = app.getWorkoutProgram().getPastSessions();;
        for (int i = 0; i < sessions.size(); i++) {
            initializeSession(sessions.get(i), createSessionPanelConstraints(i));
        }
    }

    // MODIFIES: this
    // EFFECTS: creates and adds session buttonPanel with constraints c
    private void initializeSession(ExerciseList session, GridBagConstraints c) {
        SessionPanel sessionPanel = new SessionPanel(this, session);
        add(sessionPanel, c);
    }

    // REQUIRES: i >= 0
    // EFFECTS: creates restraints for session buttonPanel
    private GridBagConstraints createSessionPanelConstraints(int i) {
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(7, 40, 7, 40);
        c.gridx = 0;
        c.gridy = i + 1;
        c.weightx = 0.5;
        c.weighty = 0;
        c.anchor = GridBagConstraints.WEST;
        c.fill = GridBagConstraints.HORIZONTAL;
        return c;
    }

    // MODIFIES: this
    // EFFECTS: creates button that allows user to add a session
    private void initializeAddSessionButton() {
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(1, 1, 1, 1);
        c.gridx = 0;
        c.weightx = 0.5;
        c.weighty = 0.5;
        c.anchor = GridBagConstraints.NORTHEAST;

        JButton addSessionButton = new JButton("Add Exercise Title");
        addSessionButton.addActionListener(this);
        add(addSessionButton, c);

        JButton saveSessionButton = new JButton("Save And Quit");
        saveSessionButton.addActionListener(this);
        add(saveSessionButton, c);
    }


    // MODIFIES: this
    // EFFECTS: on button click, either add exercise, delete session, or add session
    @SuppressWarnings("methodlength")
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        JPanel buttonPanel;
        SessionPanel sessionPanel;
        switch (e.getActionCommand()) {
            case "Add Exercise Statistics":
                sessionPanel = (SessionPanel) button.getParent();
                addExerciseAction(sessionPanel);
                break;
            case "Delete Exercise":
                sessionPanel = (SessionPanel) button.getParent();
                deleteSessionAction(sessionPanel);
                break;
            case "Save And Quit":
                //sessionPanel = (SessionPanel) button.getParent();
                try {
                    saveSessionAction();
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
                break;
            case "Add Exercise Title":
                addSessionAction();
                break;
            default:
                break;
        }
    }

    // MODIFIES: this
    // EFFECTS: removes session model and refreshes display
    private void deleteSessionAction(SessionPanel sessionPanel) {
        app.getWorkoutProgram().removeSession(sessionPanel.getSession());
        remove(sessionPanel);
        revalidate();
        repaint();
    }

    // MODIFIES: this
    // EFFECTS: saves session model
    private void saveSessionAction() throws FileNotFoundException {
        app.saveDataPrompt();
        /*
        try {
            jsonWriter.open();
            JSONObject json = ex.toJson();
            json.put("exercises", ex);
            jsonWriter.saveToFile(json.toString(TAB));

            jsonWriter.close();
            System.out.println("Saved " + ex.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write file: " + JSON_STORE);
            app.getWorkoutProgram().addSession(ex);
            revalidate();
            repaint();
        }

         */
    }

    // MODIFIES: this, sessionPanel
    // EFFECTS: adds given exercise to model and refresh display
    private void addExerciseAction(SessionPanel sessionPanel) {
        Optional<String[]> response = addExerciseDialog();
        if (response.isPresent()) {
            if (!isValidExercise(response.get())) {
                JOptionPane.showMessageDialog(this,
                        "Name cannot be empty! Duration, weight, sets and reps must be integers.",
                        "Input error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            String name = response.get()[0];
            int dur = Integer.parseInt(response.get()[1]);
            int weight = Integer.parseInt(response.get()[2]);
            int sets = Integer.parseInt(response.get()[3]);
            int reps = Integer.parseInt(response.get()[4]);

            sessionPanel.getSession().addExercise(new Exercise(name, dur, weight, sets, reps));

            refreshAll();
            revalidate();
            repaint();
        }
    }


    // EFFECTS: creates dialog asking for exercise
    @SuppressWarnings("methodlength")
    private Optional<String[]> addExerciseDialog() {
        JTextField name = new JTextField();
        JTextField dur = new JTextField();
        JTextField weight = new JTextField();
        JTextField sets = new JTextField();
        JTextField reps = new JTextField();

        JPanel labelAndText = new JPanel();
        labelAndText.setLayout(new GridLayout(0, 2));
        labelAndText.add(new JLabel("Name: "));
        labelAndText.add(name);
        labelAndText.add(new JLabel("Duration (min): "));
        labelAndText.add(dur);
        labelAndText.add(new JLabel("Weight (kg): "));
        labelAndText.add(weight);
        labelAndText.add(new JLabel("Sets: "));
        labelAndText.add(sets);
        labelAndText.add(new JLabel("Reps: "));
        labelAndText.add(reps);

        int result = JOptionPane.showConfirmDialog(this, labelAndText,
                "Add Exercise", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            return Optional.of(new String[]{name.getText(), dur.getText(),
                    weight.getText(), sets.getText(), reps.getText()});
        }

        return Optional.empty();
    }

    // REQUIRES: response length is 3
    // EFFECTS: returns true if response is formatted correctly
    private boolean isValidExercise(String[] response) {
        try {
            Integer.parseInt(response[1]);
            Integer.parseInt(response[2]);
        } catch (NumberFormatException e) {
            return false;
        }
        return !response[0].isEmpty();
    }

    // MODIFIES: this
    // EFFECTS: if response given, add session to this
    private void addSessionAction() {
        Optional<String> response = addDialog();
        if (response.isPresent()) {
            String name = response.get();
            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Name must not be empty.",
                        "Input error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            app.getWorkoutProgram().addSession(new ExerciseList(name));

            refreshAll();
            revalidate();
            repaint();
        }
    }


    // EFFECTS: creates dialog asking for session
    private Optional<String> addDialog() {
        JTextField name = new JTextField();

        JPanel labelAndText = new JPanel();
        labelAndText.setLayout(new GridLayout(0, 2));
        labelAndText.add(new JLabel("Name: "));
        labelAndText.add(name);

        int result = JOptionPane.showConfirmDialog(this, labelAndText,
                "Add Exercise", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            return Optional.of(name.getText());
        }

        return Optional.empty();
    }

    // MODIFIES: this
    // EFFECTS: removes all components and creates them again
    public void refreshAll() {
        removeAll();
        initializeTitle();
        initializeSessions();
        initializeAddSessionButton();
    }
}
