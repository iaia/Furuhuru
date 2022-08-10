package dev.iaiabot.furuhuru.repository

internal interface UserRepository {
    fun getUserName(): String
    fun saveUserName(userName: String)
}
