package me.research.doc.openapi.repositories

import me.research.doc.openapi.models.Project
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.PagingAndSortingRepository

interface ProjectRepository : JpaRepository<Project, Long>, PagingAndSortingRepository<Project, Long>
