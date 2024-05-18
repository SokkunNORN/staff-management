package me.research.doc.openapi.controllers.response

import me.research.doc.openapi.common.ErrorCode

class Status(
    val code: Int,
    val errorCode: String? = null,
    val error: String? = null,
    val warning: String? = null
) {
    companion object {
        val SUCCESSFUL = Status(code = ResponseCode.SUCCESS.code, errorCode = ErrorCode.SUCCESSFUL.code, error = ErrorCode.SUCCESSFUL.message)
    }
}
