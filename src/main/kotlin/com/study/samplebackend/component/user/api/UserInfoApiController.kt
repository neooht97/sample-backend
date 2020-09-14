package com.study.samplebackend.component.user.api

import com.study.samplebackend.component.user.UserInfo
import com.study.samplebackend.component.user.UserInfoRepository
import com.study.samplebackend.util.okResponse
import com.study.samplebackend.util.orNull
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

    private fun toResponse(
        userInfo: UserInfo
    ): QueryUserResource {
        return QueryUserResource(
            password = userInfo.password,
            authority = userInfo.authority
        )
    }
}