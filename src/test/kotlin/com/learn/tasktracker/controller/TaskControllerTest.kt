package com.learn.tasktracker.controller

import com.learn.tasktracker.model.Task
import com.learn.tasktracker.service.TaskService
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Import
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.web.reactive.function.BodyInserters
import java.time.LocalDateTime

@WebFluxTest(TaskController::class)
@Import(value = [TaskControllerTest.ControllerTestConfig::class])
class TaskControllerTest(
    @Autowired val webTestClient: WebTestClient,
    @Autowired val taskService: TaskService
) {

    @BeforeEach
    fun setUp() {
        every { taskService.fetchAllTasks() } returns listOf(
            Task(
                id = 1,
                name = "name",
                description = "SomeValue",
                createdDate = LocalDateTime.now(),
            ), Task(
                id = 2,
                name = "name",
                description = "SomeValue",
                createdDate = LocalDateTime.now(),
            )
        )

    }

    @Test
    fun `should return all tasks`() {
        val response = webTestClient.get()
            .uri("/api/task")
            .exchange()
            .expectStatus().isOk
            .expectBodyList(Task::class.java)
            .returnResult()
            .responseBody!!

        response.size shouldBe 2
    }

    @Test
    fun `should be able to create a task`() {
        val task = Task(id = 0, name = "", description = "")
        every { taskService.createTask(any()) } returns task
        val response = webTestClient.post()
            .uri("/api/task")
            .body(
                BodyInserters.fromFormData("id", "0")
                    .with("name", "")
                    .with("description", "")
            )
            .exchange()
            .expectStatus().isCreated
            .expectBody(Task::class.java)
            .returnResult()
            .responseBody!!

        response shouldBe task
    }

    @TestConfiguration
    class ControllerTestConfig {
        @Bean
        fun taskService() = mockk<TaskService>()
    }
}