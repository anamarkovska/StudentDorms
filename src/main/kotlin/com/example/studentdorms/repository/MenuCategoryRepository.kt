package com.example.studentdorms.repository

import com.example.studentdorms.domain.MenuCategory
import org.springframework.data.jpa.repository.JpaRepository

interface MenuCategoryRepository : JpaRepository<MenuCategory,Long> {
}