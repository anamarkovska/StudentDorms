package com.example.studentdorms.api
import com.example.studentdorms.domain.dto.CommentDto
import com.example.studentdorms.exceptions.PostNotFoundException
import com.example.studentdorms.exceptions.UnauthorizedUserException
import com.example.studentdorms.service.CommentService
import com.example.studentdorms.service.JwtUserDetailsService
import com.example.studentdorms.service.PostService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.security.Principal

@RestController
@RequestMapping("/api/comments")
class CommentController(
    private val commentService: CommentService,
    private val userService: JwtUserDetailsService,
    private val postService: PostService
) {

    @GetMapping
    fun getAllComments(): List<CommentDto> {
        return commentService.getAllComments()
    }
    @GetMapping("/{postId}")
    fun getCommentsByPostId(@PathVariable postId: Long): ResponseEntity<List<CommentDto>> {
        val comments = commentService.getCommentsByPostId(postId)
        return ResponseEntity.ok(comments)
    }

    @PostMapping("/{postId}")
    fun createComment(@RequestBody commentDto: CommentDto, @PathVariable postId: Long): ResponseEntity<Any> {
        try {
            commentService.createComment(commentDto, postId)
            return ResponseEntity.status(HttpStatus.CREATED).build()
        } catch (e: Exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()
        }
    }
    @DeleteMapping("/delete/{id}")
    fun deleteComment(@PathVariable id: Long, principal: Principal): ResponseEntity<Any> {
        val comment = commentService.getCommentById(id)
        val currentUser = userService.loadUserByUsername(principal.name)
        val post = postService.getPostById(comment.postId)

        if (currentUser?.username == principal.name || post?.userDto?.username == principal.name) {
            commentService.deleteComment(id)
            return ResponseEntity.ok().build()
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build()
        }
    }
    @PutMapping("/update")
    fun updateComment(@RequestBody commentDto: CommentDto, principal: Principal): ResponseEntity<CommentDto> {
        try {
            val updatedComment = commentService.updateComment(commentDto, principal)
            return ResponseEntity.ok(updatedComment)
        } catch (e: PostNotFoundException) {
            return ResponseEntity.notFound().build()
        } catch (e: UnauthorizedUserException) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()
        }
    }

}
