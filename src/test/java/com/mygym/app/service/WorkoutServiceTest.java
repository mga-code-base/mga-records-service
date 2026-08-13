package com.mygym.app.service;

import com.mygym.app.models.TargetMuscleWrapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestClient;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WorkoutServiceTest {

    @Mock
    private RestClient restClient;

    @Mock
    private RestClient.RequestHeadersUriSpec requestHeadersUriSpec;

    @Mock
    private RestClient.RequestBodyUriSpec requestBodyUriSpec;

    @Mock
    private RestClient.RequestHeadersSpec requestHeadersSpec;

    @Mock
    private RestClient.ResponseSpec responseSpec;

    @InjectMocks
    private WorkoutService workoutService;

    @Test
    @DisplayName("Should fetch chest exercises downstream")
    void testGetChestExercises_Success() {
        TargetMuscleWrapper wrapper = new TargetMuscleWrapper(List.of("Bench Press"));
        when(restClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri(eq("/chest"))).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.body(TargetMuscleWrapper.class)).thenReturn(wrapper);

        TargetMuscleWrapper result = workoutService.getChestExercises();

        assertNotNull(result);
    }

    @Test
    @DisplayName("Should add chest exercise downstream")
    void testAddChestExercise_Success() {
        TargetMuscleWrapper wrapper = new TargetMuscleWrapper(List.of("Incline Fly"));
        when(restClient.put()).thenReturn(requestBodyUriSpec);
        when(requestBodyUriSpec.uri(eq("/chest/Incline Fly"))).thenReturn(requestBodyUriSpec);
        when(requestBodyUriSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.body(TargetMuscleWrapper.class)).thenReturn(wrapper);

        TargetMuscleWrapper result = workoutService.addChestExercise("Incline Fly");

        assertNotNull(result);
    }
}