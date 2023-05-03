package com.example.studentdorms.config.security

import com.example.studentdorms.domain.Security.ErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import javax.servlet.http.HttpServletRequest

@ControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(UsernameNotFoundException::class)
    fun handleUsernameNotFoundException(ex: UsernameNotFoundException, request: HttpServletRequest): ResponseEntity<ErrorResponse> {
        val error = ErrorResponse(HttpStatus.NOT_FOUND, ex.message ?: "User not found")
        return ResponseEntity(error, error.status)
    }
}
