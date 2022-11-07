package com.learn.tasktracker.model

import java.time.LocalDateTime

data class Task(
    val id: Long,
    val name: String,
    val description: String,
    val createdDate: LocalDateTime = LocalDateTime.now(),
    val taskStartDts: LocalDateTime = LocalDateTime.now(),
    val taskEndDts: LocalDateTime = LocalDateTime.now().minusHours(1),
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
}
