package dev.iaiabot.furuhuru.datasource.github.response

import kotlinx.serialization.Serializable

@Serializable
internal data class ContentResponse(
    val content: ContentInfoResponse
)
