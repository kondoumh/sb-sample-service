package com.kondoumh.sbsampleservice.config;

import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;

@Configuration
public class MongoConfig extends AbstractMongoConfiguration {

    @Value("${spring.data.mongodb.host}")
    private String host;

    @Value("${spring.data.mongodb.port}")
    private int port;

    @Value("${spring.data.mongodb.database}")
    private String database;

    @Override
    public MongoClient mongoClient() {
        return new MongoClient(new ServerAddress(host, port));
    }

    @Override
    protected String getDatabaseName() {
        return database;
    }
}
