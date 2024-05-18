package me.research.doc.openapi.repositories

import me.research.doc.openapi.models.Status
import org.springframework.data.jpa.repository.JpaRepository

interface StatusRepository : JpaRepository<Status, Long>
