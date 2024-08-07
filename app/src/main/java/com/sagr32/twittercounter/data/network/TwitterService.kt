package com.sagr32.twittercounter.data.network

import com.sagr32.twittercounter.data.models.TweetBody
import com.sagr32.twittercounter.data.models.TweetResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface TwitterService {
    @Headers("Content-Type: application/json")
    @POST("2/tweets")
    suspend fun postTweet(
        @Body body: TweetBody,
    ): Response<TweetResponse>
}