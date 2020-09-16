package com.study.samplebackend.component.board.api

import io.swagger.annotations.ApiModelProperty

data class UpdateBoardResource(
    @get:ApiModelProperty(
        value = "Board description",
        example = "This is a sample description",
        required = true,
        position = 0
    )
    val description: String,

    @get:ApiModelProperty(
        value = "Board Group",
        example = "Company A",
        required = true,
        position = 1
    )
    val group: String
)
