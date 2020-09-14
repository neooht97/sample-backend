package com.study.samplebackend.component.user

import com.study.samplebackend.util.orNull
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class UserService @Autowired internal constructor(
    val repository: UserInfoRepository
) {
    fun createUser(user: CreateUserResource) {
        repository.save(
            UserInfo(
                id = user.id,
                password = user.password,
                authority = user.authority
            )
        )
    }

    fun deleteUser(userId: String) {
        repository.deleteById(userId)
    }

    fun updateUser(updatedUser: UpdateUserResource) {
        repository.findById(updatedUser.id).orNull()
            ?.apply {
                this.password = updatedUser.password
                this.authority = updatedUser.authority
            }
            ?.let(repository::save)
    }
}