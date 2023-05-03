package com.example.studentdorms.domain.Security

import org.springframework.http.HttpStatus

data class ErrorResponse(
    val status: HttpStatus,
    val message: String
)
