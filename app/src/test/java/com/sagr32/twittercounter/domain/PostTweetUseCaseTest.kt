package com.sagr32.twittercounter.domain

import com.sagr32.twittercounter.domain.repositories.MainRepository
import com.sagr32.twittercounter.domain.useCases.PostTweetUseCase
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`

// Assuming that the package and imports are correct for your project setup

class PostTweetUseCaseTest {

    private lateinit var mainRepository: MainRepository
    private lateinit var postTweetUseCase: PostTweetUseCase

    @Before
    fun setUp() {
        // Initialize the mock repository and use case before each test
        mainRepository = mock(MainRepository::class.java)
        postTweetUseCase = PostTweetUseCase(mainRepository)
    }

    @Test
    fun `invoke should call postTweet on MainRepository with the correct text`() {
        // Given
        val tweetText = "Hello, world!"

        // When
        postTweetUseCase(tweetText)

        // Then
        verify(mainRepository).postTweet(tweetText) // Verify that postTweet was called with the correct parameter
    }
}
