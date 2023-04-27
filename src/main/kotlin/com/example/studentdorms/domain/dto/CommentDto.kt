package com.example.studentdorms.domain.dto

class CommentDto {
    var id: Long? = null
    var content: String = ""
    var userDto: UserDto? = null

    constructor()

    constructor(id: Long?, content: String, userDto: UserDto?) {
        this.id = id
        this.content = content
        this.userDto = userDto
    }
}