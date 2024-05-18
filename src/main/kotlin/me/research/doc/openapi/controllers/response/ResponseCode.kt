package me.research.doc.openapi.controllers.response

enum class ResponseCode(val code: Int, val description: String) {
    SUCCESS(0, "The response success"),
    FAIL(1, "The response contain errors.")
}
