package dev.iaiabot.furuhuru.decorator

import android.os.Build
import dev.iaiabot.furuhuru.BuildConfig
import dev.iaiabot.furuhuru.decorator.IssueBodyTemplate.DEVICE_OS
import dev.iaiabot.furuhuru.decorator.IssueBodyTemplate.DEVICE_VERSION
import dev.iaiabot.furuhuru.decorator.IssueBodyTemplate.FURUFURU_VERSION_NAME
import dev.iaiabot.furuhuru.decorator.IssueBodyTemplate.IMAGE_FILE_URL
import dev.iaiabot.furuhuru.decorator.IssueBodyTemplate.IMAGE_URL
import dev.iaiabot.furuhuru.decorator.IssueBodyTemplate.TEMPLATE
import dev.iaiabot.furuhuru.decorator.IssueBodyTemplate.USERS_BODY
import dev.iaiabot.furuhuru.decorator.IssueBodyTemplate.USER_NAME

actual object IssueBodyBuilder {

    actual fun build(
        userName: String,
        usersBody: String,
        imageUrl: String?,
        fileUrl: String?
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

        body = body.replace(DEVICE_VERSION, Build.MANUFACTURER + " " + Build.MODEL)
        body = body.replace(DEVICE_OS, Build.VERSION.RELEASE)

        body = body.replace(FURUFURU_VERSION_NAME, BuildConfig.FURUHURU_VERSION)

        /*
        Furufuru.getApplicationName()?.let {
            body = body.replace(APP_NAME, it)
        }

        Furufuru.getAppVersionName()?.let {
            body = body.replace(APP_VERSION, it)
        }

         */

        return body
    }
}
