package com.example.studentdorms.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import javax.persistence.Entity
import javax.persistence.*


@Entity
@Table(name = "users")
open class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Column(unique = true)
    var username: String = ""

    var password: String = ""

    var isAdmin: Boolean = false

    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL], targetEntity = Comment::class)
    var comments: List<Comment> = emptyList()

    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL], targetEntity = Post::class)
    @JsonIgnore // Exclude posts from JSON serialization to break circular reference
    var posts: List<Post> = emptyList()

//    @ManyToMany
//    @JoinTable(
//        name = "user_liked_posts",
//        joinColumns = [JoinColumn(name = "user_id")],
//        inverseJoinColumns = [JoinColumn(name = "post_id")]
//    )
//    var likedPosts: MutableSet<Post> = mutableSetOf()

    constructor()

    constructor(
        id: Long?,
        username: String,
        password: String,
        isAdmin: Boolean,
        posts: List<Post> = emptyList(),
        comments: List<Comment> = emptyList(),

        ) {
        this.id = id
        this.username = username
        this.password = password
        this.isAdmin = isAdmin
        this.posts = posts
        this.comments = comments

    }
}