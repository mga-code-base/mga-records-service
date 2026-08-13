package com.mygym.app.config;

import static com.mygym.app.util.RecordsConstants.APP_JSON;
import static com.mygym.app.util.RecordsConstants.CONTENT_TYPE;
import static com.mygym.app.util.RecordsConstants.DEV_RECORDS_BASE_URL;
import static com.mygym.app.util.RecordsConstants.DEV_WORKOUT_BASE_URL;
import static com.mygym.app.util.RecordsConstants.PRD_RECORDS_BASE_URL;
import static com.mygym.app.util.RecordsConstants.PRD_WORKOUT_BASE_URL;
import static com.mygym.app.util.RecordsConstants.RECORDS_CRUD_BEAN;
import static com.mygym.app.util.RecordsConstants.WORKOUT_CRUD_BEAN;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class ResourceConfig {

	    @Value("${app.environment}")
	    private String env;
    
    @Bean(name = RECORDS_CRUD_BEAN)
    public RestClient restRecordsCrudClient() {
    	String baseUrl = env.contentEquals("DEV") ? DEV_RECORDS_BASE_URL : PRD_RECORDS_BASE_URL;
        return RestClient.builder()
                .baseUrl(baseUrl)
                .defaultHeader(CONTENT_TYPE, APP_JSON)
                .build();
    }
    
    @Bean(name = WORKOUT_CRUD_BEAN)
    public RestClient restWorkoutCrudClient() {
    	String baseUrl = env.contentEquals("DEV") ? DEV_WORKOUT_BASE_URL : PRD_WORKOUT_BASE_URL;
        return RestClient.builder()
                .baseUrl(baseUrl)
                .defaultHeader(CONTENT_TYPE, APP_JSON)
                .build();
    }
}