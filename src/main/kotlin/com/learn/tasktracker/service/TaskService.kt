package com.learn.tasktracker.service

import com.learn.tasktracker.model.Task
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.time.LocalDateTime

@Service
class TaskService {
    fun getTasks(): Flux<Task> {
        return Flux.just(
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

    fun getTaskById(id: Long): Mono<Task> {
        return Mono.just(
            Task(
                id = id,
                name = "task name",
                description = "task description",
                createdDate = LocalDateTime.now(),
            )
        )
    }


}
