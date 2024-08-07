package com.sagr32.twittercounter.data.repositories

import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.sagr32.twittercounter.data.models.ErrorResponse
import com.sagr32.twittercounter.data.models.TweetBody
import com.sagr32.twittercounter.data.models.TweetResponse
import com.sagr32.twittercounter.data.network.ApiStatus
import com.sagr32.twittercounter.data.network.TwitterService
import com.sagr32.twittercounter.domain.repositories.MainRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val twitterService: TwitterService
) : MainRepository {
    private val gson =  Gson()

    override fun postTweet(tweetText: String): Flow<ApiStatus<TweetResponse>> {
        return flow {
            emit(ApiStatus.Loading()) // Emit loading state
            try {
                val response = twitterService.postTweet(TweetBody(tweetText), )
                if (response.isSuccessful) {
                    emit(ApiStatus.Success(response.body()!!))
                } else {
                    val errorResponse = response.errorBody()?.let {
                        parseErrorResponse(it.string())
                    }
                    emit(ApiStatus.Error(errorResponse?.detail ?: "Error: ${response.code()} ${response.message()}"))
                }
            } catch (e: Exception) {
                emit(ApiStatus.Error(e.message ?: "Unknown error"))
            }
        }
    }
    private fun parseErrorResponse(errorBody: String): ErrorResponse? {
        return try {
            gson.fromJson(errorBody, ErrorResponse::class.java)
        } catch (e: JsonSyntaxException) {
            null
        }
    }
}
