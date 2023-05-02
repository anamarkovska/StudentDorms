package com.example.studentdorms.api

import com.example.studentdorms.config.security.JwtTokenUtil
import com.example.studentdorms.config.security.MyAuthenticationManager
import com.example.studentdorms.domain.Security.JwtRequest
import com.example.studentdorms.domain.Security.JwtResponse
import com.example.studentdorms.domain.dto.UserDto
import com.example.studentdorms.service.JwtUserDetailsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.DisabledException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.web.bind.annotation.*


@RestController
@CrossOrigin
@RequestMapping("/api")
class JwtAuthenticationController(private val authenticationManager: MyAuthenticationManager,
                                  private val jwtTokenUtil: JwtTokenUtil) {

    @Autowired
    private val userDetailsService: JwtUserDetailsService? = null

    @RequestMapping(value = ["/authenticate"], method = [RequestMethod.POST])
    @Throws(Exception::class)
    fun createAuthenticationToken(@RequestBody authenticationRequest: JwtRequest): ResponseEntity<*> {
        authenticate(authenticationRequest.username, authenticationRequest.password)
        val userDetails = authenticationRequest.username.let {
            userDetailsService!!.loadUserByUsername(it)
        }
        val token = jwtTokenUtil!!.generateToken(userDetails!!)
        return ResponseEntity.ok<Any>(JwtResponse(token))
    }

    @RequestMapping(value = ["/register"], method = [RequestMethod.POST])
    @Throws(java.lang.Exception::class)
    fun saveUser(@RequestBody user: UserDto): ResponseEntity<*>? {
        return ResponseEntity.ok(userDetailsService!!.save(user))
    }

        @Throws(Exception::class)
    private fun authenticate(username: String, password: String) {
        try {
            authenticationManager!!.authenticate(UsernamePasswordAuthenticationToken(username, password))
        } catch (e: DisabledException) {
            throw Exception("USER_DISABLED", e)
        } catch (e: BadCredentialsException) {
            throw Exception("INVALID_CREDENTIALS", e)
        }
    }
}
