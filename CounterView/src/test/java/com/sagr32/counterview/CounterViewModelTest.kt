package com.sagr32.counterview


import com.sagr32.counterview.domain.usecases.CalculateTweetLengthUseCase
import com.sagr32.counterview.domain.usecases.CheckTweetValidityUseCase
import com.sagr32.counterview.presentation.TweetViewModel
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class TweetViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: TweetViewModel
    @Mock
    private lateinit var calculateTweetLengthUseCase: CalculateTweetLengthUseCase

    @Mock
    private lateinit var checkTweetValidityUseCase: CheckTweetValidityUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        viewModel = TweetViewModel(calculateTweetLengthUseCase, checkTweetValidityUseCase)
    }

    @Test
    fun testUpdateTweetText() {
        viewModel.updateTweetText("Hello")
        val state = viewModel.state.value
        assertEquals(5, state.typedCharacters)
        assertEquals(TweetViewModel.TWITTER_MAX_CHAR - 5, state.remainingCharacters)
        assertTrue(state.isValid)
    }

    @Test
    fun testTweetExceedsCharacterLimit() {
        val longText = "A".repeat(TweetViewModel.TWITTER_MAX_CHAR + 1)
        viewModel.updateTweetText(longText)
        val state = viewModel.state.value
        assertFalse(state.isValid)
    }
}
