package me.research.doc.openapi.controllers

import me.research.doc.openapi.common.ok
import me.research.doc.openapi.dto.request.ProjectReq
import me.research.doc.openapi.services.ProjectService
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.SortDefault
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/project")
class ProjectController(
    private val service: ProjectService
) {

    @GetMapping
    fun findProjects(
        request: ProjectReq.Filter?,
        @SortDefault.SortDefaults(
            SortDefault(sort = ["createdAt"], direction = Sort.Direction.DESC)
        ) pageable: Pageable
    ) = service.findAll(request, pageable).ok()

    @GetMapping("/reference")
    fun findProjectsReference() = service.findAllReference().ok()

    @GetMapping("/{id}")
    fun findProjectById(@PathVariable id: Long) = service.findById(id).ok()

    @PostMapping
    fun createProject(@RequestBody request: ProjectReq) = service.create(request).ok()

    @PutMapping("/{id}")
    fun updateProject(@PathVariable id: Long, @RequestBody request: ProjectReq) = service.update(id, request).ok()

    @PostMapping("/{id}/add-participant")
    fun addProjectParticipants(@PathVariable id: Long, @RequestBody userIds: List<Long>) = service.addParticipants(id, userIds).ok()
}