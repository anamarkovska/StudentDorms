package com.example.studentdorms.service

import com.example.studentdorms.domain.MenuCategory
import com.example.studentdorms.domain.MenuItem

interface MenuItemService {
    fun getAllMenuItems(): List<MenuItem?>?
    fun getMenuItemById(id: Long?): MenuItem?
    fun createMenuItem(menuItem: MenuItem?): MenuItem?
    fun updateMenuItem(id: Long?, menuItem: MenuItem?): MenuItem?
    fun deleteMenuItem(id: Long?)
    fun getMenuItemsByCategory(id: Long) : List<MenuItem>?
    fun getMenuItemsByStudentDorm(id:Long):List<MenuItem>?


}