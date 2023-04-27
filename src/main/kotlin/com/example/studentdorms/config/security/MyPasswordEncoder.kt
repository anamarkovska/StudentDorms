package com.example.studentdorms.config.security

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component

@Component
class MyPasswordEncoder: PasswordEncoder {

    private val encoder = BCryptPasswordEncoder()

    override fun encode(rawPassword: CharSequence?): String {
        return encoder.encode(rawPassword)
    }

    override fun matches(rawPassword: CharSequence?, encodedPassword: String?): Boolean {
        return encoder.matches(rawPassword, encodedPassword)
    }
}