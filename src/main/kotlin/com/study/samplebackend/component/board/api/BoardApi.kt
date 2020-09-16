package com.study.samplebackend.component.board.api

import com.study.samplebackend.component.board.query.Board
import com.study.samplebackend.util.ObjectStatusResource
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody

@Api(
    tags = ["Board Service"],
    value = "Board Service",
    description = "To create new boards base on user"
)
interface BoardApi {
    @ApiOperation(
        value = "Query user boards",
        notes = "Query user boards"
    )
    @ApiResponses(
        ApiResponse(code = 200, message = "Query successful"),
        ApiResponse(code = 404, message = "Board does not exists")
    )
    @GetMapping("/api/v1/board/{boardName}")
    fun queryBoardBy(
        @ApiParam(
            value = "Name of the board",
            required = true
        )
        @PathVariable("boardName")
        boardName: String
    ): ResponseEntity<QueryBoardResource>

    @ApiOperation(
        value = "Create a user board",
        notes = "Create a user board"
    )
    @ApiResponses(
        ApiResponse(code = 200, message = "Create successful"),
        ApiResponse(code = 404, message = "Create failed")
    )
    @PostMapping("/api/v1/board/{boardName}")
    fun createNewBoard(
        @ApiParam(
            value = "Information of the board",
            required = true
        )
        @RequestBody
        resource: CreateBoardResource
    ): ResponseEntity<ObjectStatusResource>

    @ApiOperation(
        value = "Update a user board",
        notes = "Update a user board"
    )
    @ApiResponses(
        ApiResponse(code = 200, message = "Update successful"),
        ApiResponse(code = 404, message = "Update failed")
    )
    @PutMapping("/api/v1/board/{boardName}")
    fun updateBoard(
        @ApiParam(
            value = "Name of the board",
            required = true
        )
        @PathVariable
        boardName: String,

        @ApiParam(
            value = "Updated information of the board",
            required = true
        )
        @RequestBody
        resource: CreateBoardResource
    ): ResponseEntity<ObjectStatusResource>

    @ApiOperation(
        value = "Delete a user board",
        notes = "Delete a user board"
    )
    @ApiResponses(
        ApiResponse(code = 200, message = "Delete successful"),
        ApiResponse(code = 404, message = "Delete failed")
    )
    @DeleteMapping("/api/v1/board/{boardName}")
    fun deleteBoard(
        @ApiParam(
            value = "Name of the board",
            required = true
        )
        @PathVariable
        boardName: String
    ): ResponseEntity<ObjectStatusResource>
}