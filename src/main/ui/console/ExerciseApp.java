package ui.console;

import model.Exercise;
import model.ExerciseList;
import model.WorkoutProgram;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

// referenced JsonSerializationDemo (https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git)
public class ExerciseApp {
    private Exercise cardio;
    private Exercise push;
    private Exercise pull;
    private Exercise legs;
    private Exercise myexercise;

    private String myexercisename;
    private WorkoutProgram workout;
    protected JsonWriter jsonWriter;
    protected JsonReader jsonReader;
    private static final String JSON_STORE = "./data/workout.json";
    protected ExerciseList curSession;
    private Scanner input;

    // EFFECTS: runs the exercise app
    public ExerciseApp() throws FileNotFoundException {
        input = new Scanner(System.in);
        workout = new WorkoutProgram("My workout");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runExercise();
    }

    // MODIFIES: this
    // EFEECTS: processes user input
    private void runExercise() {
        boolean stillContinue = true;
        String command = null;

        inside();

        while (stillContinue) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("e")) {
                stillContinue = false;
            } else {
                processCommand(command);
            }
        }
        System.out.println("\nGood Workout!");
    }

    //MODIFIES: this
    //EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("aw")) {
            addWeights();
        } else if (command.equals("rw")) {
            removeWeights();
        } else if (command.equals("ae")) {
            addExercise();
        } else if (command.equals("re")) {
            removeExercise();
        } else if (command.equals("ar")) {
            addReps();
        } else if (command.equals("as")) {
            addSets();
        } else if (command.equals("ad")) {
            addTime();
        } else if (command.equals("s")) {
            saveWorkout();
        } else if (command.equals("l")) {
            loadWorkout();
        } else if (command.equals("p")) {
            printWorkout();
        } else {
            System.out.println("Invalid Selection, please try again");
        }
    }

    private void printWorkout() {
        List<ExerciseList> sessions = workout.getPastSessions();

        for (ExerciseList e : sessions) {
            System.out.println(e.getName());
            System.out.println("\n\tTotal time of exercise: " + e.totalTime(e) + " minutes");
            System.out.println("\tTotal calories burned from all exercises: " + e.totalCalories(e)
                    + "kcal");
          //  time = time +  e.totalTime(e);
            //cals = cals + e.totalCalories(e);
        }
    }

    // MODIFIES: this
    // EFFECTS: initialize exercise
    private void inside() {
        cardio = new Exercise("Running", 0, 0, 0, 0);
        push = new Exercise("Shoulder press", 0, 0, 0, 0);
        pull = new Exercise("Lat pulldown", 0, 0, 0, 0);
        legs = new Exercise("Squats", 0, 0, 0, 0);

        curSession = new ExerciseList("My Workout");
        input = new Scanner(System.in);
        input.useDelimiter("\n");
        loadWorkout();
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("Add or remove entries:");
        System.out.println("\tae -> add new exercise");
        System.out.println("\tre -> remove exercise");
        System.out.println("\ts -> save workout to file");
        System.out.println("\tl -> load workout from file");
        System.out.println("\tp -> print workout summary from file");
        System.out.println("To modify a past exercise:");
        System.out.println("\trw -> remove weights (kg)");
        System.out.println("\taw -> add weights (kg)");
        System.out.println("\tas -> add sets");
        System.out.println("\tar -> add reps");
        System.out.println("\tad -> add duration (min)");
        System.out.println("To quit:");
        System.out.println("\te -> end session");
    }

    // MODIFIES: this
    // EFFECTS: removes exercise from nth position in list
    private void removeExercise() {
        System.out.print("\tWith the top exercise being the first, input the position of exercise to delete: \t");
        int pos = input.nextInt();
        if ((pos > (curSession.numberOfExercises() + 1)) || (pos < 0)) {
            System.out.print("\tInvalid Selection\t");
        }
        curSession.removeExercise(pos - 1);
        printExercise(curSession);

    }

    // REQUIRES: exerciseName is a string
    // MODIFIES: this
    // EFFECTS: adds exercise to list with custom duration, weight, sets and reps
    private void addExercise() {
        System.out.print("\tEnter new exercise name: \t");
        String selection = input.next();
        selection = selection.toLowerCase();
        myexercisename = selection;
        myexercise = new Exercise(selection, 0, 0, 0, 0);
        System.out.print("\tEnter duration of exercise (min): \t");
        int amount = input.nextInt();
        myexercise.addTime(amount);
        System.out.print("\tEnter weight lifted (kg): \t");
        int amount1 = input.nextInt();
        myexercise.addWeight(amount1);
        System.out.print("\tEnter number of sets: \t");
        int amount2 = input.nextInt();
        myexercise.addSets(amount2);
        System.out.print("\tEnter number of reps: \t");
        int amount3 = input.nextInt();
        myexercise.addRep(amount3);
        curSession.addExercise(myexercise);
        printExercise(curSession);
    }

    // MODIFIES: this
    // EFFECTS: adds weights
    private void addWeights() {
        Exercise selected = selectExercise();
        System.out.print("\tEnter weight (kg) to add: \t");
        int amount = input.nextInt();

        if (amount >= 0) {
            selected.addWeight(amount);
            curSession.addExercise(selected);
        } else {
            System.out.println("\tCannot add negative amount...\n");
        }
        printExercise(curSession);
    }

    // MODIFIES: this
    // EFFECTS: removes weight that user inputs
    private void removeWeights() {
        Exercise selected = selectExercise();
        System.out.print("\tEnter weight (kg) to remove: \t");
        int amount = input.nextInt();

        if (amount < 0) {
            System.out.println("Cannot remove negative amount. \n");
        } else if (selected.getWeight() - amount < 0) {
            System.out.println("cannot remove this much weight; would create negative amount. \n");
        } else {
            selected.removeWeight(amount);
            curSession.addExercise(selected);
        }
        printExercise(curSession);
    }

    private void addReps() {
        Exercise selected = selectExercise();
        System.out.print("\tEnter amount of reps to add: \t");
        int amount = input.nextInt();

        if (amount < 0) {
            System.out.println("Cannot add negative amount. \n");
        } else {
            selected.addRep(amount);
            curSession.addExercise(selected);
        }
        printExercise(curSession);
    }

    private void addSets() {
        Exercise selected = selectExercise();
        System.out.print("\tEnter amount of sets to add; \t");
        int amount = input.nextInt();

        if (amount < 0) {
            System.out.println("Cannot add negative amount. \n");
        } else {
            selected.addSets(amount);
            curSession.addExercise(selected);
        }
        printExercise(curSession);
    }

    private void addTime() {
        Exercise selected = selectExercise();
        System.out.print("\tEnter the time (min) to add; \t");
        int amount = input.nextInt();

        if (amount < 0) {
            System.out.println("Cannot add negative amount. \n");
        } else {
            selected.addTime(amount);
            curSession.addExercise(selected);
        }
        printExercise(curSession);
    }

    // EFFECTS: prompts user to select exercise and returns it
    @SuppressWarnings("methodlength")
    private Exercise selectExercise() {
        System.out.println("\nChoose from the following exercises:");
        String selection = "";  // force entry into loop

        while (!(selection.equals("r") || selection.equals("s") || selection.equals("l") || selection.equals("9")
                || selection.equals("sq") || selection.equals("my " + myexercisename) || selection.equals("8")
                || selection.equals("1") || selection.equals("2") || selection.equals("5") || selection.equals("6")
                || selection.equals("3") || selection.equals("4") || selection.equals("7"))) {
            //|| (Integer.parseInt(selection) > (selecteds.numberOfExercises()))
            // || ((Integer.parseInt(selection) < 0)))) {

            if (!(myexercisename == null)) {
                System.out.println("\nTo modify the exercise you just created, type:");
             //   System.out.println("\t<<my " + myexercisename + ">> for my previous entry of " + myexercisename);
            }
            System.out.println("\nTo modify your last entry:");
            System.out.println("\tenter <<1>> to modify your last entry");
            System.out.println("\nOr select from the following generic exercises:");
            System.out.println("\t<<r>> for running");
            System.out.println("\t<<s>> for shoulder press");
            System.out.println("\t<<l>> for lat pulldown");
            System.out.println("\t<<sq>> for squats");

            selection = input.next();
            selection = selection.toLowerCase();
        }
        if (selection.equals("r")) {
            return cardio;
        } else if (selection.equals("my " + myexercisename)) {
            return myexercise;
        } else if (selection.equals("l")) {
            return pull;
        } else if (selection.equals("s")) {
            return push;
        } else if (selection.equals("sq")) {
            return legs;
        } else if (selection.equals(Integer.parseInt(selection))) {
            return curSession.getExercise(Integer.parseInt(selection) + 1);
        }
        return myexercise;
    }


    // EFFECTS: prints exercise to the screen
    private void printExercise(ExerciseList selecteds) {
        System.out.println("\nA list of your completed exercises: ");

        for (Exercise e: selecteds.getExercises()) {
            System.out.println("\n- " + e.toString());
        }
        System.out.println("\n\tTotal time of exercise: " + selecteds.totalTime(selecteds) + " minutes");
        System.out.println("\tTotal calories burned from all exercises: " + selecteds.totalCalories(selecteds)
                + "kcal");

    }

    // EFFECTS: saves the workout to file
    private void saveWorkout() {
        try {
            //System.out.println("Enter name for session to create: ");
            //String name = input.next();
            workout.addSession(curSession);
            jsonWriter.open();
            jsonWriter.write(workout);
            jsonWriter.close();
            System.out.println("Saved " + workout.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads workout from file
    private void loadWorkout() {
        try {
            workout = jsonReader.read();
            System.out.println("Loaded " + workout.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

}
