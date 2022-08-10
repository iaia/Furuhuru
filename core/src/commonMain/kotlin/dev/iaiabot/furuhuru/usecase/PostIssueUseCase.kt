package dev.iaiabot.furuhuru.usecase

import dev.iaiabot.furuhuru.datasource.github.request.Content
import dev.iaiabot.furuhuru.datasource.github.request.Issue
import dev.iaiabot.furuhuru.datasource.local.GithubSettings
import dev.iaiabot.furuhuru.decorator.IssueBodyBuilder
import dev.iaiabot.furuhuru.entity.ApplicationInfo
import dev.iaiabot.furuhuru.entity.ContentImageUrls
import dev.iaiabot.furuhuru.repository.ContentRepository
import dev.iaiabot.furuhuru.repository.IssueRepository
import dev.iaiabot.furuhuru.repository.ScreenshotRepository
import dev.iaiabot.furuhuru.usecase.user.SaveUsernameUseCase
import kotlinx.datetime.Clock

interface PostIssueUseCase {
    suspend operator fun invoke(
        title: String?,
        userName: String?,
        body: String?,
        labels: List<String> = emptyList()
    )
}

internal class PostIssueUseCaseImpl(
    private val issueRepository: IssueRepository,
    private val screenshotRepository: ScreenshotRepository,
    private val contentRepository: ContentRepository,
    private val githubSettings: GithubSettings,
    private val saveUsernameUseCase: SaveUsernameUseCase,
    private val applicationInfo: ApplicationInfo,
) : PostIssueUseCase {

    override suspend fun invoke(
        title: String?,
        userName: String?,
        body: String?,
        labels: List<String>
    ) {
        if (title.isNullOrBlank()) {
            // TODO: no title exception作る
            throw Exception("no title")
        }

        saveUsernameUseCase(userName)

        val imageUrls = uploadScreenShot() ?: throw Exception("failed to upload screenshot")

        val issue = Issue(
            title,
            IssueBodyBuilder.build(
                userName ?: "",
                body ?: "",
                imageUrls.imageUrl,
                imageUrls.fileUrl,
                applicationInfo,
            ),
            labels = labels
        )

        try {
            issueRepository.post(issue)
            screenshotRepository.remove()
        } catch (e: Exception) {
            throw e
        }
    }

    private suspend fun uploadScreenShot(): ContentImageUrls? {
        val screenshot = screenshotRepository.load(false)
        if (screenshot.isNullOrEmpty()) {
            throw Exception("no screenshot")
        }
        val content = Content(
            content = screenshot,
            branch = githubSettings.furufuruBranch
        )
        try {
            val result = contentRepository.post(content, generateUploadDestinationPath())
            return result
        } catch (e: Exception) {
            throw e
        }
    }

    private fun generateUploadDestinationPath(): String {
        val now = Clock.System.now()
        val nowString = now.toString()
        return "$nowString.jpg"
    }
}
