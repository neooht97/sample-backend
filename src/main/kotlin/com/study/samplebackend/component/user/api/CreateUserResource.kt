package com.study.samplebackend.component.user.api

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty

@ApiModel("CreateUser")
data class CreateUserResource (
    @get:ApiModelProperty(
        value = "User ID",
        example = "test user",
        required = true,
        position = 0
    )
    val id: String,

    @get:ApiModelProperty(
        value = "Password",
        example = "password",
        required = true,
        position = 1
    )
    val password: String,

    @get:ApiModelProperty(
        value = "Authority",
        example = "admin",
        required = true,
        position = 2
    )
    val authority: String
)