package com.sagr32.twittercounter.presentation.home

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.ui.AppBarConfiguration
import com.sagr32.counterview.presentation.TweetCounter
import com.sagr32.twittercounter.R
import com.sagr32.twittercounter.data.enum.ToastStatus
import com.sagr32.twittercounter.data.network.ApiStatus
import com.sagr32.twittercounter.databinding.ActivityMainBinding
import com.sagr32.twittercounter.utils.Utils
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var tweetCounter: TweetCounter
    private val viewModel: HomeViewModel by viewModels() // Use Hilt to get ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        tweetCounter = binding.tweetCounter
        setUpOnClicks()
        observeViewModel()
    }

    private fun setUpOnClicks() {
        binding.apply {
            tvCopyText.setOnClickListener {
                val tweetText = tweetCounter.getTweetText()
                if (tweetText.isNotBlank()) {
                    Utils.copyToClipboard(this@MainActivity, tweetText)
                } else {
                    Utils.showCustomToast(
                        this@MainActivity,
                        getString(R.string.tweet_is_empty),
                        ToastStatus.ERROR
                    )
                }
            }
            tvClearText.setOnClickListener {
                tweetCounter.clearText()
            }
            tvPostTweet.setOnClickListener {
                val tweetText = tweetCounter.getTweetText()
                when {
                    tweetText.isBlank() -> {
                        Utils.showCustomToast(
                            this@MainActivity,
                            getString(R.string.tweet_cannot_be_empty),
                            ToastStatus.ERROR
                        )
                    }

                    tweetCounter.isTweetValid() -> {
                        viewModel.postTweet(tweetText)
                    }

                    else -> {
                        Utils.showCustomToast(
                            this@MainActivity,
                            getString(R.string.tweet_exceeds_the_character_limit),
                            ToastStatus.ERROR
                        )
                    }
                }
            }
        }
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            viewModel.tweetStatus.collect { status ->
                when (status) {
                    is ApiStatus.Initial -> {
                    }

                    is ApiStatus.Loading -> {
                        binding.tvPostTweet.text = getString(R.string.posting)
                    }

                    is ApiStatus.Success -> {
                        Utils.showCustomToast(
                            this@MainActivity,
                            getString(R.string.tweet_posted_successfully),
                            ToastStatus.SUCCESS
                        )
                        binding.tvPostTweet.text = getString(R.string.post_tweet)
                        tweetCounter.clearText()
                    }

                    is ApiStatus.Error -> {
                        Utils.showCustomToast(
                            this@MainActivity,
                            "Error posting tweet: ${status}",
                            ToastStatus.ERROR
                        )
                        binding.tvPostTweet.text = getString(R.string.post_tweet)
                    }
                }
            }
        }
    }

}