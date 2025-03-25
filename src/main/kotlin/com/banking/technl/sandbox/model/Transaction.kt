package com.banking.technl.sandbox.model


import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.math.BigDecimal
import java.time.LocalDateTime

/**
 * Represents a transaction associated with an account.
 */

@Document(collection = "transactions")
data class Transaction(
    @Id val id: String? = null,   // Unique transaction ID
    val accountID: String,        // ID of the account the transaction belongs to
    val amount: BigDecimal,       // Transaction amount
    val timestamp: LocalDateTime = LocalDateTime.now() // Transaction timestamp
)
