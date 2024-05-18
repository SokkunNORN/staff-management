package me.research.doc.openapi.services

import me.research.doc.openapi.common.getOrElseThrow
import me.research.doc.openapi.dto.request.UserProjectReq
import me.research.doc.openapi.dto.response.UserProjectRes
import me.research.doc.openapi.models.UserProjectLog
import me.research.doc.openapi.repositories.StatusRepository
import me.research.doc.openapi.repositories.UserProjectLogRepository
import me.research.doc.openapi.repositories.UserProjectRepository
import org.springframework.stereotype.Service

@Service
class UserProjectService(
    private val repository: UserProjectRepository,
    private val statusRepo: StatusRepository,
    private val logRepo: UserProjectLogRepository
) {

    fun updateByUserAndProject(userId: Long, projectId: Long, request: UserProjectReq): UserProjectRes {
        val userProject = getOrElseThrow("User in project", userId, projectId, repository::findByUserIdAndProjectId)
        val status = getOrElseThrow("Status", request.statusId, statusRepo::findById)
        userProject.apply {
            this.status = status
        }
        val log = UserProjectLog().apply {
            this.user = userProject.user
            this.project = userProject.project
            this.status = userProject.status
        }
        repository.save(userProject)
        logRepo.save(log)
        return userProject.toRes()
    }
}
