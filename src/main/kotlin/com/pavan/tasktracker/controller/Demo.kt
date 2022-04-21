package com.pavan.tasktracker.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class Demo {

    @GetMapping("/demo")
    fun getSample(): String {
        return "Hello Pavan"
    }

    @GetMapping("/json/hello")
    fun getSampleJson(): Map<String, String> {
        return mapOf("name" to "Lalith")
    }
}