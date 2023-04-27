package com.example.studentdorms.service.impl

import com.example.studentdorms.domain.MenuCategory
import com.example.studentdorms.repository.MenuCategoryRepository
import com.example.studentdorms.service.MenuCategoryService
import org.springframework.stereotype.Service

@Service
class MenuCategoryServiceImpl(val categoryRepository:MenuCategoryRepository) : MenuCategoryService{

    override fun getAllCategories(): List<MenuCategory>? {
        return categoryRepository.findAll()
    }
}