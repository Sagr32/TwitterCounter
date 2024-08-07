package com.sagr32.twittercounter.data.network

import okhttp3.Interceptor
import okhttp3.Response
import java.net.URLEncoder
import java.util.UUID
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

class OAuthInterceptor(
    private val consumerKey: String,
    private val consumerSecret: String,
    private val token: String,
    private val tokenSecret: String
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val authorizationHeader = generateAuthorizationHeader(
            method = request.method,
            url = request.url.toString()
        )
        val modifiedRequest = request.newBuilder()
            .addHeader("Authorization", authorizationHeader)
            .build()
        return chain.proceed(modifiedRequest)
    }

    private fun generateAuthorizationHeader(method: String, url: String): String {
        val nonce = UUID.randomUUID().toString().replace("-", "")
        val timestamp = (System.currentTimeMillis() / 1000).toString()

        val baseString = buildBaseString(method, url, nonce, timestamp)
        val signature = computeSignature(baseString)

        return formatAuthorizationHeader(nonce, timestamp, signature)
    }

    private fun buildBaseString(method: String, url: String, nonce: String, timestamp: String): String {
        val parameters = listOf(
            "oauth_consumer_key" to consumerKey,
            "oauth_nonce" to nonce,
            "oauth_signature_method" to "HMAC-SHA1",
            "oauth_timestamp" to timestamp,
            "oauth_token" to token,
            "oauth_version" to "1.0"
        ).joinToString("&") { "${it.first}=${encode(it.second)}" }

        return "$method&${encode(url)}&${encode(parameters)}"
    }

    private fun computeSignature(baseString: String): String {
        val signingKey = "$consumerSecret&$tokenSecret"
        val mac = Mac.getInstance("HmacSHA1")
        val secretKeySpec = SecretKeySpec(signingKey.toByteArray(), "HmacSHA1")
        mac.init(secretKeySpec)
        return android.util.Base64.encodeToString(mac.doFinal(baseString.toByteArray()), android.util.Base64.NO_WRAP)
    }

    private fun formatAuthorizationHeader(nonce: String, timestamp: String, signature: String): String {
        return "OAuth " +
                "oauth_consumer_key=\"${encode(consumerKey)}\", " +
                "oauth_nonce=\"${encode(nonce)}\", " +
                "oauth_signature=\"${encode(signature)}\", " +
                "oauth_signature_method=\"HMAC-SHA1\", " +
                "oauth_timestamp=\"${encode(timestamp)}\", " +
                "oauth_token=\"${encode(token)}\", " +
                "oauth_version=\"1.0\""
    }

    private fun encode(value: String): String {
        return URLEncoder.encode(value, "UTF-8").replace("+", "%20")
    }
}
