package com.example.studentdorms.domain.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.time.LocalDateTime

@JsonIgnoreProperties(ignoreUnknown = true)
class PostDto {
    var id: Long? = null
    var title: String = ""
    var content: String = ""
    var userDto: UserDto? = null
    var likedBy: MutableSet<UserDto>? = mutableSetOf()
    var createdAt: LocalDateTime = LocalDateTime.now()
    var postCategoryDto: PostCategoryDto? = null
    var comments: List<CommentDto> = emptyList()

    constructor(
        id: Long?,
        title: String,
        content: String,
        userDto: UserDto?,
        likedBy: List<UserDto>,
        createdAt: LocalDateTime,
        postCategoryDto: PostCategoryDto?,
        comments: List<CommentDto>
    ) {
        this.id = id
        this.title = title
        this.content = content
        this.userDto = userDto
        this.likedBy?.addAll(likedBy)
        this.createdAt = createdAt
        this.postCategoryDto = postCategoryDto
        this.comments = comments
    }


}
