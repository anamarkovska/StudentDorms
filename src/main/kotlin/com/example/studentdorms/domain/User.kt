package com.example.studentdorms.domain

import jakarta.persistence.*

@Entity
@Table(name = "users")
open class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    var username: String = ""

    var password: String = ""

    var isAdmin: Boolean = false

    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL], targetEntity = Post::class)
    var posts: List<Post> = emptyList()

    @field:ManyToMany
    @JoinTable(
        name = "user_liked_posts",
        joinColumns = [JoinColumn(name = "user_id")],
        inverseJoinColumns = [JoinColumn(name = "post_id")]
    )
    var likedPosts: MutableSet<Post> = mutableSetOf()
    constructor()

    constructor(
        id: Long?,
        username: String,
        password: String,
        isAdmin: Boolean,
        posts: List<Post> = emptyList(),
        likedPosts: MutableSet<Post> // Changed to MutableSet<Post>
    ) {
        this.id = id
        this.username = username
        this.password = password
        this.isAdmin = isAdmin
        this.posts = posts
        this.likedPosts = likedPosts
    }
}
