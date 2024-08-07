package com.sagr32.twittercounter.domain.repositories

import com.sagr32.twittercounter.data.models.TweetResponse
import com.sagr32.twittercounter.data.network.ApiStatus
import kotlinx.coroutines.flow.Flow

interface MainRepository {
    fun postTweet(tweetText: String, ): Flow<ApiStatus<TweetResponse>>

}