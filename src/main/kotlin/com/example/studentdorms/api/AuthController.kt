package com.example.studentdorms.api

import com.example.studentdorms.config.JwtTokenUtil
import com.example.studentdorms.config.MyPasswordEncoder
import com.example.studentdorms.domain.*
import com.example.studentdorms.domain.dto.UserDto
import com.example.studentdorms.service.UserService
import com.example.studentdorms.service.impl.UserServiceImpl
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/auth")
class AuthController(
    private val userService: UserService,
    private val passwordEncoder: MyPasswordEncoder
) {
        @PostMapping("/register")
        fun register(@RequestBody userDto: UserDto): ResponseEntity<User> {
            val savedUser = userService.register(userDto)
            return ResponseEntity.ok(savedUser)
        }

        @PostMapping("/login")
        fun login(@RequestBody request: LoginRequest): ResponseEntity<LoginResponse> {
            val token = userService.login(request.username, request.password)
            val response = LoginResponse(token)
            return ResponseEntity.ok(response)
        } }
