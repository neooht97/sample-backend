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
    val boardName: String,

    @get:ApiModelProperty(
        value = "Board group",
        example = "Company A",
        required = true,
        position = 1
    )
    val group: String,

    @get:ApiModelProperty(
        value = "Board description",
        example = "This is an example board",
        required = true,
        position = 2
    )
    val description: String
)
