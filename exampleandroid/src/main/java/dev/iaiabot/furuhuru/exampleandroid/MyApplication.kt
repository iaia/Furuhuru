package dev.iaiabot.furuhuru.exampleandroid

import android.app.Application
import dev.iaiabot.furuhuru.android.Furuhuru

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Furuhuru.Builder(
            this,
        ).settingGithub(
            BuildConfig.GITHUB_API_TOKEN,
            "iaia",
            "Furuhuru"
        ).setLabels(
            "bug", "documentation"
        ).build()
    }
}
