package com.study.samplebackend.component.user.api

import io.swagger.annotations.*
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@Api(
    tags = ["User Info Service"],
    value = "User Info Service",
    description = "To query, create, update user info"
)
interface UserInfoApi {
    @ApiOperation(
        value = "Query user info",
        notes = "Query user info"
    )
    @ApiResponses(
        ApiResponse(code = 200, message = "Query successful"),
        ApiResponse(code = 404, message = "User is not found")
    )
    @GetMapping("api/v1/users/{id}")
    fun userInfoBy(
        @ApiParam(
            value = "Username of the user",
            required = true
        )
        @PathVariable("id")
        id: String
    ): ResponseEntity<QueryUserResource>
}