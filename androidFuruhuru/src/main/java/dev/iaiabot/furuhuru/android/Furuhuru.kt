package dev.iaiabot.furuhuru.android

import android.app.Application
import android.content.pm.PackageInfo
import dev.iaiabot.furuhuru.android.di.diModules
import dev.iaiabot.furuhuru.android.utils.lifecycle.FuruhuruLifecycleCallback
import dev.iaiabot.furuhuru.datasource.local.GithubSettings
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.java.KoinJavaComponent.inject

class Furuhuru private constructor(
    private val application: Application,
) {

    class Builder(
        private val application: Application
    ) {
        private var githubApiToken: String? = null
        private var githubReposOwner: String? = null
        private var githubRepository: String? = null
        private var FuruhuruBranch: String? = null
        private var labels: List<String> = emptyList()

        fun settingGithub(
            githubApiToken: String,
            githubReposOwner: String,
            githubRepository: String,
            FuruhuruBranch: String? = null
        ): Builder {
            this.githubApiToken = githubApiToken
            this.githubReposOwner = githubReposOwner
            this.githubRepository = githubRepository
            this.FuruhuruBranch = FuruhuruBranch
            return this
        }

        fun setLabels(vararg labels: String): Builder {
            this.labels = labels.map { it }
            return this
        }

        fun build(): Furuhuru {
            // TODO: すでにあるinstance破棄して新しく作り直したい
            val instance = getInstance(application) ?: throw Exception()
            instance.githubSettings.init(
                githubApiToken = githubApiToken ?: "",
                githubReposOwner = githubReposOwner ?: "",
                githubRepository = githubRepository ?: "",
                FuruhuruBranch = FuruhuruBranch,
            )
            instance.githubSettings.addLabels(labels)
            instance.start()
            return instance
        }
    }

    companion object {
        private var instance: Furuhuru? = null

        internal fun getAppVersionName() = getInstance()?.getApplicationVersion()

        internal fun getApplicationName() = getInstance()?.getApplicationName()

        internal fun takeScreenshot() {
            getInstance()?.takeScreenshot()
        }

        private fun getInstance(application: Application? = null): Furuhuru? {
            if (instance == null) {
                application?.let {
                    instance = Furuhuru(application)
                }
            }
            return instance
        }
    }

    private val githubSettings: GithubSettings by inject(GithubSettings::class.java)
    private val applicationLifecycleCallbacks: FuruhuruLifecycleCallback =
        FuruhuruLifecycleCallback()

    init {
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(application)
            modules(diModules)
        }
    }

    internal fun start() {
        application.registerActivityLifecycleCallbacks(applicationLifecycleCallbacks)
    }

    fun takeScreenshot() {
        applicationLifecycleCallbacks.takeScreenshot()
    }

    private fun getApplicationName(): String? {
        val applicationInfo = application.applicationInfo
        val stringId = applicationInfo.labelRes
        return if (stringId == 0) applicationInfo.nonLocalizedLabel.toString() else application.getString(
            stringId
        )
    }

    private fun getApplicationVersion(): String {
        val pInfo: PackageInfo =
            application.packageManager.getPackageInfo(
                application.packageName, 0
            )
        return pInfo.versionName
    }
}
