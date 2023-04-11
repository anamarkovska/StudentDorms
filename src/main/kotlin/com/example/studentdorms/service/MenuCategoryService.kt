package com.example.studentdorms.service

import com.example.studentdorms.domain.MenuCategory

interface MenuCategoryService {
    fun getAllCategories():List<MenuCategory>?
}