package com.example.studentdorms.domain
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import javax.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "comments")
open class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    var content: String = ""

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties("comments")
    var user: User? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties("comments")
    @JoinColumn(name = "post_id")
    var post: Post? = null

    var createdAt: LocalDateTime = LocalDateTime.now()

    constructor()

    constructor(id: Long?, content: String, user: User?, post: Post?, createdAt: LocalDateTime) {
        this.id = id
        this.content = content
        this.user = user
        this.post = post
        this.createdAt = createdAt
    }
}