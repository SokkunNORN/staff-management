package me.research.doc.openapi.dto.response

import java.time.LocalDateTime

data class ProjectRes(
    val id: Long,
    val name: String,
    val status: ShortRes,
    val createdBy: ShortRes?,
    val createdAt: LocalDateTime?,
    val updatedBy: ShortRes?,
    val updatedAt: LocalDateTime?,
) {
    data class ParticipantInvolveRes(
        val id: Long,
        val name: String,
        val status: ShortRes,
        val participants: List<UserRes.UserShortProfileRes>
    )

    data class ProjectShortProfileRes(
        val id: Long,
        val name: String,
        val status: ShortRes
    )

    data class DetailRes(
        val id: Long,
        val name: String,
        val status: ShortRes,
        val createdBy: ShortRes?,
        val createdAt: LocalDateTime?,
        val updatedBy: ShortRes?,
        val updatedAt: LocalDateTime?,
        val participants: List<UserRes.UserShortProfileRes>
    )
}
