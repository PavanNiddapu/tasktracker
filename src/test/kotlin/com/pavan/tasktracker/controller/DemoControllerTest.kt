package com.pavan.tasktracker.controller

import com.pavan.tasktracker.model.Task
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.test.web.reactive.server.WebTestClient

@WebFluxTest(DemoController::class)
class DemoControllerTest(
    @Autowired val webTestClient: WebTestClient
) {

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
}