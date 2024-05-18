package me.research.doc.openapi.dto.response

import java.time.LocalDateTime

data class UserProjectRes(
    val user: ShortRes,
    val project: ShortRes,
    val status: ShortRes,
    val createdAt: LocalDateTime
)
