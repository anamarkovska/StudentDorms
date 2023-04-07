package com.example.studentdorms.repository

import com.example.studentdorms.domain.MenuItem
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MenuItemRepository : JpaRepository<MenuItem,Long>{
    fun findByCategoryId(categoryId: Long): List<MenuItem>
    fun findByStudentDormId(dormId:Long):List<MenuItem>
}