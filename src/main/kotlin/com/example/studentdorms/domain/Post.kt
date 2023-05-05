package com.example.studentdorms.domain

import com.example.studentdorms.domain.dto.PostCreationDto
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import javax.persistence.*
import java.sql.Timestamp
import java.time.Instant
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(name = "posts")
open class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    var title: String = ""

    var content: String = ""

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties("posts")
    @JoinColumn(name = "user_id")
    var user: User? = null

    @OneToMany(mappedBy = "post", cascade = [CascadeType.ALL], targetEntity = Comment::class)
    var comments: List<Comment> = emptyList()

    @ManyToMany
    @JoinTable(
        name = "post_likes",
        joinColumns = arrayOf(JoinColumn(name = "post_id")),
        inverseJoinColumns = arrayOf(JoinColumn(name = "user_id"))
    )
    var likedBy: MutableSet<User> = mutableSetOf()
//    @OneToMany(mappedBy = "post", cascade = [CascadeType.ALL], targetEntity = PostLikes::class)


    var createdAt: LocalDateTime = LocalDateTime.now()

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties("posts")
    @JoinColumn(name = "post_category_id")
    var postCategory: PostCategory? = null

    constructor()

    constructor(
        id: Long?,
        title: String,
        content: String,
        user: User?,
        comments: List<Comment> = emptyList(),
        likedBy: MutableSet<User> = mutableSetOf(),
        createdAt: LocalDateTime = LocalDateTime.now(),
        postCategory: PostCategory? = null
    ) {
        this.id = id
        this.title = title
        this.content = content
        this.user = user
        this.comments = comments
        this.likedBy = likedBy
        this.createdAt = createdAt
        this.postCategory = postCategory
    }

    constructor(postCreationDto: PostCreationDto, postCategory:PostCategory, user: User) {
        this.id = id
        this.title = postCreationDto.title
        this.content = postCreationDto.content
        this.user = user
        this.comments = comments
        this.likedBy = likedBy
        this.createdAt = createdAt
        this.postCategory = postCategory
    }
}
