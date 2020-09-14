package com.study.samplebackend.util

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ResponseBody

fun <T> okResponse(responseBody: T) =
        ResponseEntity.ok(responseBody)