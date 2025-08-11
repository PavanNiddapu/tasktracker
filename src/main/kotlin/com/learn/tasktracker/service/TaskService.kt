package com.learn.tasktracker.service

import com.learn.tasktracker.model.Task
import com.learn.tasktracker.repository.TaskRepository
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Service

@Service
@Profile("!demo")
class TaskService(
    private val taskRepository: TaskRepository
) {
    fun fetchAllTasks(): List<Task> {
        return taskRepository.findAll() as List<Task>
    }

    fun createTask(task: Task): Task {
        return taskRepository.save(task)
    }

    fun startTask(id: Int): Boolean {
        return try {
            val task = taskRepository.findById(id.toString())
            if (task.isPresent) {
                task.get().startActivity()
                taskRepository.save(task.get())
                true
            } else {
                false
            }
        } catch (e: Exception) {
            false
        }
    }

    fun stopTask(id: Int): Boolean {
        return try {
            val task = taskRepository.findById(id.toString())
            if (task.isPresent) {
                task.get().stopActivity()
                taskRepository.save(task.get())
                true
            } else {
                false
            }
        } catch (e: Exception) {
            false
        }
    }
}
