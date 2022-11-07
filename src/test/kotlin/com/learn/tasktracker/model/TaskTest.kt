package com.learn.tasktracker.model

import io.kotest.extensions.time.withConstantNow
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.time.LocalDateTime

class TaskTest {

    @Test
    fun `task should have created date`() {
        val someFixedDts = LocalDateTime.of(2022, 11, 11, 11, 11, 11)
        withConstantNow(someFixedDts) {
            val task = sampleTask()
            task.createdDate shouldBe someFixedDts
        }
    }

    @Test
    fun `task should have list of TimePiece`() {
        val task = sampleTask()
        task.startActivity()
        task.stopActivity()
        task.startActivity()
        task.stopActivity()
        task.timelineSize() shouldBe 2
    }

    @Test
    fun `should throw unexpected state exception when starting a already started TimePiece`() {
        val task = sampleTask()
        task.startActivity()
        assertThrows<UnExpectedState> {
            task.startActivity()
        }
    }

    @Test
    fun `should throw unexpected state exception when stopping a already stopped TimePiece`() {
        val task = sampleTask()
        task.startActivity()
        task.stopActivity()
        assertThrows<UnExpectedState> {
            task.stopActivity()
        }
    }

    @Test
    fun `calculate hours worked on the task`() {
        val task = sampleTask()
        val someFixedDts = LocalDateTime.of(2022, 11, 11, 11, 11, 11)
        withConstantNow(someFixedDts) {
            task.startActivity()
        }
        withConstantNow(someFixedDts.plusHours(1)) {
            task.stopActivity()
        }
        withConstantNow(someFixedDts.plusHours(2)) {
            task.startActivity()
        }
        withConstantNow(someFixedDts.plusHours(3)) {
            task.stopActivity()
        }
        task.getTotalHoursWorked() shouldBe 2
    }

    private fun sampleTask() = Task(
        id = 1,
        name = "task1",
        description = "some desc"
    )

}