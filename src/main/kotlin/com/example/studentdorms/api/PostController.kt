package com.example.studentdorms.api

import com.example.studentdorms.domain.dto.PostCreationDto
import com.example.studentdorms.domain.dto.PostDto
import com.example.studentdorms.repository.UserRepository
import com.example.studentdorms.service.JwtUserDetailsService
import com.example.studentdorms.service.PostService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin
@RequestMapping("/api/posts")
class PostController(private val postService: PostService,private val userService: JwtUserDetailsService, private val userRepository: UserRepository) {

    @GetMapping
    fun getAllPosts(): ResponseEntity<List<PostDto?>> {
        val allPosts = postService.getAllPosts()
        return ResponseEntity.ok(allPosts)
    }

    @GetMapping("/{postId}")
    fun getPost(@PathVariable postId: Long?): ResponseEntity<PostDto?>? {
        return ResponseEntity.ok(postService.getPostById(postId))
    }

    @GetMapping("/category/{categoryId}")
    fun getPostsByCategory(@PathVariable categoryId: Long): ResponseEntity<List<PostDto?>> {
        val postsByCategory = postService.getPostsByCategory(categoryId)
        return ResponseEntity.ok(postsByCategory)
    }

    @PostMapping("/{categoryId}")
    fun createPost(@RequestBody postCreationDto: PostCreationDto?, @PathVariable categoryId: Long): ResponseEntity<*> {
        if (postCreationDto != null) {
            val createdPost = postService.createPost(postCreationDto, categoryId)
            return ResponseEntity.status(HttpStatus.CREATED).body(createdPost)
        }
        return ResponseEntity.badRequest().build<String>()
    }

//    @PutMapping("/{postId}/like")
//    fun likePost(@PathVariable postId: Long?): ResponseEntity<*>? {
//        postId?.let { postService.like(it) }
//        return ResponseEntity.ok().build<Any>()
//    }

    @PostMapping("/{postId}/like")
    fun likePost(@PathVariable postId: Long?): ResponseEntity<*> {
        val userDetails = userService.findAuthenticatedUser()
        val username = userDetails.username
        val user = userRepository.findByUsername(username)
        postId?.let { postService.createLike(it, user) }
        return ResponseEntity.ok().build<Any>()
    }

    @DeleteMapping("/{postId}/delete")
    fun deletePost(@PathVariable postId: Long?):ResponseEntity<*>?{
        postService.delete(postId)
        return ResponseEntity.ok().build<Any>()
    }
    @GetMapping("/{postId}/likes")
    fun getNumberOfLikes(@PathVariable postId: Long): Long {
        return postService.getNumberOfLikes(postId)
    }

    @GetMapping("/{postId}/likes/usernames")
    fun getUsernamesFromPostLikes(@PathVariable postId: Long): List<String> {
        return postService.getUsernamesFromPostLikes(postId)
    }

    @DeleteMapping("/{postId}/likes/delete")
    fun deleteLike(@PathVariable postId: Long) {
        postService.deleteLike(postId)
    }


}