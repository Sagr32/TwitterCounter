package com.sagr32.counterview.domain.usecases

import com.sagr32.counterview.domain.repositories.CounterRepository
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`


class CheckTweetValidityUseCaseTest {

    private lateinit var counterRepository: CounterRepository
    private lateinit var checkTweetValidityUseCase: CheckTweetValidityUseCase

    @Before
    fun setUp() {
        counterRepository = mock(CounterRepository::class.java)
        checkTweetValidityUseCase = CheckTweetValidityUseCase(counterRepository)
    }

    @Test
    fun `invoke should return true if tweet is valid`() {
        // Given
        val testText = "Valid tweet"
        `when`(counterRepository.isTweetValid(testText)).thenReturn(true)

        // When
        val isValid = checkTweetValidityUseCase(testText)

        // Then
        assertTrue(isValid)
        verify(counterRepository).isTweetValid(testText)
    }

    @Test
    fun `invoke should return false if tweet is not valid`() {
        // Given
        val testText = "Invalid tweet"
        `when`(counterRepository.isTweetValid(testText)).thenReturn(false)

        // When
        val isValid = checkTweetValidityUseCase(testText)

        // Then
        assertFalse(isValid)
        verify(counterRepository).isTweetValid(testText) // Verify interaction with the repository
    }
}
