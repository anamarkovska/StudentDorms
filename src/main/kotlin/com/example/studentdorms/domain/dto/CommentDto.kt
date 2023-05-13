package com.example.studentdorms.domain.dto

import java.time.LocalDateTime

class CommentDto {
    var id: Long? = null
    var content: String = ""
    var postId: Long? = null
    var userDto: UserDto? = null
    var createdAt: LocalDateTime = LocalDateTime.now()
    constructor()

    constructor(id: Long?, content: String,postId: Long?, userDto: UserDto?, createdAt: LocalDateTime) {
        this.id = id
        this.content = content
        this.postId = postId
        this.userDto = userDto
        this.createdAt = createdAt
    }
}