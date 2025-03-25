package com.banking.technl.sandbox.controller

import com.banking.technl.sandbox.service.AccountService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.math.BigDecimal

/**
 * REST Controller for handling banking operations.
 */
@RestController
@CrossOrigin(origins = ["http://localhost:8082"])
@RequestMapping("/api/v1")
class BankingController(private val accountService: AccountService) {
    private val logger: Logger = LoggerFactory.getLogger(BankingController::class.java)

    @PostMapping("/open-account")
    fun openAccount(@RequestParam customerID: String, @RequestParam initialCredit: BigDecimal): ResponseEntity<Any> {
        logger.info("Received request to open account for customer ID: $customerID with initial credit: $initialCredit")
        val account = accountService.createAccount(customerID, initialCredit)
        return ResponseEntity.ok(account)
    }

    @GetMapping("/customer/{customerID}")
    fun getCustomerDetails(@PathVariable customerID: String): ResponseEntity<Any> {
        logger.info("Fetching details for customer ID: $customerID")
        val customerDetails = accountService.getCustomerDetails(customerID)
        return ResponseEntity.ok(customerDetails)
    }
}
