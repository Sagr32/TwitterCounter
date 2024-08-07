package com.sagr32.counterview.data.source

import com.sagr32.counterview.domain.source.TweetValidator
import moe.tlaster.twitter.parser.CashTagToken
import moe.tlaster.twitter.parser.EmojiToken
import moe.tlaster.twitter.parser.HashTagToken
import moe.tlaster.twitter.parser.StringToken
import moe.tlaster.twitter.parser.Token
import moe.tlaster.twitter.parser.TwitterParser
import moe.tlaster.twitter.parser.UrlToken
import moe.tlaster.twitter.parser.UserNameToken
import javax.inject.Inject

class TweetValidatorImpl @Inject constructor() : TweetValidator {
    private val twitterCharacterLimit = 280

    private fun twitterCounter(text: String): Pair<Int, Boolean> {
        val parser = TwitterParser(enableEscapeInUrl = true)
        val tokens = parser.parse(text)
        fun calculateLengthFromTokens(tokens: List<Token>): Int {
            var length = 0
            tokens.forEach { token ->
                length += when (token) {
                    is StringToken -> token.value.length
                    is UserNameToken -> token.value.length
                    is HashTagToken -> token.value.length
                    is UrlToken -> 23
                    is CashTagToken -> token.value.length
                    is EmojiToken -> 2
                }
            }
            return length
        }
        val calculatedLength = calculateLengthFromTokens(tokens)
        val isWithinLimit = calculatedLength <= twitterCharacterLimit
        return Pair(calculatedLength, isWithinLimit)
    }

    override fun getTweetLength(text: String): Int {
        return twitterCounter(text).first
    }

    override fun isTweetValid(text: String): Boolean {
        return twitterCounter(text).second
    }

}
