package com.sagr32.counterview.domain.usecases

import com.sagr32.counterview.domain.repositories.CounterRepository
import javax.inject.Inject

class CheckTweetValidityUseCase @Inject constructor(
    private val counterRepository: CounterRepository
) {
    operator fun invoke(text: String): Boolean {
        return counterRepository.isTweetValid(text)
    }
}
