package com.sagr32.twittercounter.data.models

data class ErrorResponse(
    val title: String,
    val type: String,
    val status: Int,
    val detail: String
)
