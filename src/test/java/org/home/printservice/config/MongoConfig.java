package org.home.printservice.config;

import org.home.printservice.repository.PrintJobRepository;
import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.net.UnknownHostException;

@TestConfiguration
public class MongoConfig {


    @Bean
    public MongoTemplate mongoTemplate() throws UnknownHostException {
        MongoTemplate mock = Mockito.mock(MongoTemplate.class);
        return mock;
    }

    @Primary
    @Bean
    public PrintJobRepository printJobRepository(){
        return Mockito.mock(PrintJobRepository.class);
    }
}