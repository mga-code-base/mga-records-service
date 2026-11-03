package com.mygym.app.service;

import static com.mygym.app.util.RecordsConstants.ID_ENDPOINT;
import static com.mygym.app.util.RecordsConstants.RECORDS_CRUD_BEAN;
import static com.mygym.app.util.RecordsConstants.PREVIOUS_ENDOINT;
import static com.mygym.app.util.RecordsConstants.PROGRESS_ENDPOINT;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.mygym.app.models.MyRecord;
import com.mygym.app.models.MyRecordsWrapper;
import com.mygym.app.models.ProgressRecords;

@Service
public class MyRecordService {

    private RestClient restClient;

    public MyRecordService(@Qualifier(RECORDS_CRUD_BEAN) RestClient restClient) {
        this.restClient = restClient;
    }

    public MyRecord createRecord(MyRecord record) {
        return restClient.post()
                .body(record)
                .retrieve()
                .body(MyRecord.class);
    }

    public MyRecord getRecordById(String id) {
        return restClient.get()
                .uri(ID_ENDPOINT, id)
                .retrieve()
                .body(MyRecord.class);
    }

    public MyRecordsWrapper getPreviousRecords(String memberId, String targetMuscle, String exerciseName) {
        return restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(PREVIOUS_ENDOINT)
                        .queryParam("memberId", memberId)
                        .queryParam("targetMuscle", targetMuscle)
                        .queryParam("exerciseName", exerciseName)
                        .build())
                .retrieve()
                .body(MyRecordsWrapper.class);
    }

    public String deleteRecord(String id) {
        return restClient.delete()
                .uri(ID_ENDPOINT, id)
                .retrieve()
                .body(String.class);
    }

    public ProgressRecords getProgressRecords(String memberId) {
        MyRecordsWrapper wrapper = restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(PROGRESS_ENDPOINT)
                        .queryParam("memberId", memberId)
                        .build())
                .retrieve()
                .body(MyRecordsWrapper.class);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        if (Objects.nonNull(wrapper) && Objects.nonNull(wrapper.allMyRecords())) {
            LocalDate thirtyDaysAgo = LocalDate.now().minusDays(30);

            Map<String, List<MyRecord>> top3ByMuscle = wrapper.allMyRecords().stream()
                    .filter(r -> r.targetMuscle() != null && r.date() != null)
                    .filter(r -> {
                        try {
                            LocalDate recordDate = LocalDate.parse(r.date(), formatter);
                            return !recordDate.isBefore(thirtyDaysAgo);
                        } catch (Exception e) {
                            return false;
                        }
                    })
                    .collect(Collectors.groupingBy(
                            record -> record.targetMuscle().trim().toLowerCase(),
                            Collectors.collectingAndThen(
                                    Collectors.toList(),
                                    list -> list.stream()
                                            .sorted((r1, r2) -> {
                                                LocalDate d1 = LocalDate.parse(r1.date(), formatter);
                                                LocalDate d2 = LocalDate.parse(r2.date(), formatter);
                                                return d2.compareTo(d1);
                                            })
                                            .collect(Collectors.toList())
                            )
                    ));

            ProgressRecords progressReport = new ProgressRecords(top3ByMuscle.getOrDefault("chest", Collections.emptyList()), top3ByMuscle.getOrDefault("shoulder", Collections.emptyList()), top3ByMuscle.getOrDefault("back", Collections.emptyList()), top3ByMuscle.getOrDefault("bicep", top3ByMuscle.getOrDefault("biceps", Collections.emptyList())), top3ByMuscle.getOrDefault("tricep", top3ByMuscle.getOrDefault("triceps", Collections.emptyList())), top3ByMuscle.getOrDefault("legs", top3ByMuscle.getOrDefault("leg", Collections.emptyList())), top3ByMuscle.getOrDefault("abs", Collections.emptyList()), top3ByMuscle.getOrDefault("cardio", Collections.emptyList()));

            return progressReport;
        }
        return new ProgressRecords(null, null, null, null, null, null, null, null);
    }
}