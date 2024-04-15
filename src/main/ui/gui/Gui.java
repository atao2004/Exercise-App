
package ui.gui;

import eventlog.Event;
import eventlog.EventLog;
import model.WorkoutProgram;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;

// GUI application,
public class Gui extends JFrame {
    private static final String JSON_STORE = "./data/workout.json";

    private WorkoutProgram workoutProgram;
    private MainScreen mainScreen;
    private SideMenu sideMenu;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // MODIFIES: this
    // EFFECTS: Starts the GUI application
    public Gui() throws IOException {
        super("GUI");

        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        initializePersistenceDialogs();
        initializeScreenComponents();

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        pack();
        setVisible(true);
    }

    // EFFECTS: creates a new instance of GUI
    public static void main(String[] args) throws IOException {
        new Gui();
    }

    public WorkoutProgram getWorkoutProgram() {
        return workoutProgram;
    }

    public MainScreen getMainScreen() {
        return mainScreen;
    }

    // MODIFIES: this
    // EFFECTS:  allows us to save and creates dialogs
    private void initializePersistenceDialogs() {
        loadDataPrompt();

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                saveDataPrompt();
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: adds main components to this
    private void initializeScreenComponents() throws IOException {
        mainScreen = new MainScreen(this);
        sideMenu = new SideMenu(this);

        add(mainScreen, BorderLayout.CENTER);
        add(sideMenu, BorderLayout.EAST);
    }


    // EFFECTS: Opens dialog box to prompt user to save data
    // if yes, saves data to file
    // if no, nothing happens
    public void saveDataPrompt() {
        int n = JOptionPane.showConfirmDialog(this,
                "Would you like to save your data?",
                "Save data?",
                JOptionPane.YES_NO_CANCEL_OPTION);
        if (n == 0) {
            try {
                for (Event e: EventLog.getInstance()) {
                    System.out.println(e.toString());
                }
                jsonWriter.open();
                jsonWriter.write(workoutProgram);
                jsonWriter.close();
                System.out.println("Saved " + workoutProgram.getName() + " to " + JSON_STORE);
                dispose();
            } catch (FileNotFoundException e) {
                System.out.println("Unable to write file: " + JSON_STORE);
            }
        } else if (n == 1) {
            for (Event e: EventLog.getInstance()) {
                System.out.println(e.toString());
            }
            dispose();
        }
    }

    // MODIFIES: this
    // EFFECTS: Opens a dialog box to prompt user to either load data or start a blank application
    private void loadDataPrompt() {
        int n = JOptionPane.showConfirmDialog(this,
                "Load previously saved data?",
                "Load data?",
                JOptionPane.YES_NO_OPTION);
        if (n == 0) {
            try {
                workoutProgram = jsonReader.read();
            } catch (IOException e) {
                System.out.println("Data couldn't be loaded");
                workoutProgram = new WorkoutProgram("My Workout Program");
            }
        } else {
            workoutProgram = new WorkoutProgram("My Workout Program");
        }
    }
}
