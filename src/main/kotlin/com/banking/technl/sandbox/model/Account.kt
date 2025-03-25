package com.banking.technl.sandbox.model


import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.math.BigDecimal
import java.util.UUID

/**
 * Represents a bank account.
 */
@Document(collection = "accounts")
data class Account(
    @Id val id: String? = null,   // Unique account ID
    val customerID: String,       // ID of the linked customer
    var balance: BigDecimal = BigDecimal.ZERO // Account balance
)
