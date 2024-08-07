package com.sagr32.twittercounter.domain.useCases

import com.sagr32.twittercounter.data.models.TweetResponse
import com.sagr32.twittercounter.data.network.ApiStatus
import com.sagr32.twittercounter.domain.repositories.MainRepository
import java.util.concurrent.Flow

class PostTweetUseCase(private val mainRepository: MainRepository) {
    suspend operator fun invoke(text: String) = mainRepository.postTweet(text)
}