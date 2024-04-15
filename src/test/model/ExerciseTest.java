package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

class ExerciseTest {
    private Exercise testExercise;


    @BeforeEach
    void runBefore() { testExercise = new Exercise("Shoulder press", 20, 20, 3, 8);}

    @Test
    void testConstructor() {
        assertEquals(20, testExercise.getDuration());
        assertEquals("Shoulder press", testExercise.getName());
        assertEquals(20, testExercise.getWeight());
        assertEquals(3, testExercise.getSets());
        assertEquals(8, testExercise.getReps());
    }
    @Test
    void testConstructorNeg() {
        testExercise = new Exercise("Running", -1, -1, -1, -1);
        assertEquals(0, testExercise.getDuration());
        assertEquals("Running", testExercise.getName());
        assertEquals(0, testExercise.getWeight());
        assertEquals(0, testExercise.getSets());
        assertEquals(0, testExercise.getReps());
    }
    @Test
    void testConstructorPos() {
        testExercise = new Exercise("Running", 1, 1, 1, 1);
        assertEquals(1, testExercise.getDuration());
        assertEquals("Running", testExercise.getName());
        assertEquals(1, testExercise.getWeight());
        assertEquals(1, testExercise.getSets());
        assertEquals(1, testExercise.getReps());
    }
    @Test
    void testConstructorZero() {
        testExercise = new Exercise("Running", 0, 0, 0, 0);
        assertEquals(0, testExercise.getDuration());
        assertEquals("Running", testExercise.getName());
        assertEquals(0, testExercise.getWeight());
        assertEquals(0, testExercise.getSets());
        assertEquals(0, testExercise.getReps());
    }
    @Test
    void testGetters() {
        testExercise.getWeight();
        assertEquals(20, testExercise.getWeight());
        testExercise.getCals();
        assertEquals(16, testExercise.getCals());
        testExercise.getSets();
        assertEquals(3, testExercise.getSets());
        testExercise.getReps();
        assertEquals(8, testExercise.getReps());
        testExercise.getDuration();
        assertEquals(20, testExercise.getDuration());
    }
    @Test
    void testAddWeights() {
        testExercise.addWeight(10);
        assertEquals(30, testExercise.getWeight());
        testExercise.addWeight(0);
        assertEquals(30, testExercise.getWeight());
        testExercise.addWeight(-9);
        assertEquals(21, testExercise.getWeight());
    }

