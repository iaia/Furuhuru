package dev.iaiabot.furuhuru.di

import dev.iaiabot.furuhuru.datasource.github.GithubService
import dev.iaiabot.furuhuru.datasource.local.GithubSettings
import dev.iaiabot.furuhuru.repository.*
import dev.iaiabot.furuhuru.usecase.GetScreenShotUseCase
import dev.iaiabot.furuhuru.usecase.GetScreenShotUseCaseImpl
import dev.iaiabot.furuhuru.usecase.PostIssueUseCase
import dev.iaiabot.furuhuru.usecase.PostIssueUseCaseImpl
import dev.iaiabot.furuhuru.usecase.user.LoadUserNameUseCase
import dev.iaiabot.furuhuru.usecase.user.LoadUserNameUseCaseImpl
import dev.iaiabot.furuhuru.usecase.user.SaveUsernameUseCase
import dev.iaiabot.furuhuru.usecase.user.SaveUsernameUseCaseImpl
import org.koin.core.qualifier.named
import org.koin.dsl.module


internal val apiModule = module {
    single<GithubService> { GithubService(get(named("github_api_token"))) }
}

internal val repositoryModule = module {
    single<IssueRepository> {
        IssueRepositoryImpl(
            get(),
            get()
        )
    }
    single<ContentRepository> {
        ContentRepositoryImpl(
            get(),
            get()
        )
    }
    single<ScreenshotRepository> {
        ScreenshotRepositoryImpl(get())
    }

    single<UserRepository> {
        UserRepositoryImpl(get())
    }
}

internal val useCaseModule = module {
    single<SaveUsernameUseCase> { SaveUsernameUseCaseImpl(get()) }
    single<LoadUserNameUseCase> { LoadUserNameUseCaseImpl(get()) }
    single<PostIssueUseCase> { PostIssueUseCaseImpl(get(), get(), get(), get(), get(), get()) }
    single<GetScreenShotUseCase> { GetScreenShotUseCaseImpl(get()) }
}

internal val utilModule = module {
    single { GithubSettings() }
    single(named("github_api_token")) { get<GithubSettings>().githubApiToken }
}

val coreModules = listOf(
    apiModule,
    repositoryModule,
    useCaseModule,
    utilModule,
)
