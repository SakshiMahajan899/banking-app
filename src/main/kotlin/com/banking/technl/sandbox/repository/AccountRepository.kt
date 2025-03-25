package com.banking.technl.sandbox.repository

import com.banking.technl.sandbox.model.Account
import org.springframework.data.mongodb.repository.MongoRepository
import java.util.UUID

/**
 * Repository for managing accounts.
 */
interface AccountRepository : MongoRepository<Account, String> {
    fun findByCustomerID(customerID: String): List<Account>
}