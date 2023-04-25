package com.example.studentdorms.config

import com.example.studentdorms.domain.User
import com.example.studentdorms.domain.dto.LoginDto
import com.example.studentdorms.domain.dto.UserDto
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import java.util.*
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JwtAuthenticationFilter(authenticationManager: AuthenticationManager, private val jwtTokenUtil: JwtTokenUtil) :
    UsernamePasswordAuthenticationFilter(authenticationManager) {

    @Throws(AuthenticationException::class)
    override fun attemptAuthentication(request: HttpServletRequest, response: HttpServletResponse): Authentication {
        val credentials = ObjectMapper().readValue(request.inputStream, LoginDto::class.java)
        val authenticationToken = UsernamePasswordAuthenticationToken(credentials.email, credentials.password, emptyList())
        return authenticationManager.authenticate(authenticationToken)
    }

    override fun successfulAuthentication(
        request: HttpServletRequest,
        response: HttpServletResponse,
        chain: FilterChain,
        authResult: Authentication
    ) {
        val user = authResult.principal as UserDto
        val authorities = user.authorities
        val token = jwtTokenUtil.generateToken(user.username)
        response.addHeader("Authorization", "Bearer $token")
    }
}
