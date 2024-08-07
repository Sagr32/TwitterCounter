package com.sagr32.twittercounter.data.models

import com.google.gson.annotations.SerializedName

data class TweetResponse(
    val data: TweetData
)

data class TweetData(
    val text: String,
    val id: String,
    @SerializedName("edit_history_tweet_ids") val editHistoryTweetIds: List<String>
)