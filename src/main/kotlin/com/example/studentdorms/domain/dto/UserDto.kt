package com.example.studentdorms.domain.dto

import com.example.studentdorms.domain.Role
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class UserDto(
    private val id: Long?,
    private val username: String,
    private val password: String,
    val isAdmin: Boolean,
    private val authorities: Collection<GrantedAuthority>,
//    val roles: List<Role>
) : UserDetails {

    override fun getAuthorities(): Collection<GrantedAuthority> = authorities

    override fun getPassword(): String = password

    override fun getUsername(): String = username

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true

    override fun isCredentialsNonExpired(): Boolean = true

    override fun isEnabled(): Boolean = true

    fun getId(): Long? = id

    fun isUserAdmin(): Boolean = isAdmin
}
