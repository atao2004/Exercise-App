# GetFit

## A Fitness Tracker

**What the Application Does**

This application lets users track their daily physical activity. Users can store their exercises and or routines that they perform frequently. The application enables users to track the duration and other metrics associated with each exercise. The application keeps a *history* of exercises performed each day so users can refer back to exercises performed in the past. 

**Target Demographics**

This application is targeted towards individuals wishing to *improve* or *maintain* their fitness. The application allows users to be accountable for their fitness and have a better understanding of their physical abilities. Individuals who are looking to become *more active, set goals, or simply maintain* their strength and physique can use this application.

**Reason of Interest in This Project**

I am an individual that strongly believes in the power of health and fitness. I believe that in our modern world it has become hard to be as physically active as optimal. I personally appreciate fitness tracker applications that provide clear metrics around fitness. Through my personal familiarity with fitness trackers, I wish to create my own software system.

**Features of this application:**

- Track various exercises performed
- Track calories burned
- Track duration of exercise 
- Track total time of physical activity
- Track weight lifted for strength exercises
- Display current and past days of exercise
- Add previously performed exercise

## User Stories
- As a user, I want to be able to add an exercise to my daily exercise journal
- As a user, I want to be able to view a list of all my physical activity from the day
- As a user, I want to be able to view a summary of my total calories burned from the day
- As a user, I want to be able to view my total minutes exercised from the day
- As a user, I want to be able to modify my last exercise entry
- As a user, I want to be able to remove my exercise routines from my journal
- As a user, I want to be able to save my exercise routines to file
- As a user, I want to be able to load my exercise routines from file

# Instructions for Grader

- You can generate the first required action related to adding an exercise to a workout by using the SideMenu to 
click on "My Workout Program" and then clicking the "Add Exercise Title" button on the left
- - You can generate the second required action related to adding exercise details to the workout by selecting the "Add Exercise Statistics" button below each exercise name
- You can generate the third action related to removing exercises from a workout by clicking the "Delete Exercise" button right below each exercise
- You can locate my visual component by running the application, and clicking on the "Welcome Page"
- You can save the state of my application by exiting the application and selecting the "Save Data" button, or selecting the "Save And Quit" button on the Program Page
- You can reload the state of my application by opening the application and selecting the "load previous entries" button

# Phase 4: Task 2, EventLog:
- Wed Aug 09 14:30:03 PDT 2023
- Added exercise: Squat
- Wed Aug 09 14:30:16 PDT 2023
- Added exercise statistics: squat for 30min  with 30kg  for 3 sets  and 8 reps
- Wed Aug 09 14:30:24 PDT 2023
- Added exercise: Shoulder Press
- Wed Aug 09 14:30:35 PDT 2023
- Added exercise statistics: shoulder press for 10min  with 30kg  for 3 sets  and 8 reps
- Wed Aug 09 14:30:58 PDT 2023
- Added exercise: Dumbell
- Wed Aug 09 14:31:10 PDT 2023
- Added exercise statistics: dumbell for 30min  with 40kg  for 3 sets  and 8 reps
- Wed Aug 09 14:31:11 PDT 2023
- Removed exercise title: Dumbell
- Wed Aug 09 14:31:11 PDT 2023
- Removed exercise title: Dumbell
- Wed Aug 09 14:31:13 PDT 2023
- Removed exercise title: Shoulder Press
- Wed Aug 09 14:31:13 PDT 2023
- Removed exercise title: Shoulder Press

# Phase 4: Task 3:
Improvements of design for the future:

Follow the composite design pattern in my model 
- I see that my WorkoutProgram class has a list of ExerciseList which has a List of Exercises.
I would have my ExerciseList be the component, the WorkoutProgram be the composite, and Exercise be the leaf.
- Get rid of the association arrow from my WorkoutProgram class to Exercise by creating a method in ExerciseList to get a list of exercises. There is no reason to have that extra association.

Refactoring:
- Refactor my GUI into many more classes to increase cohesion and follow the single responsibility principle. For example, 
I would refactor highly repetitive code in the methods of the child classes to an abstract class. 
- I could better organize my panels and various visual components into their own classes to follow the single responsibility principle and increase cohesion. 

