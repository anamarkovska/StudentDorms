package com.example.studentdorms.service.impl

import com.example.studentdorms.domain.Post
import com.example.studentdorms.domain.PostLikes
import com.example.studentdorms.domain.User
import com.example.studentdorms.repository.PostLikesRepository
import com.example.studentdorms.service.PostLikesService
import org.springframework.stereotype.Service
import java.util.*

@Service
class PostLikesServiceImpl(val repository: PostLikesRepository) : PostLikesService {
    override fun toggleLike(post: Optional<Post>, user: User?) {
        val p = post.orElseThrow { IllegalArgumentException("Post not found") }
        val postLike: PostLikes? = repository.findPostLikeByPostAndUser(p, user)
        if (postLike != null) {
            repository.delete(postLike)
        } else {
            val userLikesPost = PostLikes(p, user)
            repository.save(userLikesPost)
        }
    }
}