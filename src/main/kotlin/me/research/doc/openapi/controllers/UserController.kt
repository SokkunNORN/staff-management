package me.research.doc.openapi.controllers

import me.research.doc.openapi.common.ok
import me.research.doc.openapi.dto.request.UserReq
import me.research.doc.openapi.services.UserService
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.SortDefault
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/user")
class UserController(
    private val service: UserService
) {

    @GetMapping
    fun findUsers(
        request: UserReq.Filter?,
        @SortDefault.SortDefaults(
            SortDefault(sort = ["createdAt"], direction = Sort.Direction.DESC)
        ) pageable: Pageable
    ) = service.findAll(request, pageable).ok()

    @GetMapping("/reference")
    fun findUsersReference() = service.findAllReference().ok()

    @GetMapping("/{id}")
    fun findUserById(@PathVariable id: Long) = service.findById(id).ok()

    @PostMapping
    fun createUser(@RequestBody request: UserReq) = service.create(request).ok()

    @PutMapping("/{id}")
    fun updateUser(@PathVariable id: Long, @RequestBody request: UserReq) = service.update(id, request).ok()
}