    @Test
    void testRemoveWeight() {
        testExercise.removeWeight(10);
        assertEquals(10, testExercise.getWeight());
        testExercise.removeWeight(10);
        assertEquals(0, testExercise.getWeight());
        testExercise.removeWeight(10);
        assertEquals(0, testExercise.getWeight());
        testExercise.addWeight(10);
        testExercise.removeWeight(1);
        assertEquals(9, testExercise.getWeight());

    }
    @Test
    void testRemoveRep() {
        testExercise.removeRep(1);
        assertEquals(7, testExercise.getReps());
        testExercise.removeRep(7);
        assertEquals(0, testExercise.getReps());
        testExercise.removeRep(3);
        assertEquals(0, testExercise.getReps());
        testExercise.addRep(3);
        testExercise.removeRep(2);
        assertEquals(1, testExercise.getReps());
    }
    @Test
    void testAddRep() {
        testExercise.addRep(1);
        assertEquals(9, testExercise.getReps());
        testExercise.addRep(0);
        assertEquals(9, testExercise.getReps());
        testExercise.addRep(10000);
        assertEquals(10009, testExercise.getReps());
        testExercise.addRep(-10000);
        assertEquals(9, testExercise.getReps());
    }
    @Test
    void testAddTime() {
        testExercise.addTime(1);
        assertEquals(21, testExercise.getDuration());
        testExercise.addTime(0);
        assertEquals(21, testExercise.getDuration());
        testExercise.addTime(10000);
        assertEquals(10021, testExercise.getDuration());
        testExercise.addTime(-10000);
        assertEquals(21, testExercise.getDuration());
    }
    @Test
    void testAddSets() {
        testExercise.addSets(1);
        assertEquals(4, testExercise.getSets());
        testExercise.addSets(0);
        assertEquals(4, testExercise.getSets());
        testExercise.addSets(10000);
        assertEquals(10004, testExercise.getSets());
        testExercise.addSets(-10000);
        assertEquals(4, testExercise.getSets());
    }
    @Test
    void testRemoveSets() {
        testExercise.removeSets(1);
        assertEquals(2, testExercise.getSets());
        testExercise.removeSets(0);
        assertEquals(2, testExercise.getSets());
        testExercise.removeSets(3);
        assertEquals(2, testExercise.getSets());
        testExercise.removeSets(2);
        assertEquals(0, testExercise.getSets());
        testExercise.removeSets(2);
        assertEquals(0, testExercise.getSets());
    }
    @Test
    void testCaloriesSquat() {
        Exercise squat = new Exercise("squat", 10, 10, 10, 10);
        assertEquals(10*10*0.02, squat.getCals());
        squat.addTime(10);
        assertEquals(20*10*0.02, squat.getCals());
        squat.addWeight(3);
        assertEquals(20*13*0.02, squat.getCals());
        squat.removeWeight(13);
        assertEquals(0, squat.getCals());
        squat.addWeight(3);
        squat.removeWeight(13);
        assertEquals(1.2, squat.getCals());
        assertEquals(16.0, testExercise.getCals());
        squat.addTime(-20);
        assertEquals(0, squat.getCals());
    }
    @Test
    void testCaloriesSquats() {
        Exercise squat = new Exercise("squats", 10, 10, 10, 10);
        assertEquals(10*10*0.02, squat.findCalories());
        squat.addTime(10);
        assertEquals(20*10*0.02, squat.getCals());
        squat.addWeight(3);
        assertEquals(20*13*0.02, squat.getCals());
        squat.removeWeight(13);
        assertEquals(0, squat.getCals());
        squat.addWeight(3);
        squat.removeWeight(13);
        assertEquals(1.2, squat.getCals());
        squat.addWeight(3);
        squat.removeWeight(13);
        assertEquals(2.4, squat.getCals());
        squat.addTime(-20);
        assertEquals(0, squat.getCals());
    }
    @Test
    void testCaloriesDeadlifts() {
        Exercise squat = new Exercise("deadlifts", 10, 10, 10, 10);
        assertEquals(10*10*0.09, squat.getCals());
        squat.addTime(10);
        assertEquals(20*10*0.09, squat.getCals());
        squat.addWeight(3);
        assertEquals(20*13*0.09, squat.getCals());
        squat.removeWeight(13);
        assertEquals(0, squat.getCals());
        squat.addWeight(3);
        squat.removeWeight(13);
        assertEquals(5.4, squat.getCals());
        squat.addTime(-20);
        assertEquals(0, squat.getCals());
    }
    @Test
    void testCaloriesDeadlift() {
        Exercise squat = new Exercise("deadlift", 10, 10, 10, 10);
        assertEquals(10*10*0.09, squat.getCals());
        squat.addTime(10);
        assertEquals(20*10*0.09, squat.getCals());
        squat.addWeight(3);
        assertEquals(20*13*0.09, squat.getCals());
        squat.removeWeight(13);
        assertEquals(0, squat.getCals());
        squat.addWeight(3);
        squat.removeWeight(13);
        assertEquals(5.4, squat.getCals());
        squat.addTime(-20);
        assertEquals(0, squat.getCals());
    }
    @Test
    void testCaloriesRun() {
        Exercise squat = new Exercise("run", 10, 10, 10, 10);
        assertEquals(10*3, squat.getCals());
        squat.addTime(10);
        assertEquals(20*3, squat.getCals());
        squat.addWeight(3);
        assertEquals(20*3, squat.getCals());
        squat.removeWeight(13);
        assertEquals(60, squat.getCals());
        squat.addWeight(3);
        squat.removeWeight(13);
        assertEquals(60, squat.getCals());
        squat.addWeight(3);
        squat.removeWeight(13);
        assertEquals(60, squat.getCals());
        squat.addTime(-20);
        assertEquals(0, squat.getCals());
    }
    @Test
    void testCaloriesRunning() {
        Exercise squat = new Exercise("running", 10, 10, 10, 10);
        assertEquals(10*6, squat.getCals());
        squat.addTime(10);
        assertEquals(20*6, squat.getCals());
        squat.addWeight(3);
        assertEquals(20*6, squat.getCals());
        squat.removeWeight(13);
        assertEquals(120, squat.getCals());
        squat.addWeight(3);
        squat.removeWeight(13);
        assertEquals(120, squat.getCals());
        squat.addWeight(3);
        squat.removeWeight(13);
        assertEquals(120, squat.getCals());
        squat.addTime(-20);
        assertEquals(0, squat.getCals());

    }
    @Test
    void testLatPulldown() {
        Exercise squat = new Exercise("lat pulldown", 10, 10, 10, 10);
        assertEquals(10*0.08 * 5, squat.getCals());
        squat.addTime(10);
        assertEquals(10*0.08 * 10, squat.getCals());
        squat.addWeight(3);
        assertEquals(10*0.08 * 13, squat.getCals());
        squat.removeWeight(13);
        assertEquals(0, squat.getCals());
        squat.addWeight(3);
        squat.removeWeight(13);
        assertEquals(2.4, squat.getCals());
        squat.addTime(-20);
        assertEquals(0, squat.getCals());
    }
    @Test
    void testLatPulldowns() {
        Exercise squat = new Exercise("lat pulldowns", 10, 10, 10, 10);
        assertEquals(10*0.08 * 5, squat.getCals());
        squat.addTime(10);
        assertEquals(10*0.08 * 10, squat.getCals());
        squat.addWeight(3);
        assertEquals(10*0.08 * 13, squat.getCals());
        squat.removeWeight(13);
        assertEquals(0, squat.getCals());
        squat.addWeight(3);
        squat.removeWeight(13);
        assertEquals(2.4, squat.getCals());
        squat.addTime(-20);
        assertEquals(0, squat.getCals());
    }
    @Test
    void testCaloriesShoulderPress() {
        Exercise squat = new Exercise("Shoulder Press", 10, 10, 10, 10);
        assertEquals(10*10*0.04, squat.getCals());
        squat.addTime(10);
        assertEquals(20*10*0.04, squat.getCals());
        squat.addWeight(3);
        assertEquals(20*13*0.04, squat.getCals());
        squat.removeWeight(13);
        assertEquals(0, squat.getCals());
        squat.addWeight(3);
        squat.removeWeight(13);
        assertEquals(2.4, squat.getCals());
        squat.addTime(-20);
        assertEquals(0, squat.getCals());
    }
    @Test
    void testCaloriesShoulderPresses() {
        Exercise squat = new Exercise("sHoulder Presses", 10, 10, 10, 10);
        assertEquals(10*10*0.04, squat.getCals());
        squat.addTime(10);
        assertEquals(20*10*0.04, squat.getCals());
        squat.addWeight(3);
        assertEquals(20*13*0.04, squat.getCals());
        squat.removeWeight(13);
        assertEquals(0, squat.getCals());
        squat.addWeight(3);
        squat.removeWeight(13);
        assertEquals(2.4, squat.getCals());
        squat.addTime(-20);
        assertEquals(0, squat.getCals());
    }
    @Test
    void testExerToString() {
        assertEquals("Shoulder press for 20min  with 20kg  for 3 sets  and 8 reps", testExercise.toString());
    }

