package com.example.studentdorms.domain

import javax.persistence.*
import java.time.LocalDate
import java.time.LocalTime


@Entity
@Table(name = "menu_categories")
open class MenuCategory(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(nullable = false)
    var name: String = "",

    @OneToMany(
        mappedBy = "category",
        cascade = [CascadeType.ALL],
        fetch = FetchType.LAZY,
        targetEntity = MenuItem::class
    )
    var items: List<MenuItem> = emptyList()
) {
    constructor() : this(null, "", emptyList())

    constructor(name: String) : this(null, name, emptyList())

    constructor(name: String, items: List<MenuItem>) : this(null, name, items)
}
