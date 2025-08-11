package com.learn.tasktracker.service

import com.learn.tasktracker.model.Task
import com.learn.tasktracker.repository.TaskRepository
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import java.util.*

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

    @Test
    fun `should start task successfully when task exists`() {
        val taskService = TaskService(taskRepository)
        val task = sampleTask()
        every { taskRepository.findById(1L) } returns Optional.of(task)
        every { taskRepository.save(any()) } returns task
        
        val result = taskService.startTask(1L)
        
        result shouldBe true
        verify { taskRepository.save(task) }
    }

    @Test
    fun `should return false when starting non-existent task`() {
        val taskService = TaskService(taskRepository)
        every { taskRepository.findById(999L) } returns Optional.empty()
        
        val result = taskService.startTask(999L)
        
        result shouldBe false
    }

    @Test
    fun `should stop task successfully when task exists`() {
        val taskService = TaskService(taskRepository)
        val task = sampleTask()
        // Start a task first to ensure it can be stopped
        task.startActivity()
        every { taskRepository.findById(1L) } returns Optional.of(task)
        every { taskRepository.save(any()) } returns task
        
        val result = taskService.stopTask(1L)
        
        result shouldBe true
        verify { taskRepository.save(task) }
    }

    @Test
    fun `should return false when stopping non-existent task`() {
        val taskService = TaskService(taskRepository)
        every { taskRepository.findById(999L) } returns Optional.empty()
        
        val result = taskService.stopTask(999L)
        
        result shouldBe false
    }

    private fun sampleTasks() = listOf(
        Task(id = 0, name = "", description = ""),
        Task(id = 0, name = "", description = "")
    )

    private fun sampleTask() = Task(
        id = 1,
        name = "task1", 
        description = "some desc"
    )
}