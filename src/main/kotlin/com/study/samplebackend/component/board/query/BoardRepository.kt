package com.study.samplebackend.component.board.query

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface BoardRepository : MongoRepository<Board, Int> {
    fun existsBoardByBoardName(boardName: String): Boolean
    fun findByBoardName(boardName: String): Board?
    fun deleteByBoardName(boardName: String)
}
