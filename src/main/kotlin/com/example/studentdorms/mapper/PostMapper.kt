package com.example.studentdorms.mapper

import com.example.studentdorms.domain.Post
import com.example.studentdorms.domain.dto.PostDto
import org.springframework.stereotype.Component
import java.util.*

@Component
class PostMapper(private val userMapper: UserMapper, private val commentMapper: CommentMapper, private val postCategoryMapper: PostCategoryMapper) {

    fun toDto(post: Post): PostDto {
        return PostDto(
            id = post.id,
            title = post.title,
            content = post.content,
            userDto = post.user?.let { userMapper.toDto(it) },
//            likedBy = userMapper.toDtoList(post.likedBy),
            postCategoryDto = post.postCategory?.let { postCategoryMapper.toDto(it) },
            comments = commentMapper.toDtoList(post.comments)
        )
    }

    fun toDtoList(posts: List<Post>): List<PostDto> {
        return posts.map { toDto(it) }
    }
}
