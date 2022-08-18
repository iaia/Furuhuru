package dev.iaiabot.furuhuru.datasource.local

import platform.Foundation.NSUserDefaults

internal actual class UserDataSourceImpl(
    private val userDefaults: NSUserDefaults,
) : UserDataSource {

    companion object {
        private const val USERNAME = "username"
    }

    override fun getUserName(): String {
        return userDefaults.objectForKey(USERNAME) as? String ?: ""
    }

    override fun saveUserName(newUserName: String) {
        userDefaults.setObject(newUserName, USERNAME)
    }
}
