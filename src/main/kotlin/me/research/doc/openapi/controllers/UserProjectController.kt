package me.research.doc.openapi.controllers

import me.research.doc.openapi.common.ok
import me.research.doc.openapi.dto.request.UserProjectReq
import me.research.doc.openapi.services.UserProjectService
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1")
class UserProjectController(
    private val service: UserProjectService
) {

    @PutMapping("/project/{projectId}/user/{userId}")
    fun updateUserProjectStatus(
        @PathVariable projectId: Long,
        @PathVariable userId: Long,
        @RequestBody request: UserProjectReq
    ) = service.updateByUserAndProject(userId, projectId, request).ok()
}