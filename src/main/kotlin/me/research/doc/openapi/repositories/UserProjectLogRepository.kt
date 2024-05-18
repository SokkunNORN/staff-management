package me.research.doc.openapi.repositories

import me.research.doc.openapi.models.UserProjectLog
import org.springframework.data.jpa.repository.JpaRepository

interface UserProjectLogRepository : JpaRepository<UserProjectLog, Long>