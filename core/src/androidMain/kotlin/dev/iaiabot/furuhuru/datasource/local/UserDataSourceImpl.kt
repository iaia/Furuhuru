package dev.iaiabot.furuhuru.datasource.local

import android.content.SharedPreferences

internal actual class UserDataSourceImpl(
    private val preferences: SharedPreferences
) : UserDataSource {

    companion object {
        private const val USERNAME = "username"
    }

    override fun getUserName(): String {
        return preferences.getString(USERNAME, "") ?: ""
    }

    override fun saveUserName(newUserName: String) {
        val editor = preferences.edit()
        editor.putString(USERNAME, newUserName)
        editor.apply()
    }
}
