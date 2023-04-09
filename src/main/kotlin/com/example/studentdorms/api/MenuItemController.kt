package com.example.studentdorms.api

import com.example.studentdorms.domain.MenuItem
import com.example.studentdorms.service.MenuItemService
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import jakarta.transaction.Transactional
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/menu")
class MenuItemController(private val menuItemService: MenuItemService) {

    @GetMapping
    fun getAllMenuItems(): ResponseEntity<List<MenuItem?>> {
        val allMenuItems = menuItemService.getAllMenuItems()
        return ResponseEntity.ok(allMenuItems)
    }

    @PostMapping
    fun createMenuItem(@RequestBody menuItem: MenuItem): ResponseEntity<MenuItem> {
        val createdMenuItem = menuItemService.createMenuItem(menuItem)
        return ResponseEntity.ok(createdMenuItem)
    }

    @GetMapping("/{id}")
    fun getMenuItem(@PathVariable id: Long): ResponseEntity<MenuItem?> {
        val menuItem = menuItemService.getMenuItemById(id)
        return if (menuItem != null) {
            ResponseEntity.ok(menuItem)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @PutMapping("/{id}")
    fun updateMenuItem(@PathVariable id: Long, @RequestBody menuItem: MenuItem): ResponseEntity<MenuItem?> {
        val updatedMenuItem = menuItemService.updateMenuItem(id, menuItem)
        return if (updatedMenuItem != null) {
            ResponseEntity.ok(updatedMenuItem)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("/{id}")
    fun deleteMenuItem(@PathVariable id: Long): ResponseEntity<Unit> {
        menuItemService.deleteMenuItem(id)
        return ResponseEntity.ok().build()
    }

    @GetMapping("/category/{categoryId}")
    fun getMenuItemsByCategory(@PathVariable categoryId: Long): ResponseEntity<List<MenuItem>> {
        val menuItemsByCategory = menuItemService.getMenuItemsByCategory(categoryId)
        return ResponseEntity.ok(menuItemsByCategory)
    }

    @GetMapping("/student-dorms/{dormId}")
    fun getMenuItemsByStudentDorm(@PathVariable dormId: Long): ResponseEntity<List<MenuItem>> {
        val menuItemsByStudentDorm = menuItemService.getMenuItemsByStudentDorm(dormId)
        return ResponseEntity.ok(menuItemsByStudentDorm)
    }

}
