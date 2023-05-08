package com.example.studentdorms.service.impl

import com.example.studentdorms.domain.*
import com.example.studentdorms.domain.dto.PostCreationDto
import com.example.studentdorms.domain.dto.PostDto
import com.example.studentdorms.mapper.PostMapper
import com.example.studentdorms.repository.PostCategoryRepository
import com.example.studentdorms.repository.PostLikesRepository
import com.example.studentdorms.repository.PostRepostiroy
import com.example.studentdorms.repository.UserRepository
import com.example.studentdorms.service.JwtUserDetailsService
import com.example.studentdorms.service.PostLikesService
import com.example.studentdorms.service.PostService
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service


@Service
class PostServiceImpl(val repostiroy: PostRepostiroy, val mapper: PostMapper,
                      val postCategoryRepository: PostCategoryRepository,
                      val postLikesService: PostLikesService,
                      val postLikesRepository: PostLikesRepository,
                      val userService: JwtUserDetailsService, private val userRepository: UserRepository
) : PostService{
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

    override fun createPost(postCreationDto: PostCreationDto, postCategory: Long) {
        val userDetails: UserDetails = userService.findAuthenticatedUser();
        val user: User = userRepository.findByUsername(userDetails.username)
        val category: PostCategory? = postCategoryRepository.findById(postCategory).orElse(null)
        category?.let {
            val post = Post(postCreationDto, it, user)
            repostiroy.save(post)
        }
    }

//    override fun createLike(postId: Long, user: User) {
//        val post = repostiroy.findById(postId).orElseThrow { UsernameNotFoundException("Post not found") }
//        val postLike = PostLikes(user,post)
//        postLikesRepository.save(postLike)
//    }

    override fun createLike(postId: Long, user: User) {
        val post = repostiroy.findById(postId).orElseThrow { UsernameNotFoundException("Post not found") }
        val postLikesId = PostLikesId(post.id!!, user.id!!)
        val postLike = PostLikes(postLikesId, post, user)
        postLikesRepository.save(postLike)
    }

    override fun delete(postId: Long?) {
        postId?.let { repostiroy.deleteById(it) }
    }

    override fun getNumberOfLikes(postId: Long): Long {
        val post = repostiroy.findById(postId).orElseThrow { UsernameNotFoundException("Post not found") }
        return post.likedBy.size.toLong()
    }

    override fun getUsernamesFromPostLikes(postId: Long): List<String> {
        val post = repostiroy.findById(postId).orElseThrow { UsernameNotFoundException("Post not found") }
        return post.likedBy.map { user -> user.username }
    }

    override fun deleteLike(postId: Long, username: String) {
        println("deleteLike is invoked")
        val post = repostiroy.findById(postId).orElseThrow { UsernameNotFoundException("Post not found") }
        println(username)
        val user = post.likedBy.find { it.username == username}
        if (user != null) {
            println("user is not null")
            post.likedBy.remove(user)
            repostiroy.save(post)
        }
    }


}