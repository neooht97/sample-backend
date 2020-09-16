package com.study.samplebackend.preconditions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import javax.servlet.http.HttpServletRequest

internal data class PreconditionErrorResource(
    val type: String,
    val messages: List<String>
)

@ControllerAdvice
internal class PreconditionsMapper {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(PreconditionException::class)
    @ResponseBody
    fun handle(
        request: HttpServletRequest,
        exception: PreconditionException
    ): PreconditionErrorResource =
        PreconditionErrorResource(
            exception.javaClass.simpleName,
            exception.messages
        )
}
