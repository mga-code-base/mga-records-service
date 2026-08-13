package com.mygym.app.controller;

import com.mygym.app.models.MyRecord;
import com.mygym.app.models.MyRecordsWrapper;
import com.mygym.app.models.ProgressRecords;
import com.mygym.app.service.MyRecordService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MyRecordControllerTest {

    @Mock
    private MyRecordService service;

    @InjectMocks
    private MyRecordController controller;

    @Test
    @DisplayName("Should create workout record via service layer")
    void testCreate_Success() {
        MyRecord record = new MyRecord();
        when(service.createRecord(any(MyRecord.class))).thenReturn(record);

        MyRecord result = controller.create(record);

        assertNotNull(result);
        verify(service, times(1)).createRecord(record);
    }

    @Test
    @DisplayName("Should read record by ID via service layer")
    void testReadById_Success() {
        MyRecord record = new MyRecord();
        when(service.getRecordById("REC-101")).thenReturn(record);

        MyRecord result = controller.readById("REC-101");

        assertNotNull(result);
        verify(service, times(1)).getRecordById("REC-101");
    }

    @Test
    @DisplayName("Should delete record by ID via service layer")
    void testDelete_Success() {
        when(service.deleteRecord("REC-101")).thenReturn("OK");

        String result = controller.delete("REC-101");

        assertNotNull(result);
        verify(service, times(1)).deleteRecord("REC-101");
    }

    @Test
    @DisplayName("Should get previous records via service layer")
    void testGetPreviousRecords_Success() {
        MyRecordsWrapper wrapper = new MyRecordsWrapper(List.of(new MyRecord()));
        when(service.getPreviousRecords("M-101", "Chest", "Bench Press")).thenReturn(wrapper);

        MyRecordsWrapper result = controller.getPreviousRecords("M-101", "Chest", "Bench Press");

        assertNotNull(result);
        verify(service, times(1)).getPreviousRecords("M-101", "Chest", "Bench Press");
    }

    @Test
    @DisplayName("Should get progress records via service layer")
    void testGetProgressRecords_Success() {
        ProgressRecords progress = new ProgressRecords();
        when(service.getProgressRecords("M-101")).thenReturn(progress);

        ProgressRecords result = controller.getPreviousRecords("M-101");

        assertNotNull(result);
        verify(service, times(1)).getProgressRecords("M-101");
    }
}