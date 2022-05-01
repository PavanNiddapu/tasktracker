package com.pavan.tasktracker.controller

import com.pavan.tasktracker.model.Task
import com.pavan.tasktracker.service.TaskService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/api")
class TaskController(
    @Autowired val taskService: TaskService
) {
    @GetMapping("/tasks")
    fun getTasks(): Flux<Task> {
        return taskService.getTasks()
    }

    @GetMapping("/tasks/{id}")
    fun getTasks(@PathVariable id: Long): Mono<Task> {
        return taskService.getTaskById(id)
    }
}
