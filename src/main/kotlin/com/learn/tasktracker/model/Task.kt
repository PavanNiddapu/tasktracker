package com.learn.tasktracker.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document(collection = "tasks")
data class Task(
    @Id
    val id: Long,
    val name: String,
    val description: String,
    val createdDate: LocalDateTime = LocalDateTime.now()
) {
    private var timeLine: ArrayList<TimePiece> = arrayListOf()

    fun startActivity() {
        if (isLastActivityNull()) {
            throw UnExpectedState()
        }
        val timePiece = TimePiece(LocalDateTime.now(), null)
        timeLine.add(timePiece)
    }

    fun stopActivity() {
        if (!isLastActivityNull()) {
            throw UnExpectedState()
        }
        timeLine.last().stopTime = LocalDateTime.now()
    }

    private fun isLastActivityNull() = timeLine.size > 0 && timeLine.last().stopTime == null

    fun timelineSize() = timeLine.size

    fun calculateTotalHoursWorked(): Int {
        return timeLine.map { timePiece -> timePiece.getTimeDiffInHours() }
            .reduce { sum, hours -> sum + hours }

    }
}
