package com.banking.technl.sandbox.repository

import com.banking.technl.sandbox.model.Transaction
import org.springframework.data.mongodb.repository.MongoRepository

/**
 * Repository for managing transactions.
 */
interface TransactionRepository : MongoRepository<Transaction, String> {
    fun findByAccountID(accountID: String): List<Transaction>
}