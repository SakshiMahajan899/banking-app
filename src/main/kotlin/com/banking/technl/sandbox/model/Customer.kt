package com.banking.technl.sandbox.model


import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import java.util.UUID

/**
 * Represents a customer in the banking system.
 */
@Document(collection = "customers")
data class Customer(
    @Id val id: String,           // Unique customer ID
    @Field("first_name") val firstName: String,
    @Field("last_name") val lastName: String
)
