package com.banking.technl.sandbox.service

import com.banking.technl.sandbox.exception.CustomerNotFoundException
import com.banking.technl.sandbox.model.Account
import com.banking.technl.sandbox.repository.AccountRepository
import com.banking.technl.sandbox.repository.CustomerRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.math.BigDecimal

/**
 * Service for handling account-related operations.
 */
@Service
class AccountService(
    private val customerRepository: CustomerRepository,
    private val accountRepository: AccountRepository,
    private val transactionService: TransactionService
) {
    private val logger: Logger = LoggerFactory.getLogger(AccountService::class.java)

    /**
     * Creates a new account for an existing customer.
     * @param customerID ID of the customer
     * @param initialCredit Initial deposit amount
     * @return The created account
     * @throws CustomerNotFoundException if customer is not found
     */
    fun createAccount(customerID: String, initialCredit: BigDecimal): Account {
        val customer = customerRepository.findById(customerID)
            .orElseThrow { CustomerNotFoundException() }

        logger.info("Creating account for customer: ${customer.id}")

        val newAccount = accountRepository.save(Account(customerID = customer.id))

        if (initialCredit > BigDecimal.ZERO) {
            transactionService.createTransaction(newAccount.id!!, initialCredit)
            newAccount.balance = newAccount.balance.add(initialCredit)
            accountRepository.save(newAccount)
            logger.info("Transaction of $initialCredit added to account: ${newAccount.id}")
        }

        return newAccount
    }

    /**
     * Retrieves customer details including accounts and transactions.
     * @param customerID ID of the customer
     * @return Customer details as a map
     */
    fun getCustomerDetails(customerID: String): Map<String, Any> {
        val customer = customerRepository.findById(customerID)
            .orElseThrow { CustomerNotFoundException() }

        logger.info("Fetching details for customer: ${customer.id}")

        val accounts = accountRepository.findByCustomerID(customerID)
        val accountDetails = accounts.map { account ->
            val transactions = transactionService.findByAccountID(account.id!!)
            mapOf(
                "accountID" to account.id,
                "balance" to account.balance,
                "transactions" to transactions
            )
        }

        return mapOf(
            "firstName" to customer.firstName,
            "lastName" to customer.lastName,
            "accounts" to accountDetails
        )
    }
}
