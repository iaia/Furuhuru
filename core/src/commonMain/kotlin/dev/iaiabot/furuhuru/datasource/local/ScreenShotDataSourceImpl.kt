package dev.iaiabot.furuhuru.datasource.local

import kotlinx.coroutines.flow.Flow

internal expect class ScreeenShotDataSourceImpl : ScreenshotDataSource {
    override val screenShotFlow: Flow<String?>

    override suspend fun save(fileStr: String)
    override suspend fun remove()
    override suspend fun load(): String?
}

