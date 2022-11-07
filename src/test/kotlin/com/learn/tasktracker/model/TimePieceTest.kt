package com.learn.tasktracker.model

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class TimePieceTest {
    @Test
    fun `get difference between startTime and endTime in hours`() {
        val startTime = LocalDateTime.now()
        val timePiece = TimePiece(startTime, startTime.plusHours(1))
        timePiece.getTimeDiffInHours() shouldBe 1
    }
}