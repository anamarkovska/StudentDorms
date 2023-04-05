package com.example.studentdorms.domain
import jakarta.persistence.*
import java.time.LocalDate
import java.time.LocalDateTime


@Entity
@Table(name = "posts")
data class Post(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    val title: String,

    val content: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    val user: User,

    @OneToMany(mappedBy = "post", cascade = [CascadeType.ALL])
    val comments: List<Comment> = emptyList(),

    @ManyToMany(mappedBy = "likedPosts")
    val likedBy: List<User> = emptyList(),

    val createdAt: LocalDateTime = LocalDateTime.now(),
)