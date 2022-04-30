package com.pavan.tasktracker.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.test.web.reactive.server.WebTestClient

@WebFluxTest(DemoController::class)
class TaskControllerTest(
    @Autowired val webTestClient: WebTestClient
) {

}