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
    override fun toggleLike(post: Post, user: User?) {
        val postLike: PostLikes? = repository.findPostLikeByPostAndUser(post, user)
        if (postLike != null) {
            repository.delete(postLike)
        } else {
            val userLikesPost = PostLikes(post, user)
            repository.save(userLikesPost)
        }
    }
}