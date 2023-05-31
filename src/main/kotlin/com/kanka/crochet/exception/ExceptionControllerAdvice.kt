package com.kanka.crochet.exception

import com.kanka.crochet.api.NotFoundException
import com.kanka.crochet.model.ApiError
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import java.lang.Exception

@ControllerAdvice
class ExceptionControllerAdvice {

    @ExceptionHandler
    fun handleIllegalStateException(exception: IllegalStateException): ResponseEntity<ApiError> {
        val errorMessage = ApiError(HttpStatus.BAD_REQUEST.value(), exception.message ?: "Unexpected error.")

        return ResponseEntity(errorMessage, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler
    fun handleHttpMessageNotReadableException(exception: HttpMessageNotReadableException): ResponseEntity<ApiError>  {
        val errorMessage = ApiError(HttpStatus.BAD_REQUEST.value(), "Invalid request parameter.")

        return ResponseEntity(errorMessage, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler
    fun handleDocumentNotFoundException(exception: NotFoundException): ResponseEntity<ApiError>  {
        val errorMessage = ApiError(HttpStatus.NOT_FOUND.value(), exception.message ?: "Document not found.")

        return ResponseEntity(errorMessage, HttpStatus.NOT_FOUND)
    }
}
