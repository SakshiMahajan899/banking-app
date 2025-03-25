package com.banking.technl.sandbox.model

import com.banking.technl.sandbox.util.TransactionStatus
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.math.BigDecimal
import java.time.LocalDateTime

/**
 * Entity representing a transaction.
 */
@Document(collection = "transactions")
data class Transaction(
    @Id
    val id: String? = null,  // MongoDB auto-generated ID
    val accountID: String,   // Reference to the account
    val amount: BigDecimal,  // Transaction amount
    val timestamp: LocalDateTime = LocalDateTime.now(), // Auto-generate timestamp
    val status: TransactionStatus = TransactionStatus.SUCCESS // Default status to SUCCESS
)
