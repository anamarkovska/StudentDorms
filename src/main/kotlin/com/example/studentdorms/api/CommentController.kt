package com.example.studentdorms.api
import com.example.studentdorms.domain.dto.CommentDto
import com.example.studentdorms.service.CommentService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/comments")
class CommentController(
    private val commentService: CommentService
) {

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
    fun deleteComment(@PathVariable id: Long) {
        commentService.deleteComment(id)
    }
}
