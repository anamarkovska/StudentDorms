package com.example.studentdorms.service

import com.example.studentdorms.domain.MenuCategory
import com.example.studentdorms.domain.MenuItem
import com.example.studentdorms.domain.dto.MenuItemDTO

interface MenuItemService {
    fun getAllMenuItems(): List<MenuItem?>?
    fun getMenuItemById(id: Long?): MenuItem?
    fun createMenuItem(menuItemDto: MenuItemDTO): MenuItem
    fun updateMenuItem(id: Long?, menuItemDto: MenuItemDTO?): MenuItem?
    fun deleteMenuItem(id: Long?)
    fun getMenuItemsByCategory(id: Long): List<MenuItem>?
    fun getMenuItemsByStudentDorm(id: Long): List<MenuItem>?
    fun getMenuItemsByCategoryAndStudentDorm(category: Long, studentDorm: Long): List<MenuItem>?


}