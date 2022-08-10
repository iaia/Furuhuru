package dev.iaiabot.furuhuru.datasource.github

import dev.iaiabot.furuhuru.entity.ContentImageUrls
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*

internal class GithubService {
    private val baseUrl = "https://api.github.com"
    private val client = HttpClient()

    suspend fun postIssue(
        owner: String,
        repo: String,
        issue: dev.iaiabot.furuhuru.datasource.github.request.Issue
    ): dev.iaiabot.furuhuru.datasource.github.response.IssueResponse {
        val response = client.post("${baseUrl}/repos/${owner}/${repo}/issues") {
            setBody(issue)
        }

        return response.body()
    }

    suspend fun postContent(
        owner: String,
        repo: String,
        content: dev.iaiabot.furuhuru.datasource.github.request.Content,
        path: String
    ): ContentImageUrls {
        val response = client.put("${baseUrl}/repos/${owner}/${repo}/contents/${path}") {
            setBody(content)
        }
        val status = response.status
        if (!status.isSuccess()) {
            val errorBody = response.body<String?>()
            throw Exception(errorBody)
        }
        val body: dev.iaiabot.furuhuru.datasource.github.response.ContentResponse = response.body()
        return ContentImageUrls(
            body.content.htmlUrl,
            body.content.downloadUrl,
        )
    }
}
