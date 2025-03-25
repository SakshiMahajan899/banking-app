package com.banking.technl.sandbox.service

import com.banking.technl.sandbox.model.Account
import com.banking.technl.sandbox.model.Transaction
import com.banking.technl.sandbox.repository.AccountRepository
import com.banking.technl.sandbox.repository.CustomerRepository
import com.banking.technl.sandbox.repository.TransactionRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.util.UUID

/**
 * Service handling banking operations.
 */
@Service
class BankingService(
    private val customerRepository: CustomerRepository,
    private val accountRepository: AccountRepository,
    private val transactionRepository: TransactionRepository
) {
    private val logger: Logger = LoggerFactory.getLogger(BankingService::class.java)

    /**
     * Creates a new account for an existing customer.
     * @param customerID ID of the customer
     * @param initialCredit Initial deposit amount
     * @return The created account
     */
    fun createAccount(customerID: String, initialCredit: BigDecimal): Account {
        val customer = customerRepository.findById(customerID)
            .orElseThrow { IllegalArgumentException("Customer not found") }

        logger.info("Creating account for customer: ${customer.id}")

        val newAccount = accountRepository.save(Account(customerID = customer.id))

        if (initialCredit > BigDecimal.ZERO) {
            val transaction = Transaction(accountID = newAccount.id!!, amount = initialCredit)
            transactionRepository.save(transaction)

            newAccount.balance = newAccount.balance.add(initialCredit)
            accountRepository.save(newAccount)

            logger.info("Transaction of $initialCredit added to account: ${newAccount.id}")
        }

        return newAccount
    }

    /**
     * Retrieves customer details including accounts and transactions.
     * @param customerID ID of the customer
     * @return Customer details
     */
    fun getCustomerDetails(customerID: String): Map<String, Any> {
        val customer = customerRepository.findById(customerID)
            .orElseThrow { IllegalArgumentException("Customer not found") }

        logger.info("Fetching details for customer: ${customer.id}")

        val accounts = accountRepository.findByCustomerID(customerID)
        val transactions = accounts.flatMap { account -> transactionRepository.findByAccountID(account.id!!) }

        return mapOf(
            "firstName" to customer.firstName,
            "lastName" to customer.lastName,
            "accounts" to accounts.map { account ->
                mapOf(
                    "accountID" to account.id,
                    "balance" to account.balance,
                    "transactions" to transactions.filter { it.accountID == account.id }
                )
            }
        )
    }
}
