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
            val task = Task(
                id = 1,
                name = "SomeTask",
                description = "Description"
            )
            task.createdDate shouldBe someFixedDts
        }
    }

    @Test
    fun `task should have list of TimePiece`() {
        val task = Task(
            id = 1,
            name = "task1",
            description = "some desc"
        )
        task.startActivity()
        task.stopActivity()
        task.startActivity()
        task.stopActivity()
        task.timelineSize() shouldBe 2
    }

    @Test
    fun `should throw unexpected state exception when starting a already started TimePiece`() {
        val task = Task(
            id = 1,
            name = "task1",
            description = "some desc"
        )
        task.startActivity();
        assertThrows<UnExpectedState> {
            task.startActivity();
        }
    }

    @Test
    fun `should throw unexpected state exception when stopping a already stopped TimePiece`() {
        val task = Task(
            id = 1,
            name = "task1",
            description = "some desc"
        )
        task.startActivity();
        task.stopActivity();
        assertThrows<UnExpectedState> {
            task.stopActivity();
        }
    }
}