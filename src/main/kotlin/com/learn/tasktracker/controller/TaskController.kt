package com.learn.tasktracker.controller

import com.learn.tasktracker.model.Task
import com.learn.tasktracker.service.TaskService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class TaskController(
    @Autowired val taskService: TaskService
) {
    @GetMapping("/task")
    fun getTasks(): List<Task> {
        return taskService.fetchAllTasks()
    }

    @PostMapping("/task")
    fun createTask(task: Task): ResponseEntity<Task> {
        return ResponseEntity.status(HttpStatus.CREATED).body(taskService.createTask(task))
    }

    @GetMapping("/task/start/{id}")
    fun startTask(@PathVariable id: Int): TaskActionResponse {
        return if (taskService.startTask(id)) TaskActionResponse("success") else TaskActionResponse("failed")
    }

    @GetMapping("/task/stop/{id}")
    fun stopTask(@PathVariable id: Int): TaskActionResponse {
        return if (taskService.stopTask(id)) TaskActionResponse("success") else TaskActionResponse("failed")
    }

    data class TaskActionResponse(val message: String)
}
