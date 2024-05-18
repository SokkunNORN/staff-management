package me.research.doc.openapi.models

import com.fasterxml.jackson.annotation.JsonManagedReference
import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime

@Entity
@Table(name = "user_project_log")
data class UserProjectLog(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    var createdAt: LocalDateTime = LocalDateTime.now()
) {
    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "user_id")
    lateinit var user: User

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "project_id")
    lateinit var project: Project

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "status_id")
    lateinit var status: Status
}