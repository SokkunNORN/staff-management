package me.research.doc.openapi.models

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonManagedReference
import jakarta.persistence.*
import me.research.doc.openapi.dto.response.ProjectRes
import me.research.doc.openapi.dto.response.ShortRes
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime

@Entity
@Table(name = "project")
data class Project(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(name = "name")
    var name: String,

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    var createdAt: LocalDateTime = LocalDateTime.now(),

    @UpdateTimestamp
    @Column(name = "updated_at")
    var updatedAt: LocalDateTime? = null
) {

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "status_id", nullable = true)
    lateinit var status: Status

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "created_by")
    var createdBy: User? = null

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "updated_by")
    var updatedBy: User? = null

    @ManyToMany
    @JoinTable(
        name = "user_project_involve",
        joinColumns = [JoinColumn(name = "project_id", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "user_id", referencedColumnName = "id")]
    )
    @JsonBackReference
    lateinit var participants: List<User>

    fun toShortRes() = ShortRes(
        this.id,
        this.name
    )

    fun toRes() = ProjectRes(
        this.id,
        this.name,
        this.status.toShortRes(),
        this.createdBy?.toShortRes(),
        this.createdAt,
        this.updatedBy?.toShortRes(),
        this.updatedAt
    )

    fun toResWithParticipant(users: List<User>) = ProjectRes.ParticipantInvolveRes(
        this.id,
        this.name,
        this.status.toShortRes(),
        users.map { it.toShortProfileRes() }
    )

    fun toShortProfileRes() = ProjectRes.ProjectShortProfileRes(
        this.id,
        this.name,
        this.status.toShortRes()
    )

    fun toDetailRes() = ProjectRes.DetailRes(
        this.id,
        this.name,
        this.status.toShortRes(),
        this.createdBy?.toShortRes(),
        this.createdAt,
        this.updatedBy?.toShortRes(),
        this.updatedAt,
        this.participants.map { it.toShortProfileRes() }
    )
}
