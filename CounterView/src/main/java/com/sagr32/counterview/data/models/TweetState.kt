package com.sagr32.counterview.data.models

data class TweetState(
    val tweetText: String = "",
    val remainingCharacters: Int = 280,
    val typedCharacters: Int = 0,
    val isValid: Boolean = true
)