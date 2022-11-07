package com.learn.tasktracker.service

import com.learn.tasktracker.model.Task
import com.learn.tasktracker.repository.TaskRepository
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test

class TaskServiceTest {

    private var taskRepository: TaskRepository = mockk()

    @Test
    fun `should fetch all tasks`() {
        val taskService = TaskService(taskRepository)
        every { taskRepository.findAll() } returns sampleTasks()
        val tasks: List<Task> = taskService.fetchAllTasks()
        tasks.size shouldBe 2
    }

    @Test
    fun `should be able to create a task`() {
        val taskService = TaskService(taskRepository)
        val sampleTask = Task(id = 0, name = "", description = "")
        every { taskRepository.save(any()) } returns sampleTask
        val createdTask: Task = taskService.createTask(sampleTask)
        createdTask shouldBe sampleTask
    }

    private fun sampleTasks() = listOf(
        Task(id = 0, name = "", description = ""),
        Task(id = 0, name = "", description = "")
    )
}