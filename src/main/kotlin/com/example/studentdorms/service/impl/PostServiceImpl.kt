package com.example.studentdorms.service.impl

import com.example.studentdorms.domain.Post
import com.example.studentdorms.repository.PostRepostiroy
import com.example.studentdorms.service.PostService
import org.springframework.stereotype.Service

@Service
class PostServiceImpl(val repostiroy: PostRepostiroy) : PostService{
    override fun getAllPosts(): List<Post> {
        return repostiroy.findAll()
    }

}