package model;

import persistence.Writable;
import org.json.JSONObject;
import org.json.JSONArray;

import java.util.Objects;

// referenced JsonSerializationDemo (https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git)
// Represents an exercise having a name, duration, weight, sets, reps
public class Exercise implements Writable {
    protected String exerciseName; // name of exercise must be a string
    private int dur; // duration of exercise
    private int set;
    private int rep;
    private int weight;
    private double calories;
    private static double SQUAT_CAL;
    private static double LAT_CAL;
    private static double SHOULDER_CAL;
    private static double DEADLIFT_CAL;
    private static double RUNNING_CAL;

    // REQUIRES: exerciseName has a length != 0
    //
    // EFFECTS: name must be a string exerciseName;
    //          duration is a positive integer that represents
    //          the duration of exercise. kg is the weight of exercise.
    //          sets is number of sets, reps is number of reps.
    //          if given negative values, automatically inputs zero
    public Exercise(String name, int duration, int kg, int sets, int reps) {
        exerciseName = name;
        if (duration < 0) {
            dur = 0;
        } else {
            dur = duration;
        }
        if (kg < 0) {
            weight = 0;
        } else {
            weight = kg;
        }
        if (sets < 0) {
            set = 0;
        } else {
            set = sets;
        }
        if (reps < 0) {
            rep = 0;
        } else {
            rep = reps;
        }
    }

    public String getName() {
        return exerciseName;
    }

    public double getCals() {
        return findCalories();
    }

    public int getDuration() {
        return dur;
    }

    public int getWeight() {
        return weight;
    }

    public int getSets() {
        return set;
    }

    public int getReps() {
        return rep;
    }

    // REQUIRES: amount > 0
    // MODIFIES: this
    // EFFECTS:  amount is added to weights pushed for that set
    public void addWeight(int amount) {
        weight = amount + weight;
    }

    // REQUIRES: amount > 0
    // MODIFIES: this
    // EFFECTS: amount is removed from weights for that set
    public void removeWeight(int amount) {
        if ((weight - amount) >= 0) {
            weight = weight - amount;
        }
    }

    // REQUIRES: amount > 0
    // MODIFIES: this
    // EFFECTS: amount is removed from reps
    public void removeRep(int amount) {
        if ((rep - amount) >= 0) {
            rep = rep - amount;
        }
    }

    // REQUIRES: amount > 0
    // MODIFIES: this
    // EFFECTS: amount is added to reps
    public void addRep(int amount) {
        rep = rep + amount;
    }

    // REQUIRES: time > 0
    // MODIFIES: this
    // EFFECTS: time is added to duration
    public void addTime(int time) {
        dur = dur + time;
    }

    // REQUIRES: sets > 0
    // MODIFIES: this
    // EFFECTS: amount is added to sets
    public void addSets(int amount) {
        set = this.set + amount;
    }

    // REQUIRES: sets > 0
    // MODIFIES: this
    // EFFECTS: amount is removed from sets
    public void removeSets(int amount) {
        if ((set - amount) >= 0) {
            set = set - amount;
        }
    }

    // EFFECTS: calculates calories according to specific exercise
    //          otherwise returns generic calculation.
    public double findCalories() {
        DEADLIFT_CAL = 0.09;
        SQUAT_CAL = 0.02;
        RUNNING_CAL = 6;
        SHOULDER_CAL = 0.04;
        LAT_CAL = 0.08;
        if ((Objects.equals(exerciseName.toLowerCase(), "squat"))
                || (Objects.equals(exerciseName.toLowerCase(), "squats"))) {
            return calories = (weight) * SQUAT_CAL * dur;
        } else if ((Objects.equals(exerciseName.toLowerCase(), "deadlift")
                || (Objects.equals(exerciseName.toLowerCase(), "deadlifts")))) {
            return calories = (weight) * DEADLIFT_CAL * dur;
        } else if (Objects.equals(exerciseName.toLowerCase(), "running")) {
            return calories = RUNNING_CAL * dur;
        } else if ((Objects.equals(exerciseName.toLowerCase(), "shoulder press"))
                || (Objects.equals(exerciseName.toLowerCase(), "shoulder presses"))) {
            return calories = (weight) * SHOULDER_CAL * dur;
        } else if ((Objects.equals(exerciseName.toLowerCase(), "lat pulldowns"))
                || (Objects.equals(exerciseName.toLowerCase(), "lat pulldown"))) {
            return calories = (weight) * LAT_CAL * ((double) dur / 2);
        } else {
            return calories = 3 * dur;
        }
    }


    // EFFECTS: converts exercise information to string
    //public String toString() {
       // String s = "min " + " with " + weight;
      //  String s1 = " sets " + " and " + rep;
      //  return exerciseName + " for " + dur + s + "kg " + " for " + set + s1 + " reps";
   // }

    // EFFECTS: returns string representation of object
    @Override
    public String toString() {
        String s = "min " + " with " + weight;
        String s1 = " sets " + " and " + rep;
        return exerciseName + " for " + dur + s + "kg " + " for " + set + s1 + " reps";
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", exerciseName);
        json.put("duration", dur);
        json.put("weight", weight);
        json.put("sets", set);
        json.put("reps", rep);
        return json;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Exercise exercise = (Exercise) o;
        return exerciseName.equals(exercise.getName())
                && dur == exercise.dur
                && weight == exercise.weight
                && set == exercise.set
                && rep == exercise.rep;
    }

  //  @Override
  //  public int hashCode() {
     //   return Objects.hash(exercise, sets, reps);
  //  }
}

