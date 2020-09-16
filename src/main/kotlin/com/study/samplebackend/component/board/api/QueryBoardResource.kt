package com.study.samplebackend.component.board.api

import com.study.samplebackend.component.board.query.Task
import io.swagger.annotations.ApiModelProperty

data class QueryBoardResource(
    @get:ApiModelProperty(
        value = "Board description",
        example = "This is a sample description",
        required = true,
        position = 0
    )
    val description: String,

    @get:ApiModelProperty(
        value = "Board group",
        example = "Company A",
        required = true,
        position = 1
    )
    val group: String,

    @get:ApiModelProperty(
        value = "Board tasks",
        example = "{}",
        required = true,
        position = 2
    )
    val tasks: List<Task>? = listOf()
)
