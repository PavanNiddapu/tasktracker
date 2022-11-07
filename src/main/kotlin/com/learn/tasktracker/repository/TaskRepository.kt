package com.learn.tasktracker.repository

import com.learn.tasktracker.model.Task
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface TaskRepository : CrudRepository<Task, String>
