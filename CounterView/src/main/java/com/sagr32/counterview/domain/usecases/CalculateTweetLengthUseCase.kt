package com.sagr32.counterview.domain.usecases

import com.sagr32.counterview.domain.repositories.CounterRepository
import javax.inject.Inject

class CalculateTweetLengthUseCase @Inject constructor(
    private val counterRepository: CounterRepository
) {
    operator fun invoke(text: String): Int {
        return counterRepository.getTweetLength(text)
    }
}