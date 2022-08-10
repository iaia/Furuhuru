package dev.iaiabot.furuhuru.repository

internal class IssueRepositoryImpl(
    private val settings: dev.iaiabot.furuhuru.datasource.local.GithubSettings,
    private val service: dev.iaiabot.furuhuru.datasource.github.GithubService,
) : IssueRepository {

    override suspend fun post(issue: dev.iaiabot.furuhuru.datasource.github.request.Issue) {
        service.postIssue(
            settings.githubRepositoryOwner,
            settings.githubRepository,
            issue,
        )
    }
}
