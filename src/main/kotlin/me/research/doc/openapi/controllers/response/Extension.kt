package me.research.doc.openapi.controllers.response

import org.springframework.http.ResponseEntity

fun <T> ok(data: T) = ResponseEntity.ok(ResponseWrapper.data(data))