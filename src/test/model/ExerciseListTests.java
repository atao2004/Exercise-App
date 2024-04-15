package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ExerciseListTests {
    private Exercise exercise;
    private Exercise anotherExercise;
    private Exercise thirdExercise;

    private ExerciseList list;
    @BeforeEach
    void runBefore() {
        exercise = new Exercise("Shoulder press", 20, 20, 3, 8);
        anotherExercise = new Exercise("Dumbbell", 20, 20, 3, 8);
        thirdExercise = new Exercise("Dumbbell", 20, 21, 4, 9);
        list = new ExerciseList("Test list");
    }
    @Test
    void testConstructor() {
        assertEquals(0, list.getExercises().size());
    }
    @Test
    public void testAddExercise() {
        assertEquals(0, list.numberOfExercises());
        list.addExercise(exercise);
        list.addExercise(exercise);
        list.addExercise(anotherExercise);
        assertEquals(2, list.numberOfExercises());
        list.addExercise(anotherExercise);
        list.addExercise(anotherExercise);
        list.addExercise(exercise);
        assertEquals(2, list.numberOfExercises());
        list.addExercise(thirdExercise);
        assertEquals(3, list.numberOfExercises());
    }
    @Test
    public void testRemoveExercise() {
        list.addExercise(exercise);
        list.addExercise(anotherExercise);
        list.addExercise(thirdExercise);
        list.removeExercise(anotherExercise);
        assertEquals(2, list.numberOfExercises());
        assertTrue(list.getExercises().contains(exercise));
        assertFalse(list.getExercises().contains(anotherExercise));
        assertTrue(list.getExercises().contains(thirdExercise));
        list.removeExercise(thirdExercise);
        assertEquals(1, list.numberOfExercises());
        assertTrue(list.getExercises().contains(exercise));
        assertFalse(list.getExercises().contains(anotherExercise));
        assertFalse(list.getExercises().contains(thirdExercise));
        list.removeExercise(exercise);
        assertEquals(0, list.numberOfExercises());
        assertFalse(list.getExercises().contains(exercise));
        assertFalse(list.getExercises().contains(anotherExercise));
        assertFalse(list.getExercises().contains(thirdExercise));
    }
    @Test
    public void testRemoveExerIndex() {
        list.addExercise(exercise);
        list.addExercise(anotherExercise);
        list.addExercise(thirdExercise);
        list.removeExercise(1);
        assertEquals(2, list.numberOfExercises());
        assertTrue(list.getExercises().contains(exercise));
        assertFalse(list.getExercises().contains(anotherExercise));
        assertTrue(list.getExercises().contains(thirdExercise));
        list.removeExercise(1);
        assertEquals(1, list.numberOfExercises());
        assertTrue(list.getExercises().contains(exercise));
        assertFalse(list.getExercises().contains(anotherExercise));
        assertFalse(list.getExercises().contains(thirdExercise));
        list.removeExercise(0);
        assertEquals(0, list.numberOfExercises());
        assertFalse(list.getExercises().contains(exercise));
        assertFalse(list.getExercises().contains(anotherExercise));
        assertFalse(list.getExercises().contains(thirdExercise));
    }
    @Test
    public void testExerciseListSize() {
        assertEquals(0, list.numberOfExercises());
        list.addExercise(exercise);
        assertEquals(1, list.numberOfExercises());
        list.addExercise(anotherExercise);
        assertEquals(2, list.numberOfExercises());
        list.addExercise(thirdExercise);
        assertEquals(3, list.numberOfExercises());
        list.addExercise(exercise);
        list.addExercise(anotherExercise);
        list.addExercise(thirdExercise);
        assertEquals(3, list.numberOfExercises());
    }
    @Test
    public void testGetExerIndex() {
        list.addExercise(exercise);
        list.addExercise(anotherExercise);
        list.addExercise(thirdExercise);
        assertEquals(exercise, list.getExercise(0));
        list.addExercise(thirdExercise);
        assertEquals(anotherExercise, list.getExercise(1));
        assertEquals(thirdExercise, list.getExercise(2));
    }
    @Test
    public void testTotalCals() {
        ExerciseList list2 = new ExerciseList("blah");
        list2.addExercise(anotherExercise);
        list.addExercise(exercise);
        assertEquals(16, list.totalCalories(list));
        list.addExercise(exercise);
        assertEquals(16, list.totalCalories(list));
        list.addExercise(anotherExercise);
        assertEquals(76, list.totalCalories(list));

        list.addExercise(thirdExercise);
        assertEquals(136, list.totalCalories(list));
        assertEquals(60, list2.totalCalories(list2));
        assertEquals(0, list.totalCalories(new ExerciseList("Test")));
    }
    @Test
    public void testTotalTime() {
        list.addExercise(exercise);
        assertEquals(20, list.totalTime(list));
        list.addExercise(exercise);
        assertEquals(20, list.totalTime(list));
        ExerciseList list2 = new ExerciseList("blah");
        list2.addExercise(anotherExercise);
        list.addExercise(exercise);

        list.addExercise(thirdExercise);
        assertEquals(40, list.totalTime(list));
        assertEquals(20, list2.totalTime(list2));
        list.addExercise(anotherExercise);
        assertEquals(60, list.totalTime(list));
        assertEquals(0, list.totalTime(new ExerciseList("Test")));
    }
    @Test
    public  void testNumOfExercises() {
        assertEquals(0, list.numberOfExercises());
        list.addExercise(exercise);
        assertEquals(1, list.numberOfExercises());
        list.addExercise(exercise);
        assertEquals(1, list.numberOfExercises());
        list.addExercise(anotherExercise);
        assertEquals(2, list.numberOfExercises());

        list.addExercise(thirdExercise);
        assertEquals(true, list.getExercises().contains(thirdExercise));
        assertEquals(true, list.getExercises().contains(anotherExercise));
        assertEquals(true, list.getExercises().contains(exercise));
        assertEquals(3, list.numberOfExercises());
    }

    @Test
    public void testToString() {
        ExerciseList container = new ExerciseList("1");
        ExerciseList container2 = new ExerciseList("2");
        assertEquals("1", container.toString());
        assertEquals("2", container2.toString());
        assertEquals("Test list", list.toString());

    }



}
