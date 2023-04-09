package com.example.studentdorms.domain.dto

class PostCategoryDto {
    var id: Long? = null
    var name: String = ""

    constructor()

    constructor(id: Long?, name: String) {
        this.id = id
        this.name = name
    }
}