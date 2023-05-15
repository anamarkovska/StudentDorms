package com.example.studentdorms.api

import com.example.studentdorms.config.security.JwtTokenUtil
import com.example.studentdorms.config.security.MyAuthenticationManager
import com.example.studentdorms.config.security.MyPasswordEncoder
import com.example.studentdorms.domain.Security.JwtRequest
import com.example.studentdorms.domain.Security.JwtResponse
import com.example.studentdorms.domain.dto.UserDto
import com.example.studentdorms.service.JwtUserDetailsService
import com.example.studentdorms.service.PostService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.DisabledException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.bind.annotation.*


@RestController
@CrossOrigin
@RequestMapping("/api")
class JwtAuthenticationController(private val authenticationManager: MyAuthenticationManager,
                                  private val jwtTokenUtil: JwtTokenUtil,
                                  private val passwordEncoder: MyPasswordEncoder,
private val postService: PostService) {

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
    fun saveUser(@RequestBody user: UserDto): ResponseEntity<*> {
        try {
            val savedUser = userDetailsService!!.save(user)
            return ResponseEntity.ok(savedUser)
        } catch (e: Exception) {
            return ResponseEntity.badRequest().body("Error: ${e.message}")
        }
    }


    @Throws(Exception::class)
    private fun authenticate(username: String, password: String) {
        val userDetails: UserDetails = userDetailsService!!.loadUserByUsername(username)

        if (!passwordEncoder.matches(password, userDetails.password)) {
            throw BadCredentialsException("INVALID_PASSWORD")
        }

        try {
            authenticationManager!!.authenticate(UsernamePasswordAuthenticationToken(username, password))
        } catch (e: DisabledException) {
            throw Exception("USER_DISABLED", e)
        } catch (e: BadCredentialsException) {
            throw Exception("INVALID_CREDENTIALS", e)
        }
    }


    @GetMapping("/authenticated-user")
    fun getAuthenticatedUser(): ResponseEntity<UserDto> {
        val user = postService.getAuthenticatedUser()
        return if (user != null) {
            ResponseEntity.ok(user)
        } else {
            ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()
        }
    }

}