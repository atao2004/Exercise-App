package persistence;


import model.Exercise;
import model.ExerciseList;
import model.WorkoutProgram;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
// referenced JsonSerializationDemo (https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git)

class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyProgram() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyProgram.json");
        try {
            WorkoutProgram wp = reader.read();
            assertEquals("My WorkoutProgram", wp.getName());
            assertEquals(0, wp.numberOfSessions());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralProgram() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralWorkout.json");
        try {
            WorkoutProgram wp = reader.read();
            assertEquals("My work out", wp.getName());
            List<ExerciseList> sessions = wp.getPastSessions();
            List<Exercise> exercise = wp.getCurSession();
            assertEquals(2, sessions.size());
            ExerciseList exercisesA = new ExerciseList("A");
            exercisesA.addExercise(new Exercise("jump", 1, 1, 1, 1));
            exercisesA.addExercise(new Exercise("squat", 1,1, 1, 1));
            checkSession("A", exercisesA, sessions.get(0));
            //checkSession("B", exercisesB, exercise.get(0));
           // List<Exercise> exercisesB = new ArrayList<>();
          //  exercisesA.add(new Exercise("squat", 20, 50, 3, 8));
           // exercisesA.add(new Exercise("deadlift", 30,10, 3, 8));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}