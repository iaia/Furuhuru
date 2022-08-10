package dev.iaiabot.furuhuru.datasource.local

import kotlinx.coroutines.flow.Flow

internal actual class ScreenshotDataSourceImpl : ScreenshotDataSource {
    override val screenShotFlow: Flow<String?>
        get() = TODO("Not yet implemented")

    override suspend fun save(fileStr: String) {
        TODO("Not yet implemented")
    }

    override suspend fun remove() {
        TODO("Not yet implemented")
    }

    override suspend fun load(): String? {
        TODO("Not yet implemented")
    }

}
