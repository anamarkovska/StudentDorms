package com.example.studentdorms.api

import com.example.studentdorms.service.MenuItemService
import com.example.studentdorms.domain.MenuItem
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/menu")
class MenuItemController(private val menuItemService: MenuItemService) {

    @PostMapping
    fun createMenuItem(@RequestBody menuItem: MenuItem): MenuItem? {
        return menuItemService.createMenuItem(menuItem)
    }

    @GetMapping("/allItems")
    fun getAllMenuItems(): List<MenuItem?>? {
        System.out.println(menuItemService.getAllMenuItems())
        return menuItemService.getAllMenuItems()
    }

    @GetMapping("/{id}")
    fun getMenuItem(@PathVariable id: Long): MenuItem? {
        return menuItemService.getMenuItemById(id)
    }

    @PutMapping("/{id}")
    fun updateMenuItem(@PathVariable id: Long, @RequestBody menuItem: MenuItem): MenuItem? {
        return menuItemService.updateMenuItem(id, menuItem)
    }

    @DeleteMapping("/{id}")
    fun deleteMenuItem(@PathVariable id: Long) {
        menuItemService.deleteMenuItem(id)
    }

    @GetMapping("/category/{categoryId}")
    fun getMenuItemsByCategory(@PathVariable categoryId: Long): List<MenuItem>? {
        return menuItemService.getMenuItemsByCategory(categoryId)
    }

}
