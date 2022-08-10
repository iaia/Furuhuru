package dev.iaiabot.furuhuru.repository

import kotlinx.coroutines.flow.Flow

interface ScreenshotRepository {
    suspend fun save(fileStr: String)
    suspend fun load(remove: Boolean = false): String?
    suspend fun remove()
    suspend fun observe(): Flow<String?>
}
