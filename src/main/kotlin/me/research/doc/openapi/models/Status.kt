package me.research.doc.openapi.models

import jakarta.persistence.*
import me.research.doc.openapi.dto.response.ShortRes

@Entity
@Table(name = "status")
data class Status(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @Column(name = "name")
    val name: String
) {
    fun toShortRes() = ShortRes(
        this.id,
        this.name
    )
}
