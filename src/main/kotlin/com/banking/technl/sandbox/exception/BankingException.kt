package com.banking.technl.sandbox.exception

import com.banking.technl.sandbox.util.ErrorCode


/**
 * Custom exception class for banking-related errors.
 */
open class BankingException(val errorCode: ErrorCode) : RuntimeException(errorCode.message)