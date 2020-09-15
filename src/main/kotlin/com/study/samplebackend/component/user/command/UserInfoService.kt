package com.study.samplebackend.component.user.command

import com.study.samplebackend.component.user.entity.UserInfoRepository
import com.study.samplebackend.util.orNull
import org.springframework.stereotype.Component

@Component
class UserInfoService(
    private val userInfoRepository: UserInfoRepository
) {
    fun isIdUnique(id: String): Boolean =
        userInfoRepository.findById(id).orNull() == null
}