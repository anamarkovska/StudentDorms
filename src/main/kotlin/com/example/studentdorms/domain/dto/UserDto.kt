package com.example.studentdorms.domain.dto


open class UserDto {
    var id: Long? = null
    var username: String = ""

    constructor()

    constructor(id: Long?, username: String) {
        this.id = id
        this.username = username
    }
}