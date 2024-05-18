package me.research.doc.openapi.models

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonManagedReference
import jakarta.persistence.*
import me.research.doc.openapi.dto.response.ShortRes
import me.research.doc.openapi.dto.response.UserRes
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime

@Entity
@Table(name = "user")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(name = "name")
    var name: String,

    @Column(name = "title")
    var title: String,

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    var createdAt: LocalDateTime = LocalDateTime.now(),

    @UpdateTimestamp
    @Column(name = "updated_at")
    var updatedAt: LocalDateTime? = null
) {

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "department_id", nullable = true)
    lateinit var department: Department

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
        joinColumns = [JoinColumn(name = "user_id", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "project_id", referencedColumnName = "id")]
    )
    @JsonBackReference
    lateinit var projects: List<Project>

    fun toShortRes() = ShortRes(
        id = this.id,
        name = this.name
    )

    fun toRes() = UserRes(
        this.id,
        this.name,
        this.title,
        this.department.toShortRes(),
        this.createdBy?.toShortRes(),
        this.createdAt,
        this.updatedBy?.toShortRes(),
        this.updatedAt
    )

    fun toShortProfileRes() = UserRes.UserShortProfileRes(
        this.id,
        this.name,
        this.title,
        this.department.toShortRes()
    )

    fun toDetailRes() = UserRes.DetailRes(
        this.id,
        this.name,
        this.title,
        this.department.toShortRes(),
        this.createdBy?.toShortRes(),
        this.createdAt,
        this.updatedBy?.toShortRes(),
        this.updatedAt,
        this.projects.map { it.toShortProfileRes() }
    )
}
