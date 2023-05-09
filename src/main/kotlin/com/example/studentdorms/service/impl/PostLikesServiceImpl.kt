package com.example.studentdorms.service.impl

import com.example.studentdorms.domain.Post
import com.example.studentdorms.domain.PostLikes
import com.example.studentdorms.domain.User
import com.example.studentdorms.repository.PostLikesRepository
import com.example.studentdorms.repository.UserRepository
import com.example.studentdorms.service.JwtUserDetailsService
import com.example.studentdorms.service.PostLikesService
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import java.util.*

@Service
class PostLikesServiceImpl(val repository: PostLikesRepository,
                           val userService: JwtUserDetailsService,
                           val userRepository: UserRepository
) : PostLikesService {
//    override fun toggleLike(post: Post, user: User?) {
//        val postLike: PostLikes? = repository.findPostLikeByPostAndUser(post, user)
//        if (postLike != null) {
//            repository.delete(postLike)
//        } else {
//            val userLikesPost = PostLikes(user,post)
//            repository.save(userLikesPost)
//        }
//    }
   override fun hasLikedPost(postId: Long): Boolean? {
   val userDetails: UserDetails = userService.findAuthenticatedUser();
   val user: User = userRepository.findByUsername(userDetails.username)
   return user.id?.let { repository.existsByPostIdAndUserId(postId, it) }

}
}