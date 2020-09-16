package com.study.samplebackend.sequence

import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class Sequence(
    val id: String? = null,
    @Indexed
    val sequenceName: String? = null,
    @Indexed
    val sequence: Int = 0
)
