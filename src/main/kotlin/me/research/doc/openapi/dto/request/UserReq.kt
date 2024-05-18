package me.research.doc.openapi.dto.request

data class UserReq(
    val name: String,
    val title: String,
    val departmentId: Long,
    val createBy: Long?,
    val updatedBy: Long?
) {
    data class Filter(
        val name: String?,
        val title: String?,
        val departmentId: Long?
    )
}
