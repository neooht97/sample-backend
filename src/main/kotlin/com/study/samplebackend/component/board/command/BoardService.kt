package com.study.samplebackend.component.board.command

import com.study.samplebackend.component.board.query.BoardRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class BoardService @Autowired internal constructor(
    val boardRepo: BoardRepository
) {}