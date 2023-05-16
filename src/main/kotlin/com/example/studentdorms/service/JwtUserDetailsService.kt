package com.example.studentdorms.service

import com.example.studentdorms.config.security.MyPasswordEncoder
import com.example.studentdorms.domain.dto.UserDto
import com.example.studentdorms.exceptions.UsernameAlreadyExistsException
import com.example.studentdorms.repository.UserRepository
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service


@Service
class JwtUserDetailsService(
    private val userRepository: UserRepository,
    private val passwordEncoder: MyPasswordEncoder

) : UserDetailsService {
    fun findAuthenticatedUser(): UserDetails {
        val authentication = SecurityContextHolder.getContext().authentication

        if (authentication != null && authentication.isAuthenticated && authentication.principal is UserDetails) {
            return authentication.principal as UserDetails
        } else {
            throw RuntimeException("User not authenticated or not found")
        }
    }

    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(username: String): UserDetails {
        val user: com.example.studentdorms.domain.User? = userRepository.findByUsername(username)
        user?.let {
            return User(
                it.username,
                it.password,
                ArrayList<GrantedAuthority>()
            )
        }
        throw UsernameNotFoundException("User not found with username: $username")
    }

    fun save(user: UserDto): com.example.studentdorms.domain.User? {
        if (userRepository.existsByUsername(user.username)) {
            throw UsernameAlreadyExistsException("username exists")
        }
        val newUser = com.example.studentdorms.domain.User()
        newUser.username = (user.username)
        newUser.password = (passwordEncoder.encode(user.password))
        return userRepository.save(newUser)
    }

    fun isAdmin(userId: Long): Boolean {
        val user = userRepository.findById(userId)
        return user.map { it.isAdmin }.orElse(false)
    }
}