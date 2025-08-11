package com.learn.tasktracker.model

import java.time.LocalDateTime
import java.time.Duration

class TimePiece(val startTime: LocalDateTime, var stopTime: LocalDateTime?) {
    override fun toString(): String {
        return "TimePiece{startTime:$startTime, stopTime:$stopTime}"
    }

    fun getTimeDiffInHours(): Int {
        return if (stopTime != null) {
            Duration.between(startTime, stopTime).toHours().toInt()
        } else {
            0
        }
    }
}

