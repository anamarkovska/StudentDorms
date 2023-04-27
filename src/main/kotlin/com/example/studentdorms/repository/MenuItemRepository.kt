package com.example.studentdorms.repository

import com.example.studentdorms.domain.MenuItem
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MenuItemRepository : JpaRepository<MenuItem,Long>{
    fun findByCategoryId(categoryId: Long): List<MenuItem>
    fun findByStudentDormId(dormId:Long):List<MenuItem>

    fun findByCategoryAndStudentDorm(categoryId: Long, dormId: Long):List<MenuItem>

//    interface MenuItemRepository : JpaRepository<MenuItem, Long> {
//        @Query("SELECT mi FROM MenuItem mi WHERE mi.category.id = :categoryId AND mi.studentDorm.id = :studentDormId")
//        fun findByCategoryAndStudentDorm(@Param("categoryId") categoryId: Long, @Param("studentDormId") studentDormId: Long): List<MenuItem>
//    }

}