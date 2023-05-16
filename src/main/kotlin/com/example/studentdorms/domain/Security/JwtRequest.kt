package com.example.studentdorms.domain.Security

import java.io.Serializable


open class JwtRequest : Serializable {
    var username: String = ""

    var password: String = ""

    constructor()
    constructor(username: String, password: String) {
        this.username = username
        this.password = password
    }

    companion object {
        private const val serialVersionUID = 5926468583005150707L
    }

}