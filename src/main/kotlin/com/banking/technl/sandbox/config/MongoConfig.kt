package com.banking.technl.sandbox.config

import com.mongodb.MongoClientSettings
import com.mongodb.client.MongoClient
import com.mongodb.client.MongoClients
import org.bson.UuidRepresentation
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.core.MongoTemplate


@Configuration
class MongoConfig {


    @Bean
    fun mongoClient(): MongoClient {
        val settings = MongoClientSettings.builder()
                .applyConnectionString(com.mongodb.ConnectionString("mongodb://mongo:27017"))
                .uuidRepresentation(UuidRepresentation.STANDARD)
                .build()
        return MongoClients.create(settings)

    }
    @Bean
    fun mongoTemplate(): MongoTemplate {
        return MongoTemplate(mongoClient(), "banking-db")
    }
}