package com.sagr32.counterview.domain.source

interface TweetValidator {
    fun getTweetLength(text: String): Int
    fun isTweetValid(text: String): Boolean
}
