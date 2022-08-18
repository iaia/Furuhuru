package dev.iaiabot.furuhuru.datasource.local

import kotlinx.coroutines.flow.MutableStateFlow
import platform.Foundation.NSCache
import platform.objc.objc_sync_enter
import platform.objc.objc_sync_exit

internal actual class ScreenshotDataSourceImpl(
    private val cache: NSCache,
) : ScreenshotDataSource {
    companion object {
        private const val SCREENSHOT_KEY = "screenshot"
    }

    override val screenShotFlow = MutableStateFlow(null)

    override suspend fun save(fileStr: String) {
        synchronized(cache) {
            cache.setObject(SCREENSHOT_KEY, fileStr)
        }
    }

    override suspend fun remove() {
        synchronized(cache) {
            cache.removeObjectForKey(SCREENSHOT_KEY)
            screenShotFlow.tryEmit(null)
        }
    }

    override suspend fun load(): String? {
        var screenshotStr: String? = null
        synchronized(cache) {
            screenshotStr = cache.objectForKey(SCREENSHOT_KEY) as? String
        }
        return screenshotStr
    }

    private fun synchronized(obj: Any, exec: () -> Unit) {
        objc_sync_enter(obj)
        exec.invoke()
        objc_sync_exit(obj)
    }
}
