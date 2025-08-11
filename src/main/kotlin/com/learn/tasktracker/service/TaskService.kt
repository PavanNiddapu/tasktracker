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

    fun startTask(id: Long): Boolean {
        return try {
            val taskOptional = taskRepository.findById(id)
            if (taskOptional.isPresent) {
                val task = taskOptional.get()
                task.startActivity()
                taskRepository.save(task)
                true
            } else {
                false
            }
        } catch (e: Exception) {
            false
        }
    }

    fun stopTask(id: Long): Boolean {
        return try {
            val taskOptional = taskRepository.findById(id)
            if (taskOptional.isPresent) {
                val task = taskOptional.get()
                task.stopActivity()
                taskRepository.save(task)
                true
            } else {
                false
            }
        } catch (e: Exception) {
            false
        }
    }
}
