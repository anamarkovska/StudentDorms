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

//    fun toEntity(userDto: UserDto): User {
//        return User(
//            id = userDto.id,
//            username = userDto.username,
//            password = "", // Set password to empty string as it's not available in UserDto
//            isAdmin = false, // Set isAdmin to false as it's not available in UserDto
//            posts = emptyList(), // Set posts to emptyList as it's not available in UserDto
//            comments = emptyList() // Set comments to emptyList as it's not available in UserDto
//        )
//    }

    fun toDtoList(users: MutableSet<User>): List<UserDto> {
        return users.map { toDto(it) }
    }

//    fun toEntityList(userDtos: List<UserDto>): List<User> {
//        return userDtos.map { toEntity(it) }
//    }
}