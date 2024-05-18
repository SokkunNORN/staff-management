package me.research.doc.openapi.controllers.response

class Status(
    val code: Int,
    val errorCode: Int? = null,
    val error: String? = null,
    val warning: String? = null
) {
    companion object {
        val SUCCESSFUL = Status(code = ResponseCode.SUCCESS.code, errorCode = ErrorCode.SUCCESSFUL.code, error = ErrorCode.SUCCESSFUL.message)
    }
}
