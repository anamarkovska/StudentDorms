package com.example.studentdorms.domain

import jakarta.persistence.*
import java.time.LocalDate
import java.time.LocalDateTime

@Entity
@Table(name = "posts")
open class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    var title: String = ""

    var content: String = ""

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    var user: User? = null

    @OneToMany(mappedBy = "post", cascade = [CascadeType.ALL], targetEntity = Comment::class)
    var comments: List<Comment> = emptyList()

    @field:ManyToMany(mappedBy = "likedPosts", targetEntity = User::class)    var likedBy: MutableSet<User> = mutableSetOf()
    var createdAt: LocalDateTime = LocalDateTime.now()

    constructor()

    constructor(
        id: Long?,
        title: String,
        content: String,
        user: User?,
        comments: List<Comment> = emptyList(),
        likedBy: MutableSet<User> = mutableSetOf(), // Changed to MutableSet<User>
        createdAt: LocalDateTime = LocalDateTime.now()
    ) {
        this.id = id
        this.title = title
        this.content = content
        this.user = user
        this.comments = comments
        this.likedBy = likedBy
        this.createdAt = createdAt
    }
}
