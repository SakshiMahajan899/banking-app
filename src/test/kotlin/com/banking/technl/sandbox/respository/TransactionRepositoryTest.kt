package com.banking.technl.sandbox.repository

import com.banking.technl.sandbox.model.Transaction
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest
import java.math.BigDecimal

@DataMongoTest
class TransactionRepositoryTest {

    @Autowired
    private lateinit var transactionRepository: TransactionRepository

    @Test
    fun `test findByAccountID`() {
        // Arrange
        val accountID = "223e4567-e89b-12d3-a456-426614174000"
        val transaction1 = Transaction(id = "transaction1", accountID = accountID, amount = BigDecimal("100"))
        val transaction2 = Transaction(id = "transaction2", accountID = accountID, amount = BigDecimal("200"))
        transactionRepository.save(transaction1)
        transactionRepository.save(transaction2)

        // Act
        val transactions = transactionRepository.findByAccountID(accountID)

        // Assert
        assertEquals(2, transactions.size)
        assertEquals("transaction1", transactions[0].id)
        assertEquals("transaction2", transactions[1].id)
    }
}
