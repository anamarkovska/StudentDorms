package com.example.studentdorms.service

import com.example.studentdorms.domain.Comment
import com.example.studentdorms.domain.dto.CommentDto
import java.security.Principal

interface CommentService {
    fun getAllComments(): List<CommentDto>
    fun createComment(commentDto: CommentDto, postId: Long)
    fun getCommentsByPostId(postId: Long): List<CommentDto>
    fun deleteComment(commentId: Long)

    fun getCommentById(commentId: Long): CommentDto
    fun updateComment(commentDto: CommentDto, principal: Principal): CommentDto
}

