package com.sagr32.counterview.data.repositories

import com.sagr32.counterview.domain.repositories.CounterRepository
import com.sagr32.counterview.domain.source.TweetValidator
import com.sagr32.counterview.domain.usecases.CalculateTweetLengthUseCase
import javax.inject.Inject

class CounterRepositoryImpl
@Inject constructor(
    private val tweetValidator: TweetValidator
) : CounterRepository {

    override fun getTweetLength(text: String): Int {
        return tweetValidator.getTweetLength(text)
    }

    override fun isTweetValid(text: String): Boolean {
        return tweetValidator.isTweetValid(text)
    }
}
