package com.banking.technl.sandbox.exception

import com.banking.technl.sandbox.util.ErrorCode
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

/**
 * Global exception handler for banking application.
 */
@ControllerAdvice
class GlobalExceptionHandler {
    private val logger: Logger = LoggerFactory.getLogger(GlobalExceptionHandler::class.java)

    /**
     * Handles custom banking exceptions.
     */
    @ExceptionHandler(BankingException::class)
    fun handleBankingException(ex: BankingException): ResponseEntity<MultiValueMap<String, String>> {
        logger.error("Error: ${ex.errorCode.code} - ${ex.errorCode.message}")

        val errorResponse: MultiValueMap<String, String> = LinkedMultiValueMap()
        errorResponse.add("errorCode", ex.errorCode.code)
        errorResponse.add("message", ex.errorCode.message)

        return ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST)
    }

    /**
     * Handles all unexpected exceptions.
     */
    @ExceptionHandler(Exception::class)
    fun handleGenericException(ex: Exception): ResponseEntity<MultiValueMap<String, String>> {
        logger.error("Unexpected error: ${ex.message}")

        val errorResponse: MultiValueMap<String, String> = LinkedMultiValueMap()
        errorResponse.add("errorCode", ErrorCode.GENERAL_ERROR.code)
        errorResponse.add("message", "Internal Server Error")

        return ResponseEntity(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR)
    }
}
