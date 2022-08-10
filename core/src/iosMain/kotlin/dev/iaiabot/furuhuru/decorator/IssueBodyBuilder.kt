package dev.iaiabot.furuhuru.decorator

import dev.iaiabot.furuhuru.entity.ApplicationInfo
import platform.UIKit.UIDevice

actual object IssueBodyBuilder {
    actual fun build(
        userName: String,
        usersBody: String,
        imageUrl: String?,
        fileUrl: String?,
        applicationInfo: ApplicationInfo,
    ): String {
        var body = IssueBodyTemplate.TEMPLATE

        body = if (userName.isEmpty()) {
            body.replace(
                IssueBodyTemplate.USER_NAME,
                "[Spartacus](https://youtu.be/FKCmyiljKo0?t=65)"
            )
        } else {
            body.replace(IssueBodyTemplate.USER_NAME, userName)
        }
        body = body.replace(IssueBodyTemplate.USERS_BODY, usersBody)

        imageUrl?.let {
            body = body.replace(IssueBodyTemplate.IMAGE_URL, it)
        }

        fileUrl?.let {
            body = body.replace(IssueBodyTemplate.IMAGE_FILE_URL, it)
        }

        body = body.replace(
            IssueBodyTemplate.DEVICE,
            UIDevice.currentDevice.model + " " + UIDevice.currentDevice.name
        )
        body = body.replace(
            IssueBodyTemplate.DEVICE_OS,
            UIDevice.currentDevice.systemVersion + " " + UIDevice.currentDevice.systemVersion
        )

        // body = body.replace(IssueBodyTemplate.FURUHURU_VERSION_NAME, BuildConfig.FURUHURU_VERSION)

        body = body.replace(IssueBodyTemplate.APP_NAME, applicationInfo.name ?: "unknown app name")

        body = body.replace(
            IssueBodyTemplate.APP_VERSION,
            applicationInfo.version ?: "unknown app version"
        )

        return body
    }
}
