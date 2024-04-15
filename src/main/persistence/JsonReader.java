package persistence;

import model.Exercise;


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import model.ExerciseList;
import model.WorkoutProgram;
import org.json.*;

// A reader that reads ExerciseList from JSON data stored in file
// Reference: JSONSerializationDemo (https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git)
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads program from file and returns it;
    // throws IOException if an error occurs reading data from file
    public WorkoutProgram read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseProgram(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses workout program from JSON object and returns it
    private WorkoutProgram parseProgram(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        WorkoutProgram wr = new WorkoutProgram(name);
        addSessions(wr, jsonObject);
        return wr;
    }

    private void addSessions(WorkoutProgram wr, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("sessions");
        for (Object json : jsonArray) {
            JSONObject nextSession = (JSONObject) json;
            addExercises(wr, nextSession);
        }
    }

    // MODIFIES: p
    // EFFECTS: parses exercises from JSON Object and adds it to program
    private void addExercises(WorkoutProgram wp, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("exercises");
        for (Object json : jsonArray) {
            JSONObject nextExerciseList = (JSONObject) json;
            String name = nextExerciseList.getString("name");
            ExerciseList el = new ExerciseList(name);
            JSONObject nextExercise = (JSONObject) json;
            addExercise(el, nextExercise);
            wp.addSession(el);
        }
    }

    // MODIFIES: el
    // EFFECTS: parses exercise from JSON object and adds it to ExerciseList

    private void addExercise(ExerciseList el, JSONObject jsonObject) {
       // JSONObject  = jsonObject.getJSONArray("exercise");
        //for (Object json : jsonArray) {
         //   JSONObject nextExercise = (JSONObject) json;// need a forloop??
        String name = jsonObject.getString("name");
        int dur = jsonObject.getInt("duration");
        int weight = jsonObject.getInt("weight");
        int set = jsonObject.getInt("sets");
        int rep = jsonObject.getInt("reps");
        Exercise exerciseA = new Exercise(name, dur, weight, set, rep);

        //return exerciseA;
        el.addExercise(exerciseA);
    }
}

        /*
    // MODIFIES: wr
    // EFFECTS: parses ExerciseList from JSON object and adds them to workroom
    private void addExerciseList(ExerciseList el, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("exercises");
        for (Object json : jsonArray) {
            JSONObject nextExerciseList = (JSONObject) json;
            addExercise(el, nextExerciseList);
        }
    }
*/

        // MODIFIES: wp
        // EFFECTS: parses Exercise from JSON object and adds it to session
        //   private void addExercise(WorkoutProgram wp, JSONObject jsonObject) {
        // Exercise exercise = getExercise(jsonObject);
        //  JSONObject exercise = jsonObject.getJSONObject("exercise");


        //          int rep = jsonObject.getInt("reps");
        //         Exercise exerciseA = new Exercise(name, dur, weight, set, rep);
        //       wp.addExercise(exerciseA);
        //  }

    /*
     MODIFIES: el
     EFFECTS: parses exercise from JSON object and adds it to ExerciseList

    private void addExercise(ExerciseList el, JSONObject jsonObject) {
        JSONObject exercise = jsonObject.getJSONObject("exercise");
        String name = exercise.getString("name");
        int dur = jsonObject.getInt("duration");
        int weight = jsonObject.getInt("weight");
        int set = jsonObject.getInt("sets");
        int rep = jsonObject.getInt("reps");

        el.addExercise(exercise);
    }
    */

        // MODIFIES: exerciselist
        // EFFECTS: parses exercise from JSON object and adds it to ExerciseList
        //  private void addExercise(ExerciseList el, JSONObject jsonObject) {
        //    JSONObject exercise = jsonObject.getJSONObject("exercise");

        //       el.addExercise(exercise);
        //  }
/*
    // EFFECTS: parses Exercise from JSON object and returns it
    private Exercise getExercise(JSONObject jsonObject) {
        JSONObject exercise = jsonObject.getJSONObject("exercise");

        String name = exercise.getString("name");
        int dur = jsonObject.getInt("duration");
        int weight = jsonObject.getInt("weight");
        int set = jsonObject.getInt("sets");
        int rep = jsonObject.getInt("reps");
        return new Exercise(name, dur, weight, set, rep);
    }
}

*/
