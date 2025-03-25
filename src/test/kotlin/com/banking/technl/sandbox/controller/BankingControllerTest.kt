package com.banking.technl.sandbox.controller

import com.banking.technl.sandbox.model.Account
import com.banking.technl.sandbox.service.AccountService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*
import org.springframework.http.ResponseEntity
import java.math.BigDecimal

class BankingControllerTest {

 private val bankingService: AccountService = mock(AccountService::class.java)
 private val bankingController = BankingController(bankingService)

 @Test
 fun `test openAccount endpoint`() {
  // Arrange
  val customerID = "123e4567-e89b-12d3-a456-426614174000"
  val initialCredit = BigDecimal("1000")
  val mockAccount = Account(
   id = "223e4567-e89b-12d3-a456-426614174000",
   customerID = customerID,
   balance = initialCredit
  )

  `when`(bankingService.createAccount(customerID, initialCredit)).thenReturn(mockAccount)

  // Act
  val response = bankingController.openAccount(customerID, initialCredit)

  // Assert
  verify(bankingService, times(1)).createAccount(customerID, initialCredit)
  assertEquals(ResponseEntity.ok(mockAccount), response)
 }

 @Test
 fun `test getCustomerDetails endpoint`() {
  // Arrange
  val customerID = "123e4567-e89b-12d3-a456-426614174000"
  val mockCustomerDetails = mapOf(
   "firstName" to "John",
   "lastName" to "Doe",
   "accounts" to listOf(
    mapOf(
     "accountID" to "223e4567-e89b-12d3-a456-426614174000",
     "balance" to BigDecimal("1000"),
     "transactions" to listOf(
      mapOf("transactionID" to "323e4567-e89b-12d3-a456-426614174000", "amount" to BigDecimal("1000"))
     )
    )
   )
  )

  `when`(bankingService.getCustomerDetails(customerID)).thenReturn(mockCustomerDetails)

  // Act
  val response = bankingController.getCustomerDetails(customerID)

  // Assert
  verify(bankingService, times(1)).getCustomerDetails(customerID)
  assertEquals(ResponseEntity.ok(mockCustomerDetails), response)
 }
}
