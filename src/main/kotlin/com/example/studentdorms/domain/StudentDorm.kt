package com.example.studentdorms.domain

import javax.persistence.*

@Entity
@Table(name = "student_dorms")
open class StudentDorm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    var dormName: String = ""

    @OneToMany(mappedBy = "studentDorm", cascade = [CascadeType.ALL], fetch = FetchType.LAZY, targetEntity = MenuItem::class)
    var menuItems: List<MenuItem> = emptyList()

    constructor()

    constructor(id: Long?, dormName: String, menuItems: List<MenuItem>) {
        this.id = id
        this.dormName = dormName
        this.menuItems = menuItems
    }
}
