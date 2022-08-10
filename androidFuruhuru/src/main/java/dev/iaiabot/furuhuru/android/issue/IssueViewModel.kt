package dev.iaiabot.furuhuru.android.issue

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.iaiabot.furuhuru.datasource.local.GithubSettings
import dev.iaiabot.furuhuru.usecase.GetScreenShotUseCase
import dev.iaiabot.furuhuru.usecase.PostIssueUseCase
import dev.iaiabot.furuhuru.usecase.user.LoadUserNameUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

internal abstract class IssueViewModel : ViewModel() {
    abstract val title: LiveData<String>
    abstract val body: LiveData<String>
    abstract val userName: LiveData<String>
    abstract val imageStrBase64: LiveData<String?>
    abstract val labels: Flow<List<String>>
    abstract val command: LiveData<Command>
    abstract val nowSending: LiveData<Boolean>

    abstract fun onTitleChange(title: String)
    abstract fun onBodyChange(body: String)
    abstract fun onUserNameChange(userName: String)
    abstract fun onCheckedChangeLabel(isChecked: Boolean, label: String)
    abstract fun post()
}

internal class IssueViewModelImpl(
    private val loadUserNameUseCase: LoadUserNameUseCase,
    private val githubSettings: GithubSettings,
    private val postIssueUseCase: PostIssueUseCase,
    getScreenShotUseCase: GetScreenShotUseCase,
) : IssueViewModel() {
    override val title = MutableLiveData("")
    override val body = MutableLiveData("")
    override val userName = MutableLiveData("")
    override val imageStrBase64 = MutableLiveData<String?>()
    override val command = MutableLiveData<Command>()
    override val nowSending = MutableLiveData(false)
    override val labels = flow {
        emit(githubSettings.labels.toList())
    }
    private val selectedLabels = mutableListOf<String>()

    init {
        viewModelScope.launch {
            userName.value = loadUserNameUseCase()

        }
        viewModelScope.launch {
            getScreenShotUseCase().collect {
                imageStrBase64.postValue(it)
            }
        }
    }

    override fun onTitleChange(title: String) {
        this.title.value = title
    }

    override fun onBodyChange(body: String) {
        this.body.value = body
    }

    override fun onUserNameChange(userName: String) {
        this.userName.value = userName
    }

    override fun onCheckedChangeLabel(isChecked: Boolean, label: String) {
        if (isChecked) {
            selectedLabels.add(label)
        } else {
            selectedLabels.remove(label)
        }
    }

    override fun post() {
        if (nowSending.value == true) {
            return
        }
        nowSending.postValue(true)

        viewModelScope.launch(Dispatchers.IO) {
            try {
                postIssueUseCase(title.value, userName.value, body.value, selectedLabels)
                command.postValue(Command.Finish)
            } catch (e: Exception) {
                Log.d("Furuhuru", e.stackTraceToString())
                command.postValue(Command.Error(e.message ?: "error"))
            } finally {
                nowSending.postValue(false)
            }
        }
    }
}
