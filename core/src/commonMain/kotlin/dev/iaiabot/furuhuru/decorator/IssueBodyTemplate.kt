package dev.iaiabot.furuhuru.decorator

object IssueBodyTemplate {
    const val USER_NAME = "USER_NAME"
    const val USERS_BODY = "USERS_BODY"
    const val APP_NAME = "APP_NAME"
    const val APP_VERSION = "APP_VERSION"
    const val DEVICE = "DEVICE_DEVICE_DEVICE"
    const val DEVICE_OS = "DEVICE_OS"
    const val FURUHURU_VERSION_NAME = "FURUHURU_VERSION_NAME"
    const val IMAGE_URL = "IMAGE_URL"
    const val IMAGE_FILE_URL = "IMAGE_FILE_URL"

    const val TEMPLATE = """
## body

$USERS_BODY

## screenshot

[screenshot]($IMAGE_FILE_URL)

<img src="$IMAGE_URL" alt="look above" width=300px />

## device info

|key|value|
|:--:|:--:|
|User Name|$USER_NAME|
|App Name|$APP_NAME|
|App Version|$APP_VERSION|
|Device|$DEVICE|
|Device OS|$DEVICE_OS|
|Furuhuru Version|$FURUHURU_VERSION_NAME|
"""
}
