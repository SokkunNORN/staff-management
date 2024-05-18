package me.research.doc.openapi.services

import me.research.doc.openapi.common.getOrElseThrow
import me.research.doc.openapi.common.toPageResponse
import me.research.doc.openapi.controllers.response.PageResponse
import me.research.doc.openapi.dto.request.UserReq
import me.research.doc.openapi.dto.response.UserRes
import me.research.doc.openapi.models.User
import me.research.doc.openapi.repositories.DepartmentRepository
import me.research.doc.openapi.repositories.UserRepository
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class UserService(
    private val repository: UserRepository,
    private val departmentRepo: DepartmentRepository
) {

    fun findAllReference() = repository.findAll().map { it.toShortRes() }

    fun findAll(request: UserReq.Filter?, pageable: Pageable): PageResponse<UserRes> {
        return repository.findAll(pageable).map { it.toRes() }.toPageResponse()
    }

    fun findById(id: Long): UserRes.DetailRes {
        return getOrElseThrow("User", id, repository::findById).toDetailRes()
    }

    fun create(request: UserReq): UserRes {
        val department = getOrElseThrow("Department", request.departmentId, departmentRepo::findById)
        val createdBy = request.createBy?.let { getOrElseThrow("User", it, repository::findById) }

        val user = User(
            name = request.name,
            title = request.title
        ).apply {
            this.department = department
            this.createdBy = createdBy
            this.updatedBy = null
        }

        return repository.save(user).toRes()
    }

    fun update(id: Long, request: UserReq): UserRes {
        val user = getOrElseThrow("User", id, repository::findById)
        val department = getOrElseThrow("Department", request.departmentId, departmentRepo::findById)
        val updatedBy = request.updatedBy?.let { getOrElseThrow("User", it, repository::findById) }

        user.run {
            name = request.name
            title = request.title
            this
        }.apply {
            this.department = department
            this.updatedBy = updatedBy
        }

        return repository.save(user).toRes()
    }

}
