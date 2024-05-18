package me.research.doc.openapi.models

import jakarta.persistence.*

@Entity
@Table(name = "status")
data class Status(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long,

    @Column(name = "name")
    val name: String
)
