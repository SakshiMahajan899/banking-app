package com.banking.technl.sandbox.exception

import com.banking.technl.sandbox.util.ErrorCode

class AccountNotFoundException : BankingException(ErrorCode.ACCOUNT_NOT_FOUND) {
}