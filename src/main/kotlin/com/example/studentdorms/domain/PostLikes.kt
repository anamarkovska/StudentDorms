package com.example.studentdorms.domain

import javax.persistence.*
import java.util.*

@Entity
@Table(name = "post_likes")
open class PostLikes {

    @Id
    var id: Long? = null

    @field:JoinColumn(name = "post_id")
    @field:ManyToOne
    var post: Post? = null

    @field:JoinColumn(name = "user_id")
    @field:ManyToOne
    var user: User? = null

    constructor()

    constructor(user: User, post: Post){
        this.user=user
        this.post=post
    }
    constructor(post: Post, user: User?){
        this.post=post
        this.user=user
    }
}
