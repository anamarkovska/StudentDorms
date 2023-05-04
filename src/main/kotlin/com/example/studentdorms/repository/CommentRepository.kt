package com.example.studentdorms.repository

import com.example.studentdorms.domain.Comment
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CommentRepository: JpaRepository<Comment, Long> {
    fun findByPostId(postId: Long): List<Comment>
}