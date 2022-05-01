package com.pavan.tasktracker.model

import java.time.LocalDateTime

data class Task(
    val id: Long,
    val name: String,
    val description: String,
    val createdDate: LocalDateTime,
    val startDate: LocalDateTime?,
    val finishDate: LocalDateTime?
) {
}
