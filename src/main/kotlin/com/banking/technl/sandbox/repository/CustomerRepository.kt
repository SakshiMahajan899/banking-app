package com.banking.technl.sandbox.repository

import com.banking.technl.sandbox.model.Customer
import org.springframework.data.mongodb.repository.MongoRepository

/**
 * Repository for managing customer data.
 */
interface CustomerRepository : MongoRepository<Customer, String>
