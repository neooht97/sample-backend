package com.study.samplebackend.component.user.api

import com.study.samplebackend.component.user.entity.UserInfo
import com.study.samplebackend.component.user.entity.UserInfoRepository
import com.study.samplebackend.util.ObjectStatusResource
import com.study.samplebackend.util.createdResponse
import com.study.samplebackend.util.deletedResponse
import com.study.samplebackend.util.updatedResponse
import com.study.samplebackend.util.okResponse
import com.study.samplebackend.util.orNull
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class UserInfoApiController(
    private val repository: UserInfoRepository
) : UserInfoApi {
    override fun userInfoBy(id: String): ResponseEntity<QueryUserResource> {
        val userInfo = repository.findById(id).orNull()

        return userInfo?.let(::toResponse)
            ?.let(::okResponse)
            ?: ResponseEntity.notFound().build()
    }

    override fun createUser(resource: CreateUserResource): ResponseEntity<ObjectStatusResource> {
        val userInfo = UserInfo(
            id = resource.id,
            password = resource.password,
            authority = resource.authority
        )

        repository.save(userInfo)

        return createdResponse(
            id = userInfo.id,
            linkBuilder = linkTo(methodOn(UserInfoApi::class.java).userInfoBy(userInfo.id))
        )
    }

    override fun updateUser(id: String, resource: UpdateUserResource): ResponseEntity<ObjectStatusResource> {
        repository.findById(id).orNull()
            ?.apply {
                this.password = resource.password
                this.authority = resource.authority
            }
            ?.let(repository::save)

        return updatedResponse(
            id = id,
            linkBuilder = linkTo(methodOn(UserInfoApi::class.java).userInfoBy(id))
        )
    }

    override fun deleteUser(id: String): ResponseEntity<ObjectStatusResource> {
        repository.deleteById(id)

        return deletedResponse(
            id = id,
            linkBuilder = linkTo(methodOn(UserInfoApi::class.java).userInfoBy(id))
        )
    }

    private fun toResponse(
        userInfo: UserInfo
    ): QueryUserResource {
        return QueryUserResource(
            password = userInfo.password,
            authority = userInfo.authority
        )
    }
}