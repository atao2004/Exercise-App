package persistence;

import model.ExerciseList;

import static org.junit.jupiter.api.Assertions.assertEquals;
// referenced JsonSerializationDemo (https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git)

public class JsonTest {
    protected void checkSession(String name, ExerciseList exercises, ExerciseList exercise) {
        assertEquals(name, exercises.getName());
        assertEquals(exercises.getExercise(1), exercises.getExercise(1));
    }
}