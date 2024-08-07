package com.sagr32.twittercounter.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sagr32.twittercounter.data.models.TweetResponse
import com.sagr32.twittercounter.data.network.ApiStatus
import com.sagr32.twittercounter.domain.useCases.PostTweetUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val postTweetUseCase: PostTweetUseCase
) : ViewModel() {

    private val _tweetStatus = MutableStateFlow<ApiStatus<TweetResponse>>(ApiStatus.Initial())
    val tweetStatus: StateFlow<ApiStatus<TweetResponse>> get() = _tweetStatus

    fun postTweet(text: String) {
        viewModelScope.launch {
            postTweetUseCase(text).collect { status ->
                _tweetStatus.value = status
            }
        }
    }
}