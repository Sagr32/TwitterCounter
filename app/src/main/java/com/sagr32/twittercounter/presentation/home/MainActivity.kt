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
                Utils.copyToClipboard(this@MainActivity, tweetCounter.getTweetText())
            }
            tvClearText.setOnClickListener {
                tweetCounter.clearText()
            }
            tvPostTweet.setOnClickListener {
                if (tweetCounter.isTweetValid()) {
                    viewModel.postTweet(tweetCounter.getTweetText())

                } else {
                    Utils.showCustomToast(
                        this@MainActivity,
                        "Tweet exceeds the character limit",
                        ToastStatus.ERROR
                    )
                }
            }
        }
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            viewModel.tweetStatus.collect { status ->
                when (status) {
                    is ApiStatus.Loading -> {
                        // Show loading indicator if needed
                        binding.tvPostTweet.text = "Posting..."
                    }

                    is ApiStatus.Success -> {
                        // Handle success
                        Utils.showCustomToast(
                            this@MainActivity,
                            "Tweet posted successfully",
                            ToastStatus.SUCCESS
                        )
                        binding.tvPostTweet.text = "Post Tweet"
                    }

                    is ApiStatus.Error -> {
                        // Handle error
                        Utils.showCustomToast(
                            this@MainActivity,
                            "Error posting tweet: ${status}",
                            ToastStatus.ERROR
                        )
                        binding.tvPostTweet.text = "Post Tweet"
                    }
                }
            }
        }
    }

}