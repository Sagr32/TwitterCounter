package com.sagr32.twittercounter.data.repositories

import com.sagr32.twittercounter.data.models.TweetBody
import com.sagr32.twittercounter.data.models.TweetResponse
import com.sagr32.twittercounter.data.models.TwitterOAuth
import com.sagr32.twittercounter.data.network.ApiStatus
import com.sagr32.twittercounter.data.network.TwitterService
import com.sagr32.twittercounter.domain.repositories.MainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val twitterService: TwitterService
) : MainRepository {

    override fun postTweet(tweetText: String): Flow<ApiStatus<TweetResponse>> {
        return flow {
            emit(ApiStatus.Loading()) // Emit loading status

            try {
                val response = withContext(Dispatchers.IO) {
                    twitterService.postTweet(TweetBody(tweetText), TwitterOAuth()).execute()
                }
                if (response.isSuccessful) {
                    response.body()?.let {
                        emit(ApiStatus.Success(it))
                    } ?: emit(ApiStatus.Error(("Response body is null")))
                } else {
                    emit(ApiStatus.Error(("Error: ${response.code()} ${response.message()}")))
                }
            } catch (e: Exception) {

                emit(ApiStatus.Error(e.message ?: "Error"))
            }
        }
    }
}
