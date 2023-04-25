package com.example.studentdorms

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan


@SpringBootApplication
@ComponentScan(basePackages = ["com.example.studentdorms"])
class StudentDormsApplication

fun main(args: Array<String>) {
    runApplication<StudentDormsApplication>(*args)
}
