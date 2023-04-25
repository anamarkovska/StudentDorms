package com.example.studentdorms.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import javax.persistence.*


@Entity
@Table(name = "users")
open class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    var username: String = ""

    var password: String = ""

    var isAdmin: Boolean = false
    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    var roles: MutableSet<Role> = mutableSetOf()

    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL], targetEntity = Comment::class)
    var comments: List<Comment> = emptyList()

    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL], targetEntity = Post::class)
    @JsonIgnore // Exclude posts from JSON serialization to break circular reference
    var posts: List<Post> = emptyList()
    constructor()

    constructor(
        id: Long?,
        username: String,
        password: String,
        isAdmin: Boolean,
        posts: List<Post> = emptyList(),
        comments: List<Comment> = emptyList(),
        roles: MutableSet<Role> = mutableSetOf()

        ) {
        this.id = id
        this.username = username
        this.password = password
        this.isAdmin = isAdmin
        this.posts = posts
        this.comments = comments
        this.roles = roles

    }
}