package model;

import eventlog.Event;
import eventlog.EventLog;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static java.awt.AWTEventMulticaster.add;

// Represents a weight-training/fitness program
// referenced JsonSerializationDemo (https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git)
public class WorkoutProgram implements Writable {
    private String name;
    private List<Exercise> currentSession;
    protected List<ExerciseList> pastSessions;

    // MODIFIES: this
    // EFFECTS: create a program object with empty list of sessions (List of ExerciseList)
    public WorkoutProgram(String name) {
        this.name = name;
        this.pastSessions = new ArrayList<>();
        this.currentSession = new ArrayList<>();
    }

    // REQUIRES: session (ExerciseList) is in sessions (List of ExerciseList)
    // MODIFIES: this
    // EFFECTS: adds session to sessions
    //          adds eventlog to this action
    public void addSession(ExerciseList session) {
        pastSessions.add(session);
        EventLog.getInstance().logEvent(new Event("Added exercise: " + session));
    }

    // MODIFIES: this
    // EFFECTS: adds exercise to this workroom
   // public void addExercise(Exercise e) {
    //    currentSession.add(e);
   // }

    // REQUIRES: session (ExerciseList) is in sessions (List of ExerciseLists)
    // MODIFIES: this
    // EFFECTS: removes session from sessions
    //          adds eventlog to this action
    public void removeSession(ExerciseList session) {
        EventLog.getInstance().logEvent(new Event("Removed exercise title: " + session));
        pastSessions.remove(session);
    }

    // REQUIRES: i is in range of sessions length
    // MODIFIES: this
    // EFFECTS: removes ith session from sessions
    //          adds eventlog to this action
    public void removeSession(int i) {
        ExerciseList session = pastSessions.get(i);
        pastSessions.remove(i);
        EventLog.getInstance().logEvent(new Event("Removed exercise title: " + session));
    }

    public List<ExerciseList> getPastSessions() {
        return Collections.unmodifiableList(pastSessions);
    }

    public List<Exercise> getCurSession() {
        return Collections.unmodifiableList(currentSession);
    }

    public ExerciseList getExercisesAtIndex(int i) {
        return pastSessions.get(i);
    }

    // EFFECTS: returns an optional Session
    public Optional<ExerciseList> getSessionByName(String name) {
        Optional<ExerciseList> session = Optional.empty();
        for (ExerciseList s : pastSessions) {
            if (s.getName().equals(name)) {
                session = Optional.of(s);
            }
        }
        return session;
    }

    // EFFECTS: returns size of sessions;
    public int numberOfSessions() {
        return pastSessions.size();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // EFFECTS: puts to Json
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", "My Workout");
        json.put("sessions", sessionsToJson());
        return json;
    }

    // EFFECTS: returns Json representation of sessions
    private JSONArray sessionsToJson() {
        JSONArray jsonArray = new JSONArray();
        for (ExerciseList s : pastSessions) {
            jsonArray.put(s.toJson());
        }
        return jsonArray;
    }
}

