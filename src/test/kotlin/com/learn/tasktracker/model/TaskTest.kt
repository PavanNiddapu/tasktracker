package com.learn.tasktracker.model

import io.kotest.extensions.time.withConstantNow
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
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
}