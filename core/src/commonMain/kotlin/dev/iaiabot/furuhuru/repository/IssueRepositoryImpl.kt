package dev.iaiabot.furuhuru.repository

import dev.iaiabot.furuhuru.datasource.github.GithubService
import dev.iaiabot.furuhuru.datasource.github.request.Issue
import dev.iaiabot.furuhuru.datasource.local.GithubSettings

internal class IssueRepositoryImpl(
    private val settings: GithubSettings,
    private val service: GithubService,
) : IssueRepository {

    override suspend fun post(issue: Issue) {
        service.postIssue(
            settings.githubRepositoryOwner,
            settings.githubRepository,
            issue,
        )
    }
}
