package com.study.samplebackend.component.board.api

import com.study.samplebackend.util.ObjectStatusResource
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class BoardApiController : BoardApi {
    override fun queryBoardBy(boardName: String): ResponseEntity<QueryBoardResource> {
        TODO("Not yet implemented")
    }

    override fun createNewBoard(resource: CreateBoardResource): ResponseEntity<ObjectStatusResource> {
        TODO("Not yet implemented")
    }

    override fun updateBoard(boardName: String, resource: CreateBoardResource): ResponseEntity<ObjectStatusResource> {
        TODO("Not yet implemented")
    }

    override fun deleteBoard(boardName: String): ResponseEntity<ObjectStatusResource> {
        TODO("Not yet implemented")
    }
}