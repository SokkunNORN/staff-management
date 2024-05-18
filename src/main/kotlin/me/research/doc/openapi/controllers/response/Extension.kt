package me.research.doc.openapi.controllers.response

import org.springframework.http.ResponseEntity

fun <T> T.ok() = ResponseEntity.ok(ResponseWrapper.data(this))