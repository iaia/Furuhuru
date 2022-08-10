package dev.iaiabot.furuhuru.android.di

import android.content.Context
import android.content.SharedPreferences
import dev.iaiabot.furuhuru.android.issue.IssueViewModel
import dev.iaiabot.furuhuru.android.issue.IssueViewModelImpl
import dev.iaiabot.furuhuru.android.utils.screenshot.ScreenShotter
import dev.iaiabot.furuhuru.di.coreModules
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal fun diModules() = listOf(
    viewModelModule,
    androidModule,
) + coreModules()

private val viewModelModule = module {
    viewModel<IssueViewModel> {
        IssueViewModelImpl(get(), get(), get(), get())
    }
}

private val androidModule = module {
    single { ScreenShotter(get()) }
    single<SharedPreferences> {
        androidApplication().getSharedPreferences(
            "furufuru",
            Context.MODE_PRIVATE
        )
    }
}
