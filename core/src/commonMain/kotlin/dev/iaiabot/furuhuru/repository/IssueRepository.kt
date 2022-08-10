package dev.iaiabot.furuhuru.repository

import dev.iaiabot.furuhuru.datasource.github.request.Issue

internal interface IssueRepository {
    suspend fun post(issue: Issue)
}
