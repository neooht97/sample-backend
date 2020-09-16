package com.study.samplebackend.preconditions

import java.time.LocalDate

class PreconditionChain {
    private var violations = mutableListOf<String>()

    fun check(precondition: Boolean, errorMessage: String): PreconditionChain {
        if (!precondition) violations.add(errorMessage)
        return this
    }

    fun check(
        value: String?,
        attributeCheck: StringCheck.() -> Unit
    ): PreconditionChain = check(StringCheck(value).apply(attributeCheck))

    fun check(
        value: Int?,
        attributeCheck: IntCheck.() -> Unit
    ): PreconditionChain = check(IntCheck(value).apply(attributeCheck))

    fun check(
        value: LocalDate?,
        attributeCheck: LocalDateCheck.() -> Unit
    ): PreconditionChain = check(LocalDateCheck(value).apply(attributeCheck))

    fun add(otherChain: PreconditionChain?): PreconditionChain {
        otherChain?.let { chain -> violations.addAll(chain.violations) }
        return this
    }

    fun verify() {
        print(violations)
        if (violations.isNotEmpty()) throw PreconditionException(violations)
    }

    private fun <T> check(attributeCheck: AttributeCheck<T>): PreconditionChain {
        violations.addAll(
            attributeCheck.violations()
        )

        return this
    }
}
