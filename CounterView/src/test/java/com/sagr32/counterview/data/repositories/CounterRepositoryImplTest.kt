package com.sagr32.counterview.data.repositories


import com.sagr32.counterview.domain.source.TweetValidator
import org.junit.Before
import org.junit.Test
import org.junit.Assert.assertEquals
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

class CounterRepositoryImplTest {

    @Mock
    lateinit var tweetValidator: TweetValidator

    lateinit var counterRepository: CounterRepositoryImpl

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        counterRepository = CounterRepositoryImpl(tweetValidator)
    }

    @Test
    fun `getTweetLength should return correct length`() {
        // Given
        val tweet = "Hello, World!"
        val expectedLength = 13
        `when`(tweetValidator.getTweetLength(tweet)).thenReturn(expectedLength)

        // When
        val length = counterRepository.getTweetLength(tweet)

        // Then
        verify(tweetValidator).getTweetLength(tweet)
        assertEquals(expectedLength, length)
    }

    @Test
    fun `isTweetValid should return correct validity`() {
        // Given
        val tweet = "Hello, World!"
        val isValid = true
        `when`(tweetValidator.isTweetValid(tweet)).thenReturn(isValid)

        // When
        val validity = counterRepository.isTweetValid(tweet)

        // Then
        verify(tweetValidator).isTweetValid(tweet)
        assertEquals(isValid, validity)
    }

    @Test
    fun `getTweetLength should return correct length for long tweet`() {
        // Given
        val tweet = "a".repeat(280)
        val expectedLength = 280
        `when`(tweetValidator.getTweetLength(tweet)).thenReturn(expectedLength)

        // When
        val length = counterRepository.getTweetLength(tweet)

        // Then
        verify(tweetValidator).getTweetLength(tweet)
        assertEquals(expectedLength, length)
    }

    @Test
    fun `isTweetValid should return false for invalid tweet`() {
        // Given
        val tweet = "a".repeat(281)
        val isValid = false
        `when`(tweetValidator.isTweetValid(tweet)).thenReturn(isValid)

        // When
        val validity = counterRepository.isTweetValid(tweet)

        // Then
        verify(tweetValidator).isTweetValid(tweet)
        assertEquals(isValid, validity)
    }
}
