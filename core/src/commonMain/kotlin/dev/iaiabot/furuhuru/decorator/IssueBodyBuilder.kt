package dev.iaiabot.furuhuru.decorator

expect object IssueBodyBuilder {
    fun build(
        userName: String,
        usersBody: String,
        imageUrl: String?,
        fileUrl: String?
    ): String
}
