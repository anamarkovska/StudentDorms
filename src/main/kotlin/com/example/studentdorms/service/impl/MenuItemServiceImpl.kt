package com.example.studentdorms.service.impl

import com.example.studentdorms.domain.MenuItem
import com.example.studentdorms.repository.MenuItemRepository
import com.example.studentdorms.service.MenuItemService
import org.springframework.stereotype.Service
import java.util.*


@Service
class MenuItemServiceImpl : MenuItemService {

    private val repository: MenuItemRepository? = null

    override fun getAllMenuItems(): List<MenuItem?>? {
        val menuItems = repository?.findAll()?.toList()
        println("Number of items retrieved: ${menuItems?.size}")
        return menuItems
    }

    override fun getMenuItemById(id: Long?): MenuItem? {
        return id?.let { repository?.getById(it) }
    }

    override fun createMenuItem(menuItem: MenuItem?): MenuItem? {
        return menuItem?.let { repository!!.save(it) }
    }

    override fun updateMenuItem(id: Long?, menuItem: MenuItem?): MenuItem? {
        val existingMenuItem = id?.let { repository!!.findById(it).orElse(null) }

        existingMenuItem?.apply {
            name = menuItem?.name!!
            startTime = menuItem?.startTime!!
            endTime = menuItem?.endTime!!
            category = menuItem?.category!!
            date = menuItem?.date!!
        }

        return existingMenuItem?.let { repository!!.save(it) }
    }

    override fun deleteMenuItem(id: Long?) {
        id?.let { repository!!.deleteById(it) }
    }

    override fun getMenuItemsByCategory(id: Long): List<MenuItem>? {
        return repository?.findByCategoryId(id)
    }
}