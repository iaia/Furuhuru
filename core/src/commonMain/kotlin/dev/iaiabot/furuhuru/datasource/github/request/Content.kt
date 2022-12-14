package dev.iaiabot.furuhuru.datasource.github.request

import kotlinx.serialization.Required
import kotlinx.serialization.Serializable

@Serializable
internal data class Content(
    @Required
    val message: String = "[ci skip] Upload Furuhuru image",
    @Required
    val content: String,
    val sha: String? = null,
    val branch: String? = null,
    val committer: String? = null,
)
