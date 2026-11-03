package com.mygym.app.controller;

import com.mygym.app.models.TargetMuscleWrapper;
import com.mygym.app.service.WorkoutService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WorkoutControllerTest {

    @Mock
    private WorkoutService service;

    @InjectMocks
    private WorkoutController controller;

    @Test
    @DisplayName("Should fetch chest exercises via service layer")
    void testGetChestExercises_Success() {
        TargetMuscleWrapper wrapper = new TargetMuscleWrapper(List.of("Bench Press", "Incline Press"));
        when(service.getChestExercises()).thenReturn(wrapper);

        TargetMuscleWrapper result = controller.getChestExercises();

        assertNotNull(result);
        verify(service, times(1)).chestExercises();
    }

    @Test
    @DisplayName("Should add chest exercise via service layer")
    void testAddChestExercise_Success() {
        TargetMuscleWrapper wrapper = new TargetMuscleWrapper(List.of("Dips"));
        when(service.addChestExercise("Dips")).thenReturn(wrapper);

        TargetMuscleWrapper result = controller.addChestExercise("Dips");

        assertNotNull(result);
        verify(service, times(1)).addChestExercise("Dips");
    }

    @Test
    @DisplayName("Should fetch shoulder exercises via service layer")
    void testGetShoulderExercises_Success() {
        TargetMuscleWrapper wrapper = new TargetMuscleWrapper(List.of("Overhead Press"));
        when(service.getShoulderExercises()).thenReturn(wrapper);

        TargetMuscleWrapper result = controller.getShoulderExercises();

        assertNotNull(result);
        verify(service, times(1)).shoulderExercises();
    }

    @Test
    @DisplayName("Should add shoulder exercise via service layer")
    void testAddShoulderExercise_Success() {
        TargetMuscleWrapper wrapper = new TargetMuscleWrapper(List.of("Arnold Press"));
        when(service.addShoulderExercise("Arnold Press")).thenReturn(wrapper);

        TargetMuscleWrapper result = controller.addShoulderExercise("Arnold Press");

        assertNotNull(result);
        verify(service, times(1)).addShoulderExercise("Arnold Press");
    }

    @Test
    @DisplayName("Should fetch back exercises via service layer")
    void testGetBackExercises_Success() {
        TargetMuscleWrapper wrapper = new TargetMuscleWrapper(List.of("Lat Pulldown"));
        when(service.getBackExercises()).thenReturn(wrapper);

        TargetMuscleWrapper result = controller.getBackExercises();

        assertNotNull(result);
        verify(service, times(1)).backExercises();
    }

    @Test
    @DisplayName("Should add back exercise via service layer")
    void testAddBackExercise_Success() {
        TargetMuscleWrapper wrapper = new TargetMuscleWrapper(List.of("Deadlift"));
        when(service.addBackExercise("Deadlift")).thenReturn(wrapper);

        TargetMuscleWrapper result = controller.addBackExercise("Deadlift");

        assertNotNull(result);
        verify(service, times(1)).addBackExercise("Deadlift");
    }

    @Test
    @DisplayName("Should fetch biceps exercises via service layer")
    void testGetBicepsExercises_Success() {
        TargetMuscleWrapper wrapper = new TargetMuscleWrapper(List.of("Preacher Curl"));
        when(service.getBicepsExercises()).thenReturn(wrapper);

        TargetMuscleWrapper result = controller.getBicepsExercises();

        assertNotNull(result);
        verify(service, times(1)).bicepsExercises();
    }

    @Test
    @DisplayName("Should add biceps exercise via service layer")
    void testAddBicepsExercise_Success() {
        TargetMuscleWrapper wrapper = new TargetMuscleWrapper(List.of("Concentration Curl"));
        when(service.addBicepsExercise("Concentration Curl")).thenReturn(wrapper);

        TargetMuscleWrapper result = controller.addBicepsExercise("Concentration Curl");

        assertNotNull(result);
        verify(service, times(1)).addBicepsExercise("Concentration Curl");
    }

    @Test
    @DisplayName("Should fetch triceps exercises via service layer")
    void testGetTricepsExercises_Success() {
        TargetMuscleWrapper wrapper = new TargetMuscleWrapper(List.of("Skullcrusher"));
        when(service.getTricepsExercises()).thenReturn(wrapper);

        TargetMuscleWrapper result = controller.getTricepsExercises();

        assertNotNull(result);
        verify(service, times(1)).tricepsExercises();
    }

    @Test
    @DisplayName("Should add triceps exercise via service layer")
    void testAddTricepsExercise_Success() {
        TargetMuscleWrapper wrapper = new TargetMuscleWrapper(List.of("Rope Pushdown"));
        when(service.addTricepsExercise("Rope Pushdown")).thenReturn(wrapper);

        TargetMuscleWrapper result = controller.addTricepsExercise("Rope Pushdown");

        assertNotNull(result);
        verify(service, times(1)).addTricepsExercise("Rope Pushdown");
    }

    @Test
    @DisplayName("Should fetch legs exercises via service layer")
    void testGetLegsExercises_Success() {
        TargetMuscleWrapper wrapper = new TargetMuscleWrapper(List.of("Leg Press"));
        when(service.getLegsExercises()).thenReturn(wrapper);

        TargetMuscleWrapper result = controller.getLegsExercises();

        assertNotNull(result);
        verify(service, times(1)).legsExercises();
    }

    @Test
    @DisplayName("Should add legs exercise via service layer")
    void testAddLegsExercise_Success() {
        TargetMuscleWrapper wrapper = new TargetMuscleWrapper(List.of("Romanian Deadlift"));
        when(service.addLegsExercise("Romanian Deadlift")).thenReturn(wrapper);

        TargetMuscleWrapper result = controller.addLegsExercise("Romanian Deadlift");

        assertNotNull(result);
        verify(service, times(1)).addLegsExercise("Romanian Deadlift");
    }

    @Test
    @DisplayName("Should fetch abs exercises via service layer")
    void testGetAbsExercises_Success() {
        TargetMuscleWrapper wrapper = new TargetMuscleWrapper(List.of("Plank"));
        when(service.getAbsExercises()).thenReturn(wrapper);

        TargetMuscleWrapper result = controller.getAbsExercises();

        assertNotNull(result);
        verify(service, times(1)).absExercises();
    }

    @Test
    @DisplayName("Should add abs exercise via service layer")
    void testAddAbsExercise_Success() {
        TargetMuscleWrapper wrapper = new TargetMuscleWrapper(List.of("Russian Twist"));
        when(service.addAbsExercise("Russian Twist")).thenReturn(wrapper);

        TargetMuscleWrapper result = controller.addAbsExercise("Russian Twist");

        assertNotNull(result);
        verify(service, times(1)).addAbsExercise("Russian Twist");
    }

    @Test
    @DisplayName("Should fetch cardio exercises via service layer")
    void testGetCardioExercises_Success() {
        TargetMuscleWrapper wrapper = new TargetMuscleWrapper(List.of("Rowing Machine"));
        when(service.getCardioExercises()).thenReturn(wrapper);

        TargetMuscleWrapper result = controller.getCardioExercises();

        assertNotNull(result);
        verify(service, times(1)).cardioExercises();
    }

    @Test
    @DisplayName("Should add cardio exercise via service layer")
    void testAddCardioExercise_Success() {
        TargetMuscleWrapper wrapper = new TargetMuscleWrapper(List.of("Stairmaster"));
        when(service.addCardioExercise("Stairmaster")).thenReturn(wrapper);

        TargetMuscleWrapper result = controller.addCardioExercise("Stairmaster");

        assertNotNull(result);
        verify(service, times(1)).addCardioExercise("Stairmaster");
    }
}