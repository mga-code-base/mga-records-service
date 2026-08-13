package com.mygym.app.service;

import com.mygym.app.models.MyRecord;
import com.mygym.app.models.MyRecordsWrapper;
import com.mygym.app.models.ProgressRecords;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestClient;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MyRecordServiceTest {

    @Mock
    private RestClient restClient;

    @Mock
    private RestClient.RequestHeadersUriSpec requestHeadersUriSpec;

    @Mock
    private RestClient.RequestBodyUriSpec requestBodyUriSpec;

    @Mock
    private RestClient.RequestBodySpec requestBodySpec;

    @Mock
    private RestClient.RequestHeadersSpec requestHeadersSpec;

    @Mock
    private RestClient.ResponseSpec responseSpec;

    @InjectMocks
    private MyRecordService myRecordService;

    @Test
    @DisplayName("Should create record via RestClient post request")
    void testCreateRecord_Success() {
        MyRecord record = new MyRecord();
        when(restClient.post()).thenReturn(requestBodyUriSpec);
        when(requestBodyUriSpec.body(eq(record))).thenReturn(requestBodySpec);
        when(requestBodySpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.body(MyRecord.class)).thenReturn(record);

        MyRecord result = myRecordService.createRecord(record);

        assertNotNull(result);
    }

    @Test
    @DisplayName("Should fetch record by ID via RestClient get request")
    void testGetRecordById_Success() {
        MyRecord record = new MyRecord();
        when(restClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri(eq("/{id}"), eq("REC-101"))).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.body(MyRecord.class)).thenReturn(record);

        MyRecord result = myRecordService.getRecordById("REC-101");

        assertNotNull(result);
    }

    @Test
    @DisplayName("Should aggregate 30-day progress records grouped by muscle group")
    void testGetProgressRecords_Success() {
        MyRecord record1 = new MyRecord();
        record1.setTargetMuscle("Chest");
        record1.setDate(LocalDate.now().toString());

        MyRecordsWrapper wrapper = new MyRecordsWrapper(List.of(record1));

        when(restClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri(any(java.util.function.Function.class))).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.body(MyRecordsWrapper.class)).thenReturn(wrapper);

        ProgressRecords progress = myRecordService.getProgressRecords("M-101");

        assertNotNull(progress);
        assertEquals(1, progress.getChestRecords().size());
    }
}