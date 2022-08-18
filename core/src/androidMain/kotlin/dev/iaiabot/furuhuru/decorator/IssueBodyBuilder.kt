package dev.iaiabot.furuhuru.decorator

import android.os.Build
import dev.iaiabot.furuhuru.BuildKonfig
import dev.iaiabot.furuhuru.decorator.IssueBodyTemplate.APP_NAME
import dev.iaiabot.furuhuru.decorator.IssueBodyTemplate.APP_VERSION
import dev.iaiabot.furuhuru.decorator.IssueBodyTemplate.DEVICE
import dev.iaiabot.furuhuru.decorator.IssueBodyTemplate.DEVICE_OS
import dev.iaiabot.furuhuru.decorator.IssueBodyTemplate.FURUHURU_VERSION_NAME
import dev.iaiabot.furuhuru.decorator.IssueBodyTemplate.IMAGE_FILE_URL
import dev.iaiabot.furuhuru.decorator.IssueBodyTemplate.IMAGE_URL
import dev.iaiabot.furuhuru.decorator.IssueBodyTemplate.TEMPLATE
import dev.iaiabot.furuhuru.decorator.IssueBodyTemplate.USERS_BODY
import dev.iaiabot.furuhuru.decorator.IssueBodyTemplate.USER_NAME
import dev.iaiabot.furuhuru.entity.ApplicationInfo

actual object IssueBodyBuilder {

    actual fun build(
        userName: String,
        usersBody: String,
        imageUrl: String?,
        fileUrl: String?,
        applicationInfo: ApplicationInfo,
    ): String {
        var body = TEMPLATE

        body = if (userName.isEmpty()) {
            body.replace(USER_NAME, "[Spartacus](https://youtu.be/FKCmyiljKo0?t=65)")
        } else {
            body.replace(USER_NAME, userName)
        }
        body = body.replace(USERS_BODY, usersBody)

        imageUrl?.let {
            body = body.replace(IMAGE_URL, it)
        }

        fileUrl?.let {
            body = body.replace(IMAGE_FILE_URL, it)
        }

        body = body.replace(DEVICE, Build.MANUFACTURER + " " + Build.MODEL)
        body = body.replace(DEVICE_OS, Build.VERSION.RELEASE)

        body = body.replace(FURUHURU_VERSION_NAME, BuildKonfig.FURUHURU_VERSION)

        body = body.replace(APP_NAME, applicationInfo.name ?: "unknown app name")

        body = body.replace(APP_VERSION, applicationInfo.version ?: "unknown app version")

        return body
    }
}
