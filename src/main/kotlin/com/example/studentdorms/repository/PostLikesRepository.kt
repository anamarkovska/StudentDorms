package com.example.studentdorms.repository

import com.example.studentdorms.domain.Post
import com.example.studentdorms.domain.PostLikes
import com.example.studentdorms.domain.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


@Repository
interface PostLikesRepository : JpaRepository<PostLikes, Long> {
    fun countPostLikeByPost(post: Post?): Int?
    fun findPostLikeByPostAndUser(post: Post?, user: User?): PostLikes
    fun existsByPostIdAndUserId(postId: Long, userId: Long?): Boolean
}