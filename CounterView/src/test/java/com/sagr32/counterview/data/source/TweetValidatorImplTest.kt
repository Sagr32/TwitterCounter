package com.sagr32.counterview.data.source

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class TweetValidatorImplTest {

    private lateinit var tweetValidator: TweetValidatorImpl

    @Before
    fun setUp() {
        tweetValidator = TweetValidatorImpl()
    }

    @Test
    fun `getTweetLength should return correct length for plain text`() {
        val tweet = "Hello, World!"
        val expectedLength = 13
        val length = tweetValidator.getTweetLength(tweet)
        assertEquals(expectedLength, length)
    }

    @Test
    fun `getTweetLength should return correct length for URL`() {
        val tweet = "Check this out: https://example.com"
        val expectedLength = 39 // 16 characters + 23 for the URL
        val length = tweetValidator.getTweetLength(tweet)
        assertEquals(expectedLength, length)
    }

    @Test
    fun `getTweetLength should return correct length for username`() {
        val tweet = "@user Hello!"
        val expectedLength = 12
        val length = tweetValidator.getTweetLength(tweet)
        assertEquals(expectedLength, length)
    }

    @Test
    fun `isTweetValid should return true for valid tweet`() {
        val tweet = "Hello, World!"
        val isValid = tweetValidator.isTweetValid(tweet)
        assertEquals(true, isValid)
    }

    @Test
    fun `isTweetValid should return false for invalid tweet`() {
        val tweet = "a".repeat(281)
        val isValid = tweetValidator.isTweetValid(tweet)
        assertEquals(false, isValid)
    }

    @Test
    fun `getTweetLength should return correct length for hashtags`() {
        val tweet = "#hello #world"
        val expectedLength = 13
        val length = tweetValidator.getTweetLength(tweet)
        assertEquals(expectedLength, length)
    }

    @Test
    fun `getTweetLength should return correct length for emojis`() {
        val tweet = "Hello 😊"
        val expectedLength = 8 // 6 characters + 2 for the emoji
        val length = tweetValidator.getTweetLength(tweet)
        assertEquals(expectedLength, length)
    }
}
