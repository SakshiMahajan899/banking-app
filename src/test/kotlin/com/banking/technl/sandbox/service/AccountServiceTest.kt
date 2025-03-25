package com.banking.technl.sandbox.service

import com.banking.technl.sandbox.exception.CustomerNotFoundException
import com.banking.technl.sandbox.model.Account
import com.banking.technl.sandbox.model.Customer
import com.banking.technl.sandbox.model.Transaction
import com.banking.technl.sandbox.repository.AccountRepository
import com.banking.technl.sandbox.repository.CustomerRepository
import com.banking.technl.sandbox.repository.TransactionRepository
import com.banking.technl.sandbox.util.TransactionStatus
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.Mockito.*
import java.math.BigDecimal
import java.util.*

class AccountServiceTest {

 private val customerRepository: CustomerRepository = mock(CustomerRepository::class.java)
 private val accountRepository: AccountRepository = mock(AccountRepository::class.java)
 private val transactionService: TransactionService = mock(TransactionService::class.java)
 private val bankingService = AccountService(customerRepository, accountRepository, transactionService)


 @Test
 fun `test createAccount throws CustomerNotFoundException`() {
  // Arrange
  val customerID = "nonexistent"
  val initialCredit = BigDecimal("1000")

  `when`(customerRepository.findById(customerID)).thenReturn(Optional.empty())

  // Act & Assert
  assertThrows<CustomerNotFoundException> {
   bankingService.createAccount(customerID, initialCredit)
  }
 }


 @Test
 fun `test getCustomerDetails with valid customer`() {
  // Arrange
  val customerID = "123e4567-e89b-12d3-a456-426614174000"
  val customer = Customer(id = customerID, firstName = "John", lastName = "Doe")
  val accounts = listOf(
   Account(id = "223e4567-e89b-12d3-a456-426614174000", customerID = customerID, balance = BigDecimal("1000"))
  )
  val transactions = listOf(
   Transaction(accountID = "223e4567-e89b-12d3-a456-426614174000", amount = BigDecimal("1000"), status = TransactionStatus.SUCCESS)
  )

  `when`(customerRepository.findById(customerID)).thenReturn(Optional.of(customer))
  `when`(accountRepository.findByCustomerID(customerID)).thenReturn(accounts)
  `when`(transactionService.findByAccountID("223e4567-e89b-12d3-a456-426614174000")).thenReturn(transactions)

  // Act
  val customerDetails = bankingService.getCustomerDetails(customerID)

  // Assert
  assertEquals("John", customerDetails["firstName"])
  assertEquals("Doe", customerDetails["lastName"])
  assertTrue((customerDetails["accounts"] as List<*>).isNotEmpty())
 }
}
