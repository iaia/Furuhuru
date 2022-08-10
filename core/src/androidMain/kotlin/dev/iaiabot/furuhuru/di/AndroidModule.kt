package dev.iaiabot.furuhuru.di

import android.util.LruCache
import dev.iaiabot.furuhuru.datasource.local.ScreenshotDataSourceImpl
import org.koin.core.qualifier.named
import org.koin.dsl.module

val coreAndroidModule = module {
    single(named("ScreenShotCache")) { LruCache<String, String>(1) }
    single<ScreenshotDataSourceImpl> { ScreenshotDataSourceImpl(get(named("ScreenShotCache"))) }
}
