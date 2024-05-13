package me.research.doc.openapi.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/hello")
class HelloController {

    @GetMapping
    fun sayHello() = "Hello, Welcome spring open api."
}