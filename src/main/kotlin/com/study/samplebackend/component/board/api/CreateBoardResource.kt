package com.study.samplebackend.component.board.api

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty

@ApiModel("CreateBoard")
data class CreateBoardResource(
    @get:ApiModelProperty(
        value = "Board name",
        example = "Test board",
        required = true,
        position = 0
    )
    val boardName: String
)