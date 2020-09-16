package com.study.samplebackend.component.board.command

import com.study.samplebackend.component.board.query.Board
import com.study.samplebackend.component.board.query.BoardRepository
import com.study.samplebackend.sequence.SequenceRepository
import com.study.samplebackend.util.orNull
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class BoardService @Autowired internal constructor(
    val boardRepo: BoardRepository,
    val sequenceRepo: SequenceRepository
) {
    internal fun nextId(): Int {
        val aggregateName = Board::class.java.simpleName
        var id: Int
        do {
            id = sequenceRepo.nextSequenceNoBy(aggregateName)
        } while (!isIdUnique(id))
        return id
    }

    fun isBoardExists(boardName: String) =
        boardRepo.existsBoardByBoardName(boardName)

    fun isIdUnique(id: Int): Boolean =
        boardRepo.findById(id).orNull() == null
}
