package me.research.doc.openapi.controllers.response

class ResponseWrapper(val data: Any?, val status: Status) {
    companion object {
        fun data(data: Any?): ResponseWrapper {
            return ResponseWrapper(data, Status.SUCCESSFUL)
        }

        fun error(error: ErrorCode): ResponseWrapper {
            val status: Status = Status(code = ResponseCode.FAIL.code, error = error.message, errorCode = error.code)
            return ResponseWrapper(data = null, status)
        }

        fun error(error: ErrorCode, message: String): ResponseWrapper {
            val status: Status = Status(code = ResponseCode.FAIL.code, error = String.format(error.message, message).trim(), errorCode = error.code)
            return ResponseWrapper(data = null, status)
        }

        fun error(error: ErrorCode, vararg message: String): ResponseWrapper {
            val status: Status = Status(code = ResponseCode.FAIL.code, error = String.format(error.message, *message).trim(), errorCode = error.code)
            return ResponseWrapper(data = null, status)
        }
    }
}