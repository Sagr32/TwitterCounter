package com.sagr32.counterview.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sagr32.counterview.data.models.TweetState
import com.sagr32.counterview.domain.usecases.CalculateTweetLengthUseCase
import com.sagr32.counterview.domain.usecases.CheckTweetValidityUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TweetViewModel @Inject constructor(
    private val calculateTweetLengthUseCase: CalculateTweetLengthUseCase,
    private val checkTweetValidityUseCase: CheckTweetValidityUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(TweetState())
    val state: StateFlow<TweetState> get() = _state.asStateFlow()

    fun updateTweetText(text: String) {
        viewModelScope.launch {
            val length = calculateTweetLengthUseCase(text)
            val isValid = checkTweetValidityUseCase(text)
            val remainingCharacters = TWITTER_MAX_CHAR - length
            _state.value = TweetState(
                tweetText = text,
                remainingCharacters = remainingCharacters,
                isValid = isValid,
                typedCharacters = length
            )
        }
    }
    companion object {
        const val TWITTER_MAX_CHAR = 280
    }
}
