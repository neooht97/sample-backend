package com.study.samplebackend.component.user.api

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty

@ApiModel("UpdateUser")
class UpdateUserResource(

    @get:ApiModelProperty(
        value = "Password",
        example = "password",
        required = true,
        position = 0
    )
    val password: String,

    @get:ApiModelProperty(
        value = "Authority",
        example = "admin",
        required = true,
        position = 1
    )
    val authority: String
)