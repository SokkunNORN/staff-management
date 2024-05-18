package me.research.doc.openapi.controllers.response

enum class ErrorCode(val code: Int, val message: String) {
    SUCCESSFUL(0, ""),
    URL_NOT_FOUND(1, "URL not found"),
    METHOD_NOT_ALLOWED(2, "Method Not Allowed"),
    INVALID_REQUEST(58, "Invalid request")
}