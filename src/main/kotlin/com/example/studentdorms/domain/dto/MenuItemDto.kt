package com.example.studentdorms.domain.dto
import java.time.LocalDate
import java.time.LocalTime

class MenuItemDTO(
    var id: Long?,
    var name: String,
    var categoryId: Long?,
    var studentDormId: Long?,
    var date: LocalDate,
    var startTime: LocalTime,
    var endTime: LocalTime
) {
    constructor() : this(null, "", null, null, LocalDate.now(), LocalTime.MIN, LocalTime.MAX)
}



