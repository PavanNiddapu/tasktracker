package com.learn.tasktracker.repository

import com.learn.tasktracker.model.Task
import org.springframework.context.annotation.Profile
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
@Profile("!demo")
interface TaskRepository : CrudRepository<Task, String>
