package me.research.doc.openapi.controllers

import me.research.doc.openapi.controllers.response.ok
import me.research.doc.openapi.services.StatusService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/status")
class StatusController(
    private val service: StatusService
) {

    @GetMapping
    fun findAll() = ok(service.findAll())
}