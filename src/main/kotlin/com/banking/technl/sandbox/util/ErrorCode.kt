package com.banking.technl.sandbox.util

/**
 * Enum class representing error codes for better API error handling.
 */
enum class ErrorCode(val code: String, val message: String) {
    CUSTOMER_NOT_FOUND("ERR_001", "Customer not found"),
    ACCOUNT_CREATION_FAILED("ERR_002", "Account creation failed"),
    INVALID_TRANSACTION("ERR_003", "Invalid transaction amount"),
    GENERAL_ERROR("ERR_999", "Unexpected server error"),
    ACCOUNT_NOT_FOUND("ERR_004", "Account not found")
}