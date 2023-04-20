package com.example.studentdorms.api
import com.example.studentdorms.domain.MenuCategory
import com.example.studentdorms.domain.MenuItem
import com.example.studentdorms.domain.StudentDorm
import com.example.studentdorms.domain.dto.MenuItemDTO
import com.example.studentdorms.repository.StudentDormRepository
import com.example.studentdorms.service.MenuCategoryService
import com.example.studentdorms.service.MenuItemService
import com.example.studentdorms.service.StudentDormService
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import jakarta.transaction.Transactional
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin(origins = ["http://localhost:4200"], allowedHeaders = ["*"], allowCredentials = "true", methods = [RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE])
@RequestMapping("/menu")
class MenuItemController(private val menuItemService: MenuItemService,
                         private val categoryService: MenuCategoryService,
                         private val studentDormService: StudentDormService) {

    @GetMapping
    fun getAllMenuItems(): ResponseEntity<List<MenuItem?>> {
        val allMenuItems = menuItemService.getAllMenuItems()
        return ResponseEntity.ok(allMenuItems)
    }

    @PostMapping
    fun createMenuItem(@RequestBody menuItem: MenuItemDTO): ResponseEntity<MenuItem> {
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

    @PutMapping("/edit/{id}")
    fun updateMenuItem(@PathVariable id: Long, @RequestBody menuItem: MenuItemDTO): ResponseEntity<MenuItem?> {
        val updatedMenuItem = menuItemService.updateMenuItem(id, menuItem)
        return if (updatedMenuItem != null) {
            ResponseEntity.ok(updatedMenuItem)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("/delete/{id}")
    fun deleteMenuItem(@PathVariable id: Long): ResponseEntity<Unit> {
        menuItemService.deleteMenuItem(id)
        return ResponseEntity.ok().build()
    }

    @GetMapping("/category")
    fun getAllCategories(): List<MenuCategory>? {
        return categoryService.getAllCategories()
    }

    @GetMapping("/student-dorms")
    fun getAllStudentDorms() : List<StudentDorm>? {
        return studentDormService.getAllStudentDorms()
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

//    @GetMapping("/student-dorms/{dormId}")
//    fun getMenuItemsByCategoryAndStudentDorm(@PathVariable categoryId: Long, @PathVariable dormId: Long):ResponseEntity<List<MenuItem>>{
//        val menuItemsByCategoryAndStudentDorm = menuItemService.getMenuItemsByCategoryAndStudentDorm(categoryId, dormId)
//        return ResponseEntity.ok(menuItemsByCategoryAndStudentDorm)
//    }

}