    @Test
    public void testEqualsEquals() {
        Exercise exercise2 = new Exercise("Shoulder press", 20, 20, 3, 8);
        ExerciseList list2 = new ExerciseList("hello");
        list2.addExercise(testExercise);

        assertTrue(exercise2.equals(testExercise));
        assertEquals(exercise2.getName().equals(testExercise.getName()),
                exercise2.equals(testExercise));
        assertTrue(exercise2.getName().equals(testExercise.getName()));
        assertTrue(list2.equals(list2));
    }

    @Test
    public void testEqualsNotEquals() {
        Exercise exercise2 = new Exercise("jump", 1, 1, 1, 1);
        Exercise exercise3 = new Exercise("NOT EQUAL", 1, 1, 1, 1);

        ExerciseList container = new ExerciseList("1");
        ExerciseList container2 = new ExerciseList("2");
        container2.addExercise(exercise2);
        ExerciseList container3 = new ExerciseList("3");
        container3.addExercise(exercise2);
        ExerciseList container4 = new ExerciseList("4");
        container4.addExercise(exercise2);
        ExerciseList container5 = new ExerciseList("5");
        container5.addExercise(exercise3);
        assertFalse(exercise2.equals(testExercise));

        assertFalse(container.equals(container2));
        assertFalse(container.equals(container3));
        assertFalse(container.equals(container4));
        assertFalse(container.equals(container5));
        assertFalse(testExercise.equals(null));
        assertFalse(container.equals(null));
        assertFalse(container.equals("a different object"));
        assertFalse(testExercise.equals("a different object"));
        assertFalse(exercise2.getName().equals(testExercise.getName()));
        assertFalse(new Exercise("jumpp", 1, 1, 1, 1).equals(exercise2));
        assertFalse(new Exercise("jump", 2, 1, 1, 1).equals(exercise2));
        assertFalse(new Exercise("jump", 1, 2, 1, 1).equals(exercise2));
        assertFalse(new Exercise("jump", 1, 1, 2, 1).equals(exercise2));
        assertFalse(new Exercise("jump", 1, 1, 1, 2).equals(exercise2));


    }

    @Test
    public void testToString() {
        ExerciseList container = new ExerciseList("1");
        ExerciseList container2 = new ExerciseList("2");
        assertEquals("1", container.toString());
        assertEquals("2", container2.toString());

    }
}
/*



    // EFFECTS: converts exercise to string
    public String toString() {
        String s = "min " + " with " + weight;
        String s1 = " sets " + " and " + rep;
        return exerciseName + " for " + dur + s + "kg " + " for " + set + s1 + " reps";
    }

}
*/
