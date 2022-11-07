package com.learn.tasktracker.service

import com.learn.tasktracker.model.Task
import com.learn.tasktracker.repository.TaskRepository
import org.springframework.stereotype.Service

@Service
class TaskService(
    private val taskRepository: TaskRepository
) {
    fun fetchAllTasks(): List<Task> {
        return taskRepository.findAll() as List<Task>
    }


    fun createTask(task: Task): Task {
        return taskRepository.save(task)
    }

}
