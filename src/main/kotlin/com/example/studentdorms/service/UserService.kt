package com.example.studentdorms.service

import com.example.studentdorms.domain.RegisterRequest
import com.example.studentdorms.domain.User
import com.example.studentdorms.domain.dto.UserDto
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetailsService

interface UserService{
    fun login(username: String, password: String): String
    fun register(userDto: UserDto): User
}