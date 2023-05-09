package com.example.studentdorms.domain.dto


open class UserDto {
    var id: Long? = null
    var username: String = ""
    var password: String = " "

    constructor()

    constructor(id: Long?, username: String, password: String) {
        this.id = id
        this.username = username
        this.password = password
    }

    constructor(username: String, password: String) {
        this.username = username
        this.password = password
    }
}