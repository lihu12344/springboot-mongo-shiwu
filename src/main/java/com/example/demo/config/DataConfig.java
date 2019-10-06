package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.MongoTransactionManager;

@Configuration
public class DataConfig {

    @Bean
    public MongoTransactionManager initMongoTransactionManager(MongoDbFactory factory){
        return new MongoTransactionManager(factory);
    }
}