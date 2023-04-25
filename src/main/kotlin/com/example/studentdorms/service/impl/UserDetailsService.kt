package com.example.studentdorms.service.impl

import com.example.studentdorms.domain.User
import com.example.studentdorms.repository.UserRepository
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserDetailsServiceImpl(private val userRepository: UserRepository) : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {
        val user = userRepository.findByUsername(username) ?: throw UsernameNotFoundException("User not found with username: $username")

        return org.springframework.security.core.userdetails.User(
            user.username,
            user.password,
            user.isAdmin,
            true,
            true,
            true,
            getAuthorities(user.isAdmin)
        )
    }

    private fun getAuthorities(isAdmin: Boolean): List<GrantedAuthority> {
        return if (isAdmin) {
            listOf(SimpleGrantedAuthority("ROLE_ADMIN"))
        } else {
            listOf(SimpleGrantedAuthority("ROLE_USER"))
        }
    }
}

