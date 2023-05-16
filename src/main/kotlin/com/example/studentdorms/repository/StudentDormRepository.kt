package com.example.studentdorms.repository

import com.example.studentdorms.domain.StudentDorm
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface StudentDormRepository : JpaRepository<StudentDorm, Long> {
}