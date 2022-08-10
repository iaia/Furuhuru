package dev.iaiabot.furuhuru.usecase.user

import dev.iaiabot.furuhuru.repository.UserRepository

internal interface SaveUsernameUseCase {
    suspend operator fun invoke(username: String?)
}

internal class SaveUsernameUseCaseImpl(
    private val userRepository: UserRepository
) : SaveUsernameUseCase {

    override suspend fun invoke(username: String?) {
        if (!username.isNullOrBlank()) {
            userRepository.saveUserName(username)
        }
    }
}
