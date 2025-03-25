package com.banking.technl.sandbox.exception

import com.banking.technl.sandbox.util.ErrorCode


/**
 * Exception thrown when a customer is not found.
 */
class CustomerNotFoundException : BankingException(ErrorCode.CUSTOMER_NOT_FOUND)