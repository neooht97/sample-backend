package com.study.samplebackend.component.user.api

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.annotations.ApiModelProperty

data class QueryUserResource(
    @get:ApiModelProperty(
        value = "User ID",
        example = "test user",
        required = true,
        position = 0
    )
    @JsonProperty("id")
    val password: String,

    @get:ApiModelProperty(
        value = "authority for the given user",
        example = "admin",
        required = true,
        position = 1
    )
    val authority: String
)