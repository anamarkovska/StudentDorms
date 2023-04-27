package com.example.studentdorms.mapper

import com.example.studentdorms.domain.PostCategory
import com.example.studentdorms.domain.dto.PostCategoryDto
import org.springframework.stereotype.Component

@Component
class PostCategoryMapper {

    fun toDto(postCategory: PostCategory): PostCategoryDto {
        return PostCategoryDto(
            id = postCategory.id,
            name = postCategory.name
        )
    }

    fun toDtoList(postCategories: List<PostCategory>): List<PostCategoryDto> {
        return postCategories.map { toDto(it) }
    }
}
