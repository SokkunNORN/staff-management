package me.research.doc.openapi.services

import me.research.doc.openapi.repositories.DepartmentRepository
import org.springframework.stereotype.Service

@Service
class DepartmentService(
    private val repository: DepartmentRepository
) {

    fun findAll() = repository.findAll()
}
