package com.example.studentdorms.domain

import jakarta.persistence.*


@Entity
@Table(name = "menu_categories")
data class MenuCategory(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    val name: String,

    @OneToMany(mappedBy = "category", cascade = [CascadeType.ALL])
    val items: List<MenuItem> = emptyList()
)