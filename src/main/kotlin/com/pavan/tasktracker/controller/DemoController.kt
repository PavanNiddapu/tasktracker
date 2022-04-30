package com.pavan.tasktracker.controller

import com.pavan.tasktracker.model.Task
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux

@RestController
@RequestMapping("/api")
class DemoController {

    @GetMapping("/demo")
    fun getSample(): String {
        return "Hello Pavan"
    }

    @GetMapping("/json/hello")
    fun getSampleJson(): Map<String, String> {
        return mapOf("name" to "Lalith")
    }


    @GetMapping("/tasks")
    fun getTasks(): Flux<Task> {
        return Flux.just(Task(), Task())
    }
}