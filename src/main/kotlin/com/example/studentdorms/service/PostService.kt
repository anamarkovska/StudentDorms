package com.example.studentdorms.service

import com.example.studentdorms.domain.Post
import com.example.studentdorms.domain.User
import com.example.studentdorms.domain.dto.PostCreationDto
import com.example.studentdorms.domain.dto.PostDto

interface PostService {

    fun getAllPosts():List<PostDto>
    fun getPostById(id: Long?): PostDto?

    fun getById(id: Long?): Post?

    fun getPostsByCategory(categoryId: Long?): List<PostDto>?

    fun createPost(postCreationDto: PostCreationDto, postCategory: Long)
    fun createLike(postId: Long, user: User)
    fun delete(postId:Long?)
    fun getNumberOfLikes(postId: Long): Long
    fun getUsernamesFromPostLikes(postId: Long): List<String>
    fun deleteLike(postId: Long, username: String)
}