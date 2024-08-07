package com.sagr32.twittercounter.data.network

import com.sagr32.twittercounter.data.models.TweetBody
import com.sagr32.twittercounter.data.models.TweetResponse
import com.sagr32.twittercounter.data.models.TwitterOAuth
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface TwitterService {
    @Headers("Content-Type: application/json")
    @POST("2/tweets")
    fun postTweet(
        @Body body: TweetBody,
        @Header("Authorization") auth: TwitterOAuth
    ): Call<TweetResponse>
}