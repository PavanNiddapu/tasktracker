package com.learn.tasktracker

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TasktrackerApplication

fun main(args: Array<String>) {
	runApplication<TasktrackerApplication>(*args)
}
