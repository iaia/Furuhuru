package dev.iaiabot.furuhuru.repository

import dev.iaiabot.furuhuru.datasource.local.UserDataSource

internal class UserRepositoryImpl(
    private val userDataSource: UserDataSource
) : UserRepository {

    override fun getUserName(): String {
        return userDataSource.getUserName()
    }

    override fun saveUserName(userName: String) {
        userDataSource.saveUserName(userName)
    }
}
