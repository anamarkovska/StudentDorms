package com.example.studentdorms.domain

import javax.persistence.*

@Entity
@Table(name = "post_categories")
class PostCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    var name: String = ""

    @OneToMany(mappedBy = "postCategory", cascade = [CascadeType.ALL], targetEntity = Post::class)
    var posts: List<Post> = emptyList()

    constructor()

    constructor(id: Long?, name: String, posts: List<Post> = emptyList()) {
        this.id = id
        this.name = name
        this.posts = posts
    }

}
