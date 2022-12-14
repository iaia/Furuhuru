package dev.iaiabot.furuhuru.datasource.github.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class ContentInfoResponse(
    val name: String,
    val url: String,
    @SerialName("html_url")
    val htmlUrl: String,
    @SerialName("download_url")
    val downloadUrl: String
)
