package me.research.doc.openapi.dto.request

data class ProjectReq(
    val name: String,
    val statusId: Long,
    val createBy: Long?,
    val updatedBy: Long?
) {
    data class Filter(
        val name: String?,
        val statusId: Long?
    )
}