package com.example.studentdorms.service.impl

import com.example.studentdorms.config.JwtTokenUtil
import com.example.studentdorms.config.MyPasswordEncoder
import com.example.studentdorms.domain.User
import com.example.studentdorms.domain.dto.UserDto
import com.example.studentdorms.repository.UserRepository
import com.example.studentdorms.service.UserService
import org.springframework.context.annotation.Bean
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
    private val jwtTokenUtil: JwtTokenUtil,
    private val passwordEncoder: MyPasswordEncoder
) : UserService {


    override fun register(userDto: UserDto): User {
        if (userRepository.findByUsername(userDto.username) != null) {
            throw RuntimeException("Username already exists")
        }

        val user = User(
            null,
            userDto.username,
            passwordEncoder.encode(userDto.password),
            userDto.isAdmin,
            emptyList(),
            emptyList()
        )

        return userRepository.save(user)
    }

    override fun login(username: String, password: String): String {
        val user = userRepository.findByUsername(username)
            ?: throw UsernameNotFoundException("User not found: $username")
        if (!passwordEncoder.matches(password, user.password)) {
            throw BadCredentialsException("Invalid password")
        }
        return jwtTokenUtil.generateToken(user.username)
    }
}
