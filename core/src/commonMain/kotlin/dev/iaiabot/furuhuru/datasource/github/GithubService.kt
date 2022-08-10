package dev.iaiabot.furuhuru.datasource.github

import dev.iaiabot.furuhuru.datasource.github.request.Content
import dev.iaiabot.furuhuru.datasource.github.response.ContentResponse
import dev.iaiabot.furuhuru.datasource.github.response.IssueResponse
import dev.iaiabot.furuhuru.entity.ContentImageUrls
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.http.ContentType.Application.Json
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

internal class GithubService(
    githubApiToken: String
) {
    companion object {
        private const val TIMEOUT_MILLI_SEC = 20_000L
    }

    private val client = HttpClient(CIO) {
        install(HttpTimeout) {
            requestTimeoutMillis = TIMEOUT_MILLI_SEC
            connectTimeoutMillis = TIMEOUT_MILLI_SEC
            socketTimeoutMillis = TIMEOUT_MILLI_SEC
        }
        install(ContentNegotiation) {
            json(
                Json {
                    encodeDefaults = false
                    ignoreUnknownKeys = true
                    isLenient = true
                }
            )
        }
        defaultRequest {
            url("https://api.github.com")
            header("Authorization", "token $githubApiToken")
            contentType(Json)
        }
    }

    suspend fun postIssue(
        owner: String,
        repo: String,
        issue: dev.iaiabot.furuhuru.datasource.github.request.Issue
    ): IssueResponse {
        val response = client.post("repos/${owner}/${repo}/issues") {
            setBody(issue)
        }

        return response.body()
    }

    suspend fun postContent(
        owner: String,
        repo: String,
        content: Content,
        path: String
    ): ContentImageUrls {
        val response = client.put("repos/${owner}/${repo}/contents/${path}") {
            setBody(content)
        }
        val status = response.status
        if (!status.isSuccess()) {
            val errorBody = response.body<String?>()
            throw Exception(errorBody)
        }
        val body = response.body<ContentResponse>()
        return ContentImageUrls(
            body.content.htmlUrl,
            body.content.downloadUrl,
        )
    }
}
