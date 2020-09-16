package com.study.samplebackend.component.user.api

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
    @GetMapping("/api/v1/users/{id}")
    fun userInfoBy(
        @ApiParam(
            value = "Username of the user",
            required = true
        )
        @PathVariable("id")
        id: String
    ): ResponseEntity<QueryUserResource>

    @ApiOperation(
        value = "Create new user",
        notes = "Create new user"
    )
    @ApiResponses(
        ApiResponse(code = 200, message = "Created successfully"),
        ApiResponse(code = 200, message = "Failed to create user")
    )
    @PostMapping("/api/v1/users")
    fun createUser(
        @ApiParam(
            value = "Representation of user",
            required = true
        )
        @RequestBody
        resource: CreateUserResource
    ): ResponseEntity<ObjectStatusResource>

    @ApiOperation(
        value = "Update user",
        notes = "Update user"
    )
    @ApiResponses(
        ApiResponse(code = 200, message = "Update successfully"),
        ApiResponse(code = 404, message = "User is not found")
    )
    @PutMapping("/api/v1/users/{id}")
    fun updateUser(
        @ApiParam(
            value = "User ID",
            required = true
        )
        @PathVariable("id")
        id: String,

        @ApiParam(
            value = "Representation of user",
            required = true
        )
        @RequestBody
        resource: UpdateUserResource
    ): ResponseEntity<ObjectStatusResource>

    @ApiOperation(
        value = "Delete user by username",
        notes = "Delete user by username"
    )
    @ApiResponses(
        ApiResponse(code = 200, message = "User deleted successfully"),
        ApiResponse(code = 400, message = "User cannot be deleted"),
        ApiResponse(code = 404, message = "User does not exist")
    )
    @DeleteMapping("/api/v1/users/{id}")
    fun deleteUser(
        @ApiParam(
            value = "ID of the user",
            required = true
        )
        @PathVariable("id")
        id: String
    ): ResponseEntity<ObjectStatusResource>
}
