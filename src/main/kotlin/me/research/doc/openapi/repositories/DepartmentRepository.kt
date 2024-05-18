package me.research.doc.openapi.repositories

import me.research.doc.openapi.models.Department
import org.springframework.data.jpa.repository.JpaRepository

interface DepartmentRepository : JpaRepository<Department, Long>
