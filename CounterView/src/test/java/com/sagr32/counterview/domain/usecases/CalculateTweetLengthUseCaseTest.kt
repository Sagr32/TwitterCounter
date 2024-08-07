package com.sagr32.counterview.domain.usecases

import com.sagr32.counterview.domain.repositories.CounterRepository
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`


// Assuming that the package and imports are correct for your project setup

class CalculateTweetLengthUseCaseTest {

    private lateinit var counterRepository: CounterRepository
    private lateinit var calculateTweetLengthUseCase: CalculateTweetLengthUseCase

    @Before
    fun setUp() {
        // Initialize the mock repository and use case before each test
        counterRepository = mock(CounterRepository::class.java)
        calculateTweetLengthUseCase = CalculateTweetLengthUseCase(counterRepository)
    }

    @Test
    fun `invoke should return the tweet length from repository`() {
        // Given
        val testText = "Hello, world!"
        val expectedLength = 13 // Adjust based on your logic
        `when`(counterRepository.getTweetLength(testText)).thenReturn(expectedLength)

        // When
        val actualLength = calculateTweetLengthUseCase(testText)

        // Then
        assertEquals(expectedLength, actualLength)
        verify(counterRepository).getTweetLength(testText) // Verify interaction with the repository
    }
}
