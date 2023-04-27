package com.example.studentdorms.service

import com.example.studentdorms.domain.Post
import com.example.studentdorms.domain.dto.PostCreationDto
import com.example.studentdorms.domain.dto.PostDto

interface PostService {

    fun getAllPosts():List<PostDto>
    fun getPostById(id: Long?): PostDto?

    fun getById(id: Long?): Post?

    fun getPostsByCategory(categoryId: Long?): List<PostDto>?

    fun createPost(postCreationDto: PostCreationDto)
    fun like(postId: Long?)
    fun delete(postId:Long?)
}