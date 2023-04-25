package com.example.studentdorms.domain.dto
import com.example.studentdorms.domain.dto.UserAppDto
class CommentDto {
    var id: Long? = null
    var content: String = ""
    var userDto: UserAppDto? = null

    constructor()

    constructor(id: Long?, content: String, userDto: UserAppDto?) {
        this.id = id
        this.content = content
        this.userDto = userDto
    }
}