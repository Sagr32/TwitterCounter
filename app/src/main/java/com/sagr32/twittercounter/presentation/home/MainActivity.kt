package com.sagr32.twittercounter.presentation.home

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import com.sagr32.counterview.presentation.TweetCounter
import com.sagr32.twittercounter.R
import com.sagr32.twittercounter.data.enum.ToastStatus
import com.sagr32.twittercounter.databinding.ActivityMainBinding
import com.sagr32.twittercounter.utils.Utils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var tweetCounter: TweetCounter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        tweetCounter = binding.tweetCounter
        setUpOnClicks()
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
                    Utils.showCustomToast(
                        this@MainActivity,
                        "Tweet posted successfully",
                        ToastStatus.SUCCESS
                    )
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

}