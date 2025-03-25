package com.banking.technl.sandbox.controller

import com.banking.technl.sandbox.service.BankingService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.*
import java.math.BigDecimal

/**
 * REST Controller for handling banking operations.
 */
@RestController
@RequestMapping("/api")
class BankingController(private val bankingService: BankingService) {
    private val logger: Logger = LoggerFactory.getLogger(BankingController::class.java)

    /**
     * Opens a new account for a customer.
     * @param customerID ID of the customer
     * @param initialCredit Initial deposit amount
     * @return The created account
     */
    @PostMapping("/open-account")
    fun openAccount(@RequestParam customerID: String, @RequestParam initialCredit: BigDecimal) =
        bankingService.createAccount(customerID, initialCredit)

    /**
     * Retrieves customer details including name, balance, and transactions.
     * @param customerID ID of the customer
     * @return Customer details
     */
    @GetMapping("/customer/{customerID}")
    fun getCustomerDetails(@PathVariable customerID: String) =
        bankingService.getCustomerDetails(customerID)
}
