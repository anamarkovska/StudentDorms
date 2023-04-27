package com.example.studentdorms.service

import com.example.studentdorms.config.security.MyPasswordEncoder
import com.example.studentdorms.domain.dto.UserDto
import com.example.studentdorms.repository.UserRepository
import org.springframework.security.core.GrantedAuthority
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
    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(username: String): UserDetails {
        val user: com.example.studentdorms.domain.User = userRepository.findByUsername(username)
            ?: throw UsernameNotFoundException("User not found with username: $username")
        return User(
            user.username, user.password,
            ArrayList<GrantedAuthority>()
        )
    }
    fun save(user: UserDto): com.example.studentdorms.domain.User? {
        val newUser = com.example.studentdorms.domain.User()
        newUser.username=(user.username)
        newUser.password=(passwordEncoder.encode(user.password))
        return userRepository.save(newUser)
    }
}