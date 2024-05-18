package me.research.doc.openapi.common

import me.research.doc.openapi.controllers.exception.GeneralErrorException

enum class ErrorCode(val code: String, val message: String) {
    UNDER_MAINTENANCE("-1", "System under maintenance"),
    SUCCESSFUL("0", ""),
    OBJECT_NOT_FOUND("1", "%s is not found"),
    INVALID_REQUEST_FORMAT("2", "%s is invalid"),
    RECORD_ALREADY_EXIST("3", "%s is already existed"),
    INTERNAL_ERROR("4", "Internal server error, %s"),
    BAD_REQUEST("5", "%s"),
    UNRECOGNIZED_FIELD("6", "Unrecognized field: %s"),
    MISSING_REQUIRED_FILTERING_PARAM("7", "Missing query params: %s"),
    JSON_PARSING_ERROR("8", "Json parsing error value: %s"),
    FIELD_REQUIRED("9", "%s is required"),
    FIELD_MUST_NOT_NULL("10", "%s must not be null"),
    MULTI_STATUS_CODE("11", "Multi status code"),
    INVALID_TOKEN("13", "Invalid token"),
    TOKEN_EXPIRED("14", "Token expired"),
    TOKEN_REQUIRED("15", "Token required"),
    UNAUTHORIZED_ACCESS("16", "Unauthorized access %s"),
    REQUEST_TIMEOUT("17", "Request timeout"),
    DB_CONNECTION("18", "Error Database Connection"),
    CHARACTER_INVALID("19", "Invalid characters, %s"),
    INVALID_REQUEST("20", "Invalid request, %s");

    fun issue(): Nothing {
        throw GeneralErrorException(this)
    }
    fun issue(vararg arg: Any): Nothing {
        throw GeneralErrorException(this, *arg)
    }
    fun init(): GeneralErrorException {
        return GeneralErrorException(this)
    }
    fun init(vararg arg: Any): GeneralErrorException {
        return GeneralErrorException(this, *arg)
    }
}