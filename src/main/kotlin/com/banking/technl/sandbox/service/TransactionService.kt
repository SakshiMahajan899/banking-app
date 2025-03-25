package com.banking.technl.sandbox.service

import com.banking.technl.sandbox.exception.InvalidTransactionException
import com.banking.technl.sandbox.model.Transaction
import com.banking.technl.sandbox.repository.TransactionRepository
import com.banking.technl.sandbox.util.TransactionStatus
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.math.BigDecimal

/**
 * Service for handling transaction-related operations.
 */
@Service
class TransactionService(
    private val transactionRepository: TransactionRepository
) {
    private val logger: Logger = LoggerFactory.getLogger(TransactionService::class.java)

    /**
     * Creates a new transaction.
     * @param accountID ID of the account
     * @param amount Transaction amount
     * @return The created transaction
     * @throws InvalidTransactionException if the transaction amount is invalid
     */
    fun createTransaction(accountID: String, amount: BigDecimal): Transaction {
        if (amount <= BigDecimal.ZERO) {
            throw InvalidTransactionException()
        }

        logger.info("Creating transaction for account ID: $accountID with amount: $amount")

        val transaction = Transaction(
            accountID = accountID,
            amount = amount,
            status = TransactionStatus.SUCCESS
        )

        return transactionRepository.save(transaction).also {
            logger.info("Transaction successfully created: $it")
        }
    }

    /**
     * Retrieves transactions for the given account ID.
     * @param accountID ID of the account
     * @return List of transactions
     */
    fun findByAccountID(accountID: String): List<Transaction> {
        logger.info("Fetching transactions for account ID: $accountID")
        return transactionRepository.findByAccountID(accountID)
    }
}
