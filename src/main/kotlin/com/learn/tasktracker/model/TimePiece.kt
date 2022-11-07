package com.learn.tasktracker.model

import java.time.LocalDateTime

class TimePiece(val startTime: LocalDateTime, var stopTime: LocalDateTime?) {
    override fun toString(): String {
        return "TimePiece{startTime:$startTime, stopTime:$stopTime}"
    }
}

