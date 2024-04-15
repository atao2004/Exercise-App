package ui.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import static java.awt.AWTEventMulticaster.add;

// creates a sidemenu with buttons to switch between pages
public class SideMenu extends JPanel implements ActionListener {
    private Gui application;
    private JButton button1;
    private JButton button2;
    private JButton b3;

    // MODIFIES: this
    // EFFECTS: creates the side menu with navigation buttons
    public SideMenu(Gui application) {
        this.application = application;

        setLayout(new GridLayout(0, 1, 0, 21));
        setBorder(BorderFactory.createEmptyBorder(30, 20, 30, 20));
        initializeButtons();
        //addWhiteSpace();
        setBackground(Styling.SIDEMENU_COLOR);
    }

    // MODIFIES: workoutTrackerGUI
    // EFFECTS: on button click, shows the corresponding card in the main screen
    @Override
    public void actionPerformed(ActionEvent e) {
        CardLayout cards = (CardLayout) application.getMainScreen().getLayout();
        switch (e.getActionCommand()) {
            case "Welcome Page":
                cards.show(application.getMainScreen(), "Home");
                break;
            case "My Workout Program":
                cards.show(application.getMainScreen(), "My Workout Program");
                break;
            case "Saved Exercises":
                cards.show(application.getMainScreen(), "Saved Exercises");
                break;
            default:
                break;
        }
    }

    // MODIFIES: this
    // EFFECTS: create and add buttons to this
    private void initializeButtons() {
        button1 = new JButton("Welcome Page");
        button2 = new JButton("My Workout Program");
        b3 = new JButton("Saved Exercises");
        button1.addActionListener(this);
        button2.addActionListener(this);
        b3.addActionListener(this);

        add(button1);
        add(button2);
        add(b3);
    }
}
