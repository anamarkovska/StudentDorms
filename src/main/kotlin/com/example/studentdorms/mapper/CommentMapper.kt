package com.example.studentdorms.mapper

import com.example.studentdorms.domain.Comment
import com.example.studentdorms.domain.dto.CommentDto
import org.springframework.stereotype.Component
import java.util.*

@Component
class CommentMapper(private val userMapper: UserMapper) {

    fun toDto(comment: Comment): CommentDto {
        return CommentDto(
            id = comment.id,
            content = comment.content,
            postId= comment.post?.id,
            userDto = comment.user?.let { userMapper.toDto(it) },
            createdAt = comment.createdAt
        )
    }

    fun toDtoList(comments: List<Comment>): List<CommentDto> {
        return comments.map { toDto(it) }
    }
}
