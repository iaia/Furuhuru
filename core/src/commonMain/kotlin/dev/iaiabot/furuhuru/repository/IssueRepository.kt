package dev.iaiabot.furuhuru.repository

internal interface IssueRepository {
    suspend fun post(issue: dev.iaiabot.furuhuru.datasource.github.request.Issue)
}
