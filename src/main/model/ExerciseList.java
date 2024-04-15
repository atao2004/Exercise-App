package model;

import eventlog.Event;
import eventlog.EventLog;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

// referenced JsonSerializationDemo (https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git)
// a List of exercises performed that session, calculates total calories and time
public class ExerciseList implements Writable {
    private String name;
    private List<Exercise> exercises;


    // MODIFIES: this
    // EFFECTS: create ExerciseList with empty list of exercises
    public ExerciseList(String name) {
        this.name = name;
        exercises = new ArrayList<>();

    }

    // REQUIRES: pre-existing exercise
    // MODIFIES: this
    // EFFECTS: adds all exercises duration to display total time of exercise
    public int totalTime(ExerciseList exerciseList) {
        int totalTime = 0;
        for (Exercise e : exerciseList.getExercises()) {
            totalTime += +e.getDuration();
        }
        return totalTime;
    }

    // REQUIRES: pre-existing exercise
    // MODIFIES: this
    // EFFECTS: calculates total calories burned from exercise
    public double totalCalories(ExerciseList exercises) {
        double totalCal = 0.0;
        for (Exercise e : exercises.getExercises()) {
            totalCal += e.getCals();
        }
        return totalCal;
    }

    // REQUIRES: sets, reps, weights > 0
    // MODIFIES: this
    // EFFECTS: adds exercise with sets, reps, weight to exercises
    //          if exercise name already exists in list, does not add
    //          adds eventlog to this action
    public void addExercise(Exercise exercise) {
        if (!(exercises.contains(exercise))) {
            exercises.add(exercise);
            EventLog.getInstance().logEvent(new Event("Added exercise statistics: " + exercise));
        }
    }


    // REQUIRES: pre-existing exercise in ExerciseList
    // MODIFIES: this
    // EFFECTS: removes exercise from exercises
    //          adds eventlog to this action
    public void removeExercise(Exercise exercise) {
        exercises.remove(exercise);
        EventLog.getInstance().logEvent(new Event("Removed exercise: " + exercise));
    }

    // REQUIRES: i is in range of ExerciseList length
    // MODIFIES: this
    // EFFECTS: remove ith element from exercises
    //          adds eventlog to this action
    public void removeExercise(int i) {
        Exercise exercise = exercises.get(i);
        exercises.remove(i);
        EventLog.getInstance().logEvent(new Event("Removed exercise: " + exercise));
    }

    public List<Exercise> getExercises() {
        return exercises;
    }

    // REQUIRES: i is in range of exercises length
    // EFFECTS: returns exercise by given index
    public Exercise getExercise(int i) {
        return exercises.get(i);
    }

    // EFFECTS: returns size of exercises
    public int numberOfExercises() {
        return exercises.size();
    }

    public String getName() {
        return name;
    }

    // EFFECTS: returns Json representation of exercises in exerciselist
    private JSONArray exercisesToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Exercise exercise : exercises) {
            jsonArray.put(exercise.toJson());
        }
        return jsonArray;
    }

    // EFFECTS: returns string representation of object
    @Override
    public String toString() {
        return name;
    }

    // EFFECTS: puts to JSON
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        //json.put("name", name);
        json.put("exercises", exercisesToJson());
        return json;
    }
}