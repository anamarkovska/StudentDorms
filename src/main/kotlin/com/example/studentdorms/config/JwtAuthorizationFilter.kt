package com.example.studentdorms.config

import com.example.studentdorms.service.impl.UserDetailsServiceImpl
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken

class JwtAuthorizationFilter(
    private val userDetailsServiceImpl: UserDetailsServiceImpl,
    authenticationManager: AuthenticationManager,
    private val jwtTokenUtil: JwtTokenUtil
) : BasicAuthenticationFilter(authenticationManager) {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        chain: FilterChain
    ) {
        val header = request.getHeader("Authorization")
        if (header == null || !header.startsWith("Bearer ")) {
            chain.doFilter(request, response)
            return
        }
        val authToken = header.substring(7)
        if (jwtTokenUtil.isTokenValid(authToken)) {
            val username = jwtTokenUtil.getUsername(authToken)
            val userDetails: UserDetails = userDetailsServiceImpl.loadUserByUsername(username)
            val authentication = UsernamePasswordAuthenticationToken(userDetails, null, userDetails.authorities)
            SecurityContextHolder.getContext().authentication = authentication
        }
        chain.doFilter(request, response)
    }
}
