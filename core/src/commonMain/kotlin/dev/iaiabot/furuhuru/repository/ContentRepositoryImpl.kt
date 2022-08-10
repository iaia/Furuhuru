package dev.iaiabot.furuhuru.repository

import dev.iaiabot.furuhuru.entity.ContentImageUrls

internal class ContentRepositoryImpl(
    private val settings: dev.iaiabot.furuhuru.datasource.local.GithubSettings,
    private val service: dev.iaiabot.furuhuru.datasource.github.GithubService,
) : ContentRepository {

    override suspend fun post(
        content: dev.iaiabot.furuhuru.datasource.github.request.Content,
        path: String
    ): ContentImageUrls? {
        return service.postContent(
            settings.githubRepositoryOwner,
            settings.githubRepository,
            content,
            path
        )
    }
}
