package ui.pages;

import model.Exercise;
import model.ExerciseList;
import ui.gui.Gui;
import ui.gui.SavePanel;
import ui.gui.SessionPanel;
import ui.gui.Styling;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

// Displays the workout overview page
public class SavePage extends JPanel implements Page {
    public static final String TITLE = "Saved Exercises";
    private Gui application;

    // MODIFIES: this
    // EFFECTS: instantiates my workoutProgram container
    public SavePage(Gui application) throws IOException {
        this.application = application;
        setBackground(Styling.BACKGROUND_COLOR);

        setLayout(new GridBagLayout());
      //  initializeAddSessionButton();
        initializeTitle();
        initializeSessions();
    }

    // MODIFIES: this
    // EFFECTS: adds title to this
    private void initializeTitle() {
        JLabel title = new JLabel(TITLE);
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
    private void initializeSessions() throws IOException {
        List<ExerciseList> sessions = application.getWorkoutProgram().getPastSessions();
        for (int i = 0; i < 1; i++) {
            try {
                initializeSession(sessions.get(i), createSessionPanelConstraints(i));
            } catch (IOException e) {
                System.out.println("Data couldn't be loaded");
            } catch (IndexOutOfBoundsException e) {
                initializeSession(new ExerciseList(""), createSessionPanelConstraints(i));
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: creates and adds session buttonPanel with constraints c
    private void initializeSession(ExerciseList session, GridBagConstraints c) throws IOException {
        SavePanel savePanel = new SavePanel(this, session); // TODO
        add(savePanel, c);
    }

    // REQUIRES: i >= 0
    // EFFECTS: creates restraints for session buttonPanel
    private GridBagConstraints createSessionPanelConstraints(int i) {
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(1, 39, 1, 41);
        c.gridx = 0;
        c.gridy = i + 1;
        c.weightx = 0.5;
        c.weighty = 0;
        c.anchor = GridBagConstraints.WEST;
        c.fill = GridBagConstraints.HORIZONTAL;
        return c;
    }


    // MODIFIES: this
    // EFFECTS: removes all components and creates them again
    public void refreshEverything() throws IOException {
        removeAll();
        initializeTitle();
        initializeSessions();
    }
}
