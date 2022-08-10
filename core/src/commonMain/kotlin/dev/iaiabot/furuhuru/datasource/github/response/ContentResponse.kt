package dev.iaiabot.furuhuru.datasource.github.response

import kotlinx.serialization.Serializable

@Serializable
internal data class ContentResponse(
    val content: dev.iaiabot.furuhuru.datasource.github.response.ContentInfoResponse
)
