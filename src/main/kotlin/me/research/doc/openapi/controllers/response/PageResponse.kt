package me.research.doc.openapi.controllers.response

data class PageResponse<T>(var content: List<T>, val pagination: Pagination, var additional: Any? = null)

data class Pagination(val currentPage: Int, val pageSize: Int, val totalElements: Long, val totalPages: Int)