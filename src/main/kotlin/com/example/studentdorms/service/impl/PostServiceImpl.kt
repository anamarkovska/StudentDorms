package com.example.studentdorms.service.impl

import com.example.studentdorms.domain.Post
import com.example.studentdorms.domain.dto.PostCreationDto
import com.example.studentdorms.domain.dto.PostDto
import com.example.studentdorms.mapper.PostMapper
import com.example.studentdorms.repository.PostRepostiroy
import com.example.studentdorms.service.PostService
import org.springframework.stereotype.Service
import java.util.*

@Service
class PostServiceImpl(val repostiroy: PostRepostiroy, val mapper: PostMapper) : PostService{
    override fun getAllPosts(): List<PostDto> {
        val posts = repostiroy.findAll()
        val postsDto = mapper.toDtoList(posts)
        return postsDto
    }

    override fun getPostById(id: Long?): PostDto? {
        val post = getById(id)
        val postsDto = post?.let { mapper.toDto(it) }
        return postsDto
    }

    override fun getById(id: Long?): Post? {
        return id?.let { repostiroy.getById(it) }
    }

    override fun getPostsByCategory(categoryId: Long?): List<PostDto>? {
        val postsByCategory = categoryId?.let { repostiroy.findByPostCategoryId(it) }
        val postsDto = postsByCategory?.let { mapper.toDtoList(it) }
        return postsDto
    }

    override fun createPost(postCreationDto: PostCreationDto) {
        val post = Post(postCreationDto)
        repostiroy.save(post)
    }

    override fun like(postId: Long?) {
        val post = postId?.let { repostiroy.getById(it) }
//        val user = userService.findAuthenticatedUser();
//        postLikesService.toggleLike(post,user)
    }

    override fun delete(postId: Long?) {
        postId?.let { repostiroy.deleteById(it) }
    }


}