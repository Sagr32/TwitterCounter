package com.sagr32.twittercounter.domain

import com.sagr32.twittercounter.domain.repositories.MainRepository
import com.sagr32.twittercounter.domain.useCases.PostTweetUseCase
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify


class PostTweetUseCaseTest {

    private lateinit var mainRepository: MainRepository
    private lateinit var postTweetUseCase: PostTweetUseCase

    @Before
    fun setUp() {
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
        verify(mainRepository).postTweet(tweetText)
    }
}
