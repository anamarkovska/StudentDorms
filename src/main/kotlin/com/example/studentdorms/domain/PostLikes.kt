package com.example.studentdorms.domain

import java.io.Serializable
import javax.persistence.*
import java.util.*

@Entity
@Table(name = "post_likes")
class PostLikes(
    @EmbeddedId
    var id: PostLikesId = PostLikesId(),

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("postId")
    var post: Post? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    var user: User? = null
)

@Embeddable
class PostLikesId(
    @Column(name = "post_id")
    var postId: Long? = null,

    @Column(name = "user_id")
    var userId: Long? = null
) : Serializable