package ui.console;

import ui.console.ExerciseApp;

import java.io.FileNotFoundException;
// referenced JsonSerializationDemo (https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git)

public class Main {
    public static void main(String[] args) {
        try {
            new ExerciseApp();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found");
        }
    }
}
