package com.study.samplebackend.sequence

interface SequenceRepository {
    fun nextSequenceNoBy(sequenceName: String): Int
    fun currentSequenceNoBy(sequenceName: String): Int
    fun ensureInitialized(sequenceName: String)
}
