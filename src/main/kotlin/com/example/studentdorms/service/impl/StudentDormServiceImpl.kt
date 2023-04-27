package com.example.studentdorms.service.impl

import com.example.studentdorms.domain.StudentDorm
import com.example.studentdorms.repository.StudentDormRepository
import com.example.studentdorms.service.StudentDormService
import org.springframework.stereotype.Service

@Service
class StudentDormServiceImpl(val repository: StudentDormRepository) : StudentDormService {

    override fun getAllStudentDorms(): List<StudentDorm>? {
        return repository.findAll()
    }
}