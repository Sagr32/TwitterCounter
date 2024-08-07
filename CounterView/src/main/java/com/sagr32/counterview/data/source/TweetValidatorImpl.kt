package com.sagr32.counterview.data.source

import com.sagr32.counterview.domain.source.TweetValidator
import javax.inject.Inject

class TweetValidatorImpl @Inject constructor() : TweetValidator {

    private val twitterCharacterLimit = 280

    private fun twitterCounter(text: String): Pair<Int, Boolean> {
        if (text.isEmpty()) {
            return Pair(0, false) // Empty text should be considered invalid
        }
        val urlRegex = Regex("https?://[\\w/\\-\\.\\?\\=]+")
        val mentionRegex = Regex("@\\w+")
        val hashtagRegex = Regex("#\\w+")
        val emojiRegex = Regex("[\\p{So}\\p{Cn}]") // Unicode emojis
        fun countUrls(text: String): Int {
            return urlRegex.findAll(text).sumOf { 23 - it.value.length }
        }
        fun countMentionsAndHashtags(text: String): Int {
            val mentions = mentionRegex.findAll(text).toList()
            val hashtags = hashtagRegex.findAll(text).toList()
            return mentions.sumOf { 1 - it.value.length } +
                    hashtags.sumOf { 1 - it.value.length }
        }
        fun countEmojis(text: String): Int {
            return emojiRegex.findAll(text).sumOf { 1 - it.value.length }
        }
        val baseLength = text.length
        val urlAdjustment = countUrls(text)
        val mentionAndHashtagAdjustment = countMentionsAndHashtags(text)
        val emojiAdjustment = countEmojis(text)

        val estimatedLength = baseLength + urlAdjustment + mentionAndHashtagAdjustment + emojiAdjustment
        val isWithinLimit = estimatedLength <= twitterCharacterLimit

        return Pair(estimatedLength, isWithinLimit)
    }

    override fun getTweetLength(text: String): Int {
        return twitterCounter(text).first
    }

    override fun isTweetValid(text: String): Boolean {
        return twitterCounter(text).second
    }
}
