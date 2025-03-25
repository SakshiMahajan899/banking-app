package com.banking.technl.sandbox.exception

import com.banking.technl.sandbox.util.ErrorCode


/**
 * Exception thrown when an invalid transaction occurs.
 */
class InvalidTransactionException : BankingException(ErrorCode.INVALID_TRANSACTION)