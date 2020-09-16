package com.study.samplebackend.component.board.api

import com.study.samplebackend.component.board.command.BoardService
import com.study.samplebackend.component.board.query.Board
import com.study.samplebackend.component.board.query.BoardRepository
import com.study.samplebackend.preconditions.PreconditionChain
import com.study.samplebackend.util.ObjectStatusResource
import com.study.samplebackend.util.createdResponse
import com.study.samplebackend.util.deletedResponse
import com.study.samplebackend.util.okResponse
import com.study.samplebackend.util.updatedResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class BoardApiController @Autowired internal constructor(
    private val boardRepo: BoardRepository,
    private val boardService: BoardService
) : BoardApi {
    override fun queryBoardBy(boardName: String): ResponseEntity<QueryBoardResource> {
        val board = boardRepo.findByBoardName(boardName)

        return board?.let(::toResponse)
            ?.let(::okResponse)
            ?: ResponseEntity.notFound().build()
    }

    override fun createNewBoard(
        resource: CreateBoardResource
    ): ResponseEntity<ObjectStatusResource> {
        PreconditionChain()
            .add(preconditionForBoard(resource.boardName))
            .verify()

        val board = Board(
            id = boardService.nextId(),
            boardName = resource.boardName,
            group = resource.group,
            description = resource.description
        )

        boardRepo.save(board)

        return createdResponse(
            id = board.boardName,
            linkBuilder = linkTo(methodOn(BoardApiController::class.java)
                .queryBoardBy(board.boardName))
        )
    }

    override fun updateBoard(boardName: String, resource: UpdateBoardResource): ResponseEntity<ObjectStatusResource> {
        boardRepo.findByBoardName(boardName)
            ?.apply {
                this.description = resource.description
                this.group = resource.group
            }
            ?.let(boardRepo::save)

        return updatedResponse(
            id = boardName,
            linkBuilder = linkTo(methodOn(BoardApiController::class.java).queryBoardBy(boardName))
        )
    }

    override fun deleteBoard(boardName: String): ResponseEntity<ObjectStatusResource> {
        boardRepo.deleteByBoardName(boardName)

        return deletedResponse(
            id = boardName,
            linkBuilder = linkTo(methodOn(BoardApiController::class.java).queryBoardBy(boardName))
        )
    }

    private fun preconditionForBoard(boardName: String): PreconditionChain =
        PreconditionChain().check(
            !boardService.isBoardExists(boardName),
            "Board with '$boardName' already exists"
        )

    private fun toResponse(
        board: Board
    ): QueryBoardResource {
        return QueryBoardResource(
            group = board.group,
            description = board.description
        )
    }
}
