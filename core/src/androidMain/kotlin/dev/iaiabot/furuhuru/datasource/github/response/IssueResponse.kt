package dev.iaiabot.furuhuru.datasource.github.response

import kotlinx.serialization.Serializable

@Serializable
data class IssueResponse(
    val body: String? = null
)
