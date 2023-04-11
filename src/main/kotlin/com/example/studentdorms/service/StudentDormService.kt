package com.example.studentdorms.service

import com.example.studentdorms.domain.StudentDorm

interface StudentDormService {
    fun getAllStudentDorms():List<StudentDorm>?
}