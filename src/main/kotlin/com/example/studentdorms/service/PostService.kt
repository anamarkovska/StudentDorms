package com.example.studentdorms.service

import com.example.studentdorms.domain.Post

interface PostService {

    fun getAllPosts():List<Post>
}