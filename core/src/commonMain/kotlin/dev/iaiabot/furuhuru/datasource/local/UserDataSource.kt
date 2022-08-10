package dev.iaiabot.furuhuru.datasource.local

internal interface UserDataSource {

    fun getUserName(): String
    fun saveUserName(newUserName: String)
}

