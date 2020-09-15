package com.study.samplebackend.preconditions

import com.study.samplebackend.exceptions.ApplicationException

data class PreconditionException(
    val messages: List<String>
) : ApplicationException(messages.joinToString("; ")) {

    constructor(vararg messages: String) : this(messages.asList())
}