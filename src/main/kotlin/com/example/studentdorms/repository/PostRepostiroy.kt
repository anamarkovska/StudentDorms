package com.example.studentdorms.repository

import com.example.studentdorms.domain.Post
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PostRepostiroy:JpaRepository<Post,Long> {
}