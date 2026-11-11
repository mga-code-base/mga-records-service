package com.mygym.app.controller;

import static com.mygym.app.util.RecordsConstants.ID_ENDPOINT;
import static com.mygym.app.util.RecordsConstants.PREVIOUS_ENDOINT;
import static com.mygym.app.util.RecordsConstants.PROGRESS_ENDPOINT;
import static com.mygym.app.util.RecordsConstants.RECORDS_REST_MAPPING;

import jakarta.validation.groups.Default;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mygym.app.models.MyRecord;
import com.mygym.app.models.MyRecordsWrapper;
import com.mygym.app.models.ProgressRecords;
import com.mygym.app.service.MyRecordService;
import com.mygym.app.validation.OnCreate;

@RestController
@RequestMapping(RECORDS_REST_MAPPING)
public class MyRecordController {

    private final MyRecordService service;

    public MyRecordController(MyRecordService service) {
        this.service = service;
    }

    @PostMapping
    public MyRecord create(@Validated({Default.class, OnCreate.class}) @RequestBody MyRecord record) {
        return service.createRecord(record);
    }

    @GetMapping(ID_ENDPOINT)
    public MyRecord readById(@PathVariable String id) {
        return service.getRecordById(id);
    }

    @DeleteMapping(ID_ENDPOINT)
    public String delete(@PathVariable String id) {
        service.deleteRecord(id);
        return "Gym record deleted successfully with id: " + id;
    }
    
    @GetMapping(PREVIOUS_ENDOINT)
    public MyRecordsWrapper getPreviousRecords(@RequestParam("memberId") String memberId, 
    		@RequestParam("targetMuscle") String targetMuscle, 
    		@RequestParam("exerciseName") String exerciseName) {
    	return service.getPreviousRecords(memberId, targetMuscle, exerciseName);
    }
    
    @GetMapping(PROGRESS_ENDPOINT)
    public ProgressRecords getPreviousRecords(@RequestParam("memberId") String memberId) {
    	return service.getProgressRecords(memberId);
    }
}
