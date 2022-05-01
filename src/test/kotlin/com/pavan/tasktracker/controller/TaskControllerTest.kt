package com.pavan.tasktracker.controller

import com.pavan.tasktracker.model.Task
import com.pavan.tasktracker.service.TaskService
import io.kotest.assertions.assertSoftly
import io.kotest.extensions.time.withConstantNow
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
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.time.LocalDateTime

@WebFluxTest(TaskController::class)
@Import(value = [TaskControllerTest.ControllerTestConfig::class])
class TaskControllerTest(
    @Autowired val webTestClient: WebTestClient,
    @Autowired val taskService: TaskService
) {

    @BeforeEach
    fun setUp() {
        every { taskService.getTasks() } returns Flux.just(
            Task(
                id = 1,
                name = "name",
                description = "SomeValue",
                createdDate = LocalDateTime.now(),
                startDate = null,
                finishDate = null
            ), Task(
                id = 2,
                name = "name",
                description = "SomeValue",
                createdDate = LocalDateTime.now(),
                startDate = null,
                finishDate = null
            )
        )

    }

    @Test
    fun `should return count of tasks`() {


        val response = webTestClient.get()
            .uri("/api/tasks")
            .exchange()
            .expectStatus().isOk
            .expectBodyList(Task::class.java)
            .returnResult()
            .responseBody!!

        response.size shouldBe 2
    }

    @Test
    fun `should return task by task id 1`() {
        every { taskService.getTaskById(any()) } returns Mono.just(
            Task(
                id = 1,
                name = "task name",
                description = "task description",
                createdDate = LocalDateTime.now(),
                startDate = null,
                finishDate = null
            )
        )
        val taskResponse = webTestClient.get()
            .uri("/api/tasks/1")
            .exchange()
            .expectStatus().isOk
            .expectBody(Task::class.java)
            .returnResult()
            .responseBody!!

        assertSoftly {
            taskResponse.id shouldBe 1
        }
    }

    @Test
    fun `Task should contain id,name,description,createdDate,startDate,finishDate`() {
        val now = LocalDateTime.now()
        withConstantNow(now) {
            every { taskService.getTaskById(any()) } returns Mono.just(
                Task(
                    id = 1,
                    name = "task name",
                    description = "task description",
                    createdDate = LocalDateTime.now(),
                    startDate = null,
                    finishDate = null
                )
            )

            val taskResponse = webTestClient.get()
                .uri("/api/tasks/1")
                .exchange()
                .expectStatus().isOk
                .expectBody(Task::class.java)
                .returnResult()
                .responseBody!!

            assertSoftly {
                taskResponse.id shouldBe 1
                taskResponse.name shouldBe "task name"
                taskResponse.description shouldBe "task description"
                taskResponse.createdDate shouldBe now
                taskResponse.startDate shouldBe null
                taskResponse.finishDate shouldBe null
            }
        }
    }

    @TestConfiguration
    class ControllerTestConfig {
        @Bean
        fun taskService() = mockk<TaskService>()
    }
}