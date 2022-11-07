package com.learn.tasktracker.controller

import com.learn.tasktracker.model.Task
import com.learn.tasktracker.service.TaskService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

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
}
