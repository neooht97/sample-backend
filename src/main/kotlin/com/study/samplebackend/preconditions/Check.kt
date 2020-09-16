package com.study.samplebackend.preconditions

import java.time.LocalDate
import java.util.stream.Collectors

private data class Check<in T>(
    val precondition: (T) -> Boolean,
    val message: String
)

open class AttributeCheck<out T> internal constructor(val value: T) {
    private var checks = listOf<Check<T>>()

    fun check(
        precondition: (T) -> Boolean,
        message: String
    ) {
        checks = checks + Check(precondition, message)
    }

    fun check(
        precondition: Boolean,
        message: String
    ) {
        checks = checks + Check({ precondition }, message)
    }

    internal fun violations(): List<String> {
        return checks.stream()
            .filter { check -> !check.precondition(value) }
            .map(Check<T>::message)
            .limit(1)
            .collect(Collectors.toList())
    }
}

class StringCheck internal constructor(value: String?) : AttributeCheck<String?>(value) {
    fun checkNotNullOrBlank(message: String) =
        check(
            precondition = { value.isNullOrBlank() },
            message = message
        )

    fun checkNotBlank(message: String) =
        check(
            precondition = { value?.isNotBlank() ?: true },
            message = message
        )

    fun checkRegex(
        regex: Regex,
        message: String
    ) {
        check(
            precondition = { value?.matches(regex) ?: false },
            message = message
        )
    }

    fun checkIfPresent(
        precondition: (String) -> Boolean,
        message: String
    ) {
        check(
            precondition = { it?.let(precondition) ?: true },
            message = message
        )
    }
}

class IntCheck internal constructor(value: Int?) : AttributeCheck<Int?>(value) {
    fun checkNotNull(message: String) =
        check(
            precondition = { value != null },
            message = message
        )
}

class LocalDateCheck internal constructor(value: LocalDate?) : AttributeCheck<LocalDate?>(value) {
    fun checkBefore(
        date: LocalDate,
        message: String
    ) = check(
        precondition = { value?.isBefore(date) ?: true },
        message = message
    )

    fun checkAfter(
        date: LocalDate,
        message: String
    ) = check(
        precondition = { value?.isAfter(date) ?: true },
        message = message
    )
}
