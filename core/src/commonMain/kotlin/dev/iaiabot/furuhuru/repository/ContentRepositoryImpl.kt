package dev.iaiabot.furuhuru.repository

import dev.iaiabot.furuhuru.datasource.github.GithubService
import dev.iaiabot.furuhuru.datasource.github.request.Content
import dev.iaiabot.furuhuru.datasource.local.GithubSettings
import dev.iaiabot.furuhuru.entity.ContentImageUrls

internal class ContentRepositoryImpl(
    private val settings: GithubSettings,
    private val service: GithubService,
) : ContentRepository {

    override suspend fun post(
        content: Content,
        path: String
    ): ContentImageUrls {
        return service.postContent(
            settings.githubRepositoryOwner,
            settings.githubRepository,
            content,
            path
        )
    }
}
