package me.research.doc.openapi.models

import com.fasterxml.jackson.annotation.JsonManagedReference
import jakarta.persistence.*
import me.research.doc.openapi.dto.response.UserProjectRes
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime

@Entity
@Table(name = "user_project_involve")
data class UserProject(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    var createdAt: LocalDateTime = LocalDateTime.now(),

    @UpdateTimestamp
    @Column(name = "updated_at")
    var updatedAt: LocalDateTime? = null
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

    fun toRes() = UserProjectRes(
        this.user.toShortRes(),
        this.project.toShortRes(),
        this.status.toShortRes(),
        this.createdAt
    )
}