package com.study.samplebackend.component.user.query

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface UserInfoRepository : MongoRepository<UserInfo, String>
