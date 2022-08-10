package dev.iaiabot.furuhuru.di

import android.util.LruCache
import dev.iaiabot.furuhuru.datasource.local.ScreenshotDataSource
import dev.iaiabot.furuhuru.datasource.local.ScreenshotDataSourceImpl
import dev.iaiabot.furuhuru.datasource.local.UserDataSource
import dev.iaiabot.furuhuru.datasource.local.UserDataSourceImpl
import org.koin.core.qualifier.named
import org.koin.dsl.module

val coreAndroidModule = module {
    single(named("ScreenShotCache")) { LruCache<String, String>(1) }
    single<ScreenshotDataSource> { ScreenshotDataSourceImpl(get(named("ScreenShotCache"))) }
    single<UserDataSource> { UserDataSourceImpl(get()) }
}
