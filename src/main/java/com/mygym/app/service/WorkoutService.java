package com.mygym.app.service;

import static com.mygym.app.util.RecordsConstants.WORKOUT_CRUD_BEAN;
import static com.mygym.app.util.RecordsConstants.WORKOUT_CHEST_ENPOINT;
import static com.mygym.app.util.RecordsConstants.WORKOUT_CHEST_PATH;
import static com.mygym.app.util.RecordsConstants.WORKOUT_SHOULDER_ENPOINT;
import static com.mygym.app.util.RecordsConstants.WORKOUT_SHOULDER_PATH;
import static com.mygym.app.util.RecordsConstants.WORKOUT_BACK_ENPOINT;
import static com.mygym.app.util.RecordsConstants.WORKOUT_BACK_PATH;
import static com.mygym.app.util.RecordsConstants.WORKOUT_BICEPS_ENPOINT;
import static com.mygym.app.util.RecordsConstants.WORKOUT_BICEPS_PATH;
import static com.mygym.app.util.RecordsConstants.WORKOUT_TRICEPS_ENPOINT;
import static com.mygym.app.util.RecordsConstants.WORKOUT_TRICEPS_PATH;
import static com.mygym.app.util.RecordsConstants.WORKOUT_LEGS_PATH;
import static com.mygym.app.util.RecordsConstants.WORKOUT_LEGS_ENPOINT;
import static com.mygym.app.util.RecordsConstants.WORKOUT_ABS_ENPOINT;
import static com.mygym.app.util.RecordsConstants.WORKOUT_ABS_PATH;
import static com.mygym.app.util.RecordsConstants.WORKOUT_CARDIO_PATH;
import static com.mygym.app.util.RecordsConstants.WORKOUT_CARDIO_ENPOINT;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.mygym.app.models.TargetMuscleWrapper;

/**
 * Service routing exercise selection and additions to downstream records CRUD storage.
 */
@Service
public class WorkoutService {

    private RestClient restClient;

    public WorkoutService(@Qualifier(WORKOUT_CRUD_BEAN) RestClient restClient) {
        this.restClient = restClient;
    }

    public TargetMuscleWrapper getChestExercises() {
        return restClient.get()
                .uri(WORKOUT_CHEST_ENPOINT)
                .retrieve()
                .body(TargetMuscleWrapper.class);
    }

    public TargetMuscleWrapper addChestExercise(String excersiceName) {
        return restClient.put()
                .uri(WORKOUT_CHEST_PATH + excersiceName)
                .retrieve()
                .body(TargetMuscleWrapper.class);
    }

    public TargetMuscleWrapper getShoulderExercises() {
        return restClient.get()
                .uri(WORKOUT_SHOULDER_ENPOINT)
                .retrieve()
                .body(TargetMuscleWrapper.class);
    }

    public TargetMuscleWrapper addShoulderExercise(String excersiceName) {
        return restClient.put()
                .uri(WORKOUT_SHOULDER_PATH + excersiceName)
                .retrieve()
                .body(TargetMuscleWrapper.class);
    }

    public TargetMuscleWrapper getBackExercises() {
        return restClient.get()
                .uri(WORKOUT_BACK_ENPOINT)
                .retrieve()
                .body(TargetMuscleWrapper.class);
    }

    public TargetMuscleWrapper addBackExercise(String excersiceName) {
        return restClient.put()
                .uri(WORKOUT_BACK_PATH + excersiceName)
                .retrieve()
                .body(TargetMuscleWrapper.class);
    }

    public TargetMuscleWrapper getBicepsExercises() {
        return restClient.get()
                .uri(WORKOUT_BICEPS_ENPOINT)
                .retrieve()
                .body(TargetMuscleWrapper.class);
    }

    public TargetMuscleWrapper addBicepsExercise(String excersiceName) {
        return restClient.put()
                .uri(WORKOUT_BICEPS_PATH + excersiceName)
                .retrieve()
                .body(TargetMuscleWrapper.class);
    }

    public TargetMuscleWrapper getTricepsExercises() {
        return restClient.get()
                .uri(WORKOUT_TRICEPS_ENPOINT)
                .retrieve()
                .body(TargetMuscleWrapper.class);
    }

    public TargetMuscleWrapper addTricepsExercise(String excersiceName) {
        return restClient.put()
                .uri(WORKOUT_TRICEPS_PATH + excersiceName)
                .retrieve()
                .body(TargetMuscleWrapper.class);
    }

    public TargetMuscleWrapper getAbsExercises() {
        return restClient.get()
                .uri(WORKOUT_ABS_ENPOINT)
                .retrieve()
                .body(TargetMuscleWrapper.class);
    }

    public TargetMuscleWrapper addAbsExercise(String excersiceName) {
        return restClient.put()
                .uri(WORKOUT_ABS_PATH + excersiceName)
                .retrieve()
                .body(TargetMuscleWrapper.class);
    }

    public TargetMuscleWrapper getCardioExercises() {
        return restClient.get()
                .uri(WORKOUT_CARDIO_ENPOINT)
                .retrieve()
                .body(TargetMuscleWrapper.class);
    }

    public TargetMuscleWrapper addCardioExercise(String excersiceName) {
        return restClient.put()
                .uri(WORKOUT_CARDIO_PATH + excersiceName)
                .retrieve()
                .body(TargetMuscleWrapper.class);
    }

    public TargetMuscleWrapper getLegsExercises() {
        return restClient.get()
                .uri(WORKOUT_LEGS_ENPOINT)
                .retrieve()
                .body(TargetMuscleWrapper.class);
    }

    public TargetMuscleWrapper addLegsExercise(String excersiceName) {
        return restClient.put()
                .uri(WORKOUT_LEGS_PATH + excersiceName)
                .retrieve()
                .body(TargetMuscleWrapper.class);
    }
}