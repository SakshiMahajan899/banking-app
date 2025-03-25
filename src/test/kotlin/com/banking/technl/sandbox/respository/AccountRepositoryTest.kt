package com.banking.technl.sandbox.repository

import com.banking.technl.sandbox.model.Account
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest
import java.math.BigDecimal

@DataMongoTest
class AccountRepositoryTest {

    @Autowired
    private lateinit var accountRepository: AccountRepository

    @Test
    fun `test findByCustomerID`() {
        // Arrange
        val customerID = "123e4567-e89b-12d3-a456-426614174000"
        val account1 = Account(id = "account1", customerID = customerID, balance = BigDecimal("1000"))
        val account2 = Account(id = "account2", customerID = customerID, balance = BigDecimal("2000"))
        accountRepository.save(account1)
        accountRepository.save(account2)

        // Act
        val accounts = accountRepository.findByCustomerID(customerID)

        // Assert
        assertEquals(2, accounts.size)
        assertEquals("account1", accounts[0].id)
        assertEquals("account2", accounts[1].id)
    }
}
