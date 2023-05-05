package com.example.studentdorms.domain.dto

class CommentDto {
    var id: Long? = null
    var content: String = ""
    var postId: Long? = null
    var userDto: UserDto? = null

    constructor()

    constructor(id: Long?, content: String,postId: Long?, userDto: UserDto?) {
        this.id = id
        this.content = content
        this.postId = postId
        this.userDto = userDto
    }
}