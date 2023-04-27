package com.example.studentdorms.config.security

import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component

@Component
class MyAuthenticationManager: AuthenticationManager {
    override fun authenticate(authentication: Authentication?): Authentication {
        return authentication!!
    }
}