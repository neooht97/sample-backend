package com.study.samplebackend.sequence

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass
import org.springframework.data.mongodb.core.FindAndModifyOptions.options
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria.where
import org.springframework.data.mongodb.core.query.Query.query
import org.springframework.data.mongodb.core.query.Update
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@ConditionalOnClass(MongoRepository::class)
@Repository
internal class MongoSequenceRepository @Autowired constructor(
    val mongoTemplate: MongoTemplate
) : SequenceRepository {
    override fun nextSequenceNoBy(sequenceName: String): Int {
        val sequence = mongoTemplate.findAndModify(
            query(where("sequenceName").`is`(sequenceName)),
            Update().inc("sequence", 1),
            options().returnNew(true).upsert(true),
            Sequence::class.java
        )

        return sequence?.sequence ?: 1
    }

    override fun currentSequenceNoBy(sequenceName: String): Int {
        val sequence = mongoTemplate.findOne(
            query(where("sequenceName").`is`(sequenceName)),
            Sequence::class.java
        )

        return sequence?.sequence!!
    }

    override fun ensureInitialized(sequenceName: String) {
        mongoTemplate.findOne(
            query(where("sequenceName").`is`(sequenceName)),
            Sequence::class.java
        ) ?: mongoTemplate.insert(
            Sequence(sequenceName = sequenceName)
        )
    }
}
