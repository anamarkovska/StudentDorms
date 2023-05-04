package com.example.studentdorms.api

import com.example.studentdorms.domain.dto.PostCreationDto
import com.example.studentdorms.domain.dto.PostDto
import com.example.studentdorms.service.PostService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin
@RequestMapping("/api/posts")
class PostController(private val postService: PostService) {

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

    @PutMapping("/{postId}/like")
    fun likePost(@PathVariable postId: Long?): ResponseEntity<*>? {
        postService.like(postId)
        return ResponseEntity.ok().build<Any>()
    }

    @DeleteMapping("/{postId}/delete")
    fun deletePost(@PathVariable postId: Long?):ResponseEntity<*>?{
        postService.delete(postId)
        return ResponseEntity.ok().build<Any>()
    }

}