package dev.iaiabot.furuhuru.exampleandroid

import android.app.Application
import dev.iaiabot.furuhuru.android.Furufuru

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Furufuru.Builder(
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
