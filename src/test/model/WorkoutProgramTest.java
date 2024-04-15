package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class WorkoutProgramTest {
    private WorkoutProgram workoutProgram;
    private ExerciseList session1;
    private ExerciseList session2;
    private ExerciseList session3;

    @BeforeEach
    public void setup() {
        workoutProgram = new WorkoutProgram("workoutProgram");
        session1 = new ExerciseList("A");
        session2 = new ExerciseList("B");
        session3 = new ExerciseList("C");

        workoutProgram.addSession(session1);
        workoutProgram.addSession(session2);
        workoutProgram.addSession(session3);
    }

    @Test
    public void testAddSession() {
        assertEquals(3, workoutProgram.numberOfSessions());
    }

    @Test
    public void testRemoveSessionBySession() {
        assertEquals(3, workoutProgram.numberOfSessions());

        workoutProgram.removeSession(session2);

        assertEquals(session3, workoutProgram.getExercisesAtIndex(1));
        assertEquals(2, workoutProgram.numberOfSessions());
        assertFalse(workoutProgram.getPastSessions().contains(session2));
    }

    @Test
    public void testRemoveSessionByIndex() {
        assertEquals(3, workoutProgram.numberOfSessions());

        workoutProgram.removeSession(1);

        assertEquals(2, workoutProgram.numberOfSessions());
        assertFalse(workoutProgram.getPastSessions().contains(session2));
    }

    @Test
    public void testName() {
        workoutProgram.setName("My workoutProgram");

        assertEquals("My workoutProgram", workoutProgram.getName());
    }

    @Test
    public void testGetSessionByName() {
        assertTrue(workoutProgram.getSessionByName("B").isPresent());
        assertEquals(session2, workoutProgram.getSessionByName("B").get());
        assertFalse(workoutProgram.getSessionByName("Fake session").isPresent());
    }

    @Test
    public void testAddExercise() {
        assertTrue(workoutProgram.getSessionByName("B").isPresent());
        assertEquals(session2, workoutProgram.getSessionByName("B").get());
        assertFalse(workoutProgram.getSessionByName("Fake session").isPresent());
    }
}
