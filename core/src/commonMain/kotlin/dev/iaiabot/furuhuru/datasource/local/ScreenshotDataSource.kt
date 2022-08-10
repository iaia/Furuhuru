package dev.iaiabot.furuhuru.datasource.local

import kotlinx.coroutines.flow.Flow

internal interface ScreenshotDataSource {
    val screenShotFlow: Flow<String?>

    suspend fun save(fileStr: String)
    suspend fun remove()
    suspend fun load(): String?
}
