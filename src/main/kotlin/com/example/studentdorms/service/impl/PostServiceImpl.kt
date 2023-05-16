package com.example.studentdorms.service.impl

import com.example.studentdorms.domain.*
import com.example.studentdorms.domain.dto.PostCreationDto
import com.example.studentdorms.domain.dto.PostDto
import com.example.studentdorms.domain.dto.UserDto
import com.example.studentdorms.exceptions.PostNotFoundException
import com.example.studentdorms.exceptions.UnauthorizedUserException
import com.example.studentdorms.mapper.PostCategoryMapper
import com.example.studentdorms.mapper.PostMapper
import com.example.studentdorms.repository.PostCategoryRepository
import com.example.studentdorms.repository.PostLikesRepository
import com.example.studentdorms.repository.PostRepostiroy
import com.example.studentdorms.repository.UserRepository
import com.example.studentdorms.service.JwtUserDetailsService
import com.example.studentdorms.service.PostLikesService
import com.example.studentdorms.service.PostService
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import java.security.Principal


@Service
class PostServiceImpl(
    val repository: PostRepostiroy,
    val mapper: PostMapper,
    val postCategoryRepository: PostCategoryRepository,
    val postLikesRepository: PostLikesRepository,
    val userService: JwtUserDetailsService,
    val userRepository: UserRepository
) : PostService {
    override fun getAllPosts(): List<PostDto> {
        val posts = repository.findAll()
        val postsDto = mapper.toDtoList(posts)
        return postsDto
    }

    override fun getPostById(id: Long?): PostDto? {
        val post = getById(id)
        val postsDto = post?.let { mapper.toDto(it) }
        return postsDto
    }

    override fun getById(id: Long?): Post? {
        return id?.let { repository.getById(it) }
    }

    override fun getPostsByCategory(categoryId: Long?): List<PostDto>? {
        val postsByCategory = categoryId?.let { repository.findByPostCategoryId(it) }
        val postsDto = postsByCategory?.let { mapper.toDtoList(it) }
        return postsDto
    }

    override fun updatePost(id: Long, title: String, content: String, principal: Principal): PostDto? {
        val post = repository.findById(id)
            .orElseThrow { PostNotFoundException("Post not found with id $id") }

        if (post.user?.username != principal.name) {
            throw UnauthorizedUserException("You are not authorized to update this post")
        }

        post.title = title
        post.content = content
        return mapper.toDto(repository.save(post))
    }

    override fun createPost(postCreationDto: PostCreationDto, postCategory: Long) {
        val userDetails: UserDetails = userService.findAuthenticatedUser();
        val user: User = userRepository.findByUsername(userDetails.username)
        val category: PostCategory? = postCategoryRepository.findById(postCategory).orElse(null)
        category?.let {
            val post = Post(postCreationDto, it, user)
            repository.save(post)
        }
    }

    override fun createLike(postId: Long, user: User) {
        val post = repository.findById(postId).orElseThrow { UsernameNotFoundException("Post not found") }
        val postLikesId = PostLikesId(post.id!!, user.id!!)
        val postLike = PostLikes(postLikesId, post, user)
        postLikesRepository.save(postLike)
    }

    override fun delete(postId: Long?) {
        postId?.let { repository.deleteById(it) }
    }

    override fun getNumberOfLikes(postId: Long): Long {
        val post = repository.findById(postId).orElseThrow { UsernameNotFoundException("Post not found") }
        return post.likedBy.size.toLong()
    }

    override fun getUsernamesFromPostLikes(postId: Long): List<String> {
        val post = repository.findById(postId).orElseThrow { UsernameNotFoundException("Post not found") }
        return post.likedBy.map { user -> user.username }
    }

    override fun deleteLike(postId: Long) {
        val userDetails: UserDetails = userService.findAuthenticatedUser()
        val user: User = userRepository.findByUsername(userDetails.username)
        println("deleteLike is invoked for user ${user.username}")
        val post = repository.findById(postId).orElseThrow { UsernameNotFoundException("Post not found") }
        val likedByUser = post.likedBy.find { it.id == user.id }
        if (likedByUser != null) {
            println("user is not null")
            post.likedBy.remove(likedByUser)
            repository.save(post)
        }
    }

    override fun getAuthenticatedUser(): UserDto? {
        val auth = SecurityContextHolder.getContext().authentication
        if (auth != null && auth.isAuthenticated) {
            val username = auth.name
            val userDetails = userService.loadUserByUsername(username)
            val user = userRepository.findByUsername(username)
            return UserDto(user.id, userDetails.username, userDetails.password)
        } else {
            return null
        }
    }

}