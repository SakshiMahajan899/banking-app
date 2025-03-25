package com.banking.technl.sandbox

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories


@SpringBootApplication(exclude = [MongoAutoConfiguration::class, MongoDataAutoConfiguration::class])
@EnableMongoRepositories(basePackages = ["com.banking.technl.sandbox.repository"])
@EnableCaching
class SandboxApplication

fun main(args: Array<String>) {
    runApplication<SandboxApplication>(*args)
}
