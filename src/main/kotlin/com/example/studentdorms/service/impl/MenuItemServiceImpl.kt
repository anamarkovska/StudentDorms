package com.example.studentdorms.service.impl

import com.example.studentdorms.domain.dto.MenuItemDTO
import com.example.studentdorms.domain.MenuCategory
import com.example.studentdorms.domain.MenuItem
import com.example.studentdorms.repository.MenuCategoryRepository
import com.example.studentdorms.repository.MenuItemRepository
import com.example.studentdorms.repository.StudentDormRepository
import com.example.studentdorms.service.MenuItemService
import jdk.jfr.Category
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.LocalTime
import java.util.*



@Service
class MenuItemServiceImpl(val repository: MenuItemRepository,
                          val categoryRepository: MenuCategoryRepository,
                            val studentDormRepository: StudentDormRepository) : MenuItemService {
    override fun getAllMenuItems(): List<MenuItem?>? {
        return repository.findAll()
    }

    override fun getMenuItemById(id: Long?): MenuItem? {
        return id?.let { repository.getById(it) }
    }

//    override fun createMenuItem(menuItem: MenuItem?): MenuItem? {
//        return menuItem?.let { repository.save(it) }
//    }
override fun createMenuItem(menuItemDto: MenuItemDTO): MenuItem {
    val menuItem = MenuItem()
    menuItem.name = menuItemDto?.name ?: ""
    menuItem.date = menuItemDto?.date ?: LocalDate.now()
    menuItem.startTime = menuItemDto?.startTime ?: LocalTime.MIN
    menuItem.endTime = menuItemDto?.endTime ?: LocalTime.MAX

    menuItemDto?.categoryId?.let { categoryId ->
        val category = categoryRepository.findById(categoryId)
            .orElseThrow { IllegalArgumentException("Invalid category id: $categoryId") }
        menuItem.category = category
    }

    menuItemDto?.studentDormId?.let { studentDormId ->
        val studentDorm = studentDormRepository.findById(studentDormId)
            .orElseThrow { IllegalArgumentException("Invalid student dorm id: $studentDormId") }
        menuItem.studentDorm = studentDorm
    }

    return repository.save(menuItem)
}
//    override fun updateMenuItem(id: Long?, menuItem: MenuItem?): MenuItem? {
//        val existingMenuItem = id?.let { repository.findById(it).orElse(null) }
//
//        existingMenuItem?.apply {
//            if (menuItem != null) {
//                name = menuItem.name
//                startTime = menuItem.startTime
//                endTime = menuItem.endTime
//                category = menuItem.category
//                date = menuItem.date
//                studentDorm = menuItem.studentDorm
//            }
//        }
//
//        return existingMenuItem?.let { repository.save(it) }
//    }

    override fun updateMenuItem(id: Long?, menuItemDTO: MenuItemDTO?): MenuItem? {
        val existingMenuItem = id?.let { repository.findById(it).orElse(null) }

        existingMenuItem?.apply {
            if (menuItemDTO != null) {
                name = menuItemDTO.name
                startTime = menuItemDTO.startTime
                endTime = menuItemDTO.endTime
                date = menuItemDTO.date

                // Fetch the corresponding MenuCategory object from the database
                val category = menuItemDTO.categoryId?.let { categoryId ->
                    categoryRepository.findById(categoryId).orElse(null)
                }
                this.category = category

                // Fetch the corresponding StudentDorm object from the database
                val studentDorm = menuItemDTO.studentDormId?.let { studentDormId ->
                    studentDormRepository.findById(studentDormId).orElse(null)
                }
                this.studentDorm = studentDorm
            }
        }

        return existingMenuItem?.let { repository.save(it) }
    }



    override fun deleteMenuItem(id: Long?) {
        id?.let { repository.deleteById(it) }
    }

    override fun getMenuItemsByCategory(id: Long): List<MenuItem>? {
        return repository.findByCategoryId(id)
    }

    override fun getMenuItemsByStudentDorm(id: Long): List<MenuItem>? {
        return repository.findByStudentDormId(id)
    }

    override fun getMenuItemsByCategoryAndStudentDorm(category: Long, studentDorm: Long): List<MenuItem>? {
        return repository.findByCategoryAndStudentDorm(category!!, studentDorm!!)
    }

}