package dev.iaiabot.furuhuru.decorator

import dev.iaiabot.furuhuru.entity.ApplicationInfo

expect object IssueBodyBuilder {
    fun build(
        userName: String,
        usersBody: String,
        imageUrl: String?,
        fileUrl: String?,
        applicationInfo: ApplicationInfo,
    ): String
}
