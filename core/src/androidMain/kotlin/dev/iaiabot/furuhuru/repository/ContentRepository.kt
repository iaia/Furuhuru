package dev.iaiabot.furuhuru.repository

import dev.iaiabot.furuhuru.datasource.github.request.Content
import dev.iaiabot.furuhuru.entity.ContentImageUrls

internal interface ContentRepository {
    suspend fun post(
        content: Content,
        path: String
    ): ContentImageUrls?
}
