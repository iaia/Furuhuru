package dev.iaiabot.furuhuru.di

import org.koin.dsl.module

val coreAndroidModule = module {
    // single(named("ScreenShotCache")) { LruCache<String, String>(1) }
    // single<ScreenshotDataSource> { ScreenshotDataSourceImpl(get(named("ScreenShotCache"))) }
    // single<UserDataSource> { UserDataSourceImpl(get()) }
}

