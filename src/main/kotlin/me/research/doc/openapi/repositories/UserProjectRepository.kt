package me.research.doc.openapi.repositories

import me.research.doc.openapi.models.UserProject
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface UserProjectRepository : JpaRepository<UserProject, Long> {
    fun existsByUserIdAndProjectId(userId: Long, projectId: Long): Boolean
    fun findByUserIdAndProjectId(userId: Long, projectId: Long): Optional<UserProject>
}
