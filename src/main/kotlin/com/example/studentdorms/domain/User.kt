package com.example.studentdorms.domain

import jakarta.persistence.*

@Entity
@Table(name = "users")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    val username: String,

    val password: String,

    val isAdmin: Boolean = false,

    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL])
    val posts: List<Post> = emptyList(),

    @ManyToMany
    @JoinTable(
        name = "user_liked_posts",
        joinColumns = [JoinColumn(name = "user_id")],
        inverseJoinColumns = [JoinColumn(name = "post_id")]
    )
    val likedPosts: List<Post> = emptyList(),
)