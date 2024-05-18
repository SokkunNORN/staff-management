package me.research.doc.openapi.services

import me.research.doc.openapi.repositories.StatusRepository
import org.springframework.stereotype.Service

@Service
class StatusService(
    private val repository: StatusRepository
) {

    fun findAll() = repository.findAll()
}
