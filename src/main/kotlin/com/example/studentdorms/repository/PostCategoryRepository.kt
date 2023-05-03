package com.example.studentdorms.repository

import com.example.studentdorms.domain.PostCategory
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface PostCategoryRepository : JpaRepository<PostCategory,Long> {

}