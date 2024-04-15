package ui.gui;

import model.Exercise;
import model.ExerciseList;
import ui.pages.ProgramPage;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;

// Creates a panel to hold each exercise in the Program Page
public class SessionPanel extends JPanel {
    private ProgramPage myProgramPage;
    private ExerciseList session;
    private JButton addExerciseButton;
    private JButton deleteSessionButton;
    private JButton saveSessionButton;
    protected JPanel buttonPanel;

// EFFECTS: creates the panels for each exercise with buttons and calories burned.
    public SessionPanel(ProgramPage myProgramCard, ExerciseList session) {
        this.myProgramPage = myProgramCard;
        this.session = session;

        JPanel bottomPane = new JPanel();
        bottomPane.setLayout(new BorderLayout(0, BoxLayout.LINE_AXIS));
        setBackground(Styling.SIDEMENU_COLOR);

        setLayout(new GridLayout(0,1));
        setBackground(Styling.SIDEMENU_COLOR);

        setBorder(BorderFactory.createCompoundBorder(BorderFactory.createSoftBevelBorder(BevelBorder.RAISED),
                BorderFactory.createEmptyBorder(10, 20, 10, 20)));


        this.addExerciseButton = new JButton("Add Exercise Statistics");
        this.deleteSessionButton = new JButton("Delete Exercise");
       // this.saveSessionButton = new JButton("Save And Quit Application");
       // buttonPanel = new JPanel(new GridLayout(1,3)); // 1 row, 2 cols
       // this.add(buttonPanel);

        setBorder(BorderFactory.createCompoundBorder(BorderFactory.createSoftBevelBorder(BevelBorder.RAISED),
                BorderFactory.createEmptyBorder(10, 20, 10, 20)));


        JLabel sessionName = new JLabel(session.getName());
        sessionName.setFont(Styling.SUBTITLE_FONT);
        add(sessionName);

        for (Exercise ex : session.getExercises()) {
            JLabel exercise = new JLabel(ex.toString());
            exercise.setFont(Styling.MAIN_FONT);
            add(exercise);
        }

        initializeButtons();
        addBuffer();

    }

    // EFFECTS: creates a panel that contains time and calories of each exercise
    private void addBuffer() {
        JPanel bufferPanel = new JPanel();
        bufferPanel.setBackground(Color.decode("#bfe3b4"));
        bufferPanel.setPreferredSize(new Dimension(0, 150));
        bufferPanel.setLayout(new BoxLayout(bufferPanel, BoxLayout.PAGE_AXIS));
        // add(bufferPanel, BorderLayout.SOUTH);

        JLabel title = new JLabel("Total Time and Calories:");
        title.setFont(Styling.SUBTITLE_FONT);
        //bufferPanel.add(title, createTitleConstraints());

        // create a label to display text
        JLabel l = new JLabel();

        // add text to label
        l.setText(String.valueOf("Calories burned: "
                + session.totalCalories(session)
                + "\t" + String.valueOf("Total min: "
                + session.totalTime(session))));

        bufferPanel.add(l);
        this.add(bufferPanel, BOTTOM_ALIGNMENT);

    }

    /*
    public double totalCals() {
        double totalCal = 0.0;
        session.totalCalories();
       // for (ExerciseList e : session) {
            e.totalCalories(e);
            totalCal += e.totalCalories(e);
        }
        return totalCal;
    }

     */

    // EFFECTS: adds buttons to the page
    private void initializeButtons() {
        addExerciseButton.addActionListener(myProgramPage);
        deleteSessionButton.addActionListener(myProgramPage);
        //saveSessionButton.addActionListener(myProgramPage);
        add(addExerciseButton);
        add(deleteSessionButton);
        //add(saveSessionButton);


        deleteSessionButton.addActionListener(myProgramPage);
       // saveSessionButton.addActionListener(myProgramPage);

       // this.add(buttonPanel);
    }

    public ExerciseList getSession() {
        return session;
    }

    public JPanel getButtonPanel() {
        return buttonPanel;
    }
}

