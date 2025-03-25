package com.banking.technl.sandbox.repository

import com.banking.technl.sandbox.model.Customer
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest

@DataMongoTest
class CustomerRepositoryTest {

    @Autowired
    private lateinit var customerRepository: CustomerRepository

    @Test
    fun `test findById`() {
        // Arrange
        val customer = Customer(id = "123e4567-e89b-12d3-a456-426614174000", firstName = "John", lastName = "Doe")
        customerRepository.save(customer)

        // Act
        val retrievedCustomer = customerRepository.findById(customer.id).orElse(null)

        // Assert
        assertEquals("John", retrievedCustomer?.firstName)
        assertEquals("Doe", retrievedCustomer?.lastName)
    }
}
