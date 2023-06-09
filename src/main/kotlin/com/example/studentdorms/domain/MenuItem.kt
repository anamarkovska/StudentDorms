package com.example.studentdorms.domain

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import jakarta.persistence.*
import java.time.LocalDate
import java.time.LocalTime
@Entity
@Table(name = "menu_items")
open class MenuItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    var name: String = ""

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    @JsonIgnoreProperties("items")
    var category: MenuCategory? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties("menuItems")
    var studentDorm:StudentDorm? = null

    var date: LocalDate = LocalDate.now()

    var startTime: LocalTime = LocalTime.MIN

    var endTime: LocalTime = LocalTime.MAX

    constructor()

    constructor(
        id: Long?,
        name: String,
        category: MenuCategory?,
        date: LocalDate,
        startTime: LocalTime,
        endTime: LocalTime
    ) {
        this.id = id
        this.name = name
        this.category = category
        this.date = date
        this.startTime = startTime
        this.endTime = endTime
    }
}

