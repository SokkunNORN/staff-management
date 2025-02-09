package me.research.doc.openapi.controllers

import me.research.doc.openapi.common.ok
import me.research.doc.openapi.services.DepartmentService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/department")
class DepartmentController(
    private val service: DepartmentService
) {

    @GetMapping
    fun findDepartments() = service.findAll().ok()
}