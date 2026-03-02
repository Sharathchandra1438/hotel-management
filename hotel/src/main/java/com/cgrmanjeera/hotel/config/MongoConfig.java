package com.cgrmanjeera.hotel.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class MongoConfig {

    private static final String URI =
            "mongodb+srv://hoteluser:Sharath123@clustertest.v1shdwx.mongodb.net/cgrmanjeera?retryWrites=true&w=majority";

    @Bean
    public MongoClient mongoClient() {
        System.out.println("🔥 CONNECTING TO ATLAS...");
        return MongoClients.create(URI);
    }

    @Bean
    public MongoTemplate mongoTemplate(MongoClient mongoClient) {
        return new MongoTemplate(mongoClient, "cgrmanjeera");
    }
}
