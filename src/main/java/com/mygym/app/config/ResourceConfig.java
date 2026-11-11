package com.mygym.app.config;

import static com.mygym.app.util.RecordsConstants.RECORDS_CRUD_BEAN;
import static com.mygym.app.util.RecordsConstants.RECORDS_CRUD_PATH;
import static com.mygym.app.util.RecordsConstants.WORKOUT_CRUD_BEAN;
import static com.mygym.app.util.RecordsConstants.WORKOUT_CRUD_PATH;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

import com.mygym.app.client.DownstreamClients;

@Configuration
public class ResourceConfig {

    private static final String RECORDS_CRUD = "records-crud";

    private final DownstreamClients clients;
    private final String recordsCrudUrl;

    public ResourceConfig(DownstreamClients clients, @Value("${mga.clients.records-crud-url}") String recordsCrudUrl) {
        this.clients = clients;
        this.recordsCrudUrl = recordsCrudUrl;
    }

    @Bean(name = RECORDS_CRUD_BEAN)
    public RestClient restRecordsCrudClient() {
        return clients.create(RECORDS_CRUD, recordsCrudUrl + RECORDS_CRUD_PATH);
    }

    @Bean(name = WORKOUT_CRUD_BEAN)
    public RestClient restWorkoutCrudClient() {
        return clients.create(RECORDS_CRUD, recordsCrudUrl + WORKOUT_CRUD_PATH);
    }
}
