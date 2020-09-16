package com.study.samplebackend.component.board.query

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface BoardRepository : MongoRepository<Board, Int>