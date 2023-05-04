package com.example.studentdorms.service

import com.example.studentdorms.domain.Comment
import com.example.studentdorms.domain.dto.CommentDto

interface CommentService {
    fun getAllComments(): List<CommentDto>
    fun createComment(commentDto: CommentDto, postId: Long)
    fun getCommentsByPostId(postId: Long): List<CommentDto>
    fun deleteComment(commentId: Long)
}

