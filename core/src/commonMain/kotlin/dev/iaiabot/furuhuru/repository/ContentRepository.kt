package dev.iaiabot.furuhuru.repository

import dev.iaiabot.furuhuru.entity.ContentImageUrls

internal interface ContentRepository {
    suspend fun post(
        content: dev.iaiabot.furuhuru.datasource.github.request.Content,
        path: String
    ): ContentImageUrls?
}
