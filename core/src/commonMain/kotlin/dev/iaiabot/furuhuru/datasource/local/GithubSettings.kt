package dev.iaiabot.furuhuru.datasource.local

class GithubSettings {
    companion object {
        private const val DEFAULT_Furuhuru_BRANCH = "furuhuru-image-branch"
    }

    var githubApiToken: String = ""
        private set
    var githubRepositoryOwner: String = ""
        private set
    var githubRepository: String = ""
        private set
    var FuruhuruBranch: String = DEFAULT_Furuhuru_BRANCH
        private set

    // TODO: Listにしたい
    val labels: MutableList<String> = mutableListOf()

    fun init(
        githubApiToken: String,
        githubReposOwner: String,
        githubRepository: String,
        FuruhuruBranch: String? = null
    ) {
        this.githubApiToken = githubApiToken
        this.githubRepositoryOwner = githubReposOwner
        this.githubRepository = githubRepository
        FuruhuruBranch?.let {
            this.FuruhuruBranch = it
        }
    }

    fun addLabels(labels: List<String>) {
        if (labels.isNotEmpty()) {
            this.labels.addAll(labels)
        }
    }
}
