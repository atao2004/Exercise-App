package persistence;

import model.Exercise;
import model.ExerciseList;
import model.WorkoutProgram;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
// referenced JsonSerializationDemo (https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git)

class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            ExerciseList el = new ExerciseList("My Workout");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass it
        }
    }

    @Test
    void testWriterEmptyProgram() {
            try {
                WorkoutProgram p = new WorkoutProgram("My WorkoutProgram");
                JsonWriter writer = new JsonWriter("./data/testWriterEmptyProgram.json");
                writer.open();
                writer.write(p);
                writer.close();

                JsonReader reader = new JsonReader("./data/testWriterEmptyProgram.json");
                p = reader.read();
                assertEquals("My WorkoutProgram", p.getName());
                assertEquals(0, p.numberOfSessions());
            } catch (IOException e) {
                fail(e + "Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            WorkoutProgram wr = new WorkoutProgram("My work out");
            //WorkoutProgram wp = new WorkoutProgram("My session");
            ExerciseList a = new ExerciseList("A");
            //ExerciseList b = new ExerciseList("B");
            wr.addSession(a);
          //  wp.addSession(b);

            a.addExercise(new Exercise("jump", 1, 1, 1, 1));
            a.addExercise(new Exercise("squat", 1, 1, 1, 1));
          //  b.addExercise(new Exercise("run", 1, 1, 1, 1));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralWorkout.json");
            writer.open();
            writer.write(wr);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralWorkout.json");
            wr = reader.read();
            assertEquals("My work out", wr.getName());
            List<ExerciseList> sessions = wr.getPastSessions();
            List<Exercise> exercises = wr.getCurSession();
            assertEquals(2, sessions.size());

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}