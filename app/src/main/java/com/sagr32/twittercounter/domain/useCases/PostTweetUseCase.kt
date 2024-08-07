package com.sagr32.twittercounter.domain.useCases

import com.sagr32.twittercounter.domain.repositories.MainRepository
import javax.inject.Inject

class PostTweetUseCase @Inject constructor(private val mainRepository: MainRepository) {
    operator fun invoke(text: String) = mainRepository.postTweet(text)
}