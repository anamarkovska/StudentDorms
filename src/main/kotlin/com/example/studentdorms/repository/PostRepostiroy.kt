package com.example.studentdorms.repository

import com.example.studentdorms.domain.Post
import com.example.studentdorms.domain.User
import com.example.studentdorms.domain.dto.PostDto
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PostRepostiroy:JpaRepository<Post,Long> {
//    fun findLikedByById(postId: Long): Set<User>
//override fun findAll(): MutableList<Post>

    fun findByPostCategoryId(categoryId: Long): List<Post>
}