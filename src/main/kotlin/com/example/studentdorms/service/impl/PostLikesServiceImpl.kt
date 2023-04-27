//package com.example.studentdorms.service.impl
//
//import com.example.studentdorms.domain.Post
//import com.example.studentdorms.domain.PostLikes
//import com.example.studentdorms.domain.User
//import com.example.studentdorms.repository.PostLikesRepository
//import com.example.studentdorms.service.PostLikesService
//import org.springframework.stereotype.Service
//
//@Service
//class PostLikesServiceImpl(val repository: PostLikesRepository) : PostLikesService {
//
////    override fun toggleLike(post: Post, user: User) {
////        val postLike: Unit = repository.findPostLikeByUserAndPost(user,post)
////
////        if (postLike.isPresent()) {
////            repository.delete(postLike.get())
////        } else {
////            val userLikesPost = PostLikes(user,post)
////            repository.save(userLikesPost)
////        }
////    }
//}