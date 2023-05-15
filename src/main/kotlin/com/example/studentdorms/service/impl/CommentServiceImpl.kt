package com.example.studentdorms.service.impl

import com.example.studentdorms.domain.Comment
import com.example.studentdorms.domain.Post
import com.example.studentdorms.domain.User
import com.example.studentdorms.domain.dto.CommentDto
import com.example.studentdorms.exceptions.PostNotFoundException
import com.example.studentdorms.exceptions.UnauthorizedUserException
import com.example.studentdorms.mapper.CommentMapper
import com.example.studentdorms.repository.CommentRepository
import com.example.studentdorms.repository.PostRepostiroy
import com.example.studentdorms.repository.UserRepository
import com.example.studentdorms.service.CommentService
import com.example.studentdorms.service.JwtUserDetailsService
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties.Jwt
import org.springframework.data.crossstore.ChangeSetPersister
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import java.security.Principal
import javax.persistence.EntityNotFoundException

@Service
class CommentServiceImpl(
    private val commentRepository: CommentRepository,
    private val commentMapper: CommentMapper,
    private val postRepository:PostRepostiroy,
    private val userService: JwtUserDetailsService,
    private val userRepository: UserRepository
) : CommentService {

    override fun getAllComments(): List<CommentDto> {
        val comments = commentRepository.findAll()
        return commentMapper.toDtoList(comments)
    }

    override fun createComment(commentDto: CommentDto, postId: Long) {
        val userDetails: UserDetails = userService.findAuthenticatedUser();
        val user: User = userRepository.findByUsername(userDetails.username)
        val post: Post? = postRepository.findById(postId).orElse(null)
        post?.let {
            val post = Comment(commentDto, it, user)
            commentRepository.save(post)
        }
    }

    override fun deleteComment(commentId: Long) {
        if (commentRepository.existsById(commentId)) {
            commentRepository.deleteById(commentId)
        } else {
            throw EntityNotFoundException("Comment with id $commentId not found.")
        }
    }

    override fun getCommentById(commentId: Long): CommentDto {
        val comment = commentRepository.findById(commentId)
            .orElseThrow { ChangeSetPersister.NotFoundException() }
        return commentMapper.toDto(comment)
    }

    override fun getCommentsByPostId(postId: Long): List<CommentDto> {
        val comments = commentRepository.findByPostId(postId)
        return commentMapper.toDtoList(comments)

    }

    override fun updateComment(commentDto: CommentDto, principal: Principal): CommentDto {
        val comment = commentRepository.findById(commentDto.id ?: throw PostNotFoundException("Comment not found"))

        if (comment.isPresent) {
            val currentUser = userService.loadUserByUsername(principal.name)
            val post: Post? = commentDto.postId?.let { postRepository.findById(it).orElse(null) }

            if (currentUser?.username == comment.get().user?.username || currentUser?.username == post?.user?.username) {
                comment.get().content = commentDto.content
                commentRepository.save(comment.get())
                return commentMapper.toDto(comment.get())
            } else {
                throw UnauthorizedUserException("You are not authorized to update this comment.")
            }
        } else {
            throw PostNotFoundException("Comment not found.")
        }
    }
}


