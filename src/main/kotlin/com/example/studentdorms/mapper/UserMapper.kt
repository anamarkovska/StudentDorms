package com.example.studentdorms.mapper

import com.example.studentdorms.domain.User
import com.example.studentdorms.domain.dto.UserDto
import org.springframework.stereotype.Component


@Component
class UserMapper {
    fun toDto(user: User): UserDto {
        return UserDto(
            id = user.id,
            username = user.username,
            password = user.password
        )
    }

    fun toDtoList(users: MutableSet<User>): List<UserDto> {
        return users.map { toDto(it) }
    }

}