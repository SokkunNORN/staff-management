package me.research.doc.openapi.controllers.exception

import me.research.doc.openapi.common.ErrorCode
import me.research.doc.openapi.controllers.response.Status

open class GeneralErrorException(var errorCode: ErrorCode, vararg arg: Any) : RuntimeException() {
    var errors: MutableList<Status> = mutableListOf()
    override fun getLocalizedMessage(): String {
        return message
    }

    var data: Any? = null
    var description: String? = null

    fun data(data: Any?): GeneralErrorException {
        this.data = data
        return this
    }

    fun description(description: String?): GeneralErrorException {
        this.description = description
        return this
    }

    constructor(arg: Any) : this(ErrorCode.BAD_REQUEST, arg)

    init {
        try {
            this.description = String.format(errorCode.message, *arg).trim()
        } catch (ex: java.lang.Exception) {
            this.description = errorCode.message
                .replace(",", "")
                .replace("%s", "")
                .trim()
        }
    }

    override val message: String
        get() = this.description.toString()


    fun fire(): Nothing {
        throw this
    }

    fun error(status: Status): GeneralErrorException {
        this.errors.add(status)
        return this
    }

    fun errors(status: List<Status>): GeneralErrorException {
        this.errors.addAll(status)
        return this
    }

}