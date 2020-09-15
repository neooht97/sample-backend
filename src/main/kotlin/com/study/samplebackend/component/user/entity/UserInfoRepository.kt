package com.study.samplebackend.component.user.entity

import com.study.samplebackend.component.user.entity.UserInfo
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface UserInfoRepository : MongoRepository<UserInfo, String>