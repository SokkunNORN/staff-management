package me.research.doc.openapi.services

import me.research.doc.openapi.common.getOrElseThrow
import me.research.doc.openapi.common.toPageResponse
import me.research.doc.openapi.controllers.response.PageResponse
import me.research.doc.openapi.dto.request.ProjectReq
import me.research.doc.openapi.dto.response.ProjectRes
import me.research.doc.openapi.models.Project
import me.research.doc.openapi.models.UserProject
import me.research.doc.openapi.repositories.ProjectRepository
import me.research.doc.openapi.repositories.StatusRepository
import me.research.doc.openapi.repositories.UserProjectRepository
import me.research.doc.openapi.repositories.UserRepository
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class ProjectService(
    private val repository: ProjectRepository,
    private val statusRepo: StatusRepository,
    private val userRepo: UserRepository,
    private val userPartRepo: UserProjectRepository
) {

    fun findAllReference() = repository.findAll().map { it.toShortRes() }

    fun findAll(request: ProjectReq.Filter?, pageable: Pageable): PageResponse<ProjectRes> {
        return repository.findAll(pageable).map { it.toRes() }.toPageResponse()
    }

    fun findById(id: Long): ProjectRes.DetailRes {
        return getOrElseThrow("Project", id, repository::findById).toDetailRes()
    }

    fun create(request: ProjectReq): ProjectRes {
        val status = getOrElseThrow("Status", request.statusId, statusRepo::findById)
        val createdBy = request.createBy?.let { getOrElseThrow("User", it, userRepo::findById) }

        val project = Project(
            name = request.name
        ).apply {
            this.status = status
            this.createdBy = createdBy
            this.updatedBy = null
        }

        return repository.save(project).toRes()
    }

    fun update(id: Long, request: ProjectReq): ProjectRes {
        val user = getOrElseThrow("User", id, repository::findById)
        val status = getOrElseThrow("Status", request.statusId, statusRepo::findById)
        val updatedBy = request.updatedBy?.let { getOrElseThrow("User", it, userRepo::findById) }

        user.run {
            name = request.name
            this
        }.apply {
            this.status = status
            this.updatedBy = updatedBy
        }

        return repository.save(user).toRes()
    }

    fun addParticipants(id: Long, userIds: List<Long>): ProjectRes.ParticipantInvolveRes {
        val project = getOrElseThrow("Project", id, repository::findById)
        val users = userRepo.findAllById(userIds.distinct())
        val status = getOrElseThrow("Status", 7, statusRepo::findById)
        val userProjects = mutableListOf<UserProject>()
        users.forEach { user ->
            val existed = userPartRepo.existsByUserIdAndProjectId(user.id, project.id)
            if (!existed) {
                userProjects.add(UserProject().apply {
                    this.user = user
                    this.project = project
                    this.status = status
                })
            }
        }
        userPartRepo.saveAll(userProjects)
        return project.toResWithParticipant(users)
    }
}
