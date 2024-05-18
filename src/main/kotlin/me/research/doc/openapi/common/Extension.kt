package me.research.doc.openapi.common

import me.research.doc.openapi.controllers.exception.GeneralErrorException
import me.research.doc.openapi.controllers.response.PageResponse
import me.research.doc.openapi.controllers.response.Pagination
import me.research.doc.openapi.controllers.response.ResponseWrapper
import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import java.util.*

fun <T> T.ok() = ResponseEntity.ok(ResponseWrapper.data(this))

fun <T> Optional<T>.orElseThrow(msg: String): T = this.orElseThrow { idNotFound(msg) }

fun <T> T?.orElseThrow(msg: String): T = this ?: idNotFound(msg)

fun <T, I> getOrElseThrow(field: String, paramI: I, loader: (I) -> Optional<T>): T {
    return loader(paramI).orElseThrow { idNotFound("$field[$paramI]") }
}

fun <T, I, J> getOrElseThrow(field: String, paramI: I, paramJ: J, loader: (I, J) -> Optional<T>): T {
    return loader(paramI, paramJ).orElseThrow { idNotFound("$field[$paramI]") }
}

fun <T, I, J, K> getOrElseThrow(field: String, paramI: I, paramJ: J, paramK: K, loader: (I, J, K) -> Optional<T>): T {
    return loader(paramI, paramJ, paramK).orElseThrow { idNotFound("$field[$paramI]") }
}

fun <T, I, J, K, L> getOrElseThrow(field: String, paramI: I, paramJ: J, paramK: K, paramL: L, loader: (I, J, K, L) -> Optional<T>): T {
    return loader(paramI, paramJ, paramK, paramL).orElseThrow { idNotFound("$field[$paramI]") }
}

fun <T, I, J, K, L, M> getOrElseThrow(field: String, paramI: I, paramJ: J, paramK: K, paramL: L, paramM: M, loader:(I, J, K, L, M) -> Optional<T>): T {
    return loader(paramI, paramJ, paramK, paramL, paramM).orElseThrow { idNotFound("$field[$paramI]") }
}

fun <T, I, J, K, L, M, N> getOrElseThrow(field: String, paramI: I, paramJ: J, paramK: K, paramL: L, paramM: M, paramN: N, loader: (I, J, K, L, M, N) -> Optional<T>): T {
    return loader(paramI, paramJ, paramK, paramL, paramM, paramN).orElseThrow { idNotFound("$field[$paramI]") }
}

fun <T, I> MutableList<GeneralErrorException>.getOrElseThrow(field: String, paramI: I, loader: (I) -> Optional<T>): T {
    return loader(paramI).orElseThrow  {
        this.add(ErrorCode.OBJECT_NOT_FOUND.init("$field[$paramI]"))
        ErrorCode.MULTI_STATUS_CODE.init().fire()
    }
}

fun <T, I, J> MutableList<GeneralErrorException>.getOrElseThrow(field: String, paramI: I, paramJ: J, loader: (I, J) -> Optional<T>): T {
    return loader(paramI, paramJ).orElseThrow {
        this.add(ErrorCode.OBJECT_NOT_FOUND.init("$field[$paramI]"))
        ErrorCode.MULTI_STATUS_CODE.init().fire()
    }
}


fun <T, I> MutableList<GeneralErrorException>.getOrElse(field: String, paramI: I, loader: (I) -> Optional<T>): T? {
    return loader(paramI).orElseGet  {
        this.add(ErrorCode.OBJECT_NOT_FOUND.init("$field[$paramI]"))
        null
    }
}

fun <T, I, J> MutableList<GeneralErrorException>.getOrElse(field: String, paramI: I, paramJ: J, loader: (I, J) -> Optional<T>): T? {
    return loader(paramI, paramJ).orElseGet {
        this.add(ErrorCode.OBJECT_NOT_FOUND.init("$field[$paramI]"))
        null
    }
}

fun idNotFound(msg: String): Nothing = throw GeneralErrorException(ErrorCode.OBJECT_NOT_FOUND, msg)

fun <T> Page<T>.toPageResponse(): PageResponse<T> {
    return PageResponse(
        content = this.content,
        pagination = Pagination(
            currentPage = this.pageable.pageNumber,
            pageSize = this.pageable.pageSize,
            totalElements = this.totalElements,
            totalPages = this.totalPages
        )
    )
}

fun <T> Page<T>.toPageResponse(additional: Any): PageResponse<T> {
    return PageResponse(
        content = this.content,
        pagination = Pagination(
            currentPage = this.pageable.pageNumber,
            pageSize = this.pageable.pageSize,
            totalElements = this.totalElements,
            totalPages = this.totalPages
        ),
        additional = additional
    )
}
