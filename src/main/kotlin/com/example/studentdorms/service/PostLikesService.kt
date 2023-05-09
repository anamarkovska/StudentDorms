package com.example.studentdorms.service

import com.example.studentdorms.domain.Post
import com.example.studentdorms.domain.User
import java.util.*


interface PostLikesService {
//    fun toggleLike(post: Post, user: User?)
    fun hasLikedPost(postId: Long): Boolean?
}