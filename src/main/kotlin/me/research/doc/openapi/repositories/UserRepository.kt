package me.research.doc.openapi.repositories

import me.research.doc.openapi.models.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.PagingAndSortingRepository

interface UserRepository : JpaRepository<User, Long>, PagingAndSortingRepository<User, Long>
