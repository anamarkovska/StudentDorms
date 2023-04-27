package com.example.studentdorms.mapper

import com.example.studentdorms.domain.MenuItem
import com.example.studentdorms.domain.dto.MenuItemDTO
import org.springframework.stereotype.Component

@Component
class MenuItemMapper {

    fun toDto(menuItem: MenuItem): MenuItemDTO {
        return MenuItemDTO(
            id = menuItem.id,
            name = menuItem.name,
            categoryId = menuItem.category?.id,
            studentDormId = menuItem.studentDorm?.id,
            date = menuItem.date,
            startTime = menuItem.startTime,
            endTime = menuItem.endTime
        )
    }

    fun toDtoList(menuItems: List<MenuItem>): List<MenuItemDTO> {
        return menuItems.map { toDto(it) }
    }
}