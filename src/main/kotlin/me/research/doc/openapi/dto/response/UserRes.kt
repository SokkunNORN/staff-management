package me.research.doc.openapi.dto.response

import java.time.LocalDateTime

data class UserRes(
    val id: Long,
    val name: String,
    val title: String,
    val department: ShortRes,
    val createdBy: ShortRes?,
    val createdAt: LocalDateTime?,
    val updatedBy: ShortRes?,
    val updatedAt: LocalDateTime?,
) {
    data class UserShortProfileRes(
        val id: Long,
        val name: String,
        val title: String,
        val department: ShortRes
    )

    data class ProjectInvolveRes(
        val id: Long,
        val name: String,
        val projects: List<ProjectRes.ProjectShortProfileRes>
    )

    data class DetailRes(
        val id: Long,
        val name: String,
        val title: String,
        val department: ShortRes,
        val createdBy: ShortRes?,
        val createdAt: LocalDateTime?,
        val updatedBy: ShortRes?,
        val updatedAt: LocalDateTime?,
        val projects: List<ProjectRes.ProjectShortProfileRes>
    )
}