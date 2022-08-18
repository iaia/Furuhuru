package dev.iaiabot.furuhuru.di

import dev.iaiabot.furuhuru.datasource.local.ScreenshotDataSource
import dev.iaiabot.furuhuru.datasource.local.ScreenshotDataSourceImpl
import dev.iaiabot.furuhuru.datasource.local.UserDataSource
import dev.iaiabot.furuhuru.datasource.local.UserDataSourceImpl
import org.koin.core.qualifier.named
import org.koin.dsl.module
import platform.Foundation.NSCache

val coreIosModule = module {
    single(named("ScreenShotCache")) { NSCache() }

    single<ScreenshotDataSource> { ScreenshotDataSourceImpl(get(named("ScreenShotCache"))) }
    single<UserDataSource> { UserDataSourceImpl(get()) }
}